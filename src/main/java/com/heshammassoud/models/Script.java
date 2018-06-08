package com.heshammassoud.models;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@ConfigurationProperties(prefix = "script")
@Validated
public class Script {

    private List<Question> questions = new ArrayList<>();

    /**
     * Tries to find the first question which has an expression matching the passed message.
     *
     * @param message the message to compare to the expressions of the questions of the current {@link Script}.
     * @return an optional containing the first question which has an expression matching the passed message.
     */
    public Optional<Question> findMatchingQuestion(@Nonnull final String message) {
        return questions.stream()
                        .filter(q -> q.matches(message))
                        .findFirst();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(@Nonnull final List<Question> questions) {
        this.questions = questions;
    }
}
