package com.syedapps.medicare.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.syedapps.medicare.R;
import com.syedapps.medicare.model.ChatMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder>
{
    private List<ChatMessage> dataList=new ArrayList<>();
    Context mContext;
    String currentID;

    public MessageListAdapter(Context context, List<ChatMessage> eventList, String currentId)
    {
        this.dataList.addAll(eventList);
        mContext=context;
        currentID= currentId;
    }

    public void addItem(ChatMessage msg)
    {
        this.dataList.add(msg);
        notifyDataSetChanged();
    }

    public void updateList(List<ChatMessage> chatLists)
    {
        final DiffCallback diffCallback = new DiffCallback(this.dataList, chatLists);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.dataList.clear();
        this.dataList.addAll(chatLists);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_chat, parent, false);
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
        final ChatMessage item = dataList.get(position);
        holder.setDetails(item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private LinearLayout mParentLayout,childLayout;
        private TextView time;
        private TextView message;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mParentLayout=itemView.findViewById(R.id.parent_layout);
            childLayout=itemView.findViewById(R.id.child_layout);
            message=itemView.findViewById(R.id.tv_message);
            time=itemView.findViewById(R.id.tv_time);
        }

         public void setDetails(final ChatMessage item)
         {
            if(item.getFrom().equalsIgnoreCase(currentID))
            {
                childLayout.getBackground().setTint(mContext.getColor(R.color.colorPrimary));
                message.setTextColor(mContext.getColor(R.color.white));
                time.setTextColor(mContext.getColor(R.color.shimmer_color));
                mParentLayout.setGravity(Gravity.RIGHT);
            }
            else
            {
                childLayout.getBackground().setTint(mContext.getColor(R.color.light_grey_chat));
                message.setTextColor(mContext.getColor(R.color.text_color_default));
                message.setGravity(Gravity.LEFT);
                time.setTextColor(mContext.getColor(R.color.chat_desc_color));
                mParentLayout.setGravity(Gravity.LEFT);
            }
            message.setText(item.getMessage());
            time.setText(formatTime(item.getTimestamp()));
         }
    }

    public static class DiffCallback extends DiffUtil.Callback
    {
        private List<ChatMessage> mOldList;
        private List<ChatMessage> mNewList;

        public DiffCallback(List<ChatMessage> oldList, List<ChatMessage> newList) {
            this.mOldList = oldList;
            this.mNewList = newList;
        }
        @Override
        public int getOldListSize() {
            return mOldList.size();
        }

        @Override
        public int getNewListSize() {
            return mNewList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            // add a unique ID property on Contact and expose a getId() method
            return mOldList.get(oldItemPosition).getId() .equalsIgnoreCase(mNewList.get(newItemPosition).getId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            ChatMessage oldContact = mOldList.get(oldItemPosition);
            ChatMessage newContact = mNewList.get(newItemPosition);
            return oldContact.getMessage().equalsIgnoreCase(newContact.getMessage());
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


