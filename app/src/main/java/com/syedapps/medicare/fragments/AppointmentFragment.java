package com.syedapps.medicare.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.syedapps.medicare.AppointmentDetails;
import com.syedapps.medicare.R;
import com.syedapps.medicare.adapter.AppointmentListAdapter;
import com.syedapps.medicare.model.Appointment;
import com.syedapps.medicare.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class AppointmentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_appointment, container, false);
        List<Doctor>doctors=new ArrayList<>();
        doctors.add(new Doctor("Martin Luither","martin@gmail.com","0.33 miles away.Dentist",33.23123,73.237723,R.drawable.img_doc_1));
        doctors.add(new Doctor("Nicole Fostler","nichole@gmail.com","1.33 miles away.Cardiologist",33.23123,73.237723,R.drawable.img_doc_2));
        doctors.add(new Doctor("Williams George","william@gmail.com","2.05 miles away.Physiotherapist",33.23123,73.237723,R.drawable.img_doc_3));
        RecyclerView recyclerView=view.findViewById(R.id.data_list);
        List<Appointment>appointments=new ArrayList<>();
        appointments.add(new Appointment(doctors.get(0),"completed","Tuesday, 10th June","11:30 am"));
        appointments.add(new Appointment(doctors.get(1),"pending","Wednesday, 21th July","02:30 pm"));
        appointments.add(new Appointment(doctors.get(2),"cancelled","Monday, 24th August","11:30 am"));
        appointments.add(new Appointment(doctors.get(1),"pending","Tuesday, 15th September","11:30 am"));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AppointmentListAdapter adapter=new AppointmentListAdapter(getContext(),appointments);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new AppointmentListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position)
            {
                startActivity(new Intent(getContext(), AppointmentDetails.class).putExtra("item",appointments.get(position)));
            }
        });
        return view;
    }
}