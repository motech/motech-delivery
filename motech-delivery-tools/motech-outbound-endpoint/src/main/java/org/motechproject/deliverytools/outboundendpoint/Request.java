package org.motechproject.deliverytools.outboundendpoint;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Map;

public class Request {
    @JsonProperty
    private String queryString;

    public Request(String queryString) {
        this.queryString = queryString;
    }

    public String queryString() {
        return queryString;
    }
}
