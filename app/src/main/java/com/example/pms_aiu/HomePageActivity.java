package com.example.pms_aiu;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;

public class HomePageActivity extends AppCompatActivity {

    private Button iconProfileBtn;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.bitmap);
        toolbar.setTitle(null);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_studentInf, R.id.nav_schOfLectures, R.id.nav_myGrades,
                R.id.nav_transcript, R.id.nav_qa, R.id.nav_mail)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.getMenu().getItem(0).setActionView(R.layout.icon_cap);
        navigationView.getMenu().getItem(1).setActionView(R.layout.icon_calendar);
        navigationView.getMenu().getItem(2).setActionView(R.layout.icon_marks);
        navigationView.getMenu().getItem(3).setActionView(R.layout.icon_grade);
        navigationView.getMenu().getItem(4).setActionView(R.layout.icon_qa);
        navigationView.getMenu().getItem(5).setActionView(R.layout.icon_mail);


        View header = navigationView.getHeaderView(0);
        ImageView mImgView=  header.findViewById(R.id.iconProfile);

        mImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileActivity = new Intent(HomePageActivity.this, ProfileActivity.class);
                startActivity(profileActivity);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
