package org.motechproject.deliverytools.jobhandlerinvoker;

import org.motechproject.model.MotechEvent;
import org.motechproject.server.event.annotations.MotechListener;

public class HandlerForJobHandlerInvokerTests {
    private MotechEvent motechEvent;
    public static final String SAMPLE_SUBJECT = "SampleSubject";

    public MotechEvent motechEvent() {
        return motechEvent;
    }

    @MotechListener(subjects = SAMPLE_SUBJECT)
    public void handle(MotechEvent motechEvent) {
        this.motechEvent = motechEvent;
    }
}
