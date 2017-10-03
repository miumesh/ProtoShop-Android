package com.example.umesh.chintushop.common;

import com.example.umesh.chintushop.model.Customer;
import com.example.umesh.chintushop.model.LineItem;

import java.util.List;

/**
 * Created by Umesh on 03-10-2017.
 */

public interface ShoppingCartContract {

    void addItemToCart(LineItem item);
    void removeItemFromCart(LineItem item);
    void clearAllItemsFromCart();
    List<LineItem> getShoppingCart();
    void setCustomer(Customer customer);
    void updateItemQty(LineItem item, int qty);
    Customer getSelectedCustomer();
    void completeCheckout();
}
