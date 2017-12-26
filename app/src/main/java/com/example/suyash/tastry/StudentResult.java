package com.example.suyash.tastry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class StudentResult extends AppCompatActivity implements View.OnClickListener{
    public String date;
    private Button goBack, logout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_result);


        firebaseAuth = FirebaseAuth.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            date = bundle.getString("passdate2");
            TextView txtdate = (TextView)findViewById(R.id.stdate);
            txtdate.setText(date);
        }



        Bundle b = getIntent().getExtras();
        if (b != null)
        {
            String meal = bundle.getString("passmeal2");
            TextView txtmeal = (TextView)findViewById(R.id.stmeal);
            txtmeal.setText(meal);
        }

        Bundle b1 = getIntent().getExtras();
        if (b1 != null)
        {
            String meal = bundle.getString("passfi1");
            TextView txtmeal = (TextView)findViewById(R.id.fi11);
            txtmeal.setText(meal);
        }

        Bundle b2 = getIntent().getExtras();
        if (b2 != null)
        {
            String meal = bundle.getString("passfi2");
            TextView txtmeal = (TextView)findViewById(R.id.fi22);
            txtmeal.setText(meal);
        }
        Bundle b3 = getIntent().getExtras();
        if (b3 != null)
        {
            String meal = bundle.getString("passfi3");
            TextView txtmeal = (TextView)findViewById(R.id.fi33);
            txtmeal.setText(meal);
        }
        Bundle b4 = getIntent().getExtras();
        if (b4 != null)
        {
            String meal = bundle.getString("passfi4");
            TextView txtmeal = (TextView)findViewById(R.id.fi44);
            txtmeal.setText(meal);
        }
        Bundle b5 = getIntent().getExtras();
        if (b5 != null)
        {
            String meal = bundle.getString("passfi5");
            TextView txtmeal = (TextView)findViewById(R.id.fi55);
            txtmeal.setText(meal);
        }
        Bundle b6 = getIntent().getExtras();
        if (b6 != null)
        {
            String meal = bundle.getString("passfi6");
            TextView txtmeal = (TextView)findViewById(R.id.fi66);
            txtmeal.setText(meal);
        }
        Bundle b7 = getIntent().getExtras();
        if (b7 != null)
        {
            String meal = bundle.getString("passfi7");
            TextView txtmeal = (TextView)findViewById(R.id.fi77);
            txtmeal.setText(meal);
        }
        Bundle b8 = getIntent().getExtras();
        if (b8 != null)
        {
            String meal = bundle.getString("passfi8");
            TextView txtmeal = (TextView)findViewById(R.id.fi88);
            txtmeal.setText(meal);
        }


        goBack = (Button)findViewById(R.id.goBack);
        goBack.setOnClickListener(this);
        logout = (Button)findViewById(R.id.logout1);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == goBack){
            finish();
            startActivity(new Intent(this,NavigationDrawerActivity.class));
        }
        if (v == logout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
