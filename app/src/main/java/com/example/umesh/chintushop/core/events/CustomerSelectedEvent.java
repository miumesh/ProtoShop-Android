package com.example.umesh.chintushop.core.events;

import com.example.umesh.chintushop.model.Customer;

/**
 * Created by Umesh on 30-09-2017.
 */

public class CustomerSelectedEvent {

    private final Customer selectedCustomer;
    private final boolean clearCustomer;

    public CustomerSelectedEvent(Customer selectedCustomer, boolean clearCustomer) {
        this.selectedCustomer = selectedCustomer;
        this.clearCustomer = clearCustomer;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public boolean isClearCustomer() {
        return clearCustomer;
    }
}
