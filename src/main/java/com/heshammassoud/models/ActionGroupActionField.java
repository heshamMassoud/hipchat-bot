package com.heshammassoud.models;

import javax.annotation.Nonnull;
import java.util.Map;

public class ActionGroupActionField {
    private ActionTarget target;
    private Map<String, String> parameters;

    public ActionGroupActionField(@Nonnull final ActionTarget target,
                                  @Nonnull final Map<String, String> parameters) {
        this.target = target;
        this.parameters = parameters;
    }

    public ActionTarget getTarget() {
        return target;
    }

    public void setTarget(@Nonnull final ActionTarget target) {
        this.target = target;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(@Nonnull final Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
