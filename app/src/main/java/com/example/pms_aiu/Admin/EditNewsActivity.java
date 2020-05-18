package com.example.pms_aiu.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pms_aiu.Models.News;
import com.example.pms_aiu.NavigationMenu.HomePageActivity;
import com.example.pms_aiu.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EditNewsActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button mBtnUploadImg, mBtnAddNews, mAddDescriptionBtn;
    private EditText mEtTitle, mEtTime, mEtPlace;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Uri mImageUri;

    private List<News> mNews;


    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;

    private String descriptionTxt;
    private int position;
    private FirebaseStorage mStorage;
    private StorageReference imageRef;
    private News selectedNews;

    private String selectedKey;
    private String uri;


    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_news);

        Toolbar toolbar = findViewById(R.id.toolbar_add_news); // id of your toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp); // set the back arrow in toolbar


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditNewsActivity.this, HomePageActivity.class));
            }
        });


        mBtnAddNews = findViewById(R.id.addNewsBtn);


        String title = getIntent().getStringExtra("Title");
        String time = getIntent().getStringExtra("Time");
        String location = getIntent().getStringExtra("Location");
        final String description = getIntent().getStringExtra("Description");



        mEtTitle = findViewById(R.id.etTitleNews);
        mEtTime = findViewById(R.id.etTimeEvent);
        mEtPlace = findViewById(R.id.etPlaceEvent);



        mEtTitle.setText(title);
        mEtTime.setText(time);
        mEtPlace.setText(location);


        mNews = new ArrayList<>();

        mAddDescriptionBtn = findViewById(R.id.addDescriptionBtn);





        position = getIntent().getIntExtra("position", -1);




        key = getIntent().getStringExtra("key");

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("News").child(key);





        descriptionTxt = description;

        mAddDescriptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(EditNewsActivity.this);
                final EditText edittext = new EditText(getApplicationContext());
                edittext.setText(description);
                alert.setMessage("Please fill form to add news description");
                alert.setTitle("Add Description");

                alert.setView(edittext);

                alert.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {


                        descriptionTxt = edittext.getText().toString();
                        if(TextUtils.isEmpty(descriptionTxt.trim())){
                            edittext.setText("Please fill description");

                        }

                    }
                });

                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });

                alert.show();
            }
        });

        mBtnAddNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(TextUtils.isEmpty(mEtTime.getText().toString().trim())){
                        mEtTime.setError("Please specify event time");
                        return;
                    }
                    if(TextUtils.isEmpty(mEtTitle.getText().toString().trim())){
                        mEtTitle.setError("Please set title for news");
                        return;
                    }
                    if (TextUtils.isEmpty(descriptionTxt)){
                        Toast.makeText(EditNewsActivity.this, "Please add description of news",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }


                    update();






            }
        });


    }

    private void update() {
        mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("title").setValue(mEtTitle.getText().toString().trim());
                dataSnapshot.getRef().child("time").setValue(mEtTime.getText().toString().trim());
                dataSnapshot.getRef().child("location").setValue(mEtPlace.getText().toString().trim());
                dataSnapshot.getRef().child("description").setValue(descriptionTxt.trim());

                Toast.makeText(EditNewsActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}
