package com.example.pms_aiu.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;

import android.view.View;
import android.widget.TextView;


import com.example.pms_aiu.R;

public class NotificationDetailActivity extends AppCompatActivity {

    private TextView mTitle, mMessage, mDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        Toolbar toolbar = findViewById(R.id.toolbar); // id of your toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);// set the back arrow in toolbar


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });


        mTitle = findViewById(R.id.titlePush);
        mMessage = findViewById(R.id.messagePush);
        mDescription = findViewById(R.id.txt_pushDetails);

        String title = getIntent().getStringExtra("title");
        String message = getIntent().getStringExtra("message");
        String description = getIntent().getStringExtra("description");

        mTitle.setText(title);
        mMessage.setText(message);
        mDescription.setText(description);


    }
}

