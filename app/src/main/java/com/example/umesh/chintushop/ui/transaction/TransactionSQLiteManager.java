package com.example.umesh.chintushop.ui.transaction;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.umesh.chintushop.common.ShoppingCart;
import com.example.umesh.chintushop.core.ChintuShopApplication;
import com.example.umesh.chintushop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.umesh.chintushop.data.DatabaseHelper;
import com.example.umesh.chintushop.model.LineItem;
import com.example.umesh.chintushop.model.SalesTransaction;
import com.example.umesh.chintushop.util.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Umesh on 09-10-2017.
 */

public class TransactionSQLiteManager implements TransactionContract.Repository{
    private DatabaseHelper dbHelper;
    private final Context mContext;
    private SQLiteDatabase database;
    private boolean DEBUG = false;
    private final String LOG_TAG = TransactionSQLiteManager.class.getSimpleName();
    @Inject ShoppingCart mCart;

    public TransactionSQLiteManager(Context context) {
        mContext = context;
        dbHelper = DatabaseHelper.newInstance(context);
        database = dbHelper.getWritableDatabase();
        ChintuShopApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public List<SalesTransaction> getAllTransactions() {
       List<SalesTransaction> transactions = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Constants.TRANSACTION_TABLE;
        if (database != null){
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    transactions.add(SalesTransaction.getSalesTransactionFromCursor(cursor));
                    cursor.moveToNext();
                }
            }
        }
        return transactions;
    }

    @Override
    public SalesTransaction getTransactionById(long id) {
        //Get the cursor representing the SalesTransaction
        Cursor cursor = database.rawQuery("SELECT * FROM " + Constants.TRANSACTION_TABLE + " WHERE " +
                Constants.COLUMN_ID + " = '" + id + "'", null);

        //Create a variable of data type SalesTransaction
        SalesTransaction transaction;


        if (cursor.moveToFirst()) {
            transaction = SalesTransaction.getSalesTransactionFromCursor(cursor);
        } else {
            transaction = null;
        }
        cursor.close();
        //Return result: either a valid transaction or null
        return transaction;
    }

    @Override
    public void deleteTransaction(long id, OnDatabaseOperationCompleteListener listener) {
        // Ensure database exists.
        if (database != null) {
            int result = database.delete(Constants.TRANSACTION_TABLE, Constants.COLUMN_ID + " = " + id, null);

            if (result > 0) {
                listener.onDatabaseOperationSucceded("Transaction Deleted");
            } else {
                listener.onDatabaseOperationFailed("Unable to Delete Transaction");
            }

        }


    }

    @Override
    public List<LineItem> getAllLineItems() {
        return mCart.getShoppingCart();
    }

    @Override
    public void saveTransaction(SalesTransaction transaction, OnDatabaseOperationCompleteListener listener) {
        //ensure that the database exists
        /*long result = -1;*/
        if (database != null) {
            //prepare the transaction information that will be saved to the database
            ContentValues values = new ContentValues();
            values.put(Constants.COLUMN_CUSTOMER_ID, transaction.getCustomerId());
            values.put(Constants.COLUMN_LINE_ITEMS, transaction.getJasonLineItems());
            values.put(Constants.COLUMN_PAYMENT_STATUS, transaction.isPaid());
            values.put(Constants.COLUMN_PAYMENT_TYPE, transaction.getPaymentType());
            values.put(Constants.COLUMN_TAX_AMOUNT, transaction.getTaxAmount());
            values.put(Constants.COLUMN_SUB_TOTAL_AMOUNT, transaction.getSubTotalAmount());
            values.put(Constants.COLUMN_TOTAL_AMOUNT, transaction.getTotalAmount());
            values.put(Constants.COLUMN_DATE_CREATED, System.currentTimeMillis());
            values.put(Constants.COLUMN_LAST_UPDATED, System.currentTimeMillis());
            try {
                /*result = */database.insertOrThrow(Constants.TRANSACTION_TABLE, null, values);
                listener.onDatabaseOperationSucceded("Transaction saved");
                if (DEBUG){
                    Log.d(LOG_TAG, "Transaction saved");
                }
            } catch (SQLException e) {
                listener.onDatabaseOperationFailed(e.getCause() + " " + e.getMessage());
            }
        }
        /*return result;*/

    }

    @Override
    public void updateTransaction(SalesTransaction transaction, OnDatabaseOperationCompleteListener listener) {
        //ensure that the database exists
        if (database != null) {
            //prepare the transaction information that will be saved to the database
            ContentValues values = new ContentValues();
            values.put(Constants.COLUMN_CUSTOMER_ID, transaction.getCustomerId());
            values.put(Constants.COLUMN_PAYMENT_STATUS, convertBooleanToInt(transaction.isPaid()));
            values.put(Constants.COLUMN_PAYMENT_TYPE, transaction.getPaymentType());
            values.put(Constants.COLUMN_TAX_AMOUNT, transaction.getTaxAmount());
            values.put(Constants.COLUMN_SUB_TOTAL_AMOUNT, transaction.getSubTotalAmount());
            values.put(Constants.COLUMN_TOTAL_AMOUNT, transaction.getTotalAmount());
            values.put(Constants.COLUMN_DATE_CREATED, System.currentTimeMillis());
            values.put(Constants.COLUMN_LAST_UPDATED, System.currentTimeMillis());

            //Now update the this row with the information supplied
            int result =  database.update(Constants.TRANSACTION_TABLE, values,
                    Constants.COLUMN_ID + " = " + transaction.getId(), null);
            if (result == 1){
                listener.onDatabaseOperationSucceded("Transaction Updated");
            }else{
                listener.onDatabaseOperationFailed("Transaction Update Failed");
            }
        }

    }

    public int convertBooleanToInt(Boolean boolValue) {
        if (boolValue) {
            return 1;
        }else{
            return 0;
        }

    }
}
