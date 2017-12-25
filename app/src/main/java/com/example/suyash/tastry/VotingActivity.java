package com.example.suyash.tastry;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VotingActivity extends AppCompatActivity implements View.OnClickListener{

    public String date;
    public String meal;
    private CheckBox fo1,fo2,fo3,fo4,fo5,fo6,fo7,fo8;
    private DatabaseReference databaseReference;
    private Button vote;
    private FirebaseAuth firebaseAuth;
    public String names1,names2,names3,names4,names5,names6,names7,names8,en;


    public VotingActivity(){

    }

    public VotingActivity(FloatingActionButton fab) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            date = bundle.getString("passdate1");
            TextView txtdate = (TextView)findViewById(R.id.studentdate);
            txtdate.setText(date);
        }



        Bundle b = getIntent().getExtras();
        if (bundle != null)
        {
            meal = bundle.getString("passmeal1");
            TextView txtmeal = (TextView)findViewById(R.id.studentmeal);
            txtmeal.setText(meal);
        }
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();


        fo1 = (CheckBox)findViewById(R.id.fi1);
        fo2 = (CheckBox)findViewById(R.id.fi2);
        fo3 = (CheckBox)findViewById(R.id.fi3);
        fo4 = (CheckBox)findViewById(R.id.fi4);
        fo5 = (CheckBox)findViewById(R.id.fi5);
        fo6 = (CheckBox)findViewById(R.id.fi6);
        fo7 = (CheckBox)findViewById(R.id.fi7);
        fo8 = (CheckBox)findViewById(R.id.fi8);
        vote = (Button)findViewById(R.id.vote);
        vote.setOnClickListener(this);

        DatabaseReference food1 = databaseReference.child(date).child(meal).child("Food Option 1");
        food1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names1 = dataSnapshot.getValue(String.class);
                fo1.setText(names1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference food2 = databaseReference.child(date).child(meal).child("Food Option 2");
        food2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names2 = dataSnapshot.getValue(String.class);
                fo2.setText(names2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        DatabaseReference food3 = databaseReference.child(date).child(meal).child("Food Option 3");
        food3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names3 = dataSnapshot.getValue(String.class);
                fo3.setText(names3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        DatabaseReference food4 = databaseReference.child(date).child(meal).child("Food Option 4");
        food4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names4 = dataSnapshot.getValue(String.class);
                fo4.setText(names4);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        DatabaseReference food5 = databaseReference.child(date).child(meal).child("Food Option 5");
        food5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names5 = dataSnapshot.getValue(String.class);
                fo5.setText(names5);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        DatabaseReference food6 = databaseReference.child(date).child(meal).child("Food Option 6");
        food6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names6 = dataSnapshot.getValue(String.class);
                fo6.setText(names6);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        DatabaseReference food7 = databaseReference.child(date).child(meal).child("Food Option 7");
        food7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names7 = dataSnapshot.getValue(String.class);
                fo7.setText(names7);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        DatabaseReference food8 = databaseReference.child(date).child(meal).child("Food Option 8");
        food8.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names8 = dataSnapshot.getValue(String.class);
                fo8.setText(names8);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference users = databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("EnrollmentNumber");
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                en = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void onClick(View v) {
        if (v == vote){
            recordResponse();
        }


    }

    private void recordResponse() {

        Intent intent = new Intent(this,StudentResult.class);
        if (names1 != null){
        if (fo1.isChecked()){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names1).child(firebaseAuth.getCurrentUser().getUid()).setValue(en);
            intent.putExtra("passfi1",names1);
        }
        else{
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names1).child(firebaseAuth.getCurrentUser().getUid()).setValue(null);
        }}
        if (names2 != null){
        if (fo2.isChecked()){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names2).child(firebaseAuth.getCurrentUser().getUid()).setValue(en);
            intent.putExtra("passfi2",names2);
        }
        else{
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names2).child(firebaseAuth.getCurrentUser().getUid()).setValue(null);
        }}
        if (names3 != null){
        if (fo3.isChecked()){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names3).child(firebaseAuth.getCurrentUser().getUid()).setValue(en);
            intent.putExtra("passfi3",names3);
        }
        else{
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names3).child(firebaseAuth.getCurrentUser().getUid()).setValue(null);
        }}
        if (names4 != null){
        if (fo4.isChecked()){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names4).child(firebaseAuth.getCurrentUser().getUid()).setValue(en);
            intent.putExtra("passfi4",names4);
        }
        else{
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names4).child(firebaseAuth.getCurrentUser().getUid()).setValue(null);
        }}
        if (names5 != null){
        if (fo5.isChecked()){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names5).child(firebaseAuth.getCurrentUser().getUid()).setValue(en);
            intent.putExtra("passfi5",names5);
        }
        else{
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names5).child(firebaseAuth.getCurrentUser().getUid()).setValue(null);
        }}
        if (names6 != null){
        if (fo6.isChecked()){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names6).child(firebaseAuth.getCurrentUser().getUid()).setValue(en);
            intent.putExtra("passfi6",names6);
        }
        else{
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names6).child(firebaseAuth.getCurrentUser().getUid()).setValue(null);
        }}
        if (names7 != null){
        if (fo7.isChecked()){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names7).child(firebaseAuth.getCurrentUser().getUid()).setValue(en);
            intent.putExtra("passfi7",names7);
        }
        else{
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names7).child(firebaseAuth.getCurrentUser().getUid()).setValue(null);
        }}
        if (names8 != null){
        if (fo8.isChecked()){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference responceDB = databaseReference.child(meal);
            responceDB.child("Result").child(names8).child(firebaseAuth.getCurrentUser().getUid()).setValue(en);
            intent.putExtra("passfi8",names8);
        }
        else{
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
        DatabaseReference responceDB = databaseReference.child(meal);
        responceDB.child("Result").child(names8).child(firebaseAuth.getCurrentUser().getUid()).setValue(null);
        }}


        Toast.makeText(getApplicationContext(), "Responses recorded successfully",Toast.LENGTH_LONG).show();
        finish();

        intent.putExtra("passmeal2",meal);
        intent.putExtra("passdate2",date);
        startActivity(intent);

    }
}
