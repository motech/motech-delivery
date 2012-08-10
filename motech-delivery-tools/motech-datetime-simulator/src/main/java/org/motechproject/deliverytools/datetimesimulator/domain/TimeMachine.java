package org.motechproject.deliverytools.datetimesimulator.domain;

import org.motechproject.util.datetime.DateTimeSource;

public interface TimeMachine extends DateTimeSource {
    void update(String date, String hour, String minute);
}
