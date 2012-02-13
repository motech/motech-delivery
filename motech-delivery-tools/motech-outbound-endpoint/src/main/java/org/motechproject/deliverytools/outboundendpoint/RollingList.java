package org.motechproject.deliverytools.outboundendpoint;

import java.util.ArrayList;
import java.util.List;

public class RollingList<T> {
    private int size;
    private List<T> list = new ArrayList<T>();

    public RollingList(int size) {
        this.size = size;
    }

    public void add(T obj) {
        if (list.size() > size - 1) {
            list.remove(0);
        }
        list.add(obj);
    }

    public List<T> getList() {
        return list;
    }
}
