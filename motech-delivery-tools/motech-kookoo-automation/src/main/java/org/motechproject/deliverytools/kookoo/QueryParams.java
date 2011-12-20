package org.motechproject.deliverytools.kookoo;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class QueryParams {
    private Map<String, Object> params = new HashMap<String, Object>();

    public static QueryParams fromQueryString(String string) {
        QueryParams queryParams = new QueryParams();
        String[] keyValuePairs = StringUtils.split(string, "&");
        for (String keyValuePair : keyValuePairs) {
            String[] keyAndValue = StringUtils.split(keyValuePair, "=");
            queryParams.put(keyAndValue[0], keyAndValue[1]);
        }
        return queryParams;
    }

    public QueryParams put(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public Map<String, Object> params() {
        return params;
    }

    public Object get(String key) {
        return params.get(key);
    }

    public String getString(String key) {
        return (String) get(key);
    }
}
