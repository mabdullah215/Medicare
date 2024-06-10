package com.syedapps.medicare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.dd.CircularProgressButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.syedapps.medicare.model.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ImageView imgBack=findViewById(R.id.img_back);
        EditText etName=findViewById(R.id.et_username);
        EditText etEmail=findViewById(R.id.et_email);
        EditText etPass=findViewById(R.id.et_pass);
        EditText etContact=findViewById(R.id.et_phone);
        TextView tvDateBirth=findViewById(R.id.tv_date_birth);
        EditText etPostalCode=findViewById(R.id.et_postal_code);
        CircularProgressButton tvStarted =findViewById(R.id.button_progress);
        tvStarted.setIndeterminateProgressMode(true);

        tvDateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                showDatePicker(tvDateBirth);
            }
        });

        tvStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                hideKeyboard();
                String name=etName.getText().toString().trim();
                String email=etEmail.getText().toString().trim();
                String pass=etPass.getText().toString().trim();
                String phone=etContact.getText().toString().trim();
                String birthDate=tvDateBirth.getText().toString().trim();
                String postalCode=etPostalCode.getText().toString().trim();

                if(name.isEmpty()||email.isEmpty()||pass.isEmpty()||phone.isEmpty()||birthDate.isEmpty()||postalCode.isEmpty())
                {
                    showToast("Information missing");
                }
                else
                {
                    User user =new User();
                    user.setEmail(email);
                    user.setUsername(name);
                    user.setPass(pass);
                    user.setPhone(phone);
                    user.setDateofBirth(birthDate);
                    user.setPostalcode(postalCode);
                    createAccount(user,tvStarted);
                }

            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Animatoo.INSTANCE.animateSlideRight(RegisterActivity.this);
            }
        });
    }

    public void showDatePicker(TextView tv)
    {
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                calendar.set(year, monthOfYear, dayOfMonth);
                tv.setText(format.format(calendar.getTime()));
            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void createAccount(User user,CircularProgressButton button)
    {
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        button.setProgress(50);
        mAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPass()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    button.setProgress(100);
                    updateFirebaseData(user);
                }
                else
                {
                    showToast(task.getException().getLocalizedMessage());
                    button.setProgress(-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            button.setProgress(0);
                        }
                    }, 3000);
                }
            }
        });
    }

    public void updateFirebaseData(User user)
    {
        String id= FirebaseAuth.getInstance().getUid();
        user.setId(id);
        DocumentReference mUserRef= FirebaseFirestore.getInstance().collection("users").document(id);
        mUserRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused)
            {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                Animatoo.INSTANCE.animateSlideLeft(RegisterActivity.this);
                finish();
            }
        });
    }
}