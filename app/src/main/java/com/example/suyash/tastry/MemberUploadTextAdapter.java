package com.example.suyash.tastry;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Suyash on 30-12-2017.
 */

class MemberUploadTextAdapter extends RecyclerView.Adapter<MemberUploadTextAdapter.MemberUploadTextHolder> {
    Context context;
    List<MealMemberUpload> list;
    MealMemberUpload mylist;

    public MemberUploadTextAdapter(Context context, List<MealMemberUpload> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public MemberUploadTextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_upload_list_item,parent,false);
        MemberUploadTextHolder textHolder = new MemberUploadTextHolder(view);
        return textHolder;




    }

    @Override
    public void onBindViewHolder(final MemberUploadTextHolder holder, int position) {

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

    public class MemberUploadTextHolder extends RecyclerView.ViewHolder {
        public TextView upload;
        public MemberUploadTextHolder(View itemView) {
            super(itemView);
            upload = itemView.findViewById(R.id.list_upload);


        }
    }
}

