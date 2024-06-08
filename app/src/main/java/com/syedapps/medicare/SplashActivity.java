package com.syedapps.medicare;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                startActivity(new Intent(getBaseContext(),WelcomeActivity.class));
                Animatoo.INSTANCE.animateSlideLeft(SplashActivity.this);
                finish();
            }
        };

        new Timer().schedule(task, 3000);
    }
}