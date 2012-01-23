package org.motechproject.deliverytools.seed.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.TypeDiscriminator;
import org.motechproject.model.MotechBaseDataObject;


@TypeDiscriminator("doc.type === 'Doctor'")
public class Doctor extends MotechBaseDataObject {
    @JsonProperty
    private String type = "Doctor";
    @JsonProperty
    private String name;
    @JsonProperty
    private Clinic clinic;

    public Doctor() {
    }

    public Doctor(String name, Clinic clinic) {
        this.name = name;
        this.clinic = clinic;
    }

    public Clinic clinic() {
        return clinic;
    }
}
