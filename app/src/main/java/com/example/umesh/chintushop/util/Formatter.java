package com.example.umesh.chintushop.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Umesh on 26-09-2017.
 */

public class Formatter {


    public  static String formatCurrency(double amount){

        NumberFormat baseFormat = NumberFormat.getCurrencyInstance();
        String moneyString = baseFormat.format(amount);
        return moneyString;

    }

    public static String formatDate(long date){

        String displayDate = new SimpleDateFormat("dd MMM").format(new Date(date));
        return displayDate;
    }


}
