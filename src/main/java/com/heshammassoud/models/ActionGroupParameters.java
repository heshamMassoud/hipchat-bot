package com.heshammassoud.models;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class ActionGroupParameters {
    private String key;
    private List<ActionGroupAction> actions;

    public ActionGroupParameters(@Nonnull final String key, @Nonnull final ActionGroupAction... actions) {
        this.key = key;
        this.actions = Arrays.asList(actions);
    }

    public String getKey() {
        return key;
    }

    public void setKey(@Nonnull final String key) {
        this.key = key;
    }


    public List<ActionGroupAction> getActions() {
        return actions;
    }

    public void setActions(@Nonnull final ActionGroupAction... actions) {
        this.actions = Arrays.asList(actions);
    }
}
