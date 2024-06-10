package com.syedapps.medicare;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(false).build();
        FirebaseFirestore.getInstance().setFirestoreSettings(settings);
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                if(mAuth.getCurrentUser()==null)
                {
                    startActivity(new Intent(getBaseContext(),LoginActivity.class));
                }
                else
                {
                    startActivity(new Intent(getBaseContext(),MainActivity.class));
                }

                Animatoo.INSTANCE.animateSlideLeft(SplashActivity.this);
                finish();
            }
        };

        new Timer().schedule(task, 3000);
    }
}