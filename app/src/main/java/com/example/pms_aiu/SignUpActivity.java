package com.example.pms_aiu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;

public class SignUpActivity extends AppCompatActivity{


    private Button mSignUpBtn, mCloseBtn;

    private TextView mLoginBtn;

    private EditText mName, mSurname, mPhone, mId, mEmail;

    private ProgressBar progressBar;


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

        mName= findViewById(R.id.etName);
        mSurname= findViewById(R.id.etSurname);
        mPhone = findViewById(R.id.etPhone);
        mId= findViewById(R.id.etId);
        mEmail = findViewById(R.id.etEmail);


        progressBar = findViewById(R.id.progressBar);


        mSignUpBtn = findViewById(R.id.signUpBtn);

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String name = mName.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String id = mId.getText().toString().trim();
                String surname = mSurname.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    mName.setError("Name is required");
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
                if(TextUtils.isEmpty(surname)){
                    mSurname.setError("Surname is required");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.createUserWithEmailAndPassword(email, id).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            firebaseAuth.getCurrentUser().sendEmailVerification().
                                    addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(SignUpActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        else{
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });










    }

}
