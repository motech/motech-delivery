package org.motechproject.deliverytools.jobhandlerinvoker.domain;

import org.motechproject.scheduler.MotechSchedulerServiceImpl;
import org.motechproject.server.event.annotations.MotechListener;

import java.lang.reflect.Method;

public class ScheduledJobName {
    private String uniqueId;
    private Method method;

    public ScheduledJobName(String uniqueId, Method handlingMethod) {
        this.uniqueId = uniqueId;
        this.method = handlingMethod;
    }

    public String jobId(boolean isRepeating) {
        MotechListener annotation = method.getAnnotation(MotechListener.class);
        String jobId = String.format("%s-%s", annotation.subjects()[0], uniqueId);
        if (isRepeating) return jobId + MotechSchedulerServiceImpl.REPEAT_JOB_SUFFIX;
        return jobId;
    }
}
