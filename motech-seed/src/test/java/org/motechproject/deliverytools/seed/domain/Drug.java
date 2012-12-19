package org.motechproject.deliverytools.seed.domain;


import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

@TypeDiscriminator("doc.type === 'Drug'")
public class Drug extends CouchDbDocument {
    @JsonProperty
    private String type = "Drug";
    @JsonProperty
    private String name;

    public Drug() {
    }

    public Drug(String name) {
        this.name = name;
    }
}
