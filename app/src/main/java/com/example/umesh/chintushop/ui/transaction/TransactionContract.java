package com.example.umesh.chintushop.ui.transaction;

import com.example.umesh.chintushop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.umesh.chintushop.model.Customer;
import com.example.umesh.chintushop.model.Transaction;

import java.util.List;

/**
 * Created by Umesh on 03-10-2017.
 */

public interface TransactionContract {
    public interface View {
        void showTransaction(List<Transaction> transactions);

        void showEmptyText();

        void hideEmptyText();

        void showConfirmDeletePrompty(Transaction transaction);

        void showMessage(String message);

    }

    public interface Actions {
        void loadTransactions();

        void onDeleteItemButtonClicked(Transaction transaction);

        void editTransaction(Transaction transaction);

        void deleteTransaction(Transaction transaction);

        Customer getCustomerById(long id);
    }


    public interface Repository {
        List<Transaction> getAllTransactions();

        void updateTransaction(Transaction transaction, OnDatabaseOperationCompleteListener listener);

        Transaction getTransactionById(long id);

        void deleteTransaction(long id, OnDatabaseOperationCompleteListener listener);
    }
}

