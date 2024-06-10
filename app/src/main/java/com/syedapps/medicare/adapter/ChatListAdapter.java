package com.syedapps.medicare.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.syedapps.medicare.R;
import com.syedapps.medicare.model.ChatList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder>
{
    private List<ChatList> dataList=new ArrayList<>();
    Context mContext;
    int [] images = {R.drawable.img_doc_1,R.drawable.img_doc_2,R.drawable.img_doc_3,R.drawable.img_doc_4,R.drawable.img_doc_1};
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<ChatList> getDataList() {
        return dataList;
    }

    public ChatListAdapter(Context context, List<ChatList> eventList)
    {
        this.dataList.addAll(eventList);
        mContext=context;
    }

    public void updateList(List<ChatList> chatLists)
    {
        this.dataList.clear();
        this.dataList.addAll(chatLists);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_chat_list, parent, false);
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
        final ChatList item = dataList.get(position);
        holder.setDetails(item,position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private CircleImageView mImage;
        private TextView mName;
        private TextView mLastmsg;
        private TextView mTime;
        private TextView mCount;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mImage=itemView.findViewById(R.id.img_layout);
            mName=itemView.findViewById(R.id.tv_title);
            mLastmsg=itemView.findViewById(R.id.tv_msg);
            mCount=itemView.findViewById(R.id.tv_count);
            mTime=itemView.findViewById(R.id.tv_date);
            itemView.setOnClickListener(this);
        }

         public void setDetails(final ChatList item,int position)
         {
             mImage.setImageResource(images[position]);
             mName.setText(item.getPersonName());
             mLastmsg.setText(item.getLastmessage());
             mTime.setText(formatTime(item.getTimestamp()));
             if(item.getCount()>0)
             {
                 mCount.setText(String.valueOf(item.getCount()));
                 mCount.setVisibility(View.VISIBLE);
                 mLastmsg.setTypeface(null, Typeface.BOLD);
             }
             else
             {
                 mLastmsg.setTypeface(null, Typeface.NORMAL);
                 mCount.setVisibility(View.GONE);
             }
         }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public String formatTime(long time){
        // income time
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(time);

        // current time
        Calendar curDate = Calendar.getInstance();
        curDate.setTimeInMillis(System.currentTimeMillis());

        SimpleDateFormat dateFormat = null;
        if(date.get(Calendar.YEAR)==curDate.get(Calendar.YEAR)){
            if(date.get(Calendar.DAY_OF_YEAR) == curDate.get(Calendar.DAY_OF_YEAR) ){
                dateFormat = new SimpleDateFormat("h:mm a", Locale.US);
            }
            else{
                dateFormat = new SimpleDateFormat("MMM d", Locale.US);
            }
        }
        else{
            dateFormat = new SimpleDateFormat("MMM yyyy", Locale.US);
        }
        return dateFormat.format(time);
    }


}


