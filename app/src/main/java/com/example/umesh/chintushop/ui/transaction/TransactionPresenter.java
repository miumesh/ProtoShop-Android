package com.example.umesh.chintushop.ui.transaction;

import com.example.umesh.chintushop.core.ChintuShopApplication;
import com.example.umesh.chintushop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.umesh.chintushop.model.Customer;
import com.example.umesh.chintushop.model.Transaction;
import com.example.umesh.chintushop.ui.customerlist.CustomerListContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Umesh on 03-10-2017.
 */

public class TransactionPresenter implements TransactionContract.Actions, OnDatabaseOperationCompleteListener {
    private final TransactionContract.View mView;
    @Inject
    TransactionContract.Repository mRepository;
    @Inject CustomerListContract.Repository mCustomerRepository;

    public TransactionPresenter(TransactionContract.View view) {
        mView = view;
        ChintuShopApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void loadTransactions() {
        List<Transaction> transactions = mRepository.getAllTransactions();
        if (transactions != null && transactions.size() > 0){
            mView.hideEmptyText();
            mView.showTransaction(transactions);
        } else {
            mView.showEmptyText();
        }
    }

    @Override
    public void onDeleteItemButtonClicked(Transaction transaction) {
        mView.showConfirmDeletePrompty(transaction);
    }

    @Override
    public void editTransaction(Transaction transaction) {
        mRepository.updateTransaction(transaction, this);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        mRepository.deleteTransaction(transaction.getId(), this);
    }

    @Override
    public Customer getCustomerById(long id) {
        return mCustomerRepository.getCustomerById(id);
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

