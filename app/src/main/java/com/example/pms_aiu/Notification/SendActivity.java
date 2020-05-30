package com.example.pms_aiu.Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pms_aiu.Models.Notifications;
import com.example.pms_aiu.NavigationMenu.HomePageActivity;
import com.example.pms_aiu.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class SendActivity extends AppCompatActivity {



    private EditText etTxtTitle;
    private EditText etTxtMessage;
    private EditText etTxtDescription;
    private Button sendBtn;


    private String title, message, description;

    private RequestQueue mRequestQueue;
    private String URL = "https://fcm.googleapis.com/fcm/send";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_send);

        Toolbar toolbar = findViewById(R.id.toolbar); // id of your toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp); // set the back arrow in toolbar




        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SendActivity.this, HomePageActivity.class));
            }
        });


        etTxtTitle = findViewById(R.id.edit_text_title);
        etTxtMessage = findViewById(R.id.edit_text_message);
        etTxtDescription = findViewById(R.id.edit_text_description);

        mRequestQueue = Volley.newRequestQueue(this);
        FirebaseMessaging.getInstance().subscribeToTopic("push");



        sendBtn = findViewById(R.id.sendPushBtn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTxtTitle.getText().toString().trim();
                message = etTxtMessage.getText().toString().trim();
                description = etTxtDescription.getText().toString().trim();

                if(TextUtils.isEmpty(title)){
                    etTxtTitle.setError("title is required");
                    return;
                }
                if(TextUtils.isEmpty(message)){
                    etTxtMessage.setError("message is required");
                    return;
                }
                sendOnChannel1();
            }
        });


    }

    private void sendOnChannel1() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("to","/topics/"+"push");
            JSONObject notificationObj = new JSONObject();
            notificationObj.put("title", title);
            notificationObj.put("body", message);
            JSONObject extraData = new JSONObject();
            extraData.put("description", description);
            jsonObject.put("data", extraData);
            jsonObject.put("notification", notificationObj);



            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL,
                    jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {



                        }
                    }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    //
                    Toast.makeText(SendActivity.this, error.networkResponse+"", Toast.LENGTH_SHORT).show();

                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> header = new HashMap<>();
                    header.put("content-type", "application/json");
                    header.put("authorization","key=AAAA6qeKY3k:APA91bEmhkcovCEaUvOjz3-TDmKY2xygZtrIiyUaq-XrrOSRVZ5hUgsN0TEq3OHC_kMv4b1ml_q4WFwnknyZ9685UPHFDkZ2FpqCi8RlAaxMdfUqwXUiZbDq0zQi3FA_sUqTKTAQuhJM");
                    return header;
                }
            };
            mRequestQueue.add(request);


        }catch (JSONException e){
            e.printStackTrace();
        }


        etTxtTitle.setText("");
        etTxtMessage.setText("");
        etTxtDescription.setText("");




    }



}

