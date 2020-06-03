package com.example.pms_aiu.NavigationMenu;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.pms_aiu.Admin.EditNewsActivity;
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
    private TextView mEditFName, mEditLName, mEditPhone, mEditDepartment;
    private LinearLayout mNameLayout;
    private Button mEditBtn;
    private Button mSaveBtn;

    private DatabaseReference table_user;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

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
        firebaseAuth = FirebaseAuth.getInstance();
        //Initialize Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        table_user = firebaseDatabase.getReference("User");


        mStName = root.findViewById(R.id.stName_profile);
        mStId = root.findViewById(R.id.stId_profile);
        mEmail = root.findViewById(R.id.email_profile);
        mPhone = root.findViewById(R.id.phone_profile);
        mDepartment = root.findViewById(R.id.department_profile);
        mEditBtn = root.findViewById(R.id.editProfileBtn);

        mNameLayout = root.findViewById(R.id.stName_edit_profile);
        mEditFName = root.findViewById(R.id.stFName_edit_profile);
        mEditLName = root.findViewById(R.id.stLName_edit_profile);
        mEditPhone = root.findViewById(R.id.phone_edit_profile);
        mEditDepartment = root.findViewById(R.id.department_edit_profile);
        mSaveBtn = root.findViewById(R.id.saveProfileBtn);



        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.child(firebaseAuth.getCurrentUser().getUid()).getValue(User.class);
                String name = user.getFirstName()+"  "+user.getLastName();
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

        mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });


        return root;
    }

    private void updateProfile() {
        String fName = mStName.getText().toString().split("  ")[0];
        String lName = mStName.getText().toString().split("  ")[1];
        String phone = mPhone.getText().toString().trim();
        String department = mDepartment.getText().toString().trim();

        mSaveBtn.setVisibility(View.VISIBLE);
        mStName.setVisibility(View.GONE);
        mNameLayout.setVisibility(View.VISIBLE);
        mEditFName.setVisibility(View.VISIBLE);
        mEditLName.setVisibility(View.VISIBLE);
        mEditFName.setText(fName);
        mEditLName.setText(lName);

        mPhone.setVisibility(View.GONE);
        mEditPhone.setVisibility(View.VISIBLE);
        mEditPhone.setText(phone);
        mDepartment.setVisibility(View.GONE);
        mEditDepartment.setVisibility(View.VISIBLE);
        mEditDepartment.setText(department);


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.child(firebaseAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("firstName").setValue(mEditFName.getText().toString().trim());
                        dataSnapshot.getRef().child("lastName").setValue(mEditLName.getText().toString().trim());
                        dataSnapshot.getRef().child("phone").setValue(mEditPhone.getText().toString().trim());
                        dataSnapshot.getRef().child("department").setValue(mEditDepartment.getText().toString().trim());

                        Toast.makeText(getActivity(), "Profile Information Updated!", Toast.LENGTH_SHORT).show();
                        refreshFragment();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(), ""+databaseError, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    private void refreshFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();
    }
}
