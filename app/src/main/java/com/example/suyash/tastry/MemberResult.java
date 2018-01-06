package com.example.suyash.tastry;

import android.annotation.SuppressLint;
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

import static android.content.ContentValues.TAG;

public class MemberResult extends AppCompatActivity implements View.OnClickListener{
    public static String date,meal;
    public TextView txtdate;
    private DatabaseReference databaseReference;
    private Button logout,goBack;
    private Session session;
    private ProgressDialog progressDialog;
    public static String oldDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_result);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        session = new Session(this);
        progressDialog = new ProgressDialog(this);


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView1);


        final List<MealMemberResult> list = new ArrayList<>();


        final MemberResultTextAdapter textAdapter = new MemberResultTextAdapter(MemberResult.this,list);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            date = bundle.getString("passdate");
            oldDate = bundle.getString("oldDate");
        }

        txtdate = (TextView)findViewById(R.id.resultdate);
        txtdate.setText(date);


        Bundle b = getIntent().getExtras();
        if (b != null)
        {
            meal = bundle.getString("passmeal");
        }
        TextView txtmeal = (TextView)findViewById(R.id.resultmeal);
        txtmeal.setText(meal);

        goBack = (Button)findViewById(R.id.goBack2);
        logout = (Button)findViewById(R.id.logout2);
        goBack.setOnClickListener(this);
        logout.setOnClickListener(this);


        progressDialog.setMessage("Fetching results...");
        progressDialog.show();

        DatabaseReference upload = databaseReference.child(date).child(meal);
        upload.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long n = dataSnapshot.getChildrenCount();
                for (int i=1; i<=n; i++){

                    DatabaseReference food1 = databaseReference.child(date).child(meal).child("Food Option " + i);
                    food1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                           final String names1 = dataSnapshot.getValue(String.class);

                            if (names1 != null && !names1.isEmpty()){
                                DatabaseReference per1 = databaseReference.child(date).child("Result").child(meal).child(names1);
                                per1.addListenerForSingleValueEvent(new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        long r1 = dataSnapshot.getChildrenCount();
                                        final MealMemberResult m = new MealMemberResult();
                                        m.setFoodOption(names1);
                                        m.setNumber(String.valueOf(r1));
                                        list.add(m);
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
                }
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

    @SuppressLint("ResourceAsColor")
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
        startActivity(intent);
        finish();
    }
}

