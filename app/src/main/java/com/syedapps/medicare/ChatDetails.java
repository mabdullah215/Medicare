package com.syedapps.medicare;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import com.syedapps.medicare.adapter.MessageListAdapter;
import com.syedapps.medicare.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatDetails extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_details);
        ImageView imgBack=findViewById(R.id.img_back);
        TextView tvTitle=findViewById(R.id.tv_title);
        tvTitle.setText("Dr Martin Luther");
        RecyclerView recyclerView=findViewById(R.id.data_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<ChatMessage>messages=new ArrayList<>();
        messages.add(new ChatMessage("Hi!","1","1",0,System.currentTimeMillis()));
        messages.add(new ChatMessage("Hello","2","1",1,System.currentTimeMillis()));
        messages.add(new ChatMessage("Can you help me please","1","1",2,System.currentTimeMillis()));
        messages.add(new ChatMessage("Yes sure","2","1",3,System.currentTimeMillis()));
        MessageListAdapter adapter=new MessageListAdapter(this,messages,"1");
        recyclerView.setAdapter(adapter);
        EditText etMessage=findViewById(R.id.text_content);
        ImageView btnSend=findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                hideKeyboard();
                String message=etMessage.getText().toString().trim();
                if(!message.isEmpty())
                {
                    etMessage.setText(null);
                    adapter.addItem(new ChatMessage(message,"1","1",adapter.getItemCount(),System.currentTimeMillis()));
                }
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Animatoo.INSTANCE.animateSlideRight(ChatDetails.this);
            }
        });
    }
}