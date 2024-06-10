package com.syedapps.medicare;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.syedapps.medicare.fragments.AppointmentFragment;
import com.syedapps.medicare.fragments.ConsultationFragment;
import com.syedapps.medicare.fragments.HomeFragment;
import com.syedapps.medicare.fragments.NotificationFragment;
import com.syedapps.medicare.fragments.SettingsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fragment> fragmentList=new ArrayList<>();
    BottomNavigationView bottomNavigationView;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private  FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth= FirebaseAuth.getInstance();
        bottomNavigationView=findViewById(R.id.bottom_menu);
        fragmentList.add(new HomeFragment());
        fragmentList.add(new AppointmentFragment());
        fragmentList.add(new ConsultationFragment());
        fragmentList.add(new NotificationFragment());
        fragmentList.add(new SettingsFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                Fragment selectedFragment = null;
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        selectedFragment = fragmentList.get(0);
                        break;
                    case R.id.navigation_appointment:
                        selectedFragment = fragmentList.get(1);
                        break;
                    case R.id.navigation_consulation:
                        selectedFragment = fragmentList.get(2);
                        break;

                    case R.id.navigation_notification:
                        selectedFragment = fragmentList.get(3);
                        break;

                    case R.id.navigation_settings:
                        selectedFragment = fragmentList.get(4);
                        break;

                }

                loadFragment(selectedFragment);
                return true;
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user==null)
                {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    Animatoo.INSTANCE.animateSlideRight(MainActivity.this);
                }
            }
        };

        loadFragment(fragmentList.get(0));
    }

    public void loadFragment(Fragment fragment)
    {
        if (fragment != null)
        {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.container_view, fragment).commit();
        }
    }

    @SuppressLint("MissingSuperCall")
    public void onBackPressed()
    {
        if(bottomNavigationView.getSelectedItemId()==R.id.navigation_home)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Exit App")
                    .setMessage("Are you sure you want to exit the app?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(R.drawable.ic_logo)
                    .show();
        }
        else
        {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }
    }

    public void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void onStop()
    {
        super.onStop();
        if (mAuthListener != null)
        { mAuth.removeAuthStateListener(mAuthListener); }
    }
}