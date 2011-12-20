package org.motechproject.deliverytools.outboundendpoint;

public class GetLastReceivedRequest {
    private int waitForSeconds;

    public void setWaitForSeconds(int waitForSeconds) {
        this.waitForSeconds = waitForSeconds;
    }

    public int waitForSeconds() {
        return waitForSeconds;
    }
}
