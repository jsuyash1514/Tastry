package com.example.suyash.tastry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MemberUpload extends AppCompatActivity implements View.OnClickListener{

    private Button upload, result;
    private EditText fi1,fi2,fi3,fi4,fi5,fi6,fi7,fi8;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    public String date;
    public String meal;
    public  TextView txtdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_upload);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(this);




        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            date = bundle.getString("passdate");
            txtdate = (TextView)findViewById(R.id.memberdate);
            txtdate.setText(date);
        }



        Bundle b = getIntent().getExtras();
        if (bundle != null)
        {
            meal = bundle.getString("passmeal");
            TextView txtmeal = (TextView)findViewById(R.id.membermeal);
            txtmeal.setText(meal);
        }
        fi1 = (EditText)findViewById(R.id.fooditem1);
        fi2 = (EditText)findViewById(R.id.fooditem2);
        fi3 = (EditText)findViewById(R.id.fooditem3);
        fi4 = (EditText)findViewById(R.id.fooditem4);
        fi5 = (EditText)findViewById(R.id.fooditem5);
        fi6 = (EditText)findViewById(R.id.fooditem6);
        fi7 = (EditText)findViewById(R.id.fooditem7);
        fi8 = (EditText)findViewById(R.id.fooditem8);
        upload = (Button)findViewById(R.id.upload);
        result = (Button)findViewById(R.id.viewResults);
        upload.setOnClickListener(this);
        result.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == result){
            Intent intent = new Intent(this,MemberResult.class);
            finish();
            intent.putExtra("passmeal",meal);
            intent.putExtra("passdate",date);
            startActivity(intent);
        }

        if (v == upload){
            uploadFoodItem();
        }

    }

    private void uploadFoodItem() {
        final String fooditem1 = fi1.getText().toString().trim();
        final String fooditem2 = fi2.getText().toString().trim();
        final String fooditem3 = fi3.getText().toString().trim();
        final String fooditem4 = fi4.getText().toString().trim();
        final String fooditem5 = fi5.getText().toString().trim();
        final String fooditem6 = fi6.getText().toString().trim();
        final String fooditem7 = fi7.getText().toString().trim();
        final String fooditem8 = fi8.getText().toString().trim();

        progressDialog.setMessage("Uploading Please wait...");
        progressDialog.show();



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
        DatabaseReference mealdb = databaseReference.child(meal);
        if (!(fooditem1.equals(""))){mealdb.child("Food Option 1").setValue(fooditem1);}
        if (!(fooditem2.equals(""))){mealdb.child("Food Option 2").setValue(fooditem2);}
        if (!(fooditem3.equals(""))){mealdb.child("Food Option 3").setValue(fooditem3);}
        if (!(fooditem4.equals(""))){mealdb.child("Food Option 4").setValue(fooditem4);}
        if (!(fooditem5.equals(""))){mealdb.child("Food Option 5").setValue(fooditem5);}
        if (!(fooditem6.equals(""))){mealdb.child("Food Option 6").setValue(fooditem6);}
        if (!(fooditem7.equals(""))){mealdb.child("Food Option 7").setValue(fooditem7);}
        if (!(fooditem8.equals(""))){mealdb.child("Food Option 8").setValue(fooditem8);}

        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Food options uploaded successfully",Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(this,MemberPersonalInfo.class));

    }
}