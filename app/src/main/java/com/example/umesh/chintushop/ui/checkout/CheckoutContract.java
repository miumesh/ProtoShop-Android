package com.example.umesh.chintushop.ui.checkout;

import com.example.umesh.chintushop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.umesh.chintushop.model.LineItem;
import com.example.umesh.chintushop.model.Transaction;

import java.util.List;

/**
 * Created by Umesh on 03-10-2017.
 */

public interface CheckoutContract {
    public interface View {
        void showLineItem(List<LineItem> items);

        void showEmptyText();

        void showCartTotals(double tax, double subTotal, double total);

        void showConfirmCheckout();

        void showConfirmClearCart();

        void hideText();

        void showMessage(String message);

    }

    public interface Actions {
        void loadLineItems();

        void onCheckoutButtonClicked();

        void onDeleteItemButtonClicked(LineItem item);

        void checkout();

        void onClearButtonClicked();

        void clearShoppingCart();

        void setPaymentType(String paymentType);

        void markAsPaid(boolean paid);

        void onItemQuantityChanged(LineItem item, int qty);

    }

    public interface Repository {
        List<LineItem> getAllLineItems();

        void saveTransaction(Transaction transaction, OnDatabaseOperationCompleteListener listener);

        void updateTransaction(Transaction transaction, OnDatabaseOperationCompleteListener listener);
    }
}
