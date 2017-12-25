package com.example.suyash.tastry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private Button register;
    private EditText eemail, pasword,et_name,et_enrl;
    private TextView already;
    private ProgressDialog progressDialog;
    private Spinner spinner;

    ArrayAdapter<CharSequence> adapter;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),NavigationDrawerActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        register = (Button)findViewById(R.id.register);
        eemail = (EditText)findViewById(R.id.emAil);
        pasword = (EditText)findViewById(R.id.passwrd);
        already = (TextView)findViewById(R.id.alreadyAcc);
        et_name = (EditText)findViewById(R.id.NAME);
        et_enrl = (EditText)findViewById(R.id.ENR);
        spinner = (Spinner)findViewById(R.id.BHAWAN);
        adapter = ArrayAdapter.createFromResource(this,R.array.bhawan,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String bhawan = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getBaseContext(),"Please select your bhawan",Toast.LENGTH_LONG);
            }
        });

        register.setOnClickListener(this);
        already.setOnClickListener(this);
    }

    private  void registerUser(){
        final String email = eemail.getText().toString().trim();
        final String password = pasword.getText().toString().trim();
        final String name = et_name.getText().toString();
        final String enrl = et_enrl.getText().toString();
        final String bhawan = spinner.getSelectedItem().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter E-mail",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter Password",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(enrl)){
            Toast.makeText(this,"Please enter your Enrollment no.",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering Please wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()) {
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
                            DatabaseReference currentUserDB = databaseReference.child(firebaseAuth.getCurrentUser().getUid());
                            currentUserDB.child("name").setValue(name);
                            currentUserDB.child("EnrollmentNumber").setValue(enrl);
                            currentUserDB.child("Bhawan").setValue(bhawan);
                            finish();
                            startActivity(new Intent(getApplicationContext(), NavigationDrawerActivity.class));
                        }
                            else{
                                Toast.makeText(getApplicationContext(), "Could not register, Please try again...",Toast.LENGTH_SHORT).show();
                            }
                        }

                });
    }
    @Override
    public void onClick (View view){
       if(view == register){
           registerUser();
       }
       if(view == already){
           finish();
           startActivity(new Intent(this,LoginActivity.class));
       }
    }
}
