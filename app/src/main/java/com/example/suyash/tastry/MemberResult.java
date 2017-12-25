package com.example.suyash.tastry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberResult extends AppCompatActivity implements View.OnClickListener{
    public  String date,meal,names1,names2,names3,names4,names5,names6,names7,names8;
    public TextView txtdate,m11,m2,m3,m4,m5,m6,m7,m8,p11,p2,p3,p4,p5,p6,p7,p8;
    public long r2=0,r3=0,r4=0,r5=0,r6=0,r7=0,r8=0;
    private DatabaseReference databaseReference;
    private Button logout,goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference();


        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            date = bundle.getString("passdate");
            txtdate = (TextView)findViewById(R.id.resultdate);
            txtdate.setText(date);
        }



        Bundle b = getIntent().getExtras();
        if (b != null)
        {
            meal = bundle.getString("passmeal");
            TextView txtmeal = (TextView)findViewById(R.id.resultmeal);
            txtmeal.setText(meal);
        }
        m11 = (TextView)findViewById(R.id.m1);
        m2 = (TextView)findViewById(R.id.m2);
        m3 = (TextView)findViewById(R.id.m3);
        m4 = (TextView)findViewById(R.id.m4);
        m5 = (TextView)findViewById(R.id.m5);
        m6 = (TextView)findViewById(R.id.m6);
        m7 = (TextView)findViewById(R.id.m7);
        m8 = (TextView)findViewById(R.id.m8);
        p11 = (TextView)findViewById(R.id.p1);
        p2 = (TextView)findViewById(R.id.p2);
        p3 = (TextView)findViewById(R.id.p3);
        p4 = (TextView)findViewById(R.id.p4);
        p5 = (TextView)findViewById(R.id.p5);
        p6 = (TextView)findViewById(R.id.p6);
        p7 = (TextView)findViewById(R.id.p7);
        p8 = (TextView)findViewById(R.id.p8);
        goBack = (Button)findViewById(R.id.goBack2);
        logout = (Button)findViewById(R.id.logout2);
        goBack.setOnClickListener(this);
        logout.setOnClickListener(this);


        DatabaseReference food1 = databaseReference.child(date).child(meal).child("Food Option 1");
        food1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names1 = dataSnapshot.getValue(String.class);
                m11.setText(names1);
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
                m2.setText(names2);
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
                m3.setText(names3);
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
                m4.setText(names4);
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
                m5.setText(names5);
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
                m6.setText(names6);
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
                m7.setText(names7);
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
                m8.setText(names8);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (names1 != null){
        DatabaseReference per1 = databaseReference.child(date).child(meal).child("Result").child(names1);
        per1.addValueEventListener(new ValueEventListener() {
            public static final String TAG = "Member Results" ;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long r1 = dataSnapshot.getChildrenCount();
                Log.d(TAG, "No.of children"+String.valueOf(r1));
                p11.setText(String.valueOf(r1));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}

        if (names2 != null){
        DatabaseReference per2 = databaseReference.child(date).child(meal).child("Result").child(names2);
        per2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                r2 = (int) dataSnapshot.getChildrenCount();
                p2.setText(String.valueOf(r2));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}

        if (names3 != null){
        DatabaseReference per3 = databaseReference.child(date).child(meal).child("Result").child(names3);
        per3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                r3 = (int) dataSnapshot.getChildrenCount();
                p3.setText(String.valueOf(r3));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}

        if (names4 != null){
        DatabaseReference per4 = databaseReference.child(date).child(meal).child("Result").child(names4);
        per4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                r4 = (int) dataSnapshot.getChildrenCount();
                p4.setText(String.valueOf(r4));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}

        if (names5 != null){
        DatabaseReference per5 = databaseReference.child(date).child(meal).child("Result").child(names5);
        per5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                r5 = (int) dataSnapshot.getChildrenCount();
                p5.setText(String.valueOf(r5));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}

        if (names6 != null){
        DatabaseReference per6 = databaseReference.child(date).child(meal).child("Result").child(names6);
        per6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                r6 = (int) dataSnapshot.getChildrenCount();
                p6.setText(String.valueOf(r6));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}

        if (names7 != null){
        DatabaseReference per7 = databaseReference.child(date).child(meal).child("Result").child(names7);
        per7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                r7 = (int) dataSnapshot.getChildrenCount();
                p7.setText(String.valueOf(r7));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}

        if (names8 != null){
        DatabaseReference per8 = databaseReference.child(date).child(meal).child("Result").child(names8);
        per8.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                r8 = (int) dataSnapshot.getChildrenCount();
                p8.setText(String.valueOf(r8));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}
        }

    @Override
    public void onClick(View v) {
        if (v == goBack){
            finish();
            startActivity(new Intent(this,MemberPersonalInfo.class));
        }
        if (v == logout){
            finish();
            startActivity(new Intent(this,MemberLogin.class));
        }
    }
}

