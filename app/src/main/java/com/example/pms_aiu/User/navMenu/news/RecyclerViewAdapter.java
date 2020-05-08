package com.example.pms_aiu.User.navMenu.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pms_aiu.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    private ArrayList<ItemHomeModel> mItemHomeList;

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        public ImageView home_photo, map_event;
        public TextView speaker_event, location_event, time_event, title_event, other_event;
        public TextView today_home, date_home;


        public ItemViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            //img
            home_photo = itemView.findViewById(R.id.home_photo);
            map_event = itemView.findViewById(R.id.map_event);
            //txt
            speaker_event = itemView.findViewById(R.id.speakerName_event);
            location_event = itemView.findViewById(R.id.location_event);
            time_event = itemView.findViewById(R.id.stId_profile);
            title_event = itemView.findViewById(R.id.title_event);
            other_event = itemView.findViewById(R.id.other_event);

            today_home = itemView.findViewById(R.id.today_home);
            date_home = itemView.findViewById(R.id.date_home);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int positon= getAdapterPosition();
                        listener.OnItemClick(positon);
                    }
                }
            });


        }
    }
    public RecyclerViewAdapter(ArrayList<ItemHomeModel> itemHomeList){
            mItemHomeList = itemHomeList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_layout_item, parent,false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(v, mListener);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            ItemHomeModel currentItem = mItemHomeList.get(position);
            holder.home_photo.setImageResource(currentItem.getmHomeImage());
        holder.map_event.setImageResource(currentItem.getmMapImage());

        holder.title_event.setText(currentItem.getmTitle_event());
        holder.time_event.setText(currentItem.getmTime_event());
        holder.location_event.setText(currentItem.getmLocation_event());
        holder.other_event.setText(currentItem.getmOther_event());
        holder.speaker_event.setText(currentItem.getmSpeaker_event());

        holder.today_home.setText(currentItem.getmTodayHome());
        holder.date_home.setText(currentItem.getmDateHome());
        if(position!=0){
            holder.today_home.setVisibility(View.GONE);
            holder.date_home.setVisibility(View.GONE);
            holder.other_event.setVisibility(View.GONE);
        }
        if(position==0){
            holder.other_event.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mItemHomeList.size();
    }




}
