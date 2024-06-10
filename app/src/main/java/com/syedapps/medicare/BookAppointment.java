package com.syedapps.medicare;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.syedapps.medicare.adapter.ChipListAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        TextView tvTitle=findViewById(R.id.tv_title);
        tvTitle.setText("Appointment Booking");
        ImageView imgBack=findViewById(R.id.img_back);
        RecyclerView timelist=findViewById(R.id.time_list);
        MaterialButton buttonConfirm=findViewById(R.id.button_confirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                showDialog();
            }
        });
        timelist.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        ChipListAdapter chipListAdapter=new ChipListAdapter(this);
        ArrayList<String>times=new ArrayList<>();
        times.add("9:30 am");
        times.add("11:00 am");
        times.add("1:00 pm");
        times.add("2:00 pm");
        times.add("3:30 pm");
        times.add("5:00 pm");
        chipListAdapter.setList(times);
        timelist.setAdapter(chipListAdapter);
        chipListAdapter.setOnItemClickListener(new ChipListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position)
            {
                chipListAdapter.setSelected(position);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
                Animatoo.INSTANCE.animateSlideRight(BookAppointment.this);
            }
        });
    }

    public void showDialog()
    {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.booking_confirm_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ImageView imgClose=dialog.findViewById(R.id.img_close);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.alert);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                mediaPlayer.release();
            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setCancelable(false);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}