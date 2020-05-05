package com.example.pms_aiu;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;

import com.example.pms_aiu.navMenu.grades.MyGradesFragment;
import com.example.pms_aiu.navMenu.news.NewsFragment;
import com.example.pms_aiu.navMenu.stInfo.StInfoFragment;
import com.example.pms_aiu.navMenu.transcript.TranscriptFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null) {


            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new NewsFragment()).commit();

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.nav_studentInf:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StInfoFragment()).commit();
                    break;

            case R.id.nav_schOfLectures:
                String urlSch = "http://com.iaau.edu.kg/calendar/schedule-of-lectures.html";
                Intent schLecIntent = new Intent(Intent.ACTION_VIEW);
                schLecIntent.setData(Uri.parse(urlSch));
                startActivity(schLecIntent);
                break;
                case R.id.nav_myGrades:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new MyGradesFragment()).commit();
                    break;

            case R.id.nav_transcript:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TranscriptFragment()).commit();
                break;

//            case R.id.nav_qa:


            case R.id.nav_mail:
                String urlMail = "https://mail.google.com/mail/u/";
                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                mailIntent.setData(Uri.parse(urlMail));
                startActivity(mailIntent);
                break;

            case R.id.nav_contacts:
                String urlContacts = "http://www.iaau.edu.kg/view/public/pages/page.xhtml;jsessionid=gl2CjdudoObLpX_mGm8McmCuRPgNqDA6EyfwZPep.unknown-host?id=153";
                Intent contactsIntent = new Intent(Intent.ACTION_VIEW);
                contactsIntent.setData(Uri.parse(urlContacts));
                startActivity(contactsIntent);
                break;

            case R.id.nav_signOut:
                Intent signOutIntent = new Intent(HomePageActivity.this, SignUpActivity.class);
                finish();
                startActivity(signOutIntent);
                break;






        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else{
        super.onBackPressed();}
    }


}
