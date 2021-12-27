package com.javacodegeeks.foodcalorieintake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class Mealtime extends AppCompatActivity {
    //Initialize variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mealtime);

        //assign varible
        drawerLayout = findViewById(R.id.drawer_layout);
    }




    public void ClickMenu(View view){
        //open drawer
        mainNav.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        //close drawer
        mainNav.closeDrawer(drawerLayout);

    }

    public void ClickHome(View view){
        //Redirect activity to home
        mainNav.redirectActivity(this,mainNav.class);
        finish();
    }
    public void ClickMeal(View view){
        //redirect activity to meal time
        recreate();

    }

    public void ClickFood(View view){
        //Redirect activity to food
        mainNav.redirectActivity(this,Food.class);
        finish();
    }
    public void ClickReport(View view){
        //Redirect activity to food
        mainNav.redirectActivity(this,Report.class);
        finish();
    }
    public void ClickSetting(View view){
        //Redirect activity to food
        mainNav.redirectActivity(this,Setting.class);
        finish();
    }
    public void ClickLogout(View view){
        //Redirect activity to food
        mainNav.logout(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        mainNav.closeDrawer(drawerLayout);
    }


}