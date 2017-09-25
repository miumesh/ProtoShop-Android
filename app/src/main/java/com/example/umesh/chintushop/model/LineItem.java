package com.example.umesh.chintushop.model;

/**
 * Created by Umesh on 22-09-2017.
 */

public class LineItem extends Product{
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private double getSumPrice(){

        return getSalePrice() * quantity;
    }
}
