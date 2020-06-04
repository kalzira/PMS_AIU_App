package com.example.pms_aiu.NavigationMenu;


import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.example.pms_aiu.MainActivity;
import com.example.pms_aiu.Models.User;

import com.example.pms_aiu.Notification.NotificationsFragment;
import com.example.pms_aiu.Notification.SendActivity;
import com.example.pms_aiu.R;

import com.example.pms_aiu.SignUpActivity;
import com.example.pms_aiu.NavigationMenu.news.NewsFragment;

import com.example.pms_aiu.Students.StudentsFragment;
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

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


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

        final NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        final TextView navId = (TextView) headerView.findViewById(R.id.stId_navHeader);
        final TextView navName = headerView.findViewById(R.id.stName_navHeader);
        ImageView navImage = headerView.findViewById(R.id.iconProfile_navHeader);





       if (firebaseAuth.getCurrentUser() == null) {
            navName.setText("Welcome!");
            navId.setVisibility(View.INVISIBLE);
            navImage.setVisibility(View.INVISIBLE);

            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_home_guest_page_drawer);


        }
       else if(firebaseAuth.getCurrentUser().getEmail().equals("pmsaiuapp@gmail.com")){
           final DatabaseReference table_admin = database.getReference("Admin");
           navName.setText("Admin Page");
           navId.setVisibility(View.INVISIBLE);
           navImage.setVisibility(View.INVISIBLE);


           navigationView.getMenu().clear();
           navigationView.inflateMenu(R.menu.activity_home_admin_page_drawer);

       }
       else {
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
        }







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



            case R.id.nav_studentInf:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StInfoFragment()).addToBackStack(null).commit();
                break;


            case R.id.nav_admin_universitySite:
            case R.id.nav_guest_universitySite:
            case R.id.nav_universitySite:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UniversitySiteFragment()).addToBackStack(null).commit();
                break;


            case R.id.nav_departmentSite:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DepartmentSiteFragment()).addToBackStack(null).commit();
                break;

            case R.id.nav_schOfLectures:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SchOfLecturesFragment()).addToBackStack(null).commit();
                break;



            case R.id.nav_mail:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MailFragment()).addToBackStack(null).commit();
                break;


            case R.id.nav_contacts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ContactFragment()).addToBackStack(null).commit();
                break;


            case R.id.nav_admin_signOut:
            case R.id.nav_signOut:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(HomePageActivity.this, MainActivity.class));
                break;


            case R.id.nav_guest_faculties:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FacultiesSiteFragment()).addToBackStack(null).commit();
                break;

            case R.id.nav_guest_signUp:
                startActivity(new Intent(HomePageActivity.this, SignUpActivity.class));
                break;
                case R.id.nav_admin_send_notifications:

            startActivity(new Intent(HomePageActivity.this, SendActivity.class));
            break;

            case R.id.nav_notifications:
            case R.id.nav_admin_notifications:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NotificationsFragment()).addToBackStack(null).commit();
                break;

            case R.id.nav_admin_students:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StudentsFragment()).addToBackStack(null).commit();
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
