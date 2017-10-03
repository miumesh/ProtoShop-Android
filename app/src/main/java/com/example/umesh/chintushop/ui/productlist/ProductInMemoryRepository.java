package com.example.umesh.chintushop.ui.productlist;

import com.example.umesh.chintushop.core.listeners.OnDatabaseOperationCompleteListener;
import com.example.umesh.chintushop.data.SampleProductData;
import com.example.umesh.chintushop.model.Category;
import com.example.umesh.chintushop.model.Product;

import java.util.List;

/**
 * Created by Umesh on 03-10-2017.
 */

public class ProductInMemoryRepository implements ProductListContract.Repository {
    public ProductInMemoryRepository(){}

    @Override
    public List<Product> getAllProducts() {
        return SampleProductData.getSampleProducts();
    }

    @Override
    public Product getProductById(long id) {
        return null;
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

