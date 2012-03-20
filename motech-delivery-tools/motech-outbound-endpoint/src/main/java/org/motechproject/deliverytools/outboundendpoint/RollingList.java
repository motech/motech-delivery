package org.motechproject.deliverytools.outboundendpoint;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.synchronizedList;

public class RollingList<T> {
    private int size;
    private final List<T> list = synchronizedList(new ArrayList<T>());

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

    public void clearAll() {
        list.clear();
    }
}
