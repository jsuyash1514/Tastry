package com.example.suyash.tastry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Suyash on 26-12-2017.
 */

public class TextAdapter extends RecyclerView.Adapter<TextHolder> implements View.OnClickListener{

    Context context;
    List<Meal> list;
    View view;



    public TextAdapter(Context context, List<Meal> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public TextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voting_list_item,parent,false);
        TextHolder textHolder = new TextHolder(view);
        return textHolder;
    }

    @Override
    public void onBindViewHolder(final TextHolder holder, final int position) {
        holder.checkBox.setChecked(list.get(position).getSelected());
        Meal mylist = list.get(position);
        holder.meal.setText(mylist.getMeal());
        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = (Integer)holder.checkBox.getTag();
                if (list.get(pos).getSelected()){
                    list.get(pos).setSelected(false);
                }
                else{
                    list.get(pos).setSelected(true);
                }
            }
        });

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

    @Override
    public void onClick(View v) {

    }
}
