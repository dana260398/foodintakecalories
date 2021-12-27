package com.javacodegeeks.foodcalorieintake;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c=null;

    //private contructor
    private DatabaseAccess(Context context){
        this.openHelper=new DatabaseOpenHelper(context);
    }

    //to return the single instance of db

    public static DatabaseAccess getInstance(Context context){
        if (instance==null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }

    //to open db

    public void open(){
        this.db=openHelper.getWritableDatabase();
    }

    //closing the db connection
    public void close(){
        if (db!= null)
            this.db.close();
    }

    //method to query and return result from db

   // public Boolean getFoodList(String username, String password, Integer age, String gender, String date, Double weight, Double height, String bmistatus,String intensity, Double calories) {

        //c = db.rawQuery("select * from food", new String[]{});
        //StringBuffer buffer = new StringBuffer();
        //while (c.moveToNext()) {

       // }

        // long result =this.db.insert("user",null,contentValues);

        //if (result== -1)
        //  return false;
        //else
        //   return true;

   // }
}
