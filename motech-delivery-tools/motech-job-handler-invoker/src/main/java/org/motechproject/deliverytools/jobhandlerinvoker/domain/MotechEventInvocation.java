package org.motechproject.deliverytools.jobhandlerinvoker.domain;

import org.motechproject.model.MotechEvent;

import java.util.HashMap;
import java.util.Map;

public class MotechEventInvocation {
    private HashMap<String, Object> map = new HashMap<String, Object>();

    public MotechEventInvocation(ScheduledJob scheduledJob) {
        map.putAll(scheduledJob.payload());
    }

    public MotechEvent event() {
        return new MotechEvent("notrelevant", map);
    }
}
