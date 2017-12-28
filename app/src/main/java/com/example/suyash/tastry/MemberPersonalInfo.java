package com.example.suyash.tastry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class MemberPersonalInfo extends AppCompatActivity implements View.OnClickListener{

    private Button proceed;
    private DatePicker datePicker;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_personal_info);

        session = new Session(this);
        if (!session.loggedIn()){
            logout();
        }
        proceed = (Button)findViewById(R.id.memberproceed);
        datePicker = (DatePicker)findViewById(R.id.membercalendar);
        proceed.setOnClickListener(this);
    }

    private void logout() {
        session.setLoggedIn(false);

        startActivity(new Intent(this,MemberLogin.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v == proceed){

            StringBuilder b = new StringBuilder();
            b.append(datePicker.getYear()+"/").append((datePicker.getMonth()+1)+"/").append(datePicker.getDayOfMonth());
            String dm = b.toString();
            Intent intent = new Intent(this,memberBraekfastLunchDinner.class);
            intent.putExtra("passdate",dm);
            int month,year;
            if (datePicker.getMonth() == 0)  {month = 11;year = (datePicker.getYear()-1);}
            else if (datePicker.getMonth() == 1) {month = 12;year = (datePicker.getYear()-1);}
            else {month = (datePicker.getMonth() - 1); year =datePicker.getYear();};
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(year+"/").append(month);
            String months = stringBuilder.toString();
            intent.putExtra("oldDate",months);
            startActivity(intent);
            finish();

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_member, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logOut) {
            session.setLoggedIn(false);

            startActivity(new Intent(this, MemberLogin.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed(){

        startActivity(new Intent(this,user_or_workerActivity.class));
        finish();
    }


}

