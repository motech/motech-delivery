package org.motechproject.deliverytools.jobhandlerinvoker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.deliverytools.jobhandlerinvoker.domain.ScheduledJob;
import org.motechproject.scheduler.MotechSchedulerServiceImpl;
import org.motechproject.util.DateUtil;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationJobHandlerInvokerContextForTest.xml"})
public class AllScheduledJobsIT {
    @Autowired
    private AllScheduledJobs allScheduledJobs;
    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    private final String jobName = "jobNameForAllScheduledJobsIT";

    @Before
    public void setUp() throws SchedulerException {
        schedulerFactory.getScheduler().deleteJob(jobKey(jobName, MotechSchedulerServiceImpl.JOB_GROUP_NAME));
    }

    @Test
    public void shouldLoadJobFromQuartzDataStore() throws SchedulerException {
	    JobDataMap jobDataMap = new JobDataMap();
	    jobDataMap.put("foo", "bar");
	    JobDetail jobDetail = newJob(JobForAllScheduledJobsIT.class)
		        .withIdentity(jobKey(jobName, MotechSchedulerServiceImpl.JOB_GROUP_NAME))
			    .usingJobData(jobDataMap)
		        .build();

        Trigger simpleTrigger = newTrigger()
		        .withIdentity(triggerKey("triggerName"))
		        .startAt(DateUtil.now().plusDays(1).toDate())
		        .build();
        schedulerFactory.getScheduler().scheduleJob(jobDetail, simpleTrigger);
        ScheduledJob scheduledJob = allScheduledJobs.get(jobDetail.getKey().getName());
        assertNotNull(scheduledJob);
        assertEquals("bar", scheduledJob.payload().get("foo"));
        assertNotNull(scheduledJob.triggerSummary(1).get(0));
    }
}
