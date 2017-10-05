package com.example.umesh.chintushop.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Umesh on 21-09-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "shop.dp";

    private static DatabaseHelper mDatabaseInstance = null;

    public static DatabaseHelper newInstance(Context context){
        if(mDatabaseInstance == null){
            mDatabaseInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return mDatabaseInstance;

    }


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
