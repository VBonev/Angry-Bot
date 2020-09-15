package com.valio.papoicheta;

import java.util.ArrayList;

/**
 * Created by inologica12 on 4/3/2014.
 */
public class LimitedArrayList<T> extends ArrayList<T> {
    public static int limit = 0;

    @Override
    public boolean add(T input) {
        if (getLimit() > 0) {
            return super.add(input);
        } else {
            return false;
        }
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }
}