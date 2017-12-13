package com.example.suyash.tastry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private Button register;
    private EditText eemail, pasword;
    private TextView already;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        register = (Button)findViewById(R.id.register);
        eemail = (EditText)findViewById(R.id.emAil);
        pasword = (EditText)findViewById(R.id.passwrd);
        already = (TextView)findViewById(R.id.alreadyAcc);

        register.setOnClickListener(this);
        already.setOnClickListener(this);
    }

    private  void registerUser(){
        String email = eemail.getText().toString().trim();
        String password = pasword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter E-mail",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter Password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering Please wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
