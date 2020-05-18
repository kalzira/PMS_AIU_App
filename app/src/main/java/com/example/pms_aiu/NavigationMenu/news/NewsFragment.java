package com.example.pms_aiu.NavigationMenu.news;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pms_aiu.Admin.AddNewsActivity;
import com.example.pms_aiu.Models.News;
import com.example.pms_aiu.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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

    private FirebaseStorage mStorage;
    private ValueEventListener mDBListener;


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

                mAddNews.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), AddNewsActivity.class);
                        startActivity(intent);
                    }
                });

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
        mAdapter = new RecyclerViewAdapter(root.getContext(), mNews);
        mRecyclerView.setAdapter(mAdapter);


        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("News");

        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(final int position) {


                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setMessage("Are you sure want to delete this news?");
                alert.setTitle("WARNING");


                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {


                        News selectedNews = mNews.get(position);
                        final String selectedKey = selectedNews.getKey();


                        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedNews.getImage());

                        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                mDatabaseRef.child(selectedKey).removeValue();
                                Toast.makeText(getActivity(), "News deleted", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = alert.create();

                dialog.show();

                // Get the alert dialog buttons reference
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);


                positiveButton.setTextColor(Color.parseColor("#FF0B8B42"));
                positiveButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));

                negativeButton.setTextColor(Color.parseColor("#FFFF0400"));
                negativeButton.setBackgroundColor(Color.parseColor("#FFFCB9B7"));









            }
        });

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mNews.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    News news = postSnapshot.getValue(News.class);
                    news.setKey(postSnapshot.getKey());
                    mNews.add(news);

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