package com.example.pms_aiu.NavigationMenu.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pms_aiu.Models.News;
import com.example.pms_aiu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {

    private Context mContext;
    private List<News> mNews;


    public RecyclerViewAdapter(Context context, List<News> news) {
        mContext = context;
        mNews = news;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.home_layout_item, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        News newsCurrent = mNews.get(position);
        holder.titleView.setText(newsCurrent.getTitle());
        holder.timeView.setText(newsCurrent.getTime());
        holder.locationView.setText(newsCurrent.getLocation());
        holder.mapView.setImageResource(R.drawable.map);
        Picasso.get().
                load(newsCurrent.getImage())
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return mNews.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView, timeView, locationView;
        public ImageView imageView, mapView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.titleNews);
            timeView = itemView.findViewById(R.id.timeNews);
            locationView = itemView.findViewById(R.id.locationNews);

            imageView = itemView.findViewById(R.id.imgNews);
            mapView = itemView.findViewById(R.id.map_event);
        }
    }


//    private OnItemClickListener mListener;
//
//    public interface OnItemClickListener{
//        void OnItemClick(int position);
//    }
//    public void setOnItemClickListener(OnItemClickListener listener){
//        mListener = listener;
//    }
//    private ArrayList<ItemHomeModel> mItemHomeList;
//
//    public static class ItemViewHolder extends RecyclerView.ViewHolder{
//        public ImageView home_photo, map_event;
//        public TextView location_event, time_event, title_event;
//
//
//        public ItemViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
//            super(itemView);
//            //img
//            home_photo = itemView.findViewById(R.id.imgNews);
//            map_event = itemView.findViewById(R.id.map_event);
//            //txt
//            location_event = itemView.findViewById(R.id.locationNews);
//            time_event = itemView.findViewById(R.id.timeNews);
//            title_event = itemView.findViewById(R.id.titleNews);
//
//
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(listener!=null){
//                        int positon= getAdapterPosition();
//                        listener.OnItemClick(positon);
//                    }
//                }
//            });
//
//
//        }
//    }
//    public RecyclerViewAdapter(ArrayList<ItemHomeModel> itemHomeList){
//            mItemHomeList = itemHomeList;
//    }
//
//    @NonNull
//    @Override
//    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_layout_item, parent,false);
//        ItemViewHolder itemViewHolder = new ItemViewHolder(v, mListener);
//        return itemViewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
//            ItemHomeModel currentItem = mItemHomeList.get(position);
//            holder.home_photo.setImageResource(currentItem.getmHomeImage());
//        holder.map_event.setImageResource(currentItem.getmMapImage());
//
//        holder.title_event.setText(currentItem.getmTitle_event());
//        holder.time_event.setText(currentItem.getmTime_event());
//        holder.location_event.setText(currentItem.getmLocation_event());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mItemHomeList.size();
//    }




}
