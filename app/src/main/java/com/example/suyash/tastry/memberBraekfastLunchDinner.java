package com.example.suyash.tastry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class memberBraekfastLunchDinner extends AppCompatActivity implements View.OnClickListener{

    private TextView breakfast;
    private TextView lunch;
    private TextView dinner;
    public String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_braekfast_lunch_dinner);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            date = bundle.getString("passdat");
            TextView txtdate = (TextView)findViewById(R.id.memberselectDate);
            txtdate.setText(date);
        }

        breakfast = (TextView)findViewById(R.id.memberbreakfast);
        lunch = (TextView)findViewById(R.id.memberlunch);
        dinner = (TextView)findViewById(R.id.memberdinner);

        breakfast.setOnClickListener(this);
        lunch.setOnClickListener(this);
        dinner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == breakfast){
            Intent intent = new Intent(this,MemberUpload.class);
            intent.putExtra("passmeal","Breakfast");
            intent.putExtra("passdate",date);
            finish();
            startActivity(intent);
        }

        if (v == lunch){
            Intent intent = new Intent(this,MemberUpload.class);
            intent.putExtra("passmeal","Lunch");
            intent.putExtra("passdate",date);
            finish();
            startActivity(intent);
        }

        if (v == dinner){
            Intent intent = new Intent(this,MemberUpload.class);
            intent.putExtra("passmeal","Dinner");
            intent.putExtra("passdate",date);
            finish();
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calender_member_bld, menu);
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
            finish();
            startActivity(new Intent(this, MemberPersonalInfo.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
