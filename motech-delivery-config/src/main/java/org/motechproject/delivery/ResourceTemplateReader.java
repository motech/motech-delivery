package org.motechproject.delivery;

import com.google.gson.reflect.TypeToken;
import org.motechproject.dao.MotechJsonReader;

import java.util.List;

public class ResourceTemplateReader {

    private static final String filename = "/delivery-config.json" ;

    public List<Resource> resources() {
        return (List<Resource>) new MotechJsonReader().readFromFile(filename, new TypeToken<List<Resource>>(){}.getType());
    }
}
