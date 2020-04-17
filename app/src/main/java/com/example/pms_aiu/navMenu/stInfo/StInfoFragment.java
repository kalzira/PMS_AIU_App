package com.example.pms_aiu.navMenu.stInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.pms_aiu.HomePageActivity;
import com.example.pms_aiu.R;

public class StInfoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_st_info, container, false);

        Toolbar toolbar = root.findViewById(R.id.toolbar_profile); // id of your toolbar
        toolbar.setNavigationIcon(R.drawable.arrow); // set the back arrow in toolbar


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HomePageActivity.class));
            }
        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        return root;
    }
}
