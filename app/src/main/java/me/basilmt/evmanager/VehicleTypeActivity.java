package me.basilmt.evmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class VehicleTypeActivity extends AppCompatActivity {

    private ImageButton scooter;
    private ImageButton car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_type);

        TextView ev_name;

        ev_name = findViewById(R.id.ev_name);

        scooter = findViewById(R.id.scooter);
        car = findViewById(R.id.car);

        scooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkData(ev_name.getText().toString())) {
                    saveToFirebase(0,ev_name.getText().toString().trim());
//                    startActivity(new Intent(getApplicationContext(), VehicleActivity.class).putExtra("ix", 0));
//                    Toast.makeText(getApplicationContext(), "Scooter", Toast.LENGTH_SHORT).show();
                }
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkData(ev_name.getText().toString())) {
                    saveToFirebase(1,ev_name.getText().toString().trim());

//                    startActivity(new Intent(getApplicationContext(), VehicleActivity.class).putExtra("ix", 1));
//                    Toast.makeText(getApplicationContext(), "Car", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void saveToFirebase(int veh_class, String veh_name) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        Map<String, Object> veh = new HashMap<>();
        veh.put("veh_class", String.valueOf(veh_class));
        veh.put("veh_name", veh_name);

// Add a new document with a generated ID
        db.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .collection("vehicles")
                .add(veh).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Cannot upload data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean checkData(String name) {
        if(name.trim().isEmpty()){
            Toast.makeText(this, "Name is mandatory", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}