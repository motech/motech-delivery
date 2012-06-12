package org.motechproject.batch.jobs.replication;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.motechproject.batch.CouchDbMetaData;

import static org.junit.Assert.assertEquals;

public class CouchDbReplicatorTest {

    @Test
    public void testJSONRequestAsString() throws JSONException {
        CouchDbMetaData couchDbMetaData = new CouchDbMetaData("localhost", CouchDbMetaData.DEFAULT_PORT);
        JSONObject jsonObject = new CouchDbReplicator().constructJSONRequest(couchDbMetaData, "motech");
        String expected = "{\"continuous\":true,\"create_target\":true,\"source\":\"http://localhost:5984/motech\",\"target\":\"motech\"}";
        assertEquals(expected, jsonObject.toString());
    }
}
