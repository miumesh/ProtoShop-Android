package com.example.umesh.chintushop.core.listeners;

import com.example.umesh.chintushop.model.Product;

/**
 * Created by Umesh on 26-09-2017.
 */

public interface OnProductSelectedListener {

    void onSelectedProduct(Product selectedProduct);
    void onLongClickProduct(Product clickedProduct);
}
