package org.motechproject.deliverytools.jobhandlerinvoker;

public class TriggerListRequest {
    private int number;

    public void setNumber(int number) {
        this.number = number;
    }

    public int number() {
        return number == 0 ? 3 : number;
    }
}
