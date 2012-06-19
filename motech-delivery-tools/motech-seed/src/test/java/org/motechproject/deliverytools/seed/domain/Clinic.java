package org.motechproject.deliverytools.seed.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.CouchDbDocument;
import org.ektorp.support.TypeDiscriminator;

@TypeDiscriminator("doc.type === 'Clinic'")
public class Clinic extends CouchDbDocument {
    @JsonProperty
    private String type = "Clinic";
    @JsonProperty
    private String name;
    @JsonProperty
    private String city;

    public Clinic() {
    }

    public Clinic(String name, String city) {
        this.city = city;
        this.name = name;
    }

    public String city() {
        return city;
    }

    public String name() {
        return name;
    }
}
