package org.motechproject.delivery;

import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class MotechJsonReaderTest {
    @Test
    public void shouldReadFromConfigJsonAndGiveResourcesList() {
        String jsonFilePath = getClass().getResource("/delivery-config.json").getPath();
        List<Resource> resources = (List<Resource>) new MotechJsonReader().readFromFile(jsonFilePath, new TypeToken<List<Resource>>() { }.getType());

        Resource usersResource = resources.get(0);
        assertEquals("users", usersResource.name());
        Map<String, String> config = usersResource.config();
        assertEquals("motechsuper", config.get("userName"));
    }
}
