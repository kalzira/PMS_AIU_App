package com.example.pms_aiu.Notification;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pms_aiu.Models.News;
import com.example.pms_aiu.Models.Notifications;
import com.example.pms_aiu.NavigationMenu.HomePageActivity;
import com.example.pms_aiu.NavigationMenu.news.RecyclerViewAdapter;
import com.example.pms_aiu.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;


public class NotificationsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterNotification mAdapter;


    private ProgressBar mProgressCircle;
    private DatabaseReference mDatabaseRef;
    private List<Notifications> mPushes;
    private LinearLayout mLocationLayout;


    private ValueEventListener mDBListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        Toolbar toolbar = root.findViewById(R.id.toolbar); // id of your toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp); // set the back arrow in toolbar


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HomePageActivity.class));
            }
        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();



        mRecyclerView = root.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        mProgressCircle = root.findViewById(R.id.progress_circle);

        mPushes = new ArrayList<>();
        mAdapter = new RecyclerViewAdapterNotification(root.getContext(), mPushes);
        mRecyclerView.setAdapter(mAdapter);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Notifications");


        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mPushes.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Notifications notifications = postSnapshot.getValue(Notifications.class);

                    notifications.setmKey(postSnapshot.getKey());
                    mPushes.add(notifications);

                }

                mAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);



            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });



        return root;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

}
