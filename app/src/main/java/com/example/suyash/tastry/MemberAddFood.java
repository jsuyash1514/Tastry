package com.example.suyash.tastry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class MemberAddFood extends AppCompatActivity implements View.OnClickListener {

    public String date,meal,oldDate;
    public TextView txtdate,txtmeal;
    public Button add;
    public EditText food;
    public static int i = 1;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_add_food);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        add = (Button)findViewById(R.id.btn_add_food);
        add.setOnClickListener(this);
        food = (EditText)findViewById(R.id.addfood);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            date = bundle.getString("passdate");
            txtdate = (TextView) findViewById(R.id.add_date);
            txtdate.setText(date);
            meal = bundle.getString("passmeal");
            txtmeal = (TextView) findViewById(R.id.add_meal);
            txtmeal.setText(meal);
            oldDate = bundle.getString("oldDate");
        }


    }

    @Override
    public void onClick(View v) {
        if (v == add){
            addfood();
        }

    }

    private void addfood() {

        Log.d(TAG, "Entered addFood method");
        String names6 = food.getText().toString().trim();
        if (names6 != null && !names6.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Food option added successfully",Toast.LENGTH_LONG).show();
            DatabaseReference mealdb = databaseReference.child(date).child("add");
            mealdb.child(meal).child("Food Option " + i).setValue(names6);
            i++;
        }
        else {
            Toast.makeText(getApplicationContext(), "No Food option added",Toast.LENGTH_LONG).show();

        }

        Intent inten = new Intent(getApplicationContext(),MemberUpload.class);
        inten.putExtra("passmeal",meal);
        inten.putExtra("passdate",date);
        inten.putExtra("oldDate",oldDate);
        Log.d(TAG, "Intent to MemberUpload");
        finish();
        startActivity(inten);



    }
    @Override
    public void onBackPressed(){

        Intent intent = new Intent(this,MemberUpload.class);
        intent.putExtra("passmeal",meal);
        intent.putExtra("passdate",date);
        intent.putExtra("oldDate",oldDate);
        startActivity(intent);
        finish();
    }
}
