package com.example.suyash.tastry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suyash on 27-12-2017.
 */

public class MemberResultTextAdapter extends RecyclerView.Adapter<MemberResultTextholder> {
    Context context;
    List<MealMemberResult> list ;


    public MemberResultTextAdapter(Context context, List<MealMemberResult> list) {
        this.context = context;
            this.list = list;


    }
    @Override
    public MemberResultTextholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_result_list_item,parent,false);
        MemberResultTextholder textHolder = new MemberResultTextholder(view);
        return textHolder;
    }

    @Override
    public void onBindViewHolder(MemberResultTextholder holder, int position) {

        MealMemberResult mylist = list.get(position);
        holder.foodOption.setText(mylist.getFoodOption());
        holder.Amount.setText(mylist.getNumber());

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
        return arr;
    }

}
