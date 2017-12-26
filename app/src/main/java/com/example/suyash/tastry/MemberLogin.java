package com.example.suyash.tastry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemberLogin extends AppCompatActivity implements View.OnClickListener {
//    Process dialog box
    private EditText email , password;
    private Button login;
    private ProgressDialog progressDialog;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);


        session = new Session(this);
        if (session.loggedIn()){
            finish();
            startActivity(new Intent(this,MemberPersonalInfo.class));
        }
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
            session.setLoggedIn(true);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mmlogin, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cancel) {
            finish();
            startActivity(new Intent(this, user_or_workerActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
