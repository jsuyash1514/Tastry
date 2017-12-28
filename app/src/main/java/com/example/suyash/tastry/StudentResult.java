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

import java.util.ArrayList;
import java.util.List;

public class StudentResult extends AppCompatActivity implements View.OnClickListener{
    public String date,meal;
    private Button goBack, logout;
    private FirebaseAuth firebaseAuth;
    public List<StudentResultModel> list;
    StudentResultTextAdapter studentResultTextAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_result);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView2);

        list = new ArrayList<>();
        final StudentResultModel m1 = new StudentResultModel();
        final StudentResultModel m2 = new StudentResultModel();
        final StudentResultModel m3 = new StudentResultModel();
        final StudentResultModel m4 = new StudentResultModel();
        final StudentResultModel m5 = new StudentResultModel();
        final StudentResultModel m6 = new StudentResultModel();
        final StudentResultModel m7 = new StudentResultModel();
        final StudentResultModel m8 = new StudentResultModel();


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

        Bundle b1 = getIntent().getExtras();
        {
            String meal = bundle.getString("passfi1");
            if (meal != null && !meal.isEmpty()){
            m1.setVote(meal);
            list.add(m1);
            studentResultTextAdapter.notifyDataSetChanged();
        }}

        Bundle b2 = getIntent().getExtras();
        {
            String meal = bundle.getString("passfi2");
        if (meal != null && !meal.isEmpty()){
            m2.setVote(meal);
            list.add(m2);
            studentResultTextAdapter.notifyDataSetChanged();
        }}
        Bundle b3 = getIntent().getExtras();
        {   String meal = bundle.getString("passfi3");
            if (meal != null && !meal.isEmpty()){
            m3.setVote(meal);
            list.add(m3);
            studentResultTextAdapter.notifyDataSetChanged();
        }}
        Bundle b4 = getIntent().getExtras();
        {
            String meal = bundle.getString("passfi4");
            if (meal != null && !meal.isEmpty()){
            m4.setVote(meal);
            list.add(m4);
            studentResultTextAdapter.notifyDataSetChanged();}
        }
        Bundle b5 = getIntent().getExtras();
        {
            String meal = bundle.getString("passfi5");
            if (meal != null && !meal.isEmpty()){
            m5.setVote(meal);
            list.add(m5);
            studentResultTextAdapter.notifyDataSetChanged();}
        }
        Bundle b6 = getIntent().getExtras();
        {
            String meal = bundle.getString("passfi6");
            if (meal != null && !meal.isEmpty()){
            m6.setVote(meal);
            list.add(m6);
            studentResultTextAdapter.notifyDataSetChanged();}
        }
        Bundle b7 = getIntent().getExtras();
        {
            String meal = bundle.getString("passfi7");
            if (meal != null && !meal.isEmpty()){
            m7.setVote(meal);
            list.add(m7);
            studentResultTextAdapter.notifyDataSetChanged();}
        }
        Bundle b8 = getIntent().getExtras();
        {
            String meal = bundle.getString("passfi8");
            if (meal != null && !meal.isEmpty()){
            m8.setVote(meal);
            list.add(m8);
            studentResultTextAdapter.notifyDataSetChanged();}
        }


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
