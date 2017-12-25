package com.example.suyash.tastry;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Breakfast_lunch_dinner extends AppCompatActivity implements View.OnClickListener {
    private TextView breakfast;
    private TextView lunch;
    private TextView dinner;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_lunch_dinner);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            date = bundle.getString("passdate");
            TextView txtdate = (TextView)findViewById(R.id.selectDate);
            txtdate.setText(date);
        }



        breakfast = (TextView)findViewById(R.id.breakfast);
        lunch = (TextView)findViewById(R.id.lunch);
        dinner = (TextView)findViewById(R.id.dinner);

        breakfast.setOnClickListener(this);
        lunch.setOnClickListener(this);
        dinner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == breakfast){
            Intent intent = new Intent(this,VotingActivity.class);
            intent.putExtra("passmeal1","Breakfast");
            intent.putExtra("passdate1",date);
            finish();
            startActivity(intent);
        }

        if (v == lunch){
            Intent intent = new Intent(this,VotingActivity.class);
            intent.putExtra("passmeal1","Lunch");
            intent.putExtra("passdate1",date);
            finish();
            startActivity(intent);
        }

        if (v == dinner){
            Intent intent = new Intent(this,VotingActivity.class);
            intent.putExtra("passmeal1","Dinner");
            intent.putExtra("passdate1",date);
            finish();
            startActivity(intent);
        }
    }
}
