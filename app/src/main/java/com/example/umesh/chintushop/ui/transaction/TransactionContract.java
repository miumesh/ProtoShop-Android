package com.example.umesh.chintushop.ui.transaction;

import com.example.umesh.chintushop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.umesh.chintushop.model.Customer;
import com.example.umesh.chintushop.model.LineItem;
import com.example.umesh.chintushop.model.SalesTransaction;

import java.util.List;

/**
 * Created by Umesh on 03-10-2017.
 */

public interface TransactionContract {
    public interface View {
        void showTransaction(List<SalesTransaction> transactions);

        void showEmptyText();

        void hideEmptyText();

        void showConfirmDeletePrompty(SalesTransaction transaction);

        void showMessage(String message);

    }

    public interface Actions {
        void loadTransactions();

        void onDeleteItemButtonClicked(SalesTransaction transaction);

        void editTransaction(SalesTransaction transaction);

        void deleteTransaction(SalesTransaction transaction);

        Customer getCustomerById(long id);
    }


    public interface Repository {
        List<SalesTransaction> getAllTransactions();

        SalesTransaction getTransactionById(long id);

        void deleteTransaction(long id, OnDatabaseOperationCompleteListener listener);

        List<LineItem> getAllLineItems();

        void saveTransaction(SalesTransaction transaction, OnDatabaseOperationCompleteListener listener);

        void updateTransaction(SalesTransaction transaction, OnDatabaseOperationCompleteListener listener);
    }
}

