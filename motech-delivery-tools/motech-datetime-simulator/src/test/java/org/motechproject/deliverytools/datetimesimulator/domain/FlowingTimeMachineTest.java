package org.motechproject.deliverytools.datetimesimulator.domain;

import org.hamcrest.core.Is;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.Seconds;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.util.datetime.DateTimeSource;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlowingTimeMachineTest {
    private FlowingTimeMachine flowingTimeMachine;

    @Before
    public void setUp() {
        DateTimeSource dateTimeSource = mock(DateTimeSource.class);
        when(dateTimeSource.timeZone()).thenReturn(DateTimeZone.UTC);
        flowingTimeMachine = new FlowingTimeMachine(dateTimeSource);
    }

    @Test
    public void shouldMoveToFutureTime(){
        LocalDate futureDate = LocalDate.now().plusDays(10);
        String futureHour = "10";
        String futureMinute = "9";
        flowingTimeMachine.update(futureDate.toString("yyyy-MM-dd"), futureHour, futureMinute);

        DateTime dateTime = flowingTimeMachine.now();

        assertThat(flowingTimeMachine.today(), Is.is(futureDate));
        assertThat(new LocalDate(dateTime), Is.is(futureDate));
        assertThat(dateTime.getHourOfDay(), Is.is(Integer.parseInt(futureHour)));
        assertThat(dateTime.getMinuteOfHour(), Is.is(Integer.parseInt(futureMinute)));
    }

    @Test
    public void shouldMoveToPastTime(){
        LocalDate futureDate = LocalDate.now().minusDays(10);
        String futureHour = "10";
        String futureMinute = "9";
        flowingTimeMachine.update(futureDate.toString("yyyy-MM-dd"), futureHour, futureMinute);

        DateTime dateTime = flowingTimeMachine.now();

        assertThat(flowingTimeMachine.today(), Is.is(futureDate));
        assertThat(new LocalDate(dateTime), Is.is(futureDate));
        assertThat(dateTime.getHourOfDay(), Is.is(Integer.parseInt(futureHour)));
        assertThat(dateTime.getMinuteOfHour(), Is.is(Integer.parseInt(futureMinute)));
    }


    @Test
    public void shouldFlowAheadInTime() throws InterruptedException {
        LocalDate futureDate = LocalDate.now().plusDays(10);
        String futureHour = "10";
        String futureMinute = "9";
        flowingTimeMachine.update(futureDate.toString("yyyy-MM-dd"), futureHour, futureMinute);

        DateTime dateTime = flowingTimeMachine.now();
        Thread.sleep(3000l);
        DateTime dateTimeAfter5Seconds = flowingTimeMachine.now();

        int secondsBetweenDates = Seconds.secondsBetween(dateTime, dateTimeAfter5Seconds).getSeconds();
        assertThat(secondsBetweenDates, greaterThanOrEqualTo(3));
    }


}
