package com.example.umesh.chintushop.ui.customerlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.umesh.chintushop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.umesh.chintushop.data.DatabaseHelper;
import com.example.umesh.chintushop.model.Customer;
import com.example.umesh.chintushop.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Umesh on 08-10-2017.
 */

public class CustomerListSQLiteManager implements CustomerListContract.Repository{
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    private Context mContext;

    public CustomerListSQLiteManager(Context context) {
        mContext = context;
        databaseHelper = DatabaseHelper.newInstance(context);
        database = databaseHelper.getWritableDatabase();

    }

    @Override
    public List<Customer> getAllCustomers() {
        //INITIALIZE AN EMPTY LIST OF PRODUCTS
        List<Customer> customers = new ArrayList<>();

        //sql command to select all product from database
        String selectQuery = "SELECT * FROM " + Constants.CUSTOMER_TABLE;

        //Make sure database is nt null
        if (database != null){
            // get cursor for all customers in database
            Cursor cursor = database.rawQuery(selectQuery,null);
            if (cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    //get each product in cursor
                    customers.add(Customer.getCustomerFromCursor(cursor));
                    cursor.moveToNext();
                }
            }
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(long id) {
        //get cursor representing customer
        Cursor cursor = database.rawQuery("SELECT * FROM" +
                Constants.CUSTOMER_TABLE + " WHERE " + Constants.COLUMN_ID + " = '" + id + "'",null);
        Customer customer;
        if (cursor.moveToFirst()){
            customer = Customer.getCustomerFromCursor(cursor);
        }else {
            customer = null;
        }
        return customer;
    }

    @Override
    public void onDeleteCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {

        if (database != null){
            int result = database.delete(Constants.PRODUCT_TABLE,
                    Constants.COLUMN_ID + " = " + customer.getId(),null);
            if (result > 0){
                    listener.onDatabaseOperationSucceded("Product Deleted");
                }else {
                listener.onDatabaseOperationFailed("Unable to delete Customer");
            }
        }
    }

    @Override
    public void addCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {
        if (database != null){
            ContentValues values = new ContentValues();
            values.put(Constants.COLUMN_NAME, customer.getCustomerName());
            values.put(Constants.COLUMN_EMAIL, customer.getEmailAddess());
            values.put(Constants.COLUMN_PHONE, customer.getPhoneNumber());
            values.put(Constants.COLUMN_IMAGE_PATH, customer.getProfileImagePath());
            values.put(Constants.COLUMN_STREET1, customer.getStreetAddress());
            values.put(Constants.COLUMN_STREET2, customer.getStreetAddress2());
            values.put(Constants.COLUMN_CITY, customer.getCity());
            values.put(Constants.COLUMN_STATE, customer.getState());
            values.put(Constants.COLUMN_ZIP, customer.getPostalCode());
            values.put(Constants.COLUMN_NOTE, customer.getNote());
            values.put(Constants.COLUMN_DATE_CREATED, System.currentTimeMillis());
            values.put(Constants.COLUMN_LAST_UPDATED, System.currentTimeMillis());

            try {
                database.insertOrThrow(Constants.CUSTOMER_TABLE, null, values);
                listener.onDatabaseOperationSucceded("Customer Added");
            } catch (SQLException e) {
                listener.onDatabaseOperationFailed(e.getMessage());
            }
        }
    }

    @Override
    public void updatedCustomer(Customer customer, OnDatabaseOperationCompleteListener listener) {
        if (database != null){
            ContentValues values = new ContentValues();
            values.put(Constants.COLUMN_NAME, customer.getCustomerName());
            values.put(Constants.COLUMN_EMAIL, customer.getEmailAddess());
            values.put(Constants.COLUMN_PHONE, customer.getPhoneNumber());
            values.put(Constants.COLUMN_IMAGE_PATH, customer.getProfileImagePath());
            values.put(Constants.COLUMN_STREET1, customer.getStreetAddress());
            values.put(Constants.COLUMN_STREET2, customer.getStreetAddress2());
            values.put(Constants.COLUMN_CITY, customer.getCity());
            values.put(Constants.COLUMN_STATE, customer.getState());
            values.put(Constants.COLUMN_ZIP, customer.getPostalCode());
            values.put(Constants.COLUMN_NOTE, customer.getNote());
            values.put(Constants.COLUMN_DATE_CREATED, System.currentTimeMillis());

            int result = database.update(Constants.CUSTOMER_TABLE, values,
                    Constants.COLUMN_ID + " = " + customer.getId(),null);
            if (result == 1){
                listener.onDatabaseOperationSucceded("Customer Updated");
            }else {
                listener.onDatabaseOperationFailed("Customer update failed");
            }

        }

    }
}
