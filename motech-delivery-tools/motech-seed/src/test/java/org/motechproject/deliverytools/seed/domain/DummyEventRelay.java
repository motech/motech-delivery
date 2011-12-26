package org.motechproject.deliverytools.seed.domain;

import org.motechproject.event.EventRelay;
import org.motechproject.model.MotechEvent;

public class DummyEventRelay implements EventRelay{
    @Override
    public void sendEventMessage(MotechEvent event) {
        //do nothing
    }
}
