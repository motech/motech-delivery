package org.motechproject.delivery;

import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class PuppetManifest {
    private List<Resource> resources;

    public PuppetManifest(List<Resource> resources) {
        this.resources = resources;
    }

    public void to(Writer writer) throws IOException {
        setupVariables(writer);
        setupNodes(writer);
        footer(writer);
    }

    private void footer(Writer writer) throws IOException {
        writer.write("Exec {\n  path => \"/usr/bin:/usr/sbin/:/bin:/sbin:/usr/local/bin:/usr/local/sbin\"\n}\n");
    }

    private void setupVariables(Writer writer) throws IOException {
        for (Resource resource : resources) {
            if (!resource.config().isEmpty()) {
                setupVariablesForResource(writer, resource);
                writer.write("\n");
            }
        }
    }

    private void setupNodes(Writer writer) throws IOException {
        writer.write("node default {\n");
        for (Resource resource : resources) {
            writer.write("  include " + resource.name() + "\n");
        }
        writer.write("}\n");
    }

    private void setupVariablesForResource(Writer writer, Resource resource) throws IOException {
        writer.write("class { '" + resource.name() + "': ");
        Map<String, String> configs = resource.config();

        for (String key: configs.keySet()) {
            writer.write(MessageFormat.format("{0} => ''{1}'', ", key, configs.get(key)));
        }

        writer.write("}");
    }
}
