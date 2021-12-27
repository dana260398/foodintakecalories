package com.javacodegeeks.foodcalorieintake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Register extends AppCompatActivity {

    EditText username,pass,confpass,age,weight,height;
    RadioGroup radioGroup;
    RadioButton gender;
    Spinner type;
    String bmiStatus;
    Double calories;
    Double bmi;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=(EditText) findViewById(R.id.editusername);
        pass=(EditText) findViewById(R.id.editpass);
        confpass = (EditText) findViewById(R.id.editconfpass);
        age= (EditText) findViewById(R.id.editage);
        weight=(EditText) findViewById(R.id.editweight);
        height = (EditText) findViewById(R.id.editheight);
        type = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.type, R.layout.support_simple_spinner_dropdown_item);
        type.setAdapter(adapter);

        DB = new DBHelper(this);

        radioGroup  =(RadioGroup) findViewById(R.id.radioGroup);
        int radioID = radioGroup.getCheckedRadioButtonId();
        gender = findViewById(radioID);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = sdf.format(new Date());


        Button buttonApply = findViewById(R.id.submitreg);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String password = pass.getText().toString();
                String confpassword = confpass.getText().toString();
                Integer ages = Integer.parseInt(age.getText().toString());
                String types = type.getSelectedItem().toString();
                Double weights = Double.parseDouble(weight.getText().toString());
                Double heights = Double.parseDouble(height.getText().toString());
                String gend = gender.getText().toString();
                Boolean checkpassword = checkPass(password,confpassword);

                if(checkpassword == false){
                    Toast.makeText(Register.this, "Password Not matching",Toast.LENGTH_SHORT).show();
                }
                else{
                    password = pass.getText().toString();
                    bmi=weights/ (heights * heights);
                    bmiStatus = calcBMI(bmi);

                    calories = calcCaloriesNeeded(ages,gend,heights,weights,types);


                    boolean isInserted=DB.registeruser(uname,password,ages,gend, currentDate, weights,heights,bmiStatus,types,calories);

                    if (isInserted ==true){
                        Toast.makeText(Register.this, "Succesfully Registered",Toast.LENGTH_SHORT).show();
                        openLogin();
                    }
                    else
                        Toast.makeText(Register.this, "Unsuccesfully",Toast.LENGTH_SHORT).show();




                }


            }
        });
    }

    private void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private Double calcCaloriesNeeded(Integer ages, String gend, Double heights, Double weights, String types) {
            Double cal=0.0;

            if(gend.equals("Male"))
            {
                if(types.equals("Tidak Aktif/Sedentary"))
                {
                    cal= 864-9.27 * ages + 1.00 * 14.2 * weights + 503.0 * heights;
                }
                else if(types.equals("Kurang Aktif/Low active"))
                {
                    cal= 864-9.27 * ages + 1.14*14.2 * weights + 503.0 * heights;
                }
                else if(types.equals("Aktif/Active"))
                {
                    cal= 864-9.27 * ages + 1.27*14.2 * weights + 503.0 * heights;
                }
                else
                {
                    cal= 864-9.27 * ages + 1.54*14.2 * weights + 503.0 * heights;
                }
            }
            if(gend.equals("Female"))
            {
                if(types.equals("Tidak Aktif/Sedentary"))
                {
                    cal= 387-7.31 * ages + 1.00 * 14.2 * weights + 503.0 * heights;
                }
                else if(types.equals("Kurang Aktif/Low active"))
                {
                    cal= 387-7.31 * ages + 1.14*14.2 * weights + 503.0 * heights;
                }
                else if(types.equals("Aktif/Active"))
                {
                    cal= 387-7.31 * ages + 1.27*14.2 * weights + 503.0 * heights;
                }
                else
                {
                    cal= 387-7.31 * ages + 1.54*14.2 * weights + 503.0 * heights;
                }

            }

            return cal;

    }

    private String calcBMI(Double bmis) {


        if(bmis < 18.5)
            bmiStatus = "Underweight";
        else if(bmis < 25)
            bmiStatus = "Normal";
        else if(bmis < 30)
            bmiStatus="Overweight";
        else
            bmiStatus="Obese";

        return bmiStatus;
    }

    private Boolean checkPass(String password, String confpassword) {
        Boolean temp = true;
        if(!password.equals(confpassword)){

          temp=false;
        }

        return temp;
    }

    public void checkButton(View v)
    {
        int radioid = radioGroup.getCheckedRadioButtonId();
        gender =(RadioButton) findViewById(radioid);

    }


}