package com.example.suyash.tastry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class memberBraekfastLunchDinner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_braekfast_lunch_dinner);

        String date;

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            date = bundle.getString("passdate");
            TextView txtdate = (TextView)findViewById(R.id.memberselectDate);
            txtdate.setText(date);
        }
    }
}
