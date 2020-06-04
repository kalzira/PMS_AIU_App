package com.example.pms_aiu.Students;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pms_aiu.Models.Notifications;
import com.example.pms_aiu.Models.User;
import com.example.pms_aiu.Notification.NotificationDetailActivity;
import com.example.pms_aiu.R;

import java.util.List;

public class RecyclerViewAdapterStudents extends RecyclerView.Adapter<RecyclerViewAdapterStudents.ItemViewHolder> {

    private Context mContext;
    private List<User> mUsers;

    ///
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
///

    public RecyclerViewAdapterStudents(Context context, List<User> users ) {
        mContext = context;
        mUsers = users;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.student_layout_item, parent, false);

        return new ItemViewHolder(v,  mListener);
    }



    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        final User currentUser = mUsers.get(position);


            holder.fName.setText(currentUser.getFirstName());
            holder.lName.setText(currentUser.getLastName());
        holder.department.setText(currentUser.getDepartment());







    }
    @Override
    public int getItemCount() {
        return mUsers.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView fName, lName, department;
        public Button delUser;


        public ItemViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            fName = itemView.findViewById(R.id.fName);
            lName = itemView.findViewById(R.id.lName);
            department = itemView.findViewById(R.id.Department);
            delUser = itemView.findViewById(R.id.btnDeleteUser);

            delUser.setOnClickListener(new View.OnClickListener() {
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
