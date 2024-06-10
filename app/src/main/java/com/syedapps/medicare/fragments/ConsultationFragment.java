package com.syedapps.medicare.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.syedapps.medicare.ChatDetails;
import com.syedapps.medicare.R;
import com.syedapps.medicare.adapter.ChatListAdapter;
import com.syedapps.medicare.model.ChatList;

import java.util.ArrayList;
import java.util.List;

public class ConsultationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_consultation, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.data_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ChatList>chatLists=new ArrayList<>();
        ChatListAdapter adapter=new ChatListAdapter(getContext(),chatLists);
        recyclerView.setAdapter(adapter);
        chatLists.add(new ChatList("You are very good doctor. Thanks",2,System.currentTimeMillis(),"Dr Matrin Luther"));
        chatLists.add(new ChatList("I am on way to hospital",0,System.currentTimeMillis()-450000,"Dr Nicole Fostler"));
        chatLists.add(new ChatList("Lorem Ipsum dolor sit ame",0,System.currentTimeMillis()-900000,"Dr Williams George"));
        chatLists.add(new ChatList("Lorem Ipsum dolor sit ame",0,System.currentTimeMillis()-19000000,"Dr Amy Jackson"));
        chatLists.add(new ChatList("Lorem Ipsum dolor sit ame",0,System.currentTimeMillis()-29000000,"Dr Brroklyn Simmons"));
        adapter.updateList(chatLists);
        adapter.setOnItemClickListener(new ChatListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position)
            {
                startActivity(new Intent(getContext(), ChatDetails.class));
                Animatoo.INSTANCE.animateSlideLeft(getContext());
            }
        });
        return view;
    }
}