package com.example.umesh.chintushop.core.listeners;

import com.example.umesh.chintushop.model.Customer;

/**
 * Created by Umesh on 27-09-2017.
 */

public interface OnCustomerSelectedListener {

    void onSelectCustomer(Customer customer);
    void onLongClickCusomer(Customer customer);
}
