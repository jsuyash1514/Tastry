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
    public String string;
    public static String date,meal,oldDate;
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

            meal = bundle.getString("passmeal");

            oldDate = bundle.getString("oldDate");
        }

        txtdate = (TextView) findViewById(R.id.edit_date);
        txtdate.setText(date);
        txtmeal = (TextView) findViewById(R.id.edit_meal);
        txtmeal.setText(meal);

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



        Intent intent = new Intent(this, MemberUpload.class);
        startActivity(intent);
        finish();
    }

    private void deleteFood() {
        DatabaseReference delete = databaseReference.child(date).child("delete").child(meal);
        delete.setValue(item);


        Intent intent = new Intent(this,MemberUpload.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(this,MemberUpload.class);
        startActivity(intent);
        finish();
    }
}
