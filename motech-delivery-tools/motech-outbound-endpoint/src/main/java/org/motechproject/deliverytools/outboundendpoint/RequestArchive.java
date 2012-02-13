package org.motechproject.deliverytools.outboundendpoint;

import java.util.List;

public class RequestArchive {
    RollingList<Request> archive = new RollingList<Request>(500);

    public void archive(Request request) {
        archive.add(request);
    }

    public List<Request> getAll() {
        return archive.getList();
    }
}
