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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_personal_info);


        proceed = (Button)findViewById(R.id.memberproceed);
        datePicker = (DatePicker)findViewById(R.id.membercalendar);
        proceed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == proceed){
            finish();
            StringBuilder b = new StringBuilder();
            b.append(datePicker.getYear()+"/").append((datePicker.getMonth()+1)+"/").append(datePicker.getDayOfMonth());
            String dm = b.toString();
            Intent intent = new Intent(this,memberBraekfastLunchDinner.class);
            intent.putExtra("passdat",dm);
            startActivity(intent);

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
            finish();
            startActivity(new Intent(this, MemberLogin.class));
        }

        return super.onOptionsItemSelected(item);
    }


}

