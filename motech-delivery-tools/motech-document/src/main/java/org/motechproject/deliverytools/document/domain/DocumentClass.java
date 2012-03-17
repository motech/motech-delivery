package org.motechproject.deliverytools.document.domain;

import java.util.ArrayList;
import java.util.List;

public class DocumentClass {

    private String name;

    private List<DocumentMethod> documentMethods = new ArrayList<DocumentMethod>();

    public DocumentClass(String name) {
        this.name = name;
    }

    public void addMethod(DocumentMethod documentMethod) {
        this.documentMethods.add(documentMethod);
    }

    public String name() {
        return name;
    }

    public List<DocumentMethod> methods() {
        return documentMethods;
    }
}
