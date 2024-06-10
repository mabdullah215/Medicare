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
import com.syedapps.medicare.DoctorDetails;
import com.syedapps.medicare.R;
import com.syedapps.medicare.adapter.ChipListAdapter;
import com.syedapps.medicare.adapter.DoctorListAdapter;
import com.syedapps.medicare.adapter.UserListAdapter;
import com.syedapps.medicare.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.category_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        ChipListAdapter adapter=new ChipListAdapter(getContext());
        ArrayList<String> categoryList=new ArrayList<>();
        categoryList.add("Dentist");
        categoryList.add("Cardiology");
        categoryList.add("Physiotherapist");
        categoryList.add("Physician");
        adapter.setList(categoryList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ChipListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position)
            {
                adapter.setSelected(position);
            }
        });

        RecyclerView doctorList=view.findViewById(R.id.profile_list);
        doctorList.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        List<Integer>list=new ArrayList<>();
        list.add(R.drawable.img_doc_1);
        list.add(R.drawable.img_doc_2);
        list.add(R.drawable.img_doc_3);
        list.add(R.drawable.img_doc_4);
        list.add(R.drawable.img_doc_5);
        UserListAdapter userListAdapter=new UserListAdapter(getContext(),list);
        doctorList.setAdapter(userListAdapter);
        userListAdapter.setOnItemClickListener(new UserListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                userListAdapter.setSelected(position);
            }
        });

        RecyclerView postList=view.findViewById(R.id.post_list);
        postList.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Doctor>doctors=new ArrayList<>();
        doctors.add(new Doctor("Martin Luither","martin@gmail.com","0.33 miles away.Dentist",33.23123,73.237723,R.drawable.img_doc_list_1));
        doctors.add(new Doctor("Nicole Fostler","nichole@gmail.com","1.33 miles away.Cardiologist",33.23123,73.237723,R.drawable.img_doc_list_2));
        doctors.add(new Doctor("Williams George","william@gmail.com","2.05 miles away.Physiotherapist",33.23123,73.237723,R.drawable.img_doc_list_3));
        doctors.add(new Doctor("Amy Jackson","amyjackson@gmail.com","3.41 miles away.Dentist",33.23123,73.237723,R.drawable.img_doc_list_4));
        DoctorListAdapter doctorListAdapter=new DoctorListAdapter(getContext(),doctors);
        postList.setAdapter(doctorListAdapter);
        doctorListAdapter.setOnItemClickListener(new DoctorListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position)
            {
                startActivity(new Intent(getContext(), DoctorDetails.class).putExtra("item",doctors.get(position)));
                Animatoo.INSTANCE.animateSlideLeft(getContext());
            }
        });

        return view;
    }
}