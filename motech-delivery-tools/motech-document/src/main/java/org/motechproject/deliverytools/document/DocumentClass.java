package org.motechproject.deliverytools.document;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentClass that = (DocumentClass) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
