package com.heshammassoud.models;

import javax.annotation.Nonnull;

public class ActionGroupAction {
    private String key;
    private String title;
    private String appearance;
    private ActionGroupActionField action;

    public ActionGroupAction(@Nonnull final String key, @Nonnull final String title, @Nonnull final String appearance,
                             @Nonnull final ActionGroupActionField action) {
        this.key = key;
        this.title = title;
        this.appearance = appearance;
        this.action = action;
    }

    public String getKey() {
        return key;
    }

    public void setKey(@Nonnull final String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@Nonnull final String title) {
        this.title = title;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(@Nonnull final String appearance) {
        this.appearance = appearance;
    }

    public ActionGroupActionField getAction() {
        return action;
    }

    public void setAction(@Nonnull final ActionGroupActionField action) {
        this.action = action;
    }
}
