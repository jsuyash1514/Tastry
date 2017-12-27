package com.example.suyash.tastry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suyash on 26-12-2017.
 */

public class TextAdapter extends RecyclerView.Adapter<TextHolder> {

    Context context;
    List<Meal> list;



    public TextAdapter(Context context, List<Meal> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public TextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.voting_list_item,parent,false);
        TextHolder textHolder = new TextHolder(view);
        return textHolder;
    }

    @Override
    public void onBindViewHolder(TextHolder holder, final int position) {
        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClick(View view, int pos) {
                CheckBox checkBox = (CheckBox)view;
                if (checkBox.isChecked()){

                }
            }

        });


        Meal mylist = list.get(position);
        holder.meal.setText(mylist.getMeal());

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
        return arr;
    }
}
