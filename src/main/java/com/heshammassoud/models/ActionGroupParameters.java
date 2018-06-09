package com.heshammassoud.models;

import javax.annotation.Nonnull;

public class ActionGroupParameters {
    private String key;
    private ActionGroupAction[] actions;

    public ActionGroupParameters(@Nonnull final String key, @Nonnull final ActionGroupAction... actions) {
        this.key = key;
        this.actions = actions;
    }

    public String getKey() {
        return key;
    }

    public void setKey(@Nonnull final String key) {
        this.key = key;
    }


    public ActionGroupAction[] getActions() {
        return actions;
    }

    public void setActions(@Nonnull final ActionGroupAction[] actions) {
        this.actions = actions;
    }
}
