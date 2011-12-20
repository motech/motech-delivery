package org.motechproject.deliverytools.jobhandlerinvoker.domain;

import org.motechproject.util.DateUtil;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ScheduledJob {
    private JobDetail jobDetail;
    private Trigger trigger;

    public ScheduledJob(JobDetail jobDetail, Trigger trigger) {
        this.jobDetail = jobDetail;
        this.trigger = trigger;
    }

    public List<Date> triggerSummary(int number) {
        Date currentDateTime = DateUtil.now().toDate();
        ArrayList<Date> followingDates = new ArrayList<Date>();
        for (int i = 0; i < number; i++) {
            currentDateTime = trigger.getFireTimeAfter(currentDateTime);
            followingDates.add(currentDateTime);
        }
        return followingDates;
    }

    public Map payload() {
        return jobDetail.getJobDataMap().getWrappedMap();
    }
}
