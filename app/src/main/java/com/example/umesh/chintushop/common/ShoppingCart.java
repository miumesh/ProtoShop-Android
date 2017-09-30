package com.example.umesh.chintushop.common;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.umesh.chintushop.core.ChintuShopApplication;
import com.example.umesh.chintushop.core.events.CustomerSelectedEvent;
import com.example.umesh.chintushop.core.events.UpdateToolbarEvent;
import com.example.umesh.chintushop.model.Customer;
import com.example.umesh.chintushop.model.LineItem;
import com.example.umesh.chintushop.util.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Umesh on 21-09-2017.
 */

public class ShoppingCart implements ShoppingCartContract {

    private List<LineItem> shoppingCart;
    private Customer selectedCustomer;
    private final SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private final static String LOG_TAG = ShoppingCart.class.getSimpleName();
    private static boolean DEBUG = true; //If dont want to log set false


    @Inject Bus mBus;


    public ShoppingCart(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        ChintuShopApplication.getInstance().getAppComponent().inject(this);
        initShoppingCart();
    }

    private void initShoppingCart() {
        shoppingCart = new ArrayList<>();
        selectedCustomer = new Customer();
        Gson gson = new Gson();

        //check if there are items saved to shared preference
        if (sharedPreferences.getBoolean(Constants.OPEN_CART_EXISTS, false)) {

            String serializedCartItems = sharedPreferences.getString(Constants.SERIALIZED_CART_ITEMS, "");
            if (DEBUG) {
                Log.d(LOG_TAG, "Serialized Cart Items: " + serializedCartItems);

            }
            String serializedCustomer = sharedPreferences.getString(Constants.SERIALIZED_CART_CUSTOMER, "");
            if (!serializedCartItems.equals("")) {
                shoppingCart = gson.<ArrayList<LineItem>>fromJson(serializedCartItems,
                        new TypeToken<ArrayList<LineItem>>() {
                        }.getType()); //To inflate serialized string to list of line items
            }
            if (!serializedCustomer.equals("")) {
                selectedCustomer = gson.fromJson(serializedCustomer, Customer.class);
            }
        }
        populateToolbar();
    }

    public void saveCartToPreference(){
        if(shoppingCart != null){
            Gson gson = new Gson();
            String serializedItems = gson.toJson(shoppingCart);
            if(DEBUG){
                Log.d(LOG_TAG,"Saving Serialized Cart Items: " + serializedItems);
            }
            String serializedCustomer = gson.toJson(selectedCustomer);
            if(DEBUG){
                Log.d(LOG_TAG,"Saving Serialized Customer Items: " + serializedCustomer);
            }

            editor.putString(Constants.SERIALIZED_CART_ITEMS, serializedItems).commit();
            editor.putString(Constants.SERIALIZED_CART_CUSTOMER, serializedCustomer).commit();
            editor.putBoolean(Constants.OPEN_CART_EXISTS, true).commit();
        }
    }


    @Override
    public void addItemToCart(LineItem item) {
        boolean isItemInCart = false;
        int itemPosition = 0;

        for (LineItem tempItem: shoppingCart){
            if(tempItem.getId() == item.getId()){
                itemPosition = shoppingCart.indexOf(tempItem);
                isItemInCart = true;
                LineItem selectedItem = shoppingCart.get(itemPosition);
                selectedItem.setQuantity(tempItem.getQuantity() + item.getQuantity());
                shoppingCart.set(itemPosition, selectedItem);
                break;
            }
        }

        if (!isItemInCart){
            shoppingCart.add(item);
        }

    }

    @Override
    public void removeItemFromCart(LineItem item) {
        shoppingCart.remove(item);
        if(shoppingCart.size() == 0){
            mBus.post(new CustomerSelectedEvent(new Customer(),true));
        }
        populateToolbar();

    }

    @Override
    public void clearAllItemsFromCart() {
        shoppingCart.clear();
        selectedCustomer = null;
        editor.putString(Constants.SERIALIZED_CART_ITEMS,"").commit();
        editor.putString(Constants.SERIALIZED_CART_CUSTOMER,"").commit();
        editor.putBoolean(Constants.OPEN_CART_EXISTS,false).commit();
        populateToolbar();
        mBus.post(new CustomerSelectedEvent(new Customer(),true));
    }

    @Override
    public List<LineItem> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void setCustomer(Customer customer) {
        selectedCustomer = customer;
        mBus.post(new CustomerSelectedEvent(customer,false));
    }

    @Override
    public void updateItemQty(LineItem item, int qty) {

        boolean itemAlreadyInCart = false;
        int itemPosition = 0;

        for (LineItem tempItem: shoppingCart){
            if(tempItem.getId() == item.getId()){
                itemPosition = shoppingCart.indexOf(tempItem);
                LineItem itemInCart = shoppingCart.get(itemPosition);
                itemInCart.setQuantity(qty);
                shoppingCart.set(itemPosition, itemInCart);
                itemAlreadyInCart = true;
                break;
            }
        }

        if (!itemAlreadyInCart){
            item.setQuantity(qty);
            shoppingCart.add(item);
        }
        populateToolbar();

    }

    private void populateToolbar(){
        mBus.post(new UpdateToolbarEvent(shoppingCart));
    }

    @Override
    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    @Override
    public void completeCheckout() {
        shoppingCart.clear();
        populateToolbar();
        mBus.post(new CustomerSelectedEvent(new Customer(),true));

    }
}
