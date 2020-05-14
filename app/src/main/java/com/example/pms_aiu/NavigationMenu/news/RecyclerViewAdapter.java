package com.example.pms_aiu.NavigationMenu.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pms_aiu.Models.News;
import com.example.pms_aiu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {

    private Context mContext;
    private List<News> mNews;
    private LinearLayout mLocationView;


    public RecyclerViewAdapter(Context context, List<News> news) {
        mContext = context;
        mNews = news;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.home_layout_item, parent, false);
        mLocationView = v.findViewById(R.id.locationLayout);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        News newsCurrent = mNews.get(position);

        if(newsCurrent.getLocation().equals("")){

            holder.mapView.setVisibility(GONE);
            holder.locationView.setVisibility(GONE);
            holder.locationView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            holder.mapView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            holder.titleView.setText(newsCurrent.getTitle());
            holder.timeView.setText(newsCurrent.getTime());
            Picasso.get().
                    load(newsCurrent.getImage())
                    .fit()
                    .centerCrop()
                    .into(holder.imageView);
        }else{
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

}
