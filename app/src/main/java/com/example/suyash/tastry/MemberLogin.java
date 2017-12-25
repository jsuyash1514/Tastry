package com.example.suyash.tastry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemberLogin extends AppCompatActivity implements View.OnClickListener {
//    Process dialog box
    private EditText email , password;
    private Button login;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        email = (EditText)findViewById(R.id.memberemail);
        password = (EditText)findViewById(R.id.memberpassword);
        login = (Button)findViewById(R.id.memberbutton);
        login.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);


    }

    private void userLogin(){
        String em = email.getText().toString().trim();
        String pw = password.getText().toString().trim();
        if (TextUtils.isEmpty(em)){
            Toast.makeText(this,"Please enter E-mail",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pw)){
            Toast.makeText(this,"Please enter Password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Logging in Please wait...");
        progressDialog.show();

        DatabaseAccess databaseAccess = new DatabaseAccess (this);
        databaseAccess.open();
        String password = databaseAccess.searchPass(em);

        progressDialog.dismiss();

        if (pw.equals(password)){
            finish();
            startActivity(new Intent(getApplicationContext(),MemberPersonalInfo.class));
        }
        else{
            Toast.makeText(getApplicationContext(), "Incorrect Email or Password",Toast.LENGTH_SHORT).show();
        }
        databaseAccess.close();
    }

    @Override
    public void onClick(View v) {
        if (v == login){
            userLogin();
        }
    }
}
