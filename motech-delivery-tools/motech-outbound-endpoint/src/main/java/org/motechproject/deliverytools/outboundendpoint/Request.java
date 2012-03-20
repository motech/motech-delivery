package org.motechproject.deliverytools.outboundendpoint;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

public class Request {
    @JsonProperty
    private String queryString;
    private DateTime time;

    public Request(String queryString) {
        this.queryString = queryString;
        this.time = DateTime.now();
    }

    public String queryString() {
        return time.toString() + " -> " + queryString;
    }

    @Override
    public String toString() {
        return queryString();
    }
}
