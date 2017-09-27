package com.example.umesh.chintushop.model;

/**
 * Created by Umesh on 21-09-2017.
 */

public class Customer {

    private long id;
    private String customerName;
    private String emailAddess;
    private String phoneNumber;
    private String profileImagePath;
    private String streetAddress;
    private String streetAddress2;
    private String city;
    private String state;
    private String postalCode;
    private String note;
    private String dateAdded;
    private String dateOfLastTransaction;


    public Customer(){

        id = 0;
        customerName = "";
        emailAddess = "";
        emailAddess = "";
        phoneNumber = "";
        profileImagePath = "empty";
        streetAddress = "";
        streetAddress2 = "";
        city = "";
        state = "";
        postalCode = "";
        note = "";
        dateAdded = "";
        dateOfLastTransaction = "";

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmailAddess() {
        return emailAddess;
    }

    public void setEmailAddess(String emailAddess) {
        this.emailAddess = emailAddess;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public void setStreetAddress2(String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateOfLastTransaction() {
        return dateOfLastTransaction;
    }

    public void setDateOfLastTransaction(String dateOfLastTransaction) {
        this.dateOfLastTransaction = dateOfLastTransaction;
    }
}
