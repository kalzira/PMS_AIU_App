package com.example.pms_aiu.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pms_aiu.Models.News;
import com.example.pms_aiu.R;
import com.example.pms_aiu.NavigationMenu.HomePageActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddNewsActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Button mBtnUploadImg, mBtnAddNews;
    private EditText mEtTitle, mEtTime, mEtPlace;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Uri mImageUri;


    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        Toolbar toolbar = findViewById(R.id.toolbar_add_news); // id of your toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp); // set the back arrow in toolbar

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNewsActivity.this, HomePageActivity.class));
            }
        });

        mBtnUploadImg = findViewById(R.id.uploadImgBtn);
        mBtnAddNews = findViewById(R.id.addNewsBtn);

        mEtTitle = findViewById(R.id.etTitleNews);
        mEtTime = findViewById(R.id.etTimeEvent);
        mEtPlace = findViewById(R.id.etPlaceEvent);


        mImageView = findViewById(R.id.uploadedImgView);
        mProgressBar = findViewById(R.id.progress_bar_add_news);


        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("News");

        mBtnUploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();

            }
        });



        mBtnAddNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(AddNewsActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    uploadFile();
                }
            }
        });


    }

    private void uploadFile() {

        if (mImageUri != null)
        {
            final StorageReference filepath = mStorageRef.child("news/"+ mEtTitle.getText().toString().trim());
            filepath.putFile(mImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
            {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                {
                    if (!task.isSuccessful())
                    {
                        throw task.getException();
                    }
                    return filepath.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>()
            {
                @Override
                public void onComplete(@NonNull Task<Uri> task)
                {
                    if (task.isSuccessful())
                    {
                        mProgressBar.setVisibility(View.GONE);
                        Uri downloadUri = task.getResult();
                        Toast.makeText(AddNewsActivity.this, "Upload successful", Toast.LENGTH_LONG).show();


                        News news = new News(mEtTitle.getText().toString().trim(),
                                downloadUri.toString(), mEtTime.getText().toString().trim(),
                                mEtPlace.getText().toString().trim());

                        mDatabaseRef.push().setValue(news);
                        mEtTitle.setText("");
                        mEtPlace.setText("");
                        mEtTime.setText("");
                        mImageView.setVisibility(View.INVISIBLE);
                    } else
                    {
                        Toast.makeText(AddNewsActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(mImageView);
            mImageView.setVisibility(View.VISIBLE);
        }
    }


}
