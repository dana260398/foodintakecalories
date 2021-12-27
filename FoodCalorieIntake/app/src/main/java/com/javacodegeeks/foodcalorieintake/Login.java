package com.javacodegeeks.foodcalorieintake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing username.
    public static final String USERNAME_KEY = "username_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    EditText password;
    CheckBox showpass;
    EditText username;

    Button login,register;
    private DBHelper databaseHelper;

    SharedPreferences sharedpreferences;
    String user,ps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DBHelper(this);
        //get data from layout
        password = findViewById(R.id.pass);
        showpass = findViewById(R.id.show);

        // getting the data which is stored in shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // in shared prefs inside het string method
        // we are passing key value as EMAIL_KEY and
        // default value is
        // set to null if not present.
        user = sharedpreferences.getString(USERNAME_KEY, null);
        ps = sharedpreferences.getString(PASSWORD_KEY, null);

        showpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
                else
                {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.pass);

        login =(Button) findViewById(R.id.login);
        register=(Button) findViewById(R.id.reg);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname =username.getText().toString();
                String pass = password.getText().toString();

                Boolean checklogin = databaseHelper.checkuser(uname,pass);
                if (checklogin == true)
                {
                    SessionManagement sessionManagement = new SessionManagement(Login.this);
                    sessionManagement.saveSession(14,uname);
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    // below two lines will put values for
                    // email and password in shared preferences.
                    editor.putString(USERNAME_KEY, uname);
                    editor.putString(PASSWORD_KEY, pass);

                    // to save our data with key and value.
                    editor.apply();
                    Toast.makeText(Login.this, "Succesfully Login",Toast.LENGTH_SHORT).show();
                    openHome();


                }
                else
                    Toast.makeText(Login.this, "User not exist",Toast.LENGTH_SHORT).show();

            }
        });
    }

    protected void onStart() {

        super.onStart();

        SessionManagement sessionManagement=new SessionManagement(Login.this);
        int userID = sessionManagement.getSession();

        if (userID != -1){
            openHome();
        }
        else{}
    }


    private void openRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    private void openHome() {
        Intent intent = new Intent(this, mainNav.class);
        startActivity(intent);
        finish();
    }
}