package org.motechproject.batch.jobs.luceneoptimization;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CouchDbLuceneMetaDataTest {

    @Test
    public void testOptimizeURL() {
        String designDocName = "designDocName";
        assertEquals(
                "http://localhost:5984/_fti/_design/" + designDocName + "//_optimize",
                new CouchDbLuceneMetaData("localhost", "5984").getOptimizationURL(designDocName)
        );
    }

    @Test
    public void testExpungeURL() {
        String designDocName = "designDocName";
        assertEquals(
                "http://localhost:5984/_fti/_design/" + designDocName + "//_expunge",
                new CouchDbLuceneMetaData("localhost", "5984").getExpungeURL(designDocName)
        );
    }
}
