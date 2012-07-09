package org.motechproject.deliverytools.jobhandlerinvoker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.deliverytools.jobhandlerinvoker.domain.ScheduledJob;
import org.motechproject.util.DateUtil;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

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
        schedulerFactory.getScheduler().deleteJob(jobName, AllScheduledJobs.JOB_GROUP_NAME);
    }

    @Test
    public void shouldLoadJobFromQuartzDataStore() throws SchedulerException {
        JobDetail jobDetail = new JobDetail();
        jobDetail.setName(jobName);
        jobDetail.setGroup(AllScheduledJobs.JOB_GROUP_NAME);
        jobDetail.setJobClass(JobForAllScheduledJobsIT.class);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("foo", "bar");
        jobDetail.setJobDataMap(jobDataMap);

        SimpleTrigger simpleTrigger = new SimpleTrigger();
        simpleTrigger.setName("triggerName");
        simpleTrigger.setStartTime(DateUtil.now().plusDays(1).toDate());
        schedulerFactory.getScheduler().scheduleJob(jobDetail, simpleTrigger);
        ScheduledJob scheduledJob = allScheduledJobs.get(jobDetail.getName());
        assertNotNull(scheduledJob);
        assertEquals("bar", scheduledJob.payload().get("foo"));
        assertNotNull(scheduledJob.triggerSummary(1).get(0));
    }
}
