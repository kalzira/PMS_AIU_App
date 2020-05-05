package com.example.pms_aiu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pms_aiu.user.HomePageUsersActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity{


    private Button mSignUpBtn, mCloseBtn;

    private TextView mLoginBtn;

    private EditText mName, mSurname, mPhone, mId, mEmail, mPassword;
    private Spinner mFaculties_sp, mDepartments_sp;


    private ProgressBar progressBar;

    ArrayList<String> arrayList_faculty,
            arrayList_engineering, arrayList_social,arrayList_economics, arrayList_medicine,
    arrayList_vocational, arrayList_distance, arrayList_lang;
    ArrayAdapter<String> arrayAdapter_faculty, arrayAdapter_department;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mCloseBtn = findViewById(R.id.closeBtn);
        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent welcomeIntent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(welcomeIntent);
            }
        });
        mLoginBtn = findViewById(R.id.loginBtn);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent welcomeIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(welcomeIntent);
            }
        });

        mName= findViewById(R.id.etFirstName);
        mSurname= findViewById(R.id.etLastName);
        mPhone = findViewById(R.id.etPhone);
        mId= findViewById(R.id.etID);
        mEmail = findViewById(R.id.etEmail);
        mPassword = findViewById(R.id.etPsw);


        progressBar = findViewById(R.id.progressBar);


        mSignUpBtn = findViewById(R.id.signUpBtn);
        mFaculties_sp = findViewById(R.id.spinner_faculty);
        mDepartments_sp = findViewById(R.id.spinner_department);

//Create faculty and department spinners content
        fillSpinners();


        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String name = mName.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String id = mId.getText().toString().trim();
                String surname = mSurname.getText().toString().trim();
                String psw = mPassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    mName.setError("Name is required");
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    mPhone.setError("Phone is required");
                    return;
                }
                if(TextUtils.isEmpty(id)){
                    mId.setError("ID is required");
                    return;
                }
                if(TextUtils.isEmpty(surname)){
                    mSurname.setError("Surname is required");
                    return;
                }
                if(TextUtils.isEmpty(psw)){
                    mPassword.setError("Password is required");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.createUserWithEmailAndPassword(email, psw).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            firebaseAuth.getCurrentUser().sendEmailVerification().
                                    addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                    final AlertDialog alertDialog = new AlertDialog.Builder(SignUpActivity.this)

                                                            .setIcon(android.R.drawable.ic_dialog_email)

                                                            .setMessage("Last Step! " +
                                                                    "Please check your e-mail in order to complete registration")

                                                            .setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                                    //set what would happen when positive button is clicked
                                                                   dialogInterface.cancel();
                                                                        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));

                                                                }
                                                            })

                                                            .show();
                                            }
                                            else{
                                                Toast.makeText(SignUpActivity.this,
                                                        task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });


                        }

                        else{
                            Toast.makeText(SignUpActivity.this,
                                    task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });

            }
        });










    }

    private void fillSpinners() {

        //faculties
        arrayList_faculty = new ArrayList<>();
        arrayList_faculty.add("Faculty of Engineering and Informatics");
        arrayList_faculty.add("Faculty of Social Sciences");
        arrayList_faculty.add("Faculty of Economics and Administrative Sciences");
        arrayList_faculty.add("Faculty of Medicine");
        arrayList_faculty.add("Vocational education");
        arrayList_faculty.add("Distance Learning Center");
        arrayList_faculty.add("Institute of Languages");

        arrayAdapter_faculty = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_faculty);
        mFaculties_sp.setAdapter(arrayAdapter_faculty);

        //departments

        //engineering
        arrayList_engineering = new ArrayList<>();
        arrayList_engineering.add("Computer Science");
        arrayList_engineering.add("Electronics and Nanoelectronics Engineering");
        arrayList_engineering.add("Applied Mathematics & Informatics");
        arrayList_engineering.add("Industrial Engineering");

        //social
        arrayList_social = new ArrayList<>();
        arrayList_social.add("Foreign Philology (Turkish Language and Literature, with knowledge of English)");
        arrayList_social.add("Translation and Translation Studies (English Language)");
        arrayList_social.add("Translation and Translation Studies (Chinese Language)");
        arrayList_social.add("Philology (English Language and Literature)");
        arrayList_social.add("Pedagogy (Psychological Counseling and Guidance, Elementary School Teaching)");
        arrayList_social.add("Journalism");

        //economics
        arrayList_economics = new ArrayList<>();
        arrayList_economics.add("Economics (International Economics and Business)");
        arrayList_economics.add("International Relations");
        arrayList_economics.add("Management");
        arrayList_economics.add("Economics (Finance & Banking)");
        arrayList_economics.add("Law");
        arrayList_economics.add("Accounting and audit");

        //medicine
        arrayList_medicine = new ArrayList<>();
        arrayList_medicine.add("eneral Medicine - Pediatrics");

        //vocational
        arrayList_vocational = new ArrayList<>();
        arrayList_vocational.add("Economy and Accounting");
        arrayList_vocational.add("Banking");
        arrayList_vocational.add("Marketing");
        arrayList_vocational.add("Computer Systems and Complexes");
        arrayList_vocational.add("Software engineer");
        arrayList_vocational.add("Design");
        arrayList_vocational.add("Jurisprudence");

        //distance
        arrayList_distance = new ArrayList<>();
        arrayList_distance.add("Management");
        arrayList_distance.add("Finance and Banking & Accounting");
        arrayList_distance.add("Accounting , Analysis and Audit");
        arrayList_distance.add("Internatıonal Economy and Business");
        arrayList_distance.add("Pedagogy");

        //lang
        arrayList_lang = new ArrayList<>();
        arrayList_lang.add("Kyrgyz Language Teaching");
        arrayList_lang.add("Russian Language Teaching");
        arrayList_lang.add("Turkish Language Teaching");
        arrayList_lang.add("English Language Teaching");

        mFaculties_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    arrayAdapter_department = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, arrayList_engineering);

                }
                if(position==1){
                    arrayAdapter_department = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, arrayList_social);

                }
                if(position==2){
                    arrayAdapter_department = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, arrayList_economics);

                }
                if(position==3){
                    arrayAdapter_department = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, arrayList_medicine);

                }
                if(position==4){
                    arrayAdapter_department = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, arrayList_vocational);

                }
                if(position==5){
                    arrayAdapter_department = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, arrayList_distance);

                }
                if(position==6){
                    arrayAdapter_department = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, arrayList_lang);

                }
                mDepartments_sp.setAdapter(arrayAdapter_department);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

}
