package org.motechproject.deliverytools.datetimesimulator.web;

import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.util.DateTimeSourceUtil;
import org.motechproject.util.DateUtil;
import org.motechproject.util.datetime.DateTimeConfiguration;
import org.motechproject.util.datetime.ExternalDateTimeSource;

import javax.servlet.http.HttpServletResponse;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DateTimeControllerTest {
    private DateTimeController controller;
    private HttpServletResponse servletResponse;

    @Before
    public void setUp() {
        controller = new DateTimeController();
        servletResponse = mock(HttpServletResponse.class);
    }

    @Test
    public void invalidInput() {
        controller.update("afsdff", null, null, servletResponse);
        verify(servletResponse).setStatus(500);
    }

    @Test
    public void todayIsNotSetWhenExternalDateTimeSourceIsNotSet() {
        controller.update("2011-10-17", null, null, servletResponse);
        verifyZeroInteractions(servletResponse);
    }

    @Test
    public void todayIsSet() {
        DateTimeConfiguration dateTimeConfiguration = mock(DateTimeConfiguration.class);
        when(dateTimeConfiguration.timeZone()).thenReturn(DateTimeZone.UTC);
        DateTimeSourceUtil.SourceInstance = new ExternalDateTimeSource(dateTimeConfiguration);

        controller.update("2011-10-17", "", "", servletResponse);
        assertEquals(10, DateUtil.today().getMonthOfYear());
        assertEquals(17, DateUtil.now().getDayOfMonth());
        verifyZeroInteractions(servletResponse);
    }

    @Test
    public void setTimeWithoutDate() {
        DateTimeConfiguration dateTimeConfiguration = mock(DateTimeConfiguration.class);
        when(dateTimeConfiguration.timeZone()).thenReturn(DateTimeZone.UTC);
        DateTimeSourceUtil.SourceInstance = new ExternalDateTimeSource(dateTimeConfiguration);

        controller.update("2011-10-17", "14", "30", servletResponse);
        assertEquals(10, DateUtil.today().getMonthOfYear());
        assertEquals(30, DateUtil.now().getMinuteOfHour());
        verifyZeroInteractions(servletResponse);
    }
}
