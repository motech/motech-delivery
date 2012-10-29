package org.motechproject.batch.jobs.luceneoptimization.component;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;
import org.motechproject.batch.jobs.luceneoptimization.CouchDbLuceneMetaData;

import java.io.IOException;
import java.util.logging.Logger;

public class CouchDbLuceneOptimizer {

    Logger log = Logger.getLogger(CouchDbLuceneOptimizer.class.getName());

    public void optimize(String host, String couchDbPort) throws IOException, JSONException {
        log.entering(CouchDbLuceneOptimizer.class.getName(), "optimizingLuceneIndex");
        CouchDbLuceneMetaData metaData = new CouchDbLuceneMetaData(host, couchDbPort);
        for (String applicationDatabase : metaData.getApplicationDatabases()) {
            for (String designDocName : metaData.getDesignDocNames(applicationDatabase)) {
                try {
                    final String urlForOptimization = metaData.getOptimizationURL(designDocName);
                    final String urlForExpunge = metaData.getExpungeURL(designDocName);
                    postToLocation(urlForOptimization);
                    postToLocation(urlForExpunge);
                } catch (Exception e) {
                    log.severe(e.getMessage());
                }
            }
        }
        log.exiting(this.getClass().getName(), "IOException");
    }

    private boolean postToLocation(String url) throws IOException, JSONException {
        HttpClient httpClient = new DefaultHttpClient();
        final HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(new BasicHeader("Content-Type", "application/json"));
        final String response = httpClient.execute(httpPost, new BasicResponseHandler());
        final JSONObject jsonResult = new JSONObject(response);
        return jsonResult.getBoolean("ok");
    }
}
