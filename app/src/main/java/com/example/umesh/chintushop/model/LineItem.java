package com.example.umesh.chintushop.model;

/**
 * Created by Umesh on 22-09-2017.
 */

public class LineItem extends Product{
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public LineItem(Product product,int quantity){
        super(product);
        this.setQuantity(quantity);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSumPrice(){

        return getSalePrice() * quantity;
    }
}
