package me.basilmt.evmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements VehicleAdapter.OnVehicleClickListen {

    VehicleAdapter vehicleAdapter;
    List<VehicleModel> vehicleModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView per_name, veh_name, time;
        Button add_ev,view_usage,payment;

        per_name = findViewById(R.id.per_name);
        ImageView iv = findViewById(R.id.profile_pic);
        add_ev = findViewById(R.id.add_ev);
        view_usage = findViewById(R.id.view_usage);
        payment = findViewById(R.id.payment);

        Glide.with(this)
                .load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl())
                .circleCrop()
                .into(iv);

        per_name.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

        add_ev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), VehicleTypeActivity.class));
            }
        });

        view_usage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),UsageActivity.class));
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String url = "https://wss.kseb.in/selfservices/quickpay";
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    // Verify that the intent will resolve to an activity

                startActivity(Intent.createChooser(intent, "Browse with"));
            }
        });

        getEvData();


    }

    private void getEvData() {

        getVehicleData();

        RecyclerView peopleRV = findViewById(R.id.ev_rv);
        vehicleAdapter = new VehicleAdapter(vehicleModels, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        peopleRV.setLayoutManager(linearLayoutManager);
        peopleRV.setHasFixedSize(true);
        peopleRV.setAdapter(vehicleAdapter);


    }

    private void getVehicleData() {
        String uid = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore.getInstance().collection("users")
                .document(uid)
                .collection("vehicles")
                .addSnapshotListener((value, error) -> {
                    if (value != null) {
                        vehicleModels.clear();
                        for (DocumentSnapshot vehicle : value.getDocuments()) {


                            VehicleModel model = new VehicleModel(
                                    vehicle.getString("veh_name"),
                                    vehicle.getString("veh_class")
                            );

                            vehicleModels.add(model);
                        }
                        vehicleAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onVehicleClick(int position) {
        startActivity(new Intent(this,ProfileActivity.class)
                .putExtra("name",vehicleModels.get(position).getName())
                .putExtra("class",vehicleModels.get(position).getV_class())
        );
    }
}