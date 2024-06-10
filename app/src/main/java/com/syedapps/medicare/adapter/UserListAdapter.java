package com.syedapps.medicare.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.syedapps.medicare.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder>
{
    private List<Integer> dataList=new ArrayList<>();
    Context mContext;
    private OnItemClickListener onItemClickListener;
    int selected=-1;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setSelected(int selected) {
        this.selected = selected;
        notifyDataSetChanged();

    }

    public int getSelected() {
        return selected;
    }

    public UserListAdapter(Context context, List<Integer> eventList)
    {
        this.dataList.addAll(eventList);
        mContext=context;
    }

    public void updateList(List<Integer> chatLists)
    {
        this.dataList.clear();
        this.dataList.addAll(chatLists);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_profile, parent, false);
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
        int item = dataList.get(position);
        holder.setDetails(item,position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        private CircleImageView mProfileImage;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mProfileImage=itemView.findViewById(R.id.img_source);
            mProfileImage.setOnClickListener(this);
        }

        public void setDetails(final int item,int pos)
        {
            mProfileImage.setImageResource(item);

            if(selected==pos)
            {
                mProfileImage.setBorderColor(Color.parseColor("#3FD5DF"));
            }
            else
            {
                mProfileImage.setBorderColor(Color.parseColor("#f2f2f2"));
            }
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

}


