package com.heshammassoud.models;

import javax.annotation.Nonnull;

public class ActionGroupActionField {
    private ActionTarget target;

    public ActionGroupActionField(@Nonnull final ActionTarget target) {
        this.target = target;
    }

    public ActionTarget getTarget() {
        return target;
    }

    public void setTarget(@Nonnull final ActionTarget target) {
        this.target = target;
    }
}
