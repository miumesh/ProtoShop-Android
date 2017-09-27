package com.example.umesh.chintushop.core.listeners;

import com.example.umesh.chintushop.model.LineItem;

/**
 * Created by Umesh on 27-09-2017.
 */

public interface CartActionsListener {

    void onItemDeleted(LineItem item);
    void  onItemQtyChange(LineItem item,int qty);


}
