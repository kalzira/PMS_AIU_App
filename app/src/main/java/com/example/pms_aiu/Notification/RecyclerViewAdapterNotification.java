package com.example.pms_aiu.Notification;

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
import com.example.pms_aiu.Models.Notifications;
import com.example.pms_aiu.NavigationMenu.news.NewsDetailsActivity;
import com.example.pms_aiu.R;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.view.View.GONE;

public class RecyclerViewAdapterNotification extends RecyclerView.Adapter<RecyclerViewAdapterNotification.ItemViewHolder> {

    private Context mContext;
    private List<Notifications> mNotifications;



    public RecyclerViewAdapterNotification(Context context, List<Notifications> notifications ) {
        mContext = context;
        mNotifications = notifications;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.notification_layout_item, parent, false);

        return new ItemViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        final Notifications notificationsCurrent = mNotifications.get(position);


            holder.titleView.setText(notificationsCurrent.getTitlePush());
            holder.messageView.setText(notificationsCurrent.getMessage());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pushDetails = new Intent(v.getContext(), NotificationDetailActivity.class);
                pushDetails.putExtra("title", notificationsCurrent.getTitlePush());
                pushDetails.putExtra("message", notificationsCurrent.getMessage());
                pushDetails.putExtra("description", notificationsCurrent.getDescription());

                v.getContext().startActivity(pushDetails);

            }
        });



    }
    @Override
    public int getItemCount() {
        return mNotifications.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView titleView, messageView;


        public ItemViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.titlePush);
            messageView = itemView.findViewById(R.id.messagePush);



        }



    }



}
