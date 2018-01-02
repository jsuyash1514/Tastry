package com.example.suyash.tastry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MemberUpload extends AppCompatActivity implements View.OnClickListener{

    private Button upload, result;
//    private EditText fi1,fi2,fi3,fi4,fi5,fi6,fi7,fi8;
    private DatabaseReference databaseReference;
    private static int k=1;
    public long n;
    private ProgressDialog progressDialog;
    public String names1;
    public FloatingActionButton floatingActionButton;
    public String date,oldDate;
    public String meal;
    public  TextView txtdate;
    public List<MealMemberUpload> list;
    public MemberUploadTextAdapter textAdapter;
    public MemberUploadTextAdapter.MemberUploadTextHolder textHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_upload);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(this);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView3);


        list = new ArrayList<>();

        textAdapter = new MemberUploadTextAdapter(MemberUpload.this,list);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            date = bundle.getString("passdate");
            oldDate = bundle.getString("oldDate");
            txtdate = (TextView)findViewById(R.id.memberdate);
            txtdate.setText(date);
        }



        Bundle b = getIntent().getExtras();
        if (b != null)
        {
            meal = bundle.getString("passmeal");
            TextView txtmeal = (TextView)findViewById(R.id.membermeal);
            txtmeal.setText(meal);
        }
        upload = (Button)findViewById(R.id.upload);
        result = (Button)findViewById(R.id.viewResults);
        upload.setOnClickListener(this);
        result.setOnClickListener(this);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.add);
        floatingActionButton.setOnClickListener(this);


        DatabaseReference upload = databaseReference.child(date).child(meal);
        upload.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                n = dataSnapshot.getChildrenCount();
                Log.d(TAG, "getChildrenCount(in memberUpload Activity): "+String.valueOf(n));
                for (int i=1; i<=n; i++){
                    final MealMemberUpload mealMemberUpload = new MealMemberUpload();
                    names1 = dataSnapshot.child("Food Option " + i).getValue(String.class);
                            if (names1 != null && !names1.isEmpty()){
                                mealMemberUpload.setUpload(names1);
                                list.add(mealMemberUpload);
                                textAdapter.notifyDataSetChanged();
                            }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        RecyclerView.LayoutManager recycler = new LinearLayoutManager(MemberUpload.this);
        recyclerView.setLayoutManager(recycler);
        recyclerView.setAdapter(textAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == result){
            Intent intent = new Intent(this,MemberResult.class);

            intent.putExtra("passmeal",meal);
            intent.putExtra("passdate",date);
            startActivity(intent);
            finish();
        }

        if (v == upload){
            uploadFoodItem();
        }
        if (v == floatingActionButton){

            Intent intent = new Intent(this,MemberAddFood.class);
            intent.putExtra("passmeal",meal);
            intent.putExtra("passdate",date);
            startActivity(intent);
            finish();

        }

    }

    private void uploadFoodItem() {

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference databaseReference1 = db.child(oldDate);
        databaseReference1.setValue(null);


        DatabaseReference upload = databaseReference.child(date).child(meal);
        upload.addValueEventListener(new ValueEventListener() {
                                         @Override
                                         public void onDataChange(DataSnapshot dataSnapshot) {
                                             long n = dataSnapshot.getChildrenCount();
                                             for (int i = 1; i <= n; i++) {
                                                 DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
                                                 DatabaseReference mealdb = databaseReference.child(meal);
                                                 if (list.get(i).getUpload() != null) {

                                                     mealdb.child("Food Option " + i).setValue(list.get(i).getUpload());
                                                 } else {
                                                     mealdb.child("Food Option " + i).setValue(null);
                                                 }
                                             }
                                         }

                                         @Override
                                         public void onCancelled(DatabaseError databaseError) {

                                         }
                                     });

        Toast.makeText(getApplicationContext(), "Food options uploaded successfully",Toast.LENGTH_LONG).show();

        startActivity(new Intent(this,MemberPersonalInfo.class));
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calender_member_upload, menu);
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

            startActivity(new Intent(this, MemberPersonalInfo.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(this,memberBraekfastLunchDinner.class);
        intent.putExtra("passmeal",meal);
        intent.putExtra("passdate",date);
        startActivity(intent);
        finish();
    }
}
