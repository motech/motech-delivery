package org.motechproject.delivery;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class ResourceTemplateReaderTest {

    @Test
    public void shouldReadFromConfigJsonAndGiveResourcesList() {
        ResourceTemplateReader reader = new ResourceTemplateReader();
        List<Resource> resources = reader.resources();

        Resource usersResource = resources.get(0);
        assertEquals("users", usersResource.name());
        Map<String, String> config = usersResource.config();
        assertEquals("motechsuper", config.get("userName"));
    }

}
