package org.motechproject.deliverytools.document;

import java.lang.reflect.Method;

public class DocumentMethod {
    private Object bean;
    private Method method;

    protected DocumentMethod(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
    }

    public String getClassName() {
        return method.getDeclaringClass().getName();
    }

}
