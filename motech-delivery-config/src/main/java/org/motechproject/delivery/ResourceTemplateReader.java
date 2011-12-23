package org.motechproject.delivery;

import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ResourceTemplateReader {
    private String jsonFilePath;

    public ResourceTemplateReader(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    public List<Resource> resources() {
        return (List<Resource>) new MotechJsonReader().readFromFile(jsonFilePath, new TypeToken<List<Resource>>(){}.getType());
    }
}
