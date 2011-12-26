package org.motechproject.delivery;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PuppetManifestFromJson {

    public static void main(String[] args) throws IOException {
        new PuppetManifestFromJson().create(args[0], args[1]);
    }

    public void create(String jsonFilePath, String manifestFileOutputPath) throws IOException {
        List<Resource> resources = (List<Resource>) new MotechJsonReader().readFromFile(jsonFilePath, new TypeToken<List<Resource>>(){}.getType());
        new PuppetManifest(resources).to(new FileWriter(new File(manifestFileOutputPath)));
    }
}
