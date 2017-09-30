package com.example.umesh.chintushop.core.events;

import com.example.umesh.chintushop.model.LineItem;

import java.util.List;

/**
 * Created by Umesh on 30-09-2017.
 */

public class UpdateToolbarEvent {

    private final List<LineItem> mLineItems;

    public UpdateToolbarEvent(List<LineItem> mLineItems) {
        this.mLineItems = mLineItems;
    }

    public List<LineItem> getmLineItems() {
        return mLineItems;
    }
}
