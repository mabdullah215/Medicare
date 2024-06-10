package com.syedapps.medicare.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.syedapps.medicare.R;
import com.syedapps.medicare.model.Appointment;
import com.syedapps.medicare.model.Doctor;

import java.util.ArrayList;
import java.util.List;

import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class AppointmentListAdapter extends RecyclerView.Adapter<AppointmentListAdapter.ViewHolder>
{
    private List<Appointment> dataList=new ArrayList<>();
    Context mContext;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public AppointmentListAdapter(Context context, List<Appointment> eventList)
    {
        this.dataList.addAll(eventList);
        mContext=context;
    }

    public void updateList(List<Appointment> chatLists)
    {
        this.dataList.clear();
        this.dataList.addAll(chatLists);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_appointment_list, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public int getItemCount()
    {
        return dataList == null? 0: dataList.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        Appointment item = dataList.get(position);
        holder.setDetails(item,position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        private ImageView mProfileImage;
        private TextView tvName;
        private TextView tvDate;
        private TextView tvTime;
        private Chip status;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mProfileImage=itemView.findViewById(R.id.img_profile);
            tvName=itemView.findViewById(R.id.tv_name);
            tvDate=itemView.findViewById(R.id.tv_date);
            tvTime=itemView.findViewById(R.id.tv_time);
            status=itemView.findViewById(R.id.chip_status);
            itemView.setOnClickListener(this);
        }

        public void setDetails(Appointment item,int pos)
        {
            mProfileImage.setImageResource(item.getDoctor().getImgRes());
            tvName.setText(item.getDoctor().getName());
            tvDate.setText(item.getDate());
            tvTime.setText(item.getTime());
            if(item.getStatus().equalsIgnoreCase("Completed"))
            {
                status.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.status_complete)));
            }
            else if (item.getStatus().equalsIgnoreCase("Cancelled"))
            {
                status.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.status_cancelled)));
            }
            else
            {
                status.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(mContext, R.color.colorAccent)));
            }
            status.setText(item.getStatus());
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

}


