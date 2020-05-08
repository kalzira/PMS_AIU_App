package com.example.pms_aiu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pms_aiu.User.HomePageUsersActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signUpBtn;
    private TextView signInGuestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        signInGuestBtn = findViewById(R.id.loginGuestBtn);
        signInGuestBtn.setOnClickListener(this);

        signUpBtn = findViewById(R.id.signUpBtn_main);
        signUpBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginGuestBtn:
                Intent signInActivity = new Intent(MainActivity.this, HomePageUsersActivity.class);
                startActivity(signInActivity);
                finish();
                break;
            case R.id.signUpBtn_main:
                Intent signUpActivity = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(signUpActivity);
                finish();
                break;

        }
    }
}
