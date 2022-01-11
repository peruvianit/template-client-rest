package it.istruzione.template_client_rest.commons.view;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class BaseView {
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
