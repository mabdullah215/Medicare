package com.syedapps.medicare.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.syedapps.medicare.R;

import java.util.ArrayList;

public class ChipListAdapter extends RecyclerView.Adapter<ChipListAdapter.ViewHolder>
{
    Context mContext;
    private OnItemClickListener onItemClickListener;
    int selected=0;
    private ArrayList<String>list=new ArrayList<>();
    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ChipListAdapter(Context context)
    {
        mContext=context;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public void setSelected(int selected) {
        this.selected = selected;
        notifyDataSetChanged();
    }

    public int getSelected() {
        return selected;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_filter, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public int getItemCount()
    {
        return list.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        String item=list.get(position);
        holder.setDetails(item,position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private Chip mTxt;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mTxt=itemView.findViewById(R.id.chip_txt);
            itemView.setOnClickListener(this);
        }

        public void setDetails(final String item,int pos)
        {
            mTxt.setText(item);

            if(pos==selected)
            {
                mTxt.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#3FD5DF")));
                mTxt.setTextColor(Color.WHITE);
            }
            else
            {
                mTxt.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#f1f1f1")));
                mTxt.setTextColor(Color.parseColor("#3FD5DF"));
            }

        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
}


