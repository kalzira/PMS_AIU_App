package com.example.pms_aiu.NavigationMenu.news;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pms_aiu.Models.News;
import com.example.pms_aiu.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private FloatingActionButton mAddNews;


    private ProgressBar mProgressCircle;
    private DatabaseReference mDatabaseRef;
    private List<News> mNews;
private LinearLayout mLocationLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        mAddNews = root.findViewById(R.id.fab_addNews);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            mAddNews.setVisibility(View.INVISIBLE);
        } else {
            if (firebaseAuth.getCurrentUser().getEmail().equals("pmsaiuapp@gmail.com")) {
                mAddNews.setVisibility(View.VISIBLE);
            } else {
                mAddNews.setVisibility(View.INVISIBLE);
            }
        }
        mLocationLayout = root.findViewById(R.id.locationLayout);
        mRecyclerView = root.findViewById(R.id.recyclerView_home);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        mProgressCircle = root.findViewById(R.id.progress_circle);
        mNews = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("News");


        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    News news = postSnapshot.getValue(News.class);
                    mNews.add(news);

                }

                    mAdapter = new RecyclerViewAdapter(root.getContext(), mNews);
                    mRecyclerView.setAdapter(mAdapter);
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
}