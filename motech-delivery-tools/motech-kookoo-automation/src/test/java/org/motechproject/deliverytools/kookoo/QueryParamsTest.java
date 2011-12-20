package org.motechproject.deliverytools.kookoo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryParamsTest {
    @Test
    public void fromQueryString() {
        QueryParams queryParams = QueryParams.fromQueryString("foo=bar&baz=quack");
        assertEquals("bar", queryParams.get("foo"));
    }
}
