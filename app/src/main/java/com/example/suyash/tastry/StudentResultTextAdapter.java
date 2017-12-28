package com.example.suyash.tastry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Suyash on 28-12-2017.
 */

public class StudentResultTextAdapter extends RecyclerView.Adapter<StudentResultTextHolder> {

    Context context;
    List<StudentResultModel> list;
    View view;



    public StudentResultTextAdapter(Context context, List<StudentResultModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public StudentResultTextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_result_list_item,parent,false);
        StudentResultTextHolder textHolder = new StudentResultTextHolder(view);
        return textHolder;
    }

    @Override
    public void onBindViewHolder(StudentResultTextHolder holder, int position) {
        StudentResultModel mylist = list.get(position);
        holder.vote.setText(mylist.getVote());

    }

    @Override
    public int getItemCount() {
        int arr = 0;
        try {
            if (list.size()==0){
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
}
