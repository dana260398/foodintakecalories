package com.javacodegeeks.foodcalorieintake;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY= "session_user";
    String KEY_NAME = "name";


    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(Integer i, String uname){
        //save session of user whenever user is logged in
        int id = i;
        String username = uname;


        editor.putString("DeviceToken",username);

        editor.putInt(SESSION_KEY, id).commit();
        editor.apply();
    }

    public int getSession(){
        //return user whose session is saved
        return sharedPreferences.getInt(SESSION_KEY, -1);
    }
    public String getUsername(){
        //return user whose session is saved
        return sharedPreferences.getString(KEY_NAME, null);
    }

    public void removeSession(){
        editor.putInt(SESSION_KEY, -1).commit();
    }
}
