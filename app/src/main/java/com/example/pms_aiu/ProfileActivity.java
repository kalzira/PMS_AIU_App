package com.example.pms_aiu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

public class ProfileActivity extends AppCompatActivity {

//    Toolbar mActionBarToolbar;

    private Button arrowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_profile);
//        mActionBarToolbar.setTitle("Student Information");
//        setSupportActionBar(mActionBarToolbar);

        arrowBtn = findViewById(R.id.arrowBack_profile);
        arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
