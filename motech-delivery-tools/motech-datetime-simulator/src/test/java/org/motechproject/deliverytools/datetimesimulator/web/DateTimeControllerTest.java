package org.motechproject.deliverytools.datetimesimulator.web;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

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
}
