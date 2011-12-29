package org.motechproject.delivery;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PuppetManifestFromJsonIT {

    private String actualManifestPath = "output-site.pp";

    @Test
    public void shouldGenerateManifestFromGivenJsonFile() throws IOException {
        String jsonFilePath = getClass().getResource("/delivery-config.json").getPath();
        String expectedManifestPath = getClass().getResource("/expected-site.pp").getPath();

        PuppetManifestFromJson.main(new String[]{jsonFilePath, actualManifestPath});

        String expected = FileUtils.readFileToString(new File(expectedManifestPath));
        String actual = FileUtils.readFileToString(new File(actualManifestPath));
        assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        FileUtils.deleteQuietly(new File(actualManifestPath));
    }
}
