package com.example.pms_aiu.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pms_aiu.ItemHomeModel;
import com.example.pms_aiu.R;
import com.example.pms_aiu.RecyclerViewAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        ArrayList<ItemHomeModel> homeList = new ArrayList<>();
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
        return root;

    }
}