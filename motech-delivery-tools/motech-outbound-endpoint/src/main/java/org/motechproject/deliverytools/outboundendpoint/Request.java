package org.motechproject.deliverytools.outboundendpoint;

import org.codehaus.jackson.annotate.JsonProperty;

public class Request {
    @JsonProperty
    private String queryString;

    public Request(String queryString) {
        this.queryString = queryString;
    }

    public String queryString() {
        return queryString;
    }

    @Override
    public String toString() {
        return queryString();
    }
}
