package org.motechproject.delivery;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PuppetManifestFromJson {

    public static void main(String[] args) throws IOException {
        new PuppetManifestFromJson().create(args[0], args[1]);
    }

    public void create(String jsonFilePathInClasspath, String manifestFileOutputPath) throws IOException {
        List<Resource> resources = new ResourceTemplateReader(jsonFilePathInClasspath).resources();
        new PuppetManifest(resources).to(new FileWriter(new File(manifestFileOutputPath)));
    }
}
