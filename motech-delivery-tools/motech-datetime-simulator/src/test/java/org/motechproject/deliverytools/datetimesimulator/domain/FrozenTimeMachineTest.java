package org.motechproject.deliverytools.datetimesimulator.domain;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.util.datetime.DateTimeSource;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FrozenTimeMachineTest {
    private FrozenTimeMachine frozenTimeMachine;

    @Before
    public void setUp() {
        DateTimeSource dateTimeSource = mock(DateTimeSource.class);
        when(dateTimeSource.timeZone()).thenReturn(DateTimeZone.UTC);
        frozenTimeMachine = new FrozenTimeMachine(dateTimeSource);
    }

    @Test
    public void whenDateAndTimeIsNotSetShouldBeToday() {
        assertNotNull(frozenTimeMachine.today());
        assertNotNull(frozenTimeMachine.now());
    }

    @Test
    public void whenOnlyDateIsSetup() {
        frozenTimeMachine.update("2011-10-17", "", null);
        assertDate();
    }

    private void assertDate() {
        //Joda should have used interface for getYear/month etc methods
        LocalDate today = frozenTimeMachine.today();
        assertNotNull(today);
        assertEquals(2011, today.getYear());
        assertEquals(10, today.getMonthOfYear());
        assertEquals(17, today.getDayOfMonth());

        DateTime now = frozenTimeMachine.now();
        assertNotNull(now);
        assertEquals(2011, now.getYear());
        assertEquals(10, now.getMonthOfYear());
        assertEquals(17, now.getDayOfMonth());
    }

    @Test
    public void whenBothDateAndTimeIsSet() {
        frozenTimeMachine.update("2011-10-17", "14", "20");
        assertDate();
        DateTime now = frozenTimeMachine.now();
        assertEquals(14, now.getHourOfDay());
        assertEquals(20, now.getMinuteOfHour());
    }

    @Test
    public void useTheLastSetValues() {
        frozenTimeMachine.update("2011-10-17", "14", "20");
        frozenTimeMachine.update(null, null, null);
        assertDate();
        DateTime now = frozenTimeMachine.now();
        assertEquals(14, now.getHourOfDay());
        assertEquals(20, now.getMinuteOfHour());
    }
}
