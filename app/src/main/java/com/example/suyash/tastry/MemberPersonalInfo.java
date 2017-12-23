package com.example.suyash.tastry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
            StringBuilder builder = new StringBuilder();
            builder.append(datePicker.getDayOfMonth()+"/").append((datePicker.getMonth()+1)+"/").append(datePicker.getYear());
            String dmy = builder.toString();
            Intent intent = new Intent(this,memberBraekfastLunchDinner.class);
            intent.putExtra("passdate",dmy);
            startActivity(intent);

        }

    }
}