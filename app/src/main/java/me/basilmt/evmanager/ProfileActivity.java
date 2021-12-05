package me.basilmt.evmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {

    ImageView v_pic;
    Button v_charge,v_schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String v_name_t = getIntent().getStringExtra("name");
        int v_class_t = getIntent().getIntExtra("class",0);

        TextView v_name = findViewById(R.id.v_name);
        v_pic = findViewById(R.id.v_pic);
        v_schedule = findViewById(R.id.v_schedule);
        v_charge = findViewById(R.id.v_charge);

        v_name.setText(v_name_t);

        putImage(v_class_t);

        v_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ScheduleActivity.class));
            }
        });

        v_charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void putImage(int v_class) {
        if(v_class == 0){
//            holder.prp.setBackgroundResource(R.drawable.scooter);
            Glide.with(v_pic)
                    .load(getDrawable(R.drawable.scooter))
                    .circleCrop()
                    .into(v_pic);
        }
        else {
//            holder.prp.setBackgroundResource(R.drawable.car);
            Glide.with(v_pic)
                    .load(getDrawable(R.drawable.car))
                    .circleCrop()
                    .into(v_pic);
        }
    }


}