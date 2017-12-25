package com.example.suyash.tastry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {
    public String date,meal,names;
    private EditText feedback,sick;
    private Button submit;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            date = bundle.getString("passdate2");
            TextView txtdate = (TextView)findViewById(R.id.studentdate1);
            txtdate.setText(date);
        }



        Bundle b = getIntent().getExtras();
        if (bundle != null)
        {
            meal = bundle.getString("passmeal2");
            TextView txtmeal = (TextView)findViewById(R.id.studentmeal1);
            txtmeal.setText(meal);
        }

        feedback = (EditText)findViewById(R.id.feedback);
        sick = (EditText)findViewById(R.id.sick);
        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == submit){
            message();
        }
    }

    private void message() {
        final String feed = feedback.getText().toString().trim();
        final String sickDiet = sick.getText().toString().trim();

        DatabaseReference users = databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("EnrollmentNumber");
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
        DatabaseReference feedback = databaseReference.child(meal);
        if (names != null) {
            feedback.child("Feedback").child(names).setValue(feed);
            feedback.child("Sick Diet").child(names).setValue(sickDiet);
        }
        else {
            feedback.child("Feedback").child(firebaseAuth.getCurrentUser().getUid()).setValue(feed);
            feedback.child("Sick Diet").child(firebaseAuth.getCurrentUser().getUid()).setValue(sickDiet);
        }



        Toast.makeText(getApplicationContext(), "Message submitted successfully",Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this,NavigationDrawerActivity.class));
    }
}
