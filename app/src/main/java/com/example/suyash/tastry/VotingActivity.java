package com.example.suyash.tastry;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class VotingActivity extends AppCompatActivity implements View.OnClickListener{

    public String date;
    public String meal;
    private DatabaseReference databaseReference;
    private Button vote;
    private FirebaseAuth firebaseAuth;
    public String names1,names2,names3,names4,names5,names6,names7,names8,en;
    public List<Meal> list;
    TextAdapter textAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        list = new ArrayList<>();
        final Meal m1 = new Meal();
        final Meal m2 = new Meal();
        final Meal m3 = new Meal();
        final Meal m4 = new Meal();
        final Meal m5 = new Meal();
        final Meal m6 = new Meal();
        final Meal m7 = new Meal();
        final Meal m8 = new Meal();


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



        DatabaseReference food1 = databaseReference.child(date).child(meal).child("Food Option 1");
        food1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names1 = dataSnapshot.getValue(String.class);
                if (names1 != null && !names1.isEmpty()){
                m1.setMeal(names1);
                list.add(m1);
                textAdapter.notifyDataSetChanged();}
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
                m2.setMeal(names2);
                list.add(m2);
                textAdapter.notifyDataSetChanged();}

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
                m3.setMeal(names3);
                list.add(m3);
                textAdapter.notifyDataSetChanged();}

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
                m4.setMeal(names4);
                list.add(m4);
                textAdapter.notifyDataSetChanged();}

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
                m5.setMeal(names5);
                list.add(m5);
                textAdapter.notifyDataSetChanged();}

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
                m6.setMeal(names6);
                list.add(m6);
                textAdapter.notifyDataSetChanged();}

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
                m7.setMeal(names7);
                list.add(m7);
                textAdapter.notifyDataSetChanged();}

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
                m8.setMeal(names8);
                list.add(m8);
                textAdapter.notifyDataSetChanged();}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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

        Intent intent = new Intent(this, StudentResult.class);
        if (names1 != null) {
        if (list.get(0).getSelected()){
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
        if (list.get(1).getSelected()){
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
        if (list.get(2).getSelected()){
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
        if (list.get(3).getSelected()){
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
        if (list.get(4).getSelected()){
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
        if (list.get(5).getSelected()){
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
        if (list.get(6).getSelected()){
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
        if (list.get(7).getSelected()){
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


        intent.putExtra("passmeal",meal);
        intent.putExtra("passdate",date);
        startActivity(intent);
        finish();

    }





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
