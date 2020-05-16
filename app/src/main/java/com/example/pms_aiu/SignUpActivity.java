package com.example.pms_aiu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pms_aiu.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity{


    private Button mSignUpBtn, mCloseBtn;

    private TextView mLoginBtn;

    private EditText mFirstName, mLastName, mPhone, mId, mEmail, mPassword, mDepartment;



    private ProgressBar progressBar;



    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mCloseBtn = findViewById(R.id.closeBtn);
        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent welcomeIntent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(welcomeIntent);
            }
        });
        mLoginBtn = findViewById(R.id.loginBtn);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent welcomeIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(welcomeIntent);
            }
        });

        mFirstName = findViewById(R.id.etFirstName);
        mLastName = findViewById(R.id.etLastName);
        mPhone = findViewById(R.id.etPhone);
        mId= findViewById(R.id.etID);
        mEmail = findViewById(R.id.etEmail);
        mPassword = findViewById(R.id.etPsw);
        mDepartment = findViewById(R.id.etDepartment);


        progressBar = findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();
        mSignUpBtn = findViewById(R.id.signUpBtn);


        //Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");


        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                final String firstName = mFirstName.getText().toString().trim();
                final String phone = mPhone.getText().toString().trim();
                final String id = mId.getText().toString().trim();
                final String lastName = mLastName.getText().toString().trim();
                final String psw = mPassword.getText().toString().trim();
                final String department = mDepartment.getText().toString().toUpperCase().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(firstName)){
                    mFirstName.setError("First Name is required");
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    mPhone.setError("Phone is required");
                    return;
                }
                if(TextUtils.isEmpty(id)){
                    mId.setError("ID is required");
                    return;
                }
                if(TextUtils.isEmpty(lastName)){
                    mLastName.setError("Last Name is required");
                    return;
                }
                if(TextUtils.isEmpty(psw)){
                    mPassword.setError("Password is required");
                    return;
                }
                if(TextUtils.isEmpty(department)){
                    mDepartment.setError("Department is required");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                firebaseAuth.createUserWithEmailAndPassword(email, psw).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            user.sendEmailVerification().
                                    addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){


                                                    final AlertDialog alertDialog = new AlertDialog.Builder(
                                                            SignUpActivity.this)

                                                            .setIcon(android.R.drawable.ic_dialog_email)

                                                            .setMessage("Last Step! " +
                                                                    "Please check your e-mail " +
                                                                    "in order to complete registration")

                                                            .setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    //set what would happen when positive button is clicked

                                                                    dialogInterface.cancel();

                                                                    startActivity(new Intent(SignUpActivity.this,
                                                                            LoginActivity.class));
                                                                    finish();
                                                                    table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                                                                            User user = new User(firstName,lastName,id,email,phone,department);
                                                                            table_user.child(firebaseAuth.getCurrentUser().getUid()).setValue(user);

                                                                        }

                                                                        @Override
                                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                        }
                                                                    });



                                                                }
                                                            })

                                                            .show();



                                            }
                                            else{
                                                Toast.makeText(SignUpActivity.this,
                                                        task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });


                        }

                        else{
                            Toast.makeText(SignUpActivity.this,
                                    task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });

            }
        });










    }



}
