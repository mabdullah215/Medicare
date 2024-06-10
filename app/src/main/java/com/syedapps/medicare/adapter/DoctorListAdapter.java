package com.syedapps.medicare.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.syedapps.medicare.R;
import com.syedapps.medicare.model.Doctor;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.ViewHolder>
{
    private List<Doctor> dataList=new ArrayList<>();
    Context mContext;
    private OnItemClickListener onItemClickListener;
    int [] ratings={5,3,4,5};

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public DoctorListAdapter(Context context, List<Doctor> eventList)
    {
        this.dataList.addAll(eventList);
        mContext=context;
    }

    public void updateList(List<Doctor> chatLists)
    {
        this.dataList.clear();
        this.dataList.addAll(chatLists);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_doctor_list, parent, false);
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
        Doctor item = dataList.get(position);
        holder.setDetails(item,position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        private ImageView mProfileImage;
        private TextView tvName;
        private TextView tvAddress;
        private MaterialRatingBar ratingBar;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mProfileImage=itemView.findViewById(R.id.img_profile);
            tvName=itemView.findViewById(R.id.tv_name);
            ratingBar=itemView.findViewById(R.id.material_rating);
            tvAddress=itemView.findViewById(R.id.tv_distance);
            itemView.setOnClickListener(this);
        }

        public void setDetails(Doctor item,int pos)
        {
            mProfileImage.setImageResource(item.getImgRes());
            tvName.setText(item.getName());
            tvAddress.setText(item.getAddress());
            ratingBar.setProgress(ratings[pos]);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

}


