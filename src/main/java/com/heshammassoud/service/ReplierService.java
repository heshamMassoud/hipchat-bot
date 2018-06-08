package com.heshammassoud.service;

import com.atlassian.adf.Document;
import com.atlassian.stride.api.StrideClient;
import com.atlassian.stride.api.model.EntityCreatedResponse;
import com.atlassian.stride.model.context.ConversationContext;
import com.atlassian.stride.model.webhooks.MessageSent;
import com.heshammassoud.models.Question;
import com.heshammassoud.models.Script;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.atlassian.stride.model.context.Context.conversation;

@Service
public class ReplierService {

    private final StrideClient strideClient;
    private final Script script;

    // This is an in-memory database    
    private final Map<String, Question> currentUserQuestion = new HashMap<>();

    public ReplierService(@Nonnull final StrideClient strideClient, @Nonnull final Script script) {
        this.strideClient = strideClient;
        this.script = script;
    }

    /**
     * TODO.
     * @param messageSent TODO.
     */
    public void reply(@Nonnull final MessageSent messageSent) {

        final String text = messageSent.getMessage().getText();
        final String sender = messageSent.getSender().getId();

        // try to continue a conversation
        final Question question = currentUserQuestion.get(sender);
        if (question != null) {
            final Optional<Question> next = question.findNext(text);
            if (next.isPresent()) {
                sendWithDelay(messageSent, next.get());
                currentUserQuestion.put(sender, question);
                return;
            }
            currentUserQuestion.remove(sender);
        }

        // try to initiate a conversation
        final Optional<Question> initialQuestion = script.findMatchingQuestion(text);
        if (initialQuestion.isPresent()) {
            final Question question1 = initialQuestion.get();
            currentUserQuestion.put(sender, question1);
            sendWithDelay(messageSent, question1);
        } else {
            send(messageSent,"I humbly wonder what you mean...");
        }
    }

    /**
     * TODO.
     * @param messageSent TODO.
     * @param question TODO.
     */
    public void sendWithDelay(@Nonnull final MessageSent messageSent, @Nonnull final Question question) {
        final List<String> answers = question.getAnswers();
        if (!answers.isEmpty()) {
            // chain each answer so that the next is just sent after the previous one is sent
            // this is done because the API is asynchronous
            CompletableFuture<?> sendMessageFuture = send(messageSent, answers.get(0));
            for (int i = 1; i < answers.size(); i++) {
                final String answer = answers.get(i);
                sendMessageFuture = sendMessageFuture.thenCompose(r -> send(messageSent, answer));
            }
        }
    }

    /**
     * TODO.
     * @param messageSent TODO.
     * @param message TODO.
     * @return TODO.
     */
    @Nonnull
    public CompletableFuture<EntityCreatedResponse> send(@Nonnull final MessageSent messageSent,
                                                         @Nonnull final String message) {

        final Document document = Document.fromMarkdown(message);
        final ConversationContext conversationContext =
                conversation(messageSent.getCloudId(), messageSent.getConversation().getId());

        return strideClient.message()
                           .send(document)
                           .toConversation(conversationContext);
    }

}
