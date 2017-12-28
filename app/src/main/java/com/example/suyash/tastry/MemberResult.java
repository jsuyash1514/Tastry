package com.example.suyash.tastry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    public TextView txtdate;//m11,m2,m3,m4,m5,m6,m7,m8,p11,p2,p3,p4,p5,p6,p7,p8;
    public long r2=0,r3=0,r4=0,r5=0,r6=0,r7=0,r8=0;
    private DatabaseReference databaseReference;
    private Button logout,goBack;
    private Session session;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_result);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        session = new Session(this);
        progressDialog = new ProgressDialog(this);


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView1);


        final List<MealMemberResult> list = new ArrayList<>();
       final MealMemberResult m1 = new MealMemberResult();
        final MealMemberResult m2 = new MealMemberResult();
        final MealMemberResult m3 = new MealMemberResult();
        final MealMemberResult m4 = new MealMemberResult();
        final MealMemberResult m5 = new MealMemberResult();
        final MealMemberResult m6 = new MealMemberResult();
        final MealMemberResult m7 = new MealMemberResult();
        final MealMemberResult m8 = new MealMemberResult();


        final MemberResultTextAdapter textAdapter = new MemberResultTextAdapter(MemberResult.this,list);



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

        goBack = (Button)findViewById(R.id.goBack2);
        logout = (Button)findViewById(R.id.logout2);
        goBack.setOnClickListener(this);
        logout.setOnClickListener(this);


        progressDialog.setMessage("Fetching results...");
        progressDialog.show();


        DatabaseReference food1 = databaseReference.child(date).child(meal).child("Food Option 1");
        food1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names1 = dataSnapshot.getValue(String.class);

                if (names1 != null && !names1.isEmpty()){
                    DatabaseReference per1 = databaseReference.child(date).child(meal).child("Result").child(names1);
                    per1.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            long r1 = dataSnapshot.getChildrenCount();

                            m1.setFoodOption(names1);
                            m1.setNumber(String.valueOf(r1));
                            list.add(m1);
                            textAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });}




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


                if (names2 != null && !names2.isEmpty()){
                    DatabaseReference per2 = databaseReference.child(date).child(meal).child("Result").child(names2);
                    per2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            r2 = (int) dataSnapshot.getChildrenCount();

                            m2.setFoodOption(names2);
                            m2.setNumber(String.valueOf(r2));
                            list.add(m2);
                            textAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });}

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




                if (names3 != null && !names3.isEmpty()){
                    DatabaseReference per3 = databaseReference.child(date).child(meal).child("Result").child(names3);
                    per3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            r3 = (int) dataSnapshot.getChildrenCount();
                            m3.setFoodOption(names3);
                            m3.setNumber(String.valueOf(r3));
                            list.add(m3);
                            textAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });}

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


                if (names4 != null && !names4.isEmpty()){
                    DatabaseReference per4 = databaseReference.child(date).child(meal).child("Result").child(names4);
                    per4.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            r4 = (int) dataSnapshot.getChildrenCount();
                            m4.setFoodOption(names4);
                            m4.setNumber(String.valueOf(r4));
                            list.add(m4);
                            textAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });}


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


                if (names5 != null && !names5.isEmpty()){
                    DatabaseReference per5 = databaseReference.child(date).child(meal).child("Result").child(String.valueOf(names5));
                    per5.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            r5 = (int) dataSnapshot.getChildrenCount();
                            m5.setFoodOption(names5);
                            m5.setNumber(String.valueOf(r5));
                            list.add(m5);
                            textAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });}

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

                if (names6 != null && !names6.isEmpty()){
                    DatabaseReference per6 = databaseReference.child(date).child(meal).child("Result").child(names6);
                    per6.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            r6 = (int) dataSnapshot.getChildrenCount();
                            m6.setFoodOption(names6);
                            m6.setNumber(String.valueOf(r6));
                            list.add(m6);
                            textAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });}

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


                if (names7 != null && !names7.isEmpty()){
                    DatabaseReference per7 = databaseReference.child(date).child(meal).child("Result").child(names7);
                    per7.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            r7 = (int) dataSnapshot.getChildrenCount();
                            m7.setFoodOption(names7);
                            m7.setNumber(String.valueOf(r7));
                            list.add(m7);
                            textAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });}

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


                if (names8 != null && !names8.isEmpty()){
                    DatabaseReference per8 = databaseReference.child(date).child(meal).child("Result").child(names8);
                    per8.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            r8 = (int) dataSnapshot.getChildrenCount();
                            m8.setFoodOption(names8);
                            m8.setNumber(String.valueOf(r8));
                            list.add(m8);
                            textAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        RecyclerView.LayoutManager recycler = new LinearLayoutManager(MemberResult.this);
        recyclerView.setLayoutManager(recycler);
        recyclerView.setAdapter(textAdapter);
        progressDialog.dismiss();

    }

    @Override
    public void onClick(View v) {
        if (v == goBack){

            startActivity(new Intent(this,MemberPersonalInfo.class));
            finish();
        }
        if (v == logout){
            session.setLoggedIn(false);

            startActivity(new Intent(this,MemberLogin.class));
            finish();
        }
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(this,MemberUpload.class);
        intent.putExtra("passmeal",meal);
        intent.putExtra("passdate",date);
        startActivity(intent);
        finish();
    }
}

