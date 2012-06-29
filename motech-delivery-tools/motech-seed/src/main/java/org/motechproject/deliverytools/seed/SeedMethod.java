package org.motechproject.deliverytools.seed;

import org.motechproject.deliverytools.util.Version;

import java.lang.reflect.Method;

public class SeedMethod implements Comparable<SeedMethod> {
    private Object bean;
    private Method method;
    private Integer priority;
    private Version version;

    protected SeedMethod(Object bean, Method method, Integer priority, String version) {
        this.bean = bean;
        this.method = method;
        this.priority = priority;
        this.version = new Version(version);
    }

    @Override
    public int compareTo(SeedMethod seedMethod) {
        return version.equals(seedMethod.version) ?
                seedMethod.priority.compareTo(priority)
                : version.compareTo(seedMethod.version);
    }

    public boolean shouldRunFor(Version currentVersion) {
        return version.isGreaterThanOrEqualTo(currentVersion);
    }

    public void run() throws Exception {
        method.invoke(bean, null);
    }

    @Override
    public String toString() {
        return version + "|" + priority + "|" + method.getName();
    }
}
