package com.example.pms_aiu.user.navMenu.grades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.pms_aiu.R;

public class MyGradesFragment extends Fragment {





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_grades, container, false);

//
//        Spinner spinner = root.findViewById(R.id.spinner_grades);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.yearAndTerm,
//                android.R.layout.simple_spinner_item);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return root;
    }


}