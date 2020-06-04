package com.example.pms_aiu.NavigationMenu.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.pms_aiu.Admin.EditNewsActivity;
import com.example.pms_aiu.Models.News;
import com.example.pms_aiu.R;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;


import java.util.List;

import static android.view.View.GONE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {

    private Context mContext;
    private List<News> mNews;


    ///
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
///

    public RecyclerViewAdapter(Context context, List<News> news ) {
        mContext = context;
        mNews = news;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.home_layout_item, parent, false);

        return new ItemViewHolder(v, mListener);
    }



    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        final News newsCurrent = mNews.get(position);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser()!=null){
            if(firebaseAuth.getCurrentUser().getEmail().equals("pmsaiuapp@gmail.com")){
                holder.btnEdit.setVisibility(View.VISIBLE);
                holder.btnDelete.setVisibility(View.VISIBLE);
            }else{
                holder.btnEdit.setVisibility(GONE);
                holder.btnDelete.setVisibility(GONE);
            }
        }else {

            holder.btnEdit.setVisibility(GONE);
            holder.btnDelete.setVisibility(GONE);
        }

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
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newsDetails = new Intent(v.getContext(), NewsDetailsActivity.class);
                newsDetails.putExtra("Title", newsCurrent.getTitle());
                newsDetails.putExtra("Image", newsCurrent.getImage());
                newsDetails.putExtra("Location", newsCurrent.getLocation());
                newsDetails.putExtra("Description", newsCurrent.getDescription());
                newsDetails.putExtra("Time", newsCurrent.getTime());

                v.getContext().startActivity(newsDetails);

            }
        });


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsEdit = new Intent(v.getContext(), EditNewsActivity.class);
               newsEdit.putExtra("position", holder.getAdapterPosition());
                newsEdit.putExtra("Title", newsCurrent.getTitle());
                newsEdit.putExtra("Image", newsCurrent.getImage());
                newsEdit.putExtra("Location", newsCurrent.getLocation());
                newsEdit.putExtra("Description", newsCurrent.getDescription());
                newsEdit.putExtra("Time", newsCurrent.getTime());
                newsEdit.putExtra("key", newsCurrent.getKey());
               v.getContext().startActivity(newsEdit);
            }
        });

    }
    @Override
    public int getItemCount() {
        return mNews.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView titleView, timeView, locationView;
        public ImageView imageView, mapView;
        public Button btnEdit, btnDelete;
        private FirebaseAuth firebaseAuth;
        public ItemViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            titleView = itemView.findViewById(R.id.titleNews);
            timeView = itemView.findViewById(R.id.timeNews);
            locationView = itemView.findViewById(R.id.locationNews);
            imageView = itemView.findViewById(R.id.imgNews);
            mapView = itemView.findViewById(R.id.map_event);



            btnEdit  = itemView.findViewById(R.id.btnEditNews);
            btnDelete = itemView.findViewById(R.id.btnDeleteNews);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int positon= getAdapterPosition();
                        listener.OnItemClick(positon);
                    }
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
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



}
