package org.motechproject.delivery;

import java.util.Map;

public class Resource {
    private String name;
    private Map<String, String> config;

    public String name() {
        return name;
    }

    public Map<String, String> config() {
        return config;
    }
}
