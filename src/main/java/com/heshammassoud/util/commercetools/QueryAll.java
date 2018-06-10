package com.heshammassoud.util.commercetools;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.models.Resource;
import io.sphere.sdk.queries.PagedQueryResult;
import io.sphere.sdk.queries.QueryDsl;
import io.sphere.sdk.queries.QueryPredicate;
import io.sphere.sdk.queries.QuerySort;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.lang.String.format;
import static java.util.concurrent.CompletableFuture.completedFuture;

final class QueryAll<T extends Resource, C extends QueryDsl<T, C>, S> {
    private final SphereClient client;
    private final QueryDsl<T, C> baseQuery;
    private final long pageSize;

    private Function<List<T>, S> pageMapper;
    private final List<S> mappedResultsTillNow;

    private Consumer<List<T>> pageConsumer;

    private QueryAll(@Nonnull final SphereClient client,
                     @Nonnull final QueryDsl<T, C> baseQuery,
                     final long pageSize) {

        this.client = client;
        this.baseQuery = !baseQuery.sort().isEmpty() ? baseQuery : baseQuery.withSort(QuerySort.of("id asc"));
        this.pageSize = pageSize;
        this.mappedResultsTillNow = new ArrayList<>();
    }

    @Nonnull
    static <T extends Resource, C extends QueryDsl<T, C>, S> QueryAll<T, C, S> of(
        @Nonnull final SphereClient client,
        @Nonnull final QueryDsl<T, C> baseQuery,
        final int pageSize) {

        return new QueryAll<>(client, baseQuery, pageSize);
    }

    /**
     * Given a {@link Function} to a page of resources of type {@code T} that returns a mapped result of type {@code S},
     * this method sets this instance's {@code pageMapper} to the supplied value, then it makes requests to fetch the
     * entire result space of the resource {@code T} on CTP, while applying the function on each fetched page.
     *
     * @param pageMapper the function to apply on each fetched page of the result space.
     * @return a future containing a list of mapped results of type {@code S}, after the function applied all the pages.
     */
    @Nonnull
    CompletionStage<List<S>> run(@Nonnull final Function<List<T>, S> pageMapper) {
        this.pageMapper = pageMapper;
        final CompletionStage<PagedQueryResult<T>> firstPage = queryPage();
        return queryNextPages(firstPage)
            .thenApply(voidResult -> this.mappedResultsTillNow);
    }

    /**
     * Given a {@link Consumer} to a page of resources of type {@code T}, this method sets this instance's
     * {@code pageConsumer} to the supplied value, then it makes requests to fetch the entire result space of the
     * resource {@code T} on CTP, while accepting the consumer on each fetched page.
     *
     * @param pageConsumer the consumer to accept on each fetched page of the result space.
     * @return a future containing void after the consumer accepted all the pages.
     */
    @Nonnull
    CompletionStage<Void> run(@Nonnull final Consumer<List<T>> pageConsumer) {
        this.pageConsumer = pageConsumer;
        final CompletionStage<PagedQueryResult<T>> firstPage = queryPage();
        return queryNextPages(firstPage).thenAccept(voidResult -> { });
    }

    /**
     * Given a completion stage {@code currentPageStage} containing a current page result {@link PagedQueryResult}, this
     * method composes the completion stage by first checking if the result is null or not. If it is not, then it
     * recursivley (by calling itself with the next page's completion stage result) composes to the supplied stage,
     * stages of the all next pages' processing. If there is no next page, then the result of the
     * {@code currentPageStage} would be null and this method would just return a completed future containing
     * containing null result, which in turn signals the last page of processing.
     *
     * @param currentPageStage a future containing a result {@link PagedQueryResult}.
     */
    @Nonnull
    private CompletionStage<Void> queryNextPages(@Nonnull final CompletionStage<PagedQueryResult<T>> currentPageStage) {
        return currentPageStage.thenCompose(currentPage ->
            currentPage != null ? queryNextPages(processPageAndGetNext(currentPage)) : completedFuture(null));
    }

    /**
     * Given a page result {@link PagedQueryResult}, this method checks if there are elements in the result (size > 0),
     * then it maps or consumes the resultant list using this instance's {@code pageMapper} or {code pageConsumer}
     * whichever is available. Then it attempts to fetch the next page if it exists and returns a completion stage
     * containing the result of the next page. If there is a next page, then a new future of the next page is returned.
     * If there are no more results, the method returns a completed future containing null.
     *
     * @param page the current page result.
     * @return If there is a next page, then a new future of the next page is returned. If there are no more results,
     *         the method returns a completed future containing null.
     */
    @Nonnull
    private CompletionStage<PagedQueryResult<T>> processPageAndGetNext(@Nonnull final PagedQueryResult<T> page) {
        final List<T> currentPageElements = page.getResults();
        if (currentPageElements.size() > 0) {
            mapOrConsume(currentPageElements);
        }
        return getNextPageStage(currentPageElements);
    }

    /**
     * Given a list of page elements of resource {@code T}, this method checks if this instance's {@code pageConsumer}
     * or {@code pageMapper} is set (not null). The one which is set is then applied on the list of page elements.
     *
     *
     * @param pageElements list of page elements of resource {@code T}.
     */
    private void mapOrConsume(@Nonnull final List<T> pageElements) {
        if (pageConsumer != null) {
            pageConsumer.accept(pageElements);
        } else {
            mappedResultsTillNow.add(pageMapper.apply(pageElements));
        }
    }

    /**
     * Given a list of page elements of resource {@code T}, this method checks if this page is the last page or not by
     * checking if the result size is equal to this instance's {@code pageSize}). If It is, then it means there might be
     * still more results. However, if not, then it means for sure there are no more results and this is the last page.
     * If there is a next page, the id of the last element in the list is fetched and a future is created containing the
     * fetched results which have an id greater than the id of the last element in the list and this future is returned.
     * If there are no more results, the method returns a completed future containing null.
     *
     * @param pageElements list of page elements of resource {@code T}.
     * @return a future containing the fetched results which have an id greater than the id of the last element
     *          in the list.
     */
    @Nonnull
    @SuppressFBWarnings("NP_NONNULL_PARAM_VIOLATION") // `https://github.com/findbugsproject/findbugs/issues/79
    private CompletionStage<PagedQueryResult<T>> getNextPageStage(@Nonnull final List<T> pageElements) {
        if (pageElements.size() == pageSize) {
            final String lastElementId = pageElements.get(pageElements.size() - 1).getId();
            final QueryPredicate<T> queryPredicate = QueryPredicate.of(format("id > \"%s\"", lastElementId));
            return queryPage(queryPredicate);
        }
        return completedFuture(null);
    }

    /**
     * Gets the results of {@link this} instance's query with a limit of this instance's {@code pageSize} and optionally
     * appending the {@code queryPredicate} if it is not null.
     *
     * @param queryPredicate query predicate to append if not null.
     * @return a future containing the results of the requested page of applying the query with a limit of this
     *         instance's {@code pageSize} and optionally appending the {@code queryPredicate} if it is not null.
     */
    @Nonnull
    private CompletionStage<PagedQueryResult<T>> queryPage(@Nullable final QueryPredicate<T> queryPredicate) {
        final QueryDsl<T, C> query = baseQuery.withLimit(pageSize);
        return client.execute(queryPredicate != null ? query.withPredicates(queryPredicate) : query);
    }

    /**
     * Gets the results of {@link this} instance's query with a limit of this instance's {@code pageSize}.
     *
     * @return a future containing the results of the requested page of applying the query with {@code pageSize}.
     */
    @Nonnull
    private CompletionStage<PagedQueryResult<T>> queryPage() {
        return queryPage(null);
    }
}