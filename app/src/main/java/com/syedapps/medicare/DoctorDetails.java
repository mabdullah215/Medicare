package com.syedapps.medicare;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.button.MaterialButton;
import com.syedapps.medicare.model.Doctor;

public class DoctorDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        TextView tvTitle=findViewById(R.id.tv_title);
        tvTitle.setText("Doctor Details");
        Doctor doctor=(Doctor) getIntent().getSerializableExtra("item");
        TextView tvName=findViewById(R.id.tv_name);
        ImageView imgProfile=findViewById(R.id.img_profile);
        tvName.setText(doctor.getName());
        imgProfile.setImageResource(doctor.getImgRes());
        ImageView imgBack=findViewById(R.id.img_back);
        MaterialButton buttonAppointment=findViewById(R.id.button_appointment);
        buttonAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),BookAppointment.class));
                Animatoo.INSTANCE.animateSlideLeft(DoctorDetails.this);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Animatoo.INSTANCE.animateSlideRight(DoctorDetails.this);
            }
        });
    }
}