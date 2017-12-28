package com.example.suyash.tastry;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Suyash on 28-12-2017.
 */

public class StudentResultTextHolder extends RecyclerView.ViewHolder {
    TextView vote;
    public StudentResultTextHolder(View itemView) {
        super(itemView);
        vote = (TextView)itemView.findViewById(R.id.student_result_li);
    }
}
