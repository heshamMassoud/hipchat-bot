package com.heshammassoud.models.stride;

import javax.annotation.Nonnull;

public class Action {
    private ActionTarget target;

    public Action(@Nonnull final String targetKey) {
        this.target = new ActionTarget(targetKey);
    }

    public ActionTarget getTarget() {
        return target;
    }

    public void setTarget(@Nonnull final ActionTarget target) {
        this.target = target;
    }
}
