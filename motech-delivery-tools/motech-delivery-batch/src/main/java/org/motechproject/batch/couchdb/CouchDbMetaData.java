package org.motechproject.batch.couchdb;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CouchDbMetaData {

    private String host;
    private String port;

    public static String DEFAULT_PORT = "5984";

    public CouchDbMetaData(String host, String port) {
        this.host = host;
        this.port = port;
    }

    private String couchDbBaseUrl() {
        return "http://" + host + ":" + port + "/";
    }

    public String getUrlForView(String databaseName, String designDocName) {
        return String.format("%s%s/%s/_view/all", couchDbBaseUrl(), databaseName, designDocName);
    }

    public String getUrlForCompaction(String databaseName) {
        return String.  format("%s%s/_compact", couchDbBaseUrl(), databaseName);
    }
    
    public String getUrlForReplication(){
        return String.format("%s/_replicate", couchDbBaseUrl());
    }
    
    public String getUrlForDatabase(String databaseName){
        return couchDbBaseUrl() + databaseName;
    }

    public List<String> getDesignDocNames(String dbName) throws IOException, JSONException {
        String url = couchDbBaseUrl() + dbName + "/_all_docs?startkey=%22_design%2F%22&endkey=%22_design0%22&include_docs=true";
        final JSONObject jsonObject = new JSONObject(httpGetAsString(url));
        final JSONArray rows = jsonObject.getJSONArray("rows");
        List<String> result = new LinkedList<String>();
        for (int i = 0; i < rows.length(); i++) {
            result.add(rows.getJSONObject(i).getString("id"));
        }
        return result;
    }

    private String httpGetAsString(String url) throws JSONException, IOException {
        HttpClient httpclient = new DefaultHttpClient();
        final BasicResponseHandler responseHandler = new BasicResponseHandler();
        return httpclient.execute(new HttpGet(url), responseHandler);
    }

    public List<String> getApplicationDatabases() throws IOException, JSONException {
        String url = couchDbBaseUrl() + "_all_dbs";
        final JSONArray jsonDBArray = new JSONArray(httpGetAsString(url));
        List<String> result = new LinkedList<String>();
        for (int i=0; i<jsonDBArray.length(); i++) {
            final String dbName = jsonDBArray.getString(i);
            if (!dbName.startsWith("_"))
                result.add(dbName);
        }
        return result;
    }
}