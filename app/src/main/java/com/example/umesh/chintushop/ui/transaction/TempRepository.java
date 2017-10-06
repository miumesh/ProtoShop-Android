package com.example.umesh.chintushop.ui.transaction;

import com.example.umesh.chintushop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.umesh.chintushop.model.SalesTransaction;

import java.util.List;

/**
 * Created by Umesh on 03-10-2017.
 */

public class TempRepository implements TransactionContract.Repository{


    @Override
    public List<SalesTransaction> getAllTransactions() {
        return null;
    }

    @Override
    public void updateTransaction(SalesTransaction transaction, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public SalesTransaction getTransactionById(long id) {
        return null;
    }

    @Override
    public void deleteTransaction(long id, OnDatabaseOperationCompleteListener listener) {

    }
}
