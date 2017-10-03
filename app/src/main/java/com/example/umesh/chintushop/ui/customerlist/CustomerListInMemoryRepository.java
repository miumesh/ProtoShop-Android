package com.example.umesh.chintushop.ui.customerlist;

import com.example.umesh.chintushop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.umesh.chintushop.data.SampleCustomerData;
import com.example.umesh.chintushop.model.Customer;

import java.util.List;

/**
 * Created by Umesh on 03-10-2017.
 */

public class CustomerListInMemoryRepository implements CustomerListContract.Repository{
    @Override
    public List<Customer> getAllCustomers() {
        /*List<Customer> customers = */return SampleCustomerData.getCustomers();
        /*Log.d("Returned Customers :", "Customers " + customers.toString());
        return customers;*/

    }

    @Override
    public Customer getCustomerById(long id) {
        return null;
    }

    @Override
    public void onDeleteCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void addCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void updatedCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {

    }
}

