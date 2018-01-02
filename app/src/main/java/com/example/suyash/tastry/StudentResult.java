package com.example.suyash.tastry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentResult extends AppCompatActivity implements View.OnClickListener{
    public String date,meal;
    private Button goBack, logout;
    private DatabaseReference databaseReference ;
    private FirebaseAuth firebaseAuth;
    public List<StudentResultModel> list;
    StudentResultTextAdapter studentResultTextAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_result);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView2);

        list = new ArrayList<>();


        studentResultTextAdapter = new StudentResultTextAdapter(StudentResult.this,list);



        firebaseAuth = FirebaseAuth.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            date = bundle.getString("passdate");
            TextView txtdate = (TextView)findViewById(R.id.stdate);
            txtdate.setText(date);
        }



        Bundle b = getIntent().getExtras();
        if (b != null)
        {
            meal = bundle.getString("passmeal");
            TextView txtmeal = (TextView)findViewById(R.id.stmeal);
            txtmeal.setText(meal);
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();


        DatabaseReference upload = databaseReference.child(date).child(meal);
        upload.addValueEventListener(new ValueEventListener() {
                                         @Override
                                         public void onDataChange(DataSnapshot dataSnapshot) {
                                             long n = dataSnapshot.getChildrenCount();
                                             for (int i = 1; i <= n; i++) {
                                                 Bundle b1 = getIntent().getExtras();
                                                 String meal = b1.getString("passfi" + i);
                                                 if (meal != null && !meal.isEmpty()) {
                                                     final StudentResultModel m = new StudentResultModel();
                                                     m.setVote(meal);
                                                     list.add(m);
                                                     studentResultTextAdapter.notifyDataSetChanged();
                                                 }
                                             }
                                         }

                                         @Override
                                         public void onCancelled(DatabaseError databaseError) {

                                         }
                                     });
        goBack = (Button)findViewById(R.id.goBack);
        goBack.setOnClickListener(this);
        logout = (Button)findViewById(R.id.logout1);
        logout.setOnClickListener(this);

        RecyclerView.LayoutManager recycler = new LinearLayoutManager(StudentResult.this);
        recyclerView.setLayoutManager(recycler);
        recyclerView.setAdapter(studentResultTextAdapter);

    }

    @Override
    public void onClick(View v) {
        if (v == goBack){

            startActivity(new Intent(this,NavigationDrawerActivity.class));
            finish();
        }
        if (v == logout){
            firebaseAuth.signOut();

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(this,VotingActivity.class);
        intent.putExtra("passmeal",meal);
        intent.putExtra("passdate",date);
        startActivity(intent);
        finish();
    }
}
