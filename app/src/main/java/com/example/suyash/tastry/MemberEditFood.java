package com.example.suyash.tastry;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class MemberEditFood extends AppCompatActivity implements View.OnClickListener{
    private Button delete;
    private Button edit;
    public String item,initial,change;
    public TextView txtdate,txtmeal;
    private EditText editText;
    public int position;
    public String string,date,meal,oldDate;
    private DatabaseReference databaseReference;
    public static int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_edit_food);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            date = bundle.getString("passdate");
            txtdate = (TextView) findViewById(R.id.edit_date);
            txtdate.setText(date);
            meal = bundle.getString("passmeal");
            txtmeal = (TextView) findViewById(R.id.edit_meal);
            txtmeal.setText(meal);
            oldDate = bundle.getString("oldDate");
        }

        Intent intent = getIntent();
        position = intent.getIntExtra("position",1000);
        Log.d("Position","Position of Item Clicked: " + String.valueOf(position));

        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null){
            item = bundle1.getString("item");
        }

        delete = (Button)findViewById(R.id.btn_delete_food);
        delete.setOnClickListener(this);
        edit = (Button)findViewById(R.id.btn_edit_food);
        edit.setOnClickListener(this);
        editText = (EditText)findViewById(R.id.editfood);
        editText.setText(item);

    }

    @Override
    public void onClick(View v) {
        if (v == delete){
            deleteFood();

        }

        if (v == edit){
            editFood();

        }
    }

    private void editFood() {
        string = editText.getText().toString();
        DatabaseReference edit1 = databaseReference.child(date);
        edit1.child("edit").child(meal).child("Initial").setValue(item);
        edit1.child("edit").child(meal).child("change").setValue(string);


        final DatabaseReference edit = databaseReference.child(date).child("edit").child(meal);
        edit.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String initial = dataSnapshot.child("Initial").getValue(String.class);
                final String change = dataSnapshot.child("change").getValue(String.class);
                Log.d(TAG,"Initial = " + initial + " and change = " + change);
                if (initial != null && !initial.isEmpty()) {
                    Log.d(TAG,"Entered if statement for changing " + initial);
                    final DatabaseReference food = databaseReference.child(date).child(meal);
                    food.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            long n = dataSnapshot.getChildrenCount();
                            String FoodOption;
                            for (int j = 1; j <= 50; j++) {
                                FoodOption = (String) dataSnapshot.child("Food Option " + j).getValue();
                                if (initial.equals(FoodOption)) {
                                    food.child("Food Option " + j).setValue(change);
                                    FoodOption = null;
                                    edit.setValue(null);
                                    Intent intent = new Intent(getApplicationContext(), MemberUpload.class);
                                    intent.putExtra("passmeal",meal);
                                    intent.putExtra("passdate",date);
                                    intent.putExtra("oldDate",oldDate);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                    final DatabaseReference food1 = databaseReference.child(date).child("add").child(meal);
                    food1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            long n = dataSnapshot.getChildrenCount();
                            String FoodOption;
                            for (int j = 1; j <= 50; j++) {
                                FoodOption = (String) dataSnapshot.child("Food Option " + j).getValue();
                                if (initial.equals(FoodOption)) {
                                    food1.child("Food Option " + j).setValue(change);
                                    FoodOption = null;
                                    edit.setValue(null);
                                    Intent intent = new Intent(getApplicationContext(), MemberUpload.class);
                                    intent.putExtra("passmeal",meal);
                                    intent.putExtra("passdate",date);
                                    intent.putExtra("oldDate",oldDate);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
                else {
                    Log.d(TAG, "Not entered if becoz initial = " + initial);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });






    }

    private void deleteFood() {
        DatabaseReference delete = databaseReference.child(date).child("delete").child(meal);
        delete.setValue(item);


        final DatabaseReference delete1 =databaseReference.child(date).child("delete").child(meal);
        delete1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                final String del = dataSnapshot.getValue(String.class);
                Log.d(TAG,"del = " + del);
                if (del != null && !del.isEmpty()) {
                    Log.d(TAG,"Entered if statement for deleting " + del);
                    final DatabaseReference food = databaseReference.child(date).child(meal);
                    food.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            long n = dataSnapshot.getChildrenCount();
                            String FoodOption;
                            for (int j = 1; j <= 50; j++) {
                                Log.d(TAG,"Entered for loop for deleting " + del);
                                FoodOption = (String) dataSnapshot.child("Food Option " + j).getValue();
                                if (del.equals(FoodOption)) {
                                    food.child("Food Option " + j).setValue(null);
                                    FoodOption = null;
                                    delete1.setValue(null);

                                    Intent intent = new Intent(getApplicationContext(),MemberUpload.class);
                                    intent.putExtra("passmeal",meal);
                                    intent.putExtra("passdate",date);
                                    intent.putExtra("oldDate",oldDate);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                    final DatabaseReference food1 = databaseReference.child(date).child("add").child(meal);
                    food1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            long n = dataSnapshot.getChildrenCount();
                            String FoodOption;
                            for (int j = 1; j <= 50; j++) {
                                FoodOption = (String) dataSnapshot.child("Food Option " + j).getValue();
                                if (del.equals(FoodOption)) {
                                    food1.child("Food Option " + j).setValue(null);
                                    FoodOption = null;
                                    delete1.setValue(null);

                                    Intent intent = new Intent(getApplicationContext(),MemberUpload.class);
                                    intent.putExtra("passmeal",meal);
                                    intent.putExtra("passdate",date);
                                    intent.putExtra("oldDate",oldDate);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
                else {
                    Log.d(TAG, "Not entered if becoz del = " + del);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });




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
