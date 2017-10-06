package com.example.umesh.chintushop.ui.productlist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.umesh.chintushop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.umesh.chintushop.data.DatabaseHelper;
import com.example.umesh.chintushop.model.Category;
import com.example.umesh.chintushop.model.Product;
import com.example.umesh.chintushop.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Umesh on 06-10-2017.
 */

public class ProductListSQLiteManager implements ProductListContract.Repository{

    private DatabaseHelper dbHelper;
    private final Context mContext;
    private SQLiteDatabase database;

    public ProductListSQLiteManager(Context context) {
        mContext = context;
        dbHelper = DatabaseHelper.newInstance(context);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public List<Product> getAllProducts() {
        //INITIAIZE AN EMPTY LIST OF PRODUCTS
        List<Product> products = new ArrayList<>();

        //sql command to select all product from database
        String selectQuery = "SELECT * FROM " + Constants.PRODUCT_TABLE;

        //Make sure database is nt null
        if (database != null){
            // get cursor for all products in database
            Cursor cursor = database.rawQuery(selectQuery,null);
            if (cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
                    //get each product in cursor
                    products.add(Product.getProductFromCursor(cursor));
                    cursor.moveToNext();

                }
            }



        }


        return products;
    }

    @Override
    public Product getProductById(long id) {
        //get cursor representing product
        Cursor cursor = database.rawQuery("SELECT * FROM " +
                Constants.PRODUCT_TABLE + " WHERE " + Constants.COLUMN_ID + " = '" + id + "'",null);
        Product product;

        if (cursor.moveToFirst()){
            product = Product.getProductFromCursor(cursor);
        }else {
            product =null;
        }

        return product;
    }

    @Override
    public void deleteProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void addProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public void updateProduct(Product product, OnDatabaseOperationCompleteListener listener) {

    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }
}
