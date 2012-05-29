package org.motechproject.batch.couchdb;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Logger;

public class CouchDbReplicator {

    Logger log = Logger.getLogger(CouchDbReplicator.class.getName());

    public void startReplication(String masterCouchDbHost, String slaveCouchDbHost) throws IOException, JSONException {
        log.entering(this.getClass().getName(), "replication");

        CouchDbMetaData masterMetaData = new CouchDbMetaData(masterCouchDbHost, CouchDbMetaData.DEFAULT_PORT);
        CouchDbMetaData slaveMetaData = new CouchDbMetaData(slaveCouchDbHost, CouchDbMetaData.DEFAULT_PORT);

        for (String applicationDatabase : masterMetaData.getApplicationDatabases()) {
            try {
                final String urlForReplication = slaveMetaData.getUrlForReplication();
                replicate(urlForReplication, masterMetaData, applicationDatabase);
            } catch (Exception e) {
                log.severe(e.getMessage());
            }
        }
        log.exiting(this.getClass().getName(), "replication");
    }

    private boolean replicate(String urlForReplication, CouchDbMetaData masterMetaData, String applicationDatabase) throws JSONException, IOException {
        log.info("Replicating : " + urlForReplication);
        HttpClient httpClient = new DefaultHttpClient();
        final HttpPost httpPost = new HttpPost(urlForReplication);
        httpPost.setHeader(new BasicHeader("Content-Type", "application/json"));

        JSONObject params = constructJSONRequest(masterMetaData, applicationDatabase);

        StringEntity entity = new StringEntity(params.toString());
        httpPost.setEntity(entity);

        final String response = httpClient.execute(httpPost, new BasicResponseHandler());
        final JSONObject jsonResult = new JSONObject(response);
        return jsonResult.getBoolean("ok");

    }

    JSONObject constructJSONRequest(CouchDbMetaData masterMetaData, String applicationDatabase) throws JSONException {
        JSONObject params = new JSONObject();
        params.put("source", masterMetaData.getUrlForDatabase(applicationDatabase));
        params.put("target", applicationDatabase);
        params.put("create_target", true);
        params.put("continuous", true);
        return params;
    }

}
