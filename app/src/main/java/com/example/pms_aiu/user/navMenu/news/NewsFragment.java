package com.example.pms_aiu.user.navMenu.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pms_aiu.R;

import java.util.ArrayList;

public class NewsFragment extends Fragment {



    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        final ArrayList<ItemHomeModel> homeList = new ArrayList<>();
        homeList.add(new ItemHomeModel(R.drawable.home_photo, R.drawable.map, "SATURDAY 25 JANUARY", "Today", "11:00-13:00", "Software engineering \nSeminar", "Other Events","Nurlan Shaidullaev", "A 309"));
        homeList.add(new ItemHomeModel(R.drawable.home_photo2, R.drawable.map, "Tomorrow\\n15:00-17:00", "Study abroad \nSeminar", "Oleg Dzhumanaliev", "Konya Hall"));
        homeList.add(new ItemHomeModel(R.drawable.home_photo, R.drawable.map, "13:00", "SMM", "Olga Kan", "B 205"));
        homeList.add(new ItemHomeModel(R.drawable.home_photo2, R.drawable.map, "Tomorrow\\n15:00-17:00", "Study abroad \nSeminar", "Oleg Dzhumanaliev", "Konya Hall"));
        homeList.add(new ItemHomeModel(R.drawable.home_photo, R.drawable.map, "13:00", "SMM", "Olga Kan", "B 205"));


        mRecyclerView= root.findViewById(R.id.recyclerView_home);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mAdapter= new RecyclerViewAdapter(homeList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent newsDetail = new Intent(getActivity(), NewsDetailsActivity.class);
                startActivity(newsDetail);
            }
        });
        return root;

    }
}