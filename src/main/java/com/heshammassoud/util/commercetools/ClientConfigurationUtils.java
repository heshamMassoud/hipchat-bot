package com.heshammassoud.util.commercetools;

import io.sphere.sdk.client.BlockingSphereClient;
import io.sphere.sdk.client.SphereAccessTokenSupplier;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.client.SphereClientConfig;
import io.sphere.sdk.http.AsyncHttpClientAdapter;
import io.sphere.sdk.http.HttpClient;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;

import javax.annotation.Nonnull;
import java.util.concurrent.TimeUnit;

public class ClientConfigurationUtils {
    private static HttpClient httpClient;
    private static final long DEFAULT_TIMEOUT = 30000;
    private static final TimeUnit DEFAULT_TIMEOUT_TIME_UNIT = TimeUnit.MILLISECONDS;

    /**
     * Creates a {@link BlockingSphereClient} with a custom {@code timeout} with a custom {@link TimeUnit}.
     *
     * @param clientConfig the client configuration for the client.
     * @param timeout      the timeout value for the client requests.
     * @param timeUnit     the timeout time unit.
     * @return the instantiated {@link BlockingSphereClient}.
     */
    public static synchronized SphereClient createClient(@Nonnull final SphereClientConfig clientConfig,
                                                         final long timeout,
                                                         @Nonnull final TimeUnit timeUnit) {
        final HttpClient httpClient = getHttpClient();
        final SphereAccessTokenSupplier tokenSupplier =
                SphereAccessTokenSupplier.ofAutoRefresh(clientConfig, httpClient, false);
        final SphereClient underlying = SphereClient.of(clientConfig, httpClient, tokenSupplier);
        return BlockingSphereClient.of(underlying, timeout, timeUnit);
    }

    /**
     * Creates a {@link BlockingSphereClient} with a default {@code timeout} value of 30 seconds.
     *
     * @param clientConfig the client configuration for the client.
     * @return the instantiated {@link BlockingSphereClient}.
     */
    public static SphereClient createClient(@Nonnull final SphereClientConfig clientConfig) {
        return createClient(clientConfig, DEFAULT_TIMEOUT, DEFAULT_TIMEOUT_TIME_UNIT);
    }

    /**
     * Gets an asynchronous {@link HttpClient} to be used by the {@link BlockingSphereClient}.
     * Client is created during first invocation and then cached.
     *
     * @return {@link HttpClient}
     */
    private static HttpClient getHttpClient() {
        if (httpClient == null) {
            final AsyncHttpClient asyncHttpClient =
                    new DefaultAsyncHttpClient(new DefaultAsyncHttpClientConfig.Builder()
                            .setHandshakeTimeout((int) DEFAULT_TIMEOUT)
                            .build());
            httpClient = AsyncHttpClientAdapter.of(asyncHttpClient);
        }
        return httpClient;
    }

}
