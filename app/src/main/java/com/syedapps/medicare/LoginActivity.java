package com.syedapps.medicare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.dd.CircularProgressButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CircularProgressButton loginBtn=findViewById(R.id.button_progress);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getBaseContext(),MainActivity.class));
                Animatoo.INSTANCE.animateSlideLeft(LoginActivity.this);
            }
        });

    }
}