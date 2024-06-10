package com.syedapps.medicare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.dd.CircularProgressButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class LoginActivity extends BaseActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth= FirebaseAuth.getInstance();
        ImageView imgBack=findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        EditText etEmail=findViewById(R.id.et_email);
        EditText etPass=findViewById(R.id.et_pass);
        CircularProgressButton loginButton=findViewById(R.id.button_progress);
        loginButton.setIndeterminateProgressMode(true);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(loginButton.getProgress()==0)
                {
                    String email = etEmail.getText().toString().trim();
                    String password = etPass.getText().toString().trim();
                    if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
                    {
                        loginUser(email,password,loginButton);
                    }
                    else
                    {
                        showToast("information missing");
                    }
                }
            }
        });

        TextView tvRegister=findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getBaseContext(),RegisterActivity.class));
                Animatoo.INSTANCE.animateSlideLeft(LoginActivity.this);
            }
        });

    }

    private void loginUser(final String username, final String password,CircularProgressButton loginButton)
    {
        loginButton.setProgress(50);
        hideKeyboard();
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            loginButton.setProgress(100);
                            String id=FirebaseAuth.getInstance().getUid();
                            DocumentReference mUserRef= FirebaseFirestore.getInstance().collection("users").document(id);
                            mUserRef.addSnapshotListener(LoginActivity.this, new EventListener<DocumentSnapshot>() {
                                @Override
                                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error)
                                {
                                    if(value.exists())
                                    {
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run()
                                            {
                                                startActivity(new Intent(getBaseContext(),MainActivity.class));
                                                finish();
                                                Animatoo.INSTANCE.animateSlideLeft(LoginActivity.this);
                                            }
                                        }, 500);

                                    }
                                    else
                                    {
                                        showToast("User data not exists");
                                        loginButton.setProgress(0);
                                    }
                                }
                            });
                        }

                        else
                        {
                            showToast(task.getException().getLocalizedMessage());
                            loginButton.setProgress(-1);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loginButton.setProgress(0);
                                }
                            }, 3000);
                        }
                    }
                });
    }
}