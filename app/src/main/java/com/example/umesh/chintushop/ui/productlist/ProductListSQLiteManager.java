package com.example.umesh.chintushop.ui.productlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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
        if (database != null){
            int result = database.delete(Constants.PRODUCT_TABLE,Constants.COLUMN_ID + " = " +product.getId(),null);
             if (result > 0){
                 listener.onDatabaseOperationSucceded("Product Deleted");
             }else {
                 listener.onDatabaseOperationFailed("Unable to delete Product");
             }
        }

    }

    @Override
    public void addProduct(Product product, OnDatabaseOperationCompleteListener listener) {
        // prepare the information that will be saved to database
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_NAME,product.getProductName());
        values.put(Constants.COLUMN_DESCRIPTION,product.getDescription());
        values.put(Constants.COLUMN_PRICE,product.getSalePrice());
        values.put(Constants.COLUMN_PURCHASE_PRICE,product.getPurchasePrice());
        values.put(Constants.COLUMN_IMAGE_PATH,product.getImagePath());
        values.put(Constants.COLUMN_CATEGORY_NAME,product.getCategoryName());
        values.put(Constants.COLUMN_DATE_CREATED,System.currentTimeMillis());
        values.put(Constants.COLUMN_LAST_UPDATED,System.currentTimeMillis());
        values.put(Constants.COLUMN_CATEGORY_ID, createOrGetCategoryId(product.getCategoryName(),listener));

        try {
            database.insertOrThrow(Constants.RETAILER_TABLE, null,values);
            listener.onDatabaseOperationSucceded("Product Added");
        } catch (SQLException e) {
            listener.onDatabaseOperationFailed(e.getMessage());
        }


    }

    private long createOrGetCategoryId(String categoryName, OnDatabaseOperationCompleteListener listener) {
        Category foundCategory = getCategory(categoryName);
        if (foundCategory == null){
            foundCategory = addCategory(categoryName, listener);
        }
        return foundCategory.getId();
    }

    private Category addCategory(final String categoryName, OnDatabaseOperationCompleteListener listener) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        saveCategory(category, listener);
        return category;
    }

    private void saveCategory(Category category, OnDatabaseOperationCompleteListener listener) {
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_NAME, category.getCategoryName());
        try {
            database.insertOrThrow(Constants.CATEGORY_TABLE, null, values);
        }catch (SQLException e){
            listener.onDatabaseOperationFailed("Unable to save Category");
        }
    }


    private Category getCategory(String categoryName) {
        Category category = null;
        Cursor cursor = database.rawQuery("SELECT * FROM " + Constants.CATEGORY_TABLE + " " +
        "WHERE " + Constants.COLUMN_NAME + " = " + categoryName + "'", null);
        if (cursor.moveToFirst()){
            category = Category.fromCursor(cursor);
        }
        return category;
    }

    @Override
    public void updateProduct(Product product, OnDatabaseOperationCompleteListener listener) {
        // prepare the information that will be saved to database
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_NAME,product.getProductName());
        values.put(Constants.COLUMN_DESCRIPTION,product.getDescription());
        values.put(Constants.COLUMN_PRICE,product.getSalePrice());
        values.put(Constants.COLUMN_PURCHASE_PRICE,product.getPurchasePrice());
        values.put(Constants.COLUMN_IMAGE_PATH,product.getImagePath());
        values.put(Constants.COLUMN_CATEGORY_NAME,product.getCategoryName());
        values.put(Constants.COLUMN_DATE_CREATED,System.currentTimeMillis());
        values.put(Constants.COLUMN_LAST_UPDATED,System.currentTimeMillis());

        int result = database.update(Constants.PRODUCT_TABLE, values,
                Constants.COLUMN_ID + " = " + product.getId(),null);
        if (result == 1){
            listener.onDatabaseOperationSucceded("Product Updated");
        }else {
            listener.onDatabaseOperationFailed("Product update failed");
        }


    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        //command to select all categories
        String selectQuery = "SELECT * FROM " + Constants.CATEGORY_TABLE;

        //get a cursor for all categories in the database
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor != null){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    categories.add(Category.fromCursor(cursor));
                    cursor.moveToNext();
                }
                cursor.close();
            }
        return categories;
    }
}
