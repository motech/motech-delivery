package org.motechproject.delivery;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class PuppetManifestTest {

    @Test
    public void shouldCreateManifestOutOfResources() throws IOException {
        List<Resource> resources = Arrays.asList(
                ResourceBuilder.of("users").with("userName", "motechsuper").resource(),
                ResourceBuilder.of("mysql").with("rootPassword", "password").resource(),
                ResourceBuilder.of("activemq").resource(),
                ResourceBuilder.of("couchdb").resource(),
                ResourceBuilder.of("httpd").resource()
        );

        PuppetManifest manifest = new PuppetManifest(resources);
        StringWriter stringWriter = new StringWriter();
        manifest.to(stringWriter);

        String expectedManifest = FileUtils.readFileToString(new File(getClass().getResource("/expected-site.pp").getFile()));
        assertEquals(expectedManifest, stringWriter.toString());
    }

    private static class ResourceBuilder {
        private String nameOfResource;
        private HashMap<String, String> configs;

        public ResourceBuilder(String nameOfResource) {
            this.nameOfResource = nameOfResource;
        }

        public static ResourceBuilder of(String nameOfResource) {
            return new ResourceBuilder(nameOfResource);
        }

        public ResourceBuilder with(String key, String value) {
            if (configs == null) {
                this.configs = new HashMap<String, String>();
            }
            configs.put(key, value);
            return this;
        }

        public Resource resource() {
            return new Resource(nameOfResource, configs);
        }
    }
}
