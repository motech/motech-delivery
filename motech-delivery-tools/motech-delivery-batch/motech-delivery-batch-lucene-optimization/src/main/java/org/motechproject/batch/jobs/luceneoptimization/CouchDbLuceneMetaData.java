package org.motechproject.batch.jobs.luceneoptimization;

import org.motechproject.batch.CouchDbMetaData;

public class CouchDbLuceneMetaData extends CouchDbMetaData {

    public CouchDbLuceneMetaData(String host, String couchdbPort) {
        super(host, couchdbPort);
    }

    public String getOptimizationURL(String designDocName) {
        return String.format("%s/%s/%s/%s//%s", couchDbBaseUrl(), "_ftl", "_design", designDocName, "_optimize");
    }

    public String getExpungeURL(String designDocName) {
        return String.format("%s/%s/%s/%s//%s", couchDbBaseUrl(), "_ftl", "_design", designDocName, "_expunge");
    }
}
