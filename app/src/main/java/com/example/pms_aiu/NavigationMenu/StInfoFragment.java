package com.example.pms_aiu.NavigationMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.pms_aiu.Models.User;
import com.example.pms_aiu.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StInfoFragment extends Fragment {

    private TextView mStName, mStId, mEmail, mPhone, mDepartment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_st_info, container, false);

        Toolbar toolbar = root.findViewById(R.id.toolbar_profile); // id of your toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp); // set the back arrow in toolbar


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HomePageActivity.class));
            }
        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        //Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        mStName = root.findViewById(R.id.stName_profile);
        mStId = root.findViewById(R.id.stId_profile);
        mEmail = root.findViewById(R.id.email_profile);
        mPhone = root.findViewById(R.id.phone_profile);
        mDepartment = root.findViewById(R.id.department_profile);

        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.child(firebaseAuth.getCurrentUser().getUid()).getValue(User.class);
                String name = user.getFirstName()+" "+user.getLastName();
                mStName.setText(name);
                mStId.setText(user.getStudentId());
                mEmail.setText(user.getEmail());
                mPhone.setText(user.getPhone());
                mDepartment.setText(user.getDepartment());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return root;
    }
}
