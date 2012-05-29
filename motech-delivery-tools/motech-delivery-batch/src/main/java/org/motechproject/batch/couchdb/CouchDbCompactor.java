package org.motechproject.batch.couchdb;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Logger;

public class CouchDbCompactor {

    Logger log = Logger.getLogger(CouchDBViewIndexer.class.getName());

    public void compactAllDatabases(String host) throws IOException, JSONException {
        log.entering(CouchDBViewIndexer.class.getName(), "compactAllDBs");
        System.out.println("started compacting couchdb database of " + host);
        CouchDbMetaData couchDbMetaData = new CouchDbMetaData(host, CouchDbMetaData.DEFAULT_PORT);
        for (String applicationDatabase : couchDbMetaData.getApplicationDatabases()) {
            try {
                final String urlForCompaction = couchDbMetaData.getUrlForCompaction(applicationDatabase);
                compact(urlForCompaction);
            } catch (Exception e) {
                log.severe(e.getMessage());
            }
        }
        log.exiting(this.getClass().getName(), "compactAllDBs");
    }

    private boolean compact(String urlForCompaction) throws IOException, JSONException {
        log.info("Compacting : " + urlForCompaction);
        HttpClient httpClient = new DefaultHttpClient();
        final HttpPost httpPost = new HttpPost(urlForCompaction);
        httpPost.setHeader(new BasicHeader("Content-Type", "application/json"));
        final String response = httpClient.execute(httpPost, new BasicResponseHandler());
        final JSONObject jsonResult = new JSONObject(response);
        return jsonResult.getBoolean("ok");
    }
}
