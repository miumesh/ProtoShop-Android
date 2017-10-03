package com.example.umesh.chintushop.core.events;

import com.example.umesh.chintushop.model.LineItem;

import java.util.List;

/**
 * Created by Umesh on 03-10-2017.
 */

public class UpdateToolbarEvent {
    private final List<LineItem> mLineItems;


    public UpdateToolbarEvent(List<LineItem> lineItems) {
        mLineItems = lineItems;
    }

    public List<LineItem> getmLineItems() {
        return mLineItems;
    }
}
