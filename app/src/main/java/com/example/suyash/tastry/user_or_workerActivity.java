package com.example.suyash.tastry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class user_or_workerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button student;
    private Button member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_or_worker);
        student = (Button)findViewById(R.id.student);
        member = (Button)findViewById(R.id.messMember);
        student.setOnClickListener(this);
        member.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        if (view == student)
        {
            startActivity(new Intent(this,LoginActivity.class));
            finish();

        }

        if (view == member)
        {
            startActivity(new Intent(this,MemberLogin.class));
            finish();

        }
    }
}
