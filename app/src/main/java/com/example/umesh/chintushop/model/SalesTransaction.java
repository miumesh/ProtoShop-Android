package com.example.umesh.chintushop.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Umesh on 23-09-2017.
 */

public class SalesTransaction {

    private long id;
    private long customerId;
    private double subTotalAmount;
    private double taxAmount;
    private double totalAmount;
    private boolean paid;
    private String paymentType;
    private long transactionDate;
    private long modifiedDate;

    private List<LineItem> lineItems;

    private String jasonLineItems;

    public SalesTransaction(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getSubTotalAmount() {
        return subTotalAmount;
    }

    public void setSubTotalAmount(double subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(long transactionDate) {
        this.transactionDate = transactionDate;
    }

    public long getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<LineItem> getLineItems() {
        Gson gson = new Gson();
        String serializedLineItems = getJasonLineItems();
        List<LineItem> result = gson.<ArrayList<LineItem>>fromJson(serializedLineItems,
                new TypeToken<ArrayList<LineItem>>(){}.getType());
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        Gson gson = new Gson();
        String lineItemJson = gson.toJson(lineItems);
        this.setJasonLineItems(lineItemJson);
    }

    public String getJasonLineItems() {
        return jasonLineItems;
    }

    public void setJasonLineItems(String jasonLineItems) {
        this.jasonLineItems = jasonLineItems;
    }
}
