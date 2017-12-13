package com.example.suyash.tastry;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.app.ProgressDialog;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @VisibleForTesting
    public ProgressDialog mProgressDialog;
    private EditText EMail , PW;
    private Button login;
    private TextView signup;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }


        EMail=(EditText)findViewById(R.id.email);
        PW=(EditText)findViewById(R.id.password);

        login=(Button)findViewById(R.id.button);
        signup=(TextView)findViewById(R.id.signUp);

         progressDialog = new ProgressDialog(this);


        login.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    private void userLogin(){
        String email = EMail.getText().toString().trim();
        String password = PW.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter E-mail",Toast.LENGTH_LONG).show();
            return;
        }

            if (TextUtils.isEmpty(password)){
                Toast.makeText(this,"Please enter Password",Toast.LENGTH_LONG).show();
                return;
        }

        progressDialog.setMessage("Logging in Please wait...");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if (task.isSuccessful()){
                                finish();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }
                        }
                    });
    }

    @Override
    public void onClick(View view){
        if (view==login){
            userLogin();
        }

        if(view == signup){
                finish();
                startActivity(new Intent(this,SignUpActivity.class));
        }
    }


}


