package org.motechproject.deliverytools.jobhandlerinvoker;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobForAllScheduledJobsIT implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
    }
}
