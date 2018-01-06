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

    public  static String date,meal,oldDate;
    public TextView txtdate,txtmeal;
    public Button add;
    public EditText food;
    public static long i = 1;
    long n;
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
            meal = bundle.getString("passmeal");
            oldDate = bundle.getString("oldDate");
        }

        txtdate = (TextView) findViewById(R.id.add_date);
        txtdate.setText(date);
        txtmeal = (TextView) findViewById(R.id.add_meal);
        txtmeal.setText(meal);


    }

    @Override
    public void onClick(View v) {
        if (v == add){
            addfood();
        }

    }

    private void addfood() {

        final DatabaseReference food1 = databaseReference.child(date).child(meal);
        food1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                n = dataSnapshot.getChildrenCount();

                i = n+1;

                Log.d(TAG, "Entered addFood method wih n= " + String.valueOf(n));
                String names6 = food.getText().toString().trim();
                if (names6 != null && !names6.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Food option added successfully",Toast.LENGTH_LONG).show();
                    DatabaseReference mealdb = databaseReference.child(date).child(meal);
                    mealdb.child("Food Option " + i).setValue(names6);
                    i++;
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Food option added",Toast.LENGTH_LONG).show();

                }

                Intent inten = new Intent(getApplicationContext(),MemberUpload.class);
                inten.putExtra("add",names6);
                Log.d(TAG, "Intent to MemberUpload");
                finish();
                startActivity(inten);


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }
    @Override
    public void onBackPressed(){

        Intent intent = new Intent(this,MemberUpload.class);
        startActivity(intent);
        finish();
    }
}
