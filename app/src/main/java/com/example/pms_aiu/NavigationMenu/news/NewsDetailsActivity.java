package com.example.pms_aiu.NavigationMenu.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pms_aiu.NavigationMenu.HomePageActivity;
import com.example.pms_aiu.NavigationMenu.SchOfLecturesFragment;
import com.example.pms_aiu.R;
import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {

    private Button backBtn;
    private TextView mTitleTxt, mTimeTxt, mLocationTxt, mDescriptionTxt;
    private ImageView mImageView;
    private LinearLayout mLocationLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Toolbar toolbar = findViewById(R.id.toolbar_profile); // id of your toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);// set the back arrow in toolbar



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });



        mTitleTxt = findViewById(R.id.titleNews);
        mTimeTxt = findViewById(R.id.timeNews);
        mLocationTxt = findViewById(R.id.locationNews);
        mDescriptionTxt = findViewById(R.id.txt_newsDetail);

        mImageView = findViewById(R.id.imgNews);
        mLocationLayout = findViewById(R.id.locationLayout);



        String title = getIntent().getStringExtra("Title");
        String time = getIntent().getStringExtra("Time");
        String location = getIntent().getStringExtra("Location");
        String description = getIntent().getStringExtra("Description");

        if(location.equals("")){
            mLocationLayout.setVisibility(View.GONE);
            mTitleTxt.setText(title);
            mTimeTxt.setText(time);
            mDescriptionTxt.setText(description);
            String getImageUrl = getIntent().getStringExtra("Image");
            Picasso.get().load(getImageUrl).into(mImageView);
        }else{
            mLocationLayout.setVisibility(View.VISIBLE);
            mTitleTxt.setText(title);
            mTimeTxt.setText(time);
            mLocationTxt.setText(location);
            mDescriptionTxt.setText(description);
            String getImageUrl = getIntent().getStringExtra("Image");
            Picasso.get().load(getImageUrl).into(mImageView);
        }

    }
}
