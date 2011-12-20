package org.motechproject.deliverytools.jobhandlerinvoker;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class QueriedJobs {
    private Set<String> jobNames = new HashSet<String>();

    public void add(String name) {
        jobNames.add(name);
    }

    public String[] list() {
        return jobNames.toArray(new String[jobNames.size()]);
    }

    public void clear() {
        jobNames.clear();
    }
}
