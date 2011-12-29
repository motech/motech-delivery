package org.motechproject.delivery;

import java.util.Map;

public class Resource {
    private String name;
    private Map<String, String> config;

    protected Resource() {
    }

    public Resource(String name, Map config) {
        this.name = name;
        this.config = config;
    }

    public String name() {
        return name;
    }

    public Map<String, String> config() {
        return config;
    }
}
