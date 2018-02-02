package com.example.suyash.tastry;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
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
    public static int j;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_upload);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

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
        upload.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                n = dataSnapshot.getChildrenCount();
                Log.d(TAG, "getChildrenCount(in memberUpload Activity): "+String.valueOf(n));
                for (int i=1; i<=50; i++){
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


        DatabaseReference upload1 = databaseReference.child(date).child("add").child(meal);
        upload1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long m = dataSnapshot.getChildrenCount();
                Log.d(TAG, "getChildrenCount(in add node): "+String.valueOf(m));
                for (int i=1; i<=50; i++){
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
            intent.putExtra("oldDate",oldDate);
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
            intent.putExtra("oldDate",oldDate);
            finish();
            startActivity(intent);


        }

    }

    private void uploadFoodItem() {

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference databaseReference1 = db.child(oldDate);
        databaseReference1.setValue(null);
        final DatabaseReference databaseReference2 = db.child(date).child("add");
        databaseReference2.setValue(null);


        long n = list.size();
        for (int i = 1; i <= n; i++) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(date);
            DatabaseReference mealdb = databaseReference.child(meal);
            if (list.get(i-1).getUpload() != null) {
                mealdb.child("Food Option " + i).setValue(list.get(i-1).getUpload());
            } else {
                mealdb.child("Food Option " + i).setValue(null);
            }
        }


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

    public  class MemberUploadTextAdapter extends RecyclerView.Adapter<com.example.suyash.tastry.MemberUpload.MemberUploadTextAdapter.MemberUploadTextHolder> {
        Context context;
        List<MealMemberUpload> list;
        MealMemberUpload mylist;

        public MemberUploadTextAdapter(Context context, List<MealMemberUpload> list) {

            this.context = context;
            this.list = list;
        }

        @Override
        public com.example.suyash.tastry.MemberUpload.MemberUploadTextAdapter.MemberUploadTextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_upload_list_item,parent,false);
            com.example.suyash.tastry.MemberUpload.MemberUploadTextAdapter.MemberUploadTextHolder textHolder = new com.example.suyash.tastry.MemberUpload.MemberUploadTextAdapter.MemberUploadTextHolder(view);
            return textHolder;




        }

        @Override
        public void onBindViewHolder(final com.example.suyash.tastry.MemberUpload.MemberUploadTextAdapter.MemberUploadTextHolder holder, int position) {

            mylist = list.get(position);
            holder.upload.setText(mylist.getUpload());
            holder.upload.setTag(position);


        }

        @Override
        public int getItemCount() {
            int arr = 0;
            try {
                if (list.size()== 0){
                    arr =0;
                }
                else {
                    arr = list.size();
                }
            }catch (Exception e){

            }

            Log.d(TAG, "getItemCount: "+String.valueOf(arr));
            return arr;
        }




        public class MemberUploadTextHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView upload;
            public MemberUploadTextHolder(View itemView) {
                super(itemView);
                upload = itemView.findViewById(R.id.list_upload);
                upload.setOnClickListener(this);




            }
            @Override
            public void onClick(View v) {
                if (v.getId() == upload.getId()){
                    Intent intent = new Intent(v.getContext(), MemberEditFood.class);
                    int position = (int)upload.getTag();
                    intent.putExtra("passmeal",meal);
                    intent.putExtra("passdate",date);
                    intent.putExtra("oldDate",oldDate);
                    intent.putExtra("position",position);
                    intent.putExtra("item",list.get(position).getUpload());
                    startActivity(intent);
                    finish();

                }
            }
        }
    }

}


