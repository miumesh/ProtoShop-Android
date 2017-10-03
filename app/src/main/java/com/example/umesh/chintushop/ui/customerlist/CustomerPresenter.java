package com.example.umesh.chintushop.ui.customerlist;

import android.util.Log;

import com.example.umesh.chintushop.common.ShoppingCart;
import com.example.umesh.chintushop.core.ChintuShopApplication;
import com.example.umesh.chintushop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.umesh.chintushop.model.Customer;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Umesh on 03-10-2017.
 */

public class CustomerPresenter implements CustomerListContract.Actions, OnDatabaseOperationCompleteListener {
    private final CustomerListContract.View mView;
    @Inject CustomerListContract.Repository mRepository;
    @Inject ShoppingCart mCart;
    @Inject Bus mBus;

    public final static String LOG_CAT = CustomerPresenter.class.getSimpleName();

    public CustomerPresenter(CustomerListContract.View view) {
        mView = view;
        ChintuShopApplication.getInstance().getAppComponent().inject(this);
    }


    @Override
    public void loadCustomer() {
        List<Customer> availableCustomers = mRepository.getAllCustomers();
        if (availableCustomers != null && availableCustomers.size() > 0){
            mView.hideEmptyText();;
            mView.showCustomers(availableCustomers);
            Log.d(LOG_CAT, "Customer List: " + availableCustomers);
        } else{
            mView.showEmptyText();
        }
    }

    @Override
    public Customer getCustomer(long id) {
        return mRepository.getCustomerById(id);
    }

    @Override
    public void onCustomerSelected(Customer customer) {
        mCart.setCustomer(customer);
    }

    @Override
    public void onAddCustomerButtonClicked() {
        mView.showAddCustomerForm();
    }

    @Override
    public void addCustomer(Customer customer) {
        mRepository.addCustomer(customer, this);
    }

    @Override
    public void onDeleteCustomerButtonClicked(Customer customer) {
        mView.showDeleteCustomerPrompt(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        mRepository.onDeleteCustomer(customer, this);
    }

    @Override
    public void onEditCustomerButtonClicked(Customer customer) {
        mView.showEditCustomerForm(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        mRepository.updatedCustomer(customer, this);
    }

    @Override
    public void onDatabaseOperationFailed(String error) {
        mView.showMessage("Error: " + error);
    }

    @Override
    public void onDatabaseOperationSucceded(String message) {
        mView.showMessage(message);
    }
}
