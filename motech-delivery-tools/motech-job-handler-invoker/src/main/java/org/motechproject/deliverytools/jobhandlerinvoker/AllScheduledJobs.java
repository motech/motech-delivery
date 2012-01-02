package org.motechproject.deliverytools.jobhandlerinvoker;

import org.motechproject.deliverytools.jobhandlerinvoker.domain.ScheduledJob;
import org.motechproject.scheduler.MotechSchedulerServiceImpl;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.quartz.JobKey.jobKey;

@Component
public class AllScheduledJobs {
    private SchedulerFactoryBean schedulerFactory;
    private QueriedJobs queriedJobs;

    @Autowired
    public AllScheduledJobs(SchedulerFactoryBean schedulerFactory, QueriedJobs queriedJobs) throws SchedulerException {
        this.schedulerFactory = schedulerFactory;
        this.queriedJobs = queriedJobs;
    }

    public ScheduledJob get(String name) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = scheduler.getJobDetail(jobKey(name, MotechSchedulerServiceImpl.JOB_GROUP_NAME));
            if (jobDetail == null)
                throw new IllegalArgumentException("No job named:" + name);
	        List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey(name, MotechSchedulerServiceImpl.JOB_GROUP_NAME));
	        if (triggers.size() == 0 || triggers.size() > 1)
                throw new AssertionError(String.format("There should be exactly one trigger for every job. Found %s triggers for %s", triggers.size(), name));
            queriedJobs.add(name);
            return new ScheduledJob(jobDetail, triggers.get(0));
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
