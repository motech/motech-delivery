package org.motechproject.deliverytools.seed.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.ektorp.support.TypeDiscriminator;
import org.motechproject.model.MotechAuditableDataObject;

import java.util.List;

@TypeDiscriminator("doc.type === 'Regimen'")
public class Regimen extends MotechAuditableDataObject {
    @JsonProperty
    private String type = "Regimen";
    @JsonProperty
    private String name;
    @JsonProperty
    private List<Drug> drugs;

    public Regimen() {
    }

    public Regimen(String name, List<Drug> drugs) {
        this.name = name;
        this.drugs = drugs;
    }

    public String name() {
        return name;
    }

    public List<Drug> drugs() {
        return drugs;
    }
}
