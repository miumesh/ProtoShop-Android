package com.example.umesh.chintushop.data;

import com.example.umesh.chintushop.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Umesh on 21-09-2017.
 */

public class SampleCustomerData {
    public static List<Customer> getCustomers(){
        List<Customer> customers = new ArrayList<>();

        Customer customer1 = new Customer();
        customer1.setCustomerName("kaustubh Sarode");
        customer1.setEmailAddess("ks@email.com");
        customer1.setProfileImagePath("https://www.dropbox.com/s/iqqo44vwjdyeu2p/kaustubh%2020171003_224029.jpg?dl=0");
        customers.add(customer1);


       /* Customer customer2 = new Customer();
        customer2.setCustomerName("Keisha Williams");
        customer2.setEmailAddess("diva@comcast.com");
        customer2.setProfileImagePath("https://dl.dropboxusercontent.com/u/15447938/attendanceapp/guest2.JPG");
        customers.add(customer2);


        Customer customer3 = new Customer();
        customer3.setCustomerName("Gregg McQuire");
        customer3.setEmailAddess("emailing@nobody.com");
        customer3.setProfileImagePath("https://dl.dropboxusercontent.com/u/15447938/attendanceapp/guest3.JPG");
        customers.add(customer3);


        Customer customer4 = new Customer();
        customer4.setCustomerName("Jamal Puma");
        customer4.setEmailAddess("jamal@hotmail.com");
        customer4.setProfileImagePath("https://dl.dropboxusercontent.com/u/15447938/attendanceapp/guest4.JPG");
        customers.add(customer4);


        Customer customer5 = new Customer();
        customer5.setCustomerName("Dora Keesler");
        customer5.setEmailAddess("dora@yahoo.com");
        customer5.setProfileImagePath("https://dl.dropboxusercontent.com/u/15447938/attendanceapp/guest5.JPG");
        customers.add(customer5);

        Customer customer6 = new Customer();
        customer6.setCustomerName("Anthony Lopez");
        customer6.setEmailAddess("toney@gmail.com");
        customer6.setProfileImagePath("https://dl.dropboxusercontent.com/u/15447938/attendanceapp/guest6.JPG");
        customers.add(customer6);

        Customer customer7 = new Customer();
        customer7.setCustomerName("Ricardo Weisel");
        customer7.setEmailAddess("ricardo@gmail.com");
        customer7.setProfileImagePath("https://dl.dropboxusercontent.com/u/15447938/attendanceapp/guest7.JPG");
        customers.add(customer7);

        Customer customer8 = new Customer();
        customer8.setCustomerName("Angele Lu");
        customer8.setEmailAddess("angele@ymail.com");
        customer8.setProfileImagePath("https://dl.dropboxusercontent.com/u/15447938/attendanceapp/guest8.JPG");
        customers.add(customer8);


        Customer customer9 = new Customer();
        customer9.setCustomerName("Brendon Suh");
        customer9.setEmailAddess("brendon@outlook.com");
        customer9.setProfileImagePath("https://dl.dropboxusercontent.com/u/15447938/attendanceapp/guest9.JPG");
        customers.add(customer9);


        Customer customer10 = new Customer();
        customer10.setCustomerName("Pietro Augustino");
        customer10.setEmailAddess("pietro@company.com");
        customer10.setProfileImagePath("https://dl.dropboxusercontent.com/u/15447938/attendanceapp/guest10.JPG");
        customers.add(customer10);


        Customer customer11 = new Customer();
        customer11.setCustomerName("Matt Zebrotta");
        customer11.setEmailAddess("matt@stopasking.com");
        customer11.setProfileImagePath("https://dl.dropboxusercontent.com/u/15447938/attendanceapp/guest11.JPG");
        customers.add(customer11);

        Customer customer12 = new Customer();
        customer11.setCustomerName("James McGiney");
        customer11.setEmailAddess("james@outlook.com");
        customer11.setProfileImagePath("https://dl.dropboxusercontent.com/u/15447938/attendanceapp/guest11.JPG");
        customers.add(customer11);*/
        return customers;
    }
}

