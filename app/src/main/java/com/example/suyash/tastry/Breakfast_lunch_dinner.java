package com.example.suyash.tastry;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
            intent.putExtra("passmeal","Breakfast");
            intent.putExtra("passdate",date);

            startActivity(intent);
            finish();
        }

        if (v == lunch){
            Intent intent = new Intent(this,VotingActivity.class);
            intent.putExtra("passmeal","Lunch");
            intent.putExtra("passdate",date);

            startActivity(intent);
            finish();
        }

        if (v == dinner){
            Intent intent = new Intent(this,VotingActivity.class);
            intent.putExtra("passmeal","Dinner");
            intent.putExtra("passdate",date);

            startActivity(intent);
            finish();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calendar, menu);
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

        Intent intent = new Intent(this,NavigationDrawerActivity.class);
        startActivity(intent);
        finish();
    }
}
