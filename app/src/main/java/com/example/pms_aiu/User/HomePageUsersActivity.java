package com.example.pms_aiu.User;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.example.pms_aiu.MainActivity;
import com.example.pms_aiu.Models.User;
import com.example.pms_aiu.R;
import com.example.pms_aiu.SignUpActivity;

import com.example.pms_aiu.User.navMenu.contact.ContactFragment;
import com.example.pms_aiu.User.navMenu.lectures.SchOfLecturesFragment;
import com.example.pms_aiu.User.navMenu.mail.MailFragment;
import com.example.pms_aiu.User.navMenu.news.NewsFragment;
import com.example.pms_aiu.User.navMenu.departmentSite.DepartmentSiteFragment;
import com.example.pms_aiu.User.navMenu.stInfo.StInfoFragment;

import com.example.pms_aiu.User.navMenu.universitySite.UniversitySiteFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.util.List;

public class HomePageUsersActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        //Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");


        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("News");
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        final TextView navId = (TextView) headerView.findViewById(R.id.stId_navHeader);
        final TextView navName = headerView.findViewById(R.id.stName_navHeader);


        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.child(firebaseAuth.getCurrentUser().getUid()).getValue(User.class);
                navId.setText(user.getStudentId());
                navName.setText(user.getFirstName() + " " + user.getLastName());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {


            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new NewsFragment()).commit();

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {


            //working
            case R.id.nav_studentInf:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StInfoFragment()).addToBackStack(null).commit();
                break;

                //working
            case R.id.nav_universitySite:
//
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UniversitySiteFragment()).addToBackStack(null).commit();
                break;

//working
            case R.id.nav_departmentSite:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DepartmentSiteFragment()).addToBackStack(null).commit();
                break;
//working
            case R.id.nav_schOfLectures:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SchOfLecturesFragment()).addToBackStack(null).commit();
                break;


                //working but need to add open current user mail??
            case R.id.nav_mail:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MailFragment()).addToBackStack(null).commit();
                break;

//working
            case R.id.nav_contacts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ContactFragment()).addToBackStack(null).commit();
                break;


            case R.id.nav_signOut:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(HomePageUsersActivity.this, MainActivity.class));
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
//        tellFragments();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
        }
    }

}
