package org.motechproject.deliverytools.jobhandlerinvoker.domain;


import org.motechproject.event.MotechEvent;

import java.util.HashMap;

public class MotechEventInvocation {
    private HashMap<String, Object> map = new HashMap<String, Object>();

    public MotechEventInvocation(ScheduledJob scheduledJob) {
        map.putAll(scheduledJob.payload());
    }

    public MotechEvent event() {
        return new MotechEvent("notrelevant", map);
    }
}
