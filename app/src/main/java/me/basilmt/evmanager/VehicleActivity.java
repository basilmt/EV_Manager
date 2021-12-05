package me.basilmt.evmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class VehicleActivity extends AppCompatActivity{

    private class Vehicle {
        private String Name;
        private double FullChargeTime;

        public Vehicle(String name, double fullChargeTime) {
            Name = name;
            FullChargeTime = fullChargeTime;
        }

        public String getName() {
            return Name;
        }

        public double getFullChargeTime() {
            return FullChargeTime;
        }
    }

    ArrayList<Vehicle> vehicle = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        int a = getIntent().getIntExtra("ix",-1);


        if (a == 0) {
            vehicle.add(new Vehicle("OLA0", 2.3));
            vehicle.add(new Vehicle("OLA2", 2.3));
        }
        else {
            vehicle.add(new Vehicle("car3", 2.3));
            vehicle.add(new Vehicle("car 6", 2.3));
        }

        for (Vehicle s: vehicle) {
            addButton(s);
        }

    }

    private void addButton(Vehicle veh){
        Button myButton = new Button(this);
        myButton.setText(veh.getName());
        myButton.setBackground(getDrawable(R.drawable.round));

        LinearLayout ll = findViewById(R.id.bg_layout);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(60,60,60,60);

        ll.addView(myButton, lp);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PrefsHelper.insertData(getApplicationContext(), PrefsHelper.VEH_NAME, veh.getName());
                PrefsHelper.insertData(getApplicationContext(), PrefsHelper.FULL_CHARGE_TIME, String.valueOf(veh.getFullChargeTime()));

                startActivity(new Intent(getApplicationContext(), DashboardActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                );
                finish();
            }
        });
    }

}