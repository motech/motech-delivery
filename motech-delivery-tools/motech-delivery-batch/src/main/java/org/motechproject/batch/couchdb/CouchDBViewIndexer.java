package org.motechproject.batch.couchdb;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class CouchDBViewIndexer {

    Logger log = Logger.getLogger(CouchDBViewIndexer.class.getName());

    public void indexAllViews(String host) throws IOException, JSONException {
        log.entering(CouchDBViewIndexer.class.getName(), "indexAllViews");
        System.out.println("started indexing views of " + host);
        CouchDbMetaData couchDbMetaData = new CouchDbMetaData(host, CouchDbMetaData.DEFAULT_PORT);
        for (String database : couchDbMetaData.getApplicationDatabases()) {
            try {
                indexAllViewsInDatabase(couchDbMetaData, database   );
            } catch (Exception e) {
                log.severe(e.getMessage());
            }
        }
        log.exiting(this.getClass().getName(), "indexAllViews");
    }

    private void indexAllViewsInDatabase(CouchDbMetaData couchDbMetaData, String databaseName) throws IOException, JSONException {
        final List<String> designDocNames = couchDbMetaData.getDesignDocNames(databaseName);
        for (String designDocName : designDocNames) {
            indexView(couchDbMetaData, databaseName, designDocName);
        }
    }

    private void indexView(CouchDbMetaData couchDbMetaData, String databaseName, String designDocName) throws IOException {
        log.info("Indexing " + databaseName + " : " + designDocName);
        String url = couchDbMetaData.getUrlForView(databaseName, designDocName);
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.execute(new HttpGet(url));
    }

}
