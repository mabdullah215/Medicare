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
import com.syedapps.medicare.adapter.NotificationListAdapter;
import com.syedapps.medicare.model.ChatList;
import com.syedapps.medicare.model.Notification;

import java.util.ArrayList;
import java.util.List;


public class NotificationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_notification, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.data_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Notification> notifications=new ArrayList<>();
        NotificationListAdapter adapter=new NotificationListAdapter(getContext(),notifications);
        recyclerView.setAdapter(adapter);
        notifications.add(new Notification("<b>Dr Martin</b> confirmed your booking request",System.currentTimeMillis()));
        notifications.add(new Notification("<b>Dr Williams George</b> cancelled your booking request",System.currentTimeMillis()));
        notifications.add(new Notification("<b>Dr Amy Fostler</b> confirmed your booking request",System.currentTimeMillis()));
        notifications.add(new Notification("<b>Dr Fostler</b> cancelled your booking request",System.currentTimeMillis()));
        adapter.updateList(notifications);
        return view;
    }
}