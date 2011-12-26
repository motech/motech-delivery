package org.motechproject.deliverytools.seed;

import java.lang.reflect.Method;

public class SeedMethod implements Comparable<SeedMethod> {
    private Object bean;
    private Method method;
    private Integer priority;

    protected SeedMethod(Object bean, Method method, Integer priority) {
        this.bean = bean;
        this.method = method;
        this.priority = priority;
    }

    @Override
    public int compareTo(SeedMethod seedMethod) {
        return seedMethod.priority.compareTo(this.priority);
    }

    public void run() throws Exception {
        method.invoke(bean, null);
    }
}
