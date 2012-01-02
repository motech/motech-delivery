package org.motechproject.deliverytools.jobhandlerinvoker;

import org.junit.Test;
import org.motechproject.deliverytools.common.DeliveryToolsObject;
import org.motechproject.deliverytools.jobhandlerinvoker.domain.ScheduledJob;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.quartz.JobBuilder.newJob;

public class ScheduledJobControllerTest extends DeliveryToolsObject {
    @Test
    public void shouldInvoke() {
        JobHandlerInvokeRequest request = new JobHandlerInvokeRequest();
        request.setClassName(HandlerForJobHandlerInvokerTests.class.getSimpleName());
        request.setMethodName("handle");
        request.setJobId("job1234");

        ApplicationContext applicationContext = mock(ApplicationContext.class);
        HandlerForJobHandlerInvokerTests jobHandler = new HandlerForJobHandlerInvokerTests();
        when(applicationContext.getBean("handlerForJobHandlerInvokerTests")).thenReturn(jobHandler);

        HttpServletResponse response = mock(HttpServletResponse.class);
        AllScheduledJobs allScheduledJobs = mock(AllScheduledJobs.class);
        when(allScheduledJobs.get(anyString())).thenReturn(new ScheduledJob(newJob().build(), null));

        ScheduledJobController controllerScheduled = new ScheduledJobController(applicationContext, allScheduledJobs, new QueriedJobs());
        controllerScheduled.invoke(request, response);
        assertNotNull(jobHandler.motechEvent());
    }
}
