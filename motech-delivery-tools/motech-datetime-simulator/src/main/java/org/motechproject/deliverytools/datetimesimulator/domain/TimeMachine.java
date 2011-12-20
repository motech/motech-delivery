package org.motechproject.deliverytools.datetimesimulator.domain;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.motechproject.util.DateUtil;
import org.motechproject.util.datetime.DateTimeSource;

public class TimeMachine implements DateTimeSource {
    private String today;
    private String hour;
    private String minute;
    private DateTimeSource dateTimeSource;

    public TimeMachine(DateTimeSource dateTimeSource) {
        this.dateTimeSource = dateTimeSource;
    }

    @Override
    public DateTimeZone timeZone() {
        return dateTimeSource.timeZone();
    }

    @Override
    public DateTime now() {
        DateTime dateTime = new DateTime(timeZone());
        LocalDate today = today();
        dateTime = dateTime.withYear(today.getYear()).withMonthOfYear(today.getMonthOfYear()).withDayOfMonth(today.getDayOfMonth());

        if (StringUtils.isEmpty(hour) || StringUtils.isEmpty(minute)) return dateTime;
        return dateTime.withHourOfDay(Integer.parseInt(hour)).withMinuteOfHour(Integer.parseInt(minute));
    }

    @Override
    public LocalDate today() {
        if (StringUtils.isEmpty(today)) return new LocalDate(timeZone());

        LocalDate configuredDate = LocalDate.parse(today);
        return new LocalDate(timeZone()).withYear(configuredDate.getYear()).withMonthOfYear(configuredDate.getMonthOfYear()).withDayOfMonth(configuredDate.getDayOfMonth());
    }

    public void update(String date, String hour, String minute) {
        this.today = StringUtils.isEmpty(date) ? this.today : date;
        this.hour = StringUtils.isEmpty(hour) ? this.hour : hour;
        this.minute = StringUtils.isEmpty(minute) ? this.minute : minute;
    }
}
