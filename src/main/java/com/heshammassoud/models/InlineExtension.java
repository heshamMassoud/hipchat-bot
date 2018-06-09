package com.heshammassoud.models;

import com.atlassian.adf.InlineNode;
import com.atlassian.adf.base.AbstractNode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("inlineExtension")
public class InlineExtension extends AbstractNode<InlineExtension> implements InlineNode {
    @JsonIgnore
    @Override
    public String text() {
        return strAttribute("text").orElse("");
    }
}
