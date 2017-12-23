package com.example.suyash.tastry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NavigationDrawerActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView name,email;

    private Button proceed;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        proceed = (Button)findViewById(R.id.proceed);
        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);

        TextView textView = ((TextView)header.findViewById(R.id.tv_email));
        final TextView tv = ((TextView)header.findViewById(R.id.username));
        datePicker = (DatePicker)findViewById(R.id.calendar);


        proceed.setOnClickListener(this);


        firebaseAuth = FirebaseAuth.getInstance();
         databaseReference = FirebaseDatabase.getInstance().getReference();

         if (firebaseAuth.getCurrentUser() == null){
             finish();
             startActivity(new Intent(this,LoginActivity.class));
         }
        FirebaseUser user = firebaseAuth.getCurrentUser();

         textView.setText(user.getEmail());

         DatabaseReference users = databaseReference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("name");
         users.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 String names = dataSnapshot.getValue(String.class);
                 tv.setText(names);
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public void onClick(View view){
        if(view == proceed){
            finish();
            StringBuilder builder = new StringBuilder();
            builder.append(datePicker.getDayOfMonth()+"/").append((datePicker.getMonth()+1)+"/").append(datePicker.getYear());
            String dmy = builder.toString();
            Intent intent = new Intent(this,Breakfast_lunch_dinner.class);
            intent.putExtra("passdate",dmy);
            startActivity(intent);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logOut) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


}
