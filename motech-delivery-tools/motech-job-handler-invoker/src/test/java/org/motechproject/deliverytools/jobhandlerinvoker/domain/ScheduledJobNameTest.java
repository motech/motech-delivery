package org.motechproject.deliverytools.jobhandlerinvoker.domain;

import org.junit.Test;
import org.motechproject.deliverytools.jobhandlerinvoker.HandlerForJobHandlerInvokerTests;
import org.motechproject.model.MotechEvent;
import org.motechproject.scheduler.domain.JobId;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class ScheduledJobNameTest {
    @Test
    public void jobId() {
        Method method = ReflectionUtils.findMethod(HandlerForJobHandlerInvokerTests.class, "handle", MotechEvent.class);
        ScheduledJobName scheduledJobName = new ScheduledJobName("abcd", method);
        assertEquals(String.format("%s-abcd", HandlerForJobHandlerInvokerTests.SAMPLE_SUBJECT), scheduledJobName.jobId(false));
        assertEquals(String.format("%s-abcd%s", HandlerForJobHandlerInvokerTests.SAMPLE_SUBJECT, JobId.REPEAT_JOB_SUFFIX), scheduledJobName.jobId(true));
    }
}
