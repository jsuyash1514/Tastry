package com.example.suyash.tastry;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class VotingActivity extends AppCompatActivity implements View.OnClickListener{

    public String date;
    public String meal;
    private DatabaseReference databaseReference;
    private Button vote;
    private FirebaseAuth firebaseAuth;
    public String en;
    public List<Meal> list;
    TextAdapter textAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        list = new ArrayList<>();


        textAdapter = new TextAdapter(VotingActivity.this,list);




        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            date = bundle.getString("passdate");
            TextView txtdate = (TextView)findViewById(R.id.studentdate);
            txtdate.setText(date);
        }



        Bundle b = getIntent().getExtras();
        if (bundle != null)
        {
            meal = bundle.getString("passmeal");
            TextView txtmeal = (TextView)findViewById(R.id.studentmeal);
            txtmeal.setText(meal);
        }
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();



        vote = (Button)findViewById(R.id.vote);
        vote.setOnClickListener(this);

        DatabaseReference upload = databaseReference.child(date).child(meal);
        upload.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long n = dataSnapshot.getChildrenCount();
                for (int i = 1; i <= n; i++) {
                    DatabaseReference food1 = databaseReference.child(date).child(meal).child("Food Option " + i);
                    food1.addValueEventListener(new ValueEventListener() {
                    @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                        String names1 = dataSnapshot.getValue(String.class);
                        if (names1 != null && !names1.isEmpty()){
                            final Meal m = new Meal();
                            m.setMeal(names1);
                            list.add(m);
                            textAdapter.notifyDataSetChanged();}
                    }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                    }
                });}}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }});

        RecyclerView.LayoutManager recycler = new LinearLayoutManager(VotingActivity.this);
        recyclerView.setLayoutManager(recycler);
        recyclerView.setAdapter(textAdapter);

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

        final Intent intent = new Intent(this, StudentResult.class);

        DatabaseReference upload = databaseReference.child(date).child(meal);
        upload.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long n = dataSnapshot.getChildrenCount();
                for (int i = 1; i <= n; i++) {

                    if (list.get(i - 1).getMeal() != null) {
                        if (list.get(i - 1).getSelected()) {
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                            DatabaseReference responceDB = databaseReference.child(date);
                            responceDB.child("Result").child(meal).child(list.get(i - 1).getMeal()).child(firebaseAuth.getCurrentUser().getUid()).setValue(en);
                            intent.putExtra("passfi" + i, list.get(i - 1).getMeal());
                        } else {
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                            DatabaseReference responceDB = databaseReference.child(date);
                            responceDB.child("Result").child(meal).child(list.get(i - 1).getMeal()).child(firebaseAuth.getCurrentUser().getUid()).setValue(null);
                        }
                    }
                }

                Toast.makeText(getApplicationContext(), "Responses recorded successfully", Toast.LENGTH_LONG).show();


                intent.putExtra("passmeal", meal);
                intent.putExtra("passdate", date);
                startActivity(intent);
                finish();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });}
            @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calendarvoting, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_datepicker) {
            startActivity(new Intent(this, NavigationDrawerActivity.class));
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this,Breakfast_lunch_dinner.class);
        intent.putExtra("passmeal",meal);
        intent.putExtra("passdate",date);
        startActivity(intent);
        finish();

    }
}
