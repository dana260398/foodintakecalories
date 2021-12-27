package com.javacodegeeks.foodcalorieintake;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME= "trackcalapp.db";
    private static final int DATABASE_VERSION =1;

    public DBHelper(Context context) {

        super(context, DBNAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table user(username TEXT primary key,password TEXT, age INTEGER, gender TEXT, registerdate TEXT, weight REAL,height REAL, bmistatus TEXT, paintensity TEXT, caloriesneed INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists user");

    }

    public Boolean registeruser(String username, String password, Integer age, String gender, String date, Double weight, Double height, String bmistatus,String intensity, Double calories){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("age",age);
        contentValues.put("gender",gender);
        contentValues.put("registerdate",date);
        contentValues.put("weight",weight);
        contentValues.put("height",height);
        contentValues.put("bmistatus",bmistatus);
        contentValues.put("paintensity",intensity);
        contentValues.put("caloriesneed",calories);

        long result =db.insert("user",null,contentValues);

        if (result== -1)
            return false;
        else
            return true;

    }

    public Boolean checkuser(String username, String password)
    {
        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor cursor = mydb.rawQuery("select * from user where username=? and password=?", new String[] {username,password});

        if(cursor.getCount()>0)
            {
               return true;
            }
            else {
                return false;
            }

    }


}
