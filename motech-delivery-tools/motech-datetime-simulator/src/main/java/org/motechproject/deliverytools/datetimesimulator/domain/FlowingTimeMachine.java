package org.motechproject.deliverytools.datetimesimulator.domain;

import org.apache.commons.lang.StringUtils;
import org.joda.time.*;
import org.motechproject.util.datetime.DateTimeSource;

public class FlowingTimeMachine implements TimeMachine {
    private DateTimeSource dateTimeSource;
    private int offsetInSeconds;

    public FlowingTimeMachine(DateTimeSource dateTimeSource) {
        this.dateTimeSource = dateTimeSource;
    }

    @Override
    public DateTimeZone timeZone() {
        return dateTimeSource.timeZone();
    }

    @Override
    public DateTime now() {
        return DateTime.now().plusSeconds(offsetInSeconds);
    }

    @Override
    public LocalDate today() {
        return new LocalDate(now());
    }

    public void update(String date, String hour, String minute) {
        DateTime currentDateTime = new DateTime().withSecondOfMinute(0);
        if (StringUtils.isEmpty(hour))
            hour = "0";
        if(StringUtils.isEmpty(minute))
            minute = "0";
        DateTime newDateTime = DateTime.parse(date).withHourOfDay(Integer.parseInt(hour)).withMinuteOfHour(Integer.parseInt(minute));
        offsetInSeconds = Seconds.secondsBetween(currentDateTime, newDateTime).getSeconds();
    }
}
