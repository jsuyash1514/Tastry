package com.example.suyash.tastry;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Suyash on 27-12-2017.
 */

public class MemberResultTextholder extends RecyclerView.ViewHolder {
    public TextView foodOption, Amount;
    public MemberResultTextholder(View itemView) {
        super(itemView);
        foodOption = (TextView)itemView.findViewById(R.id.list_food);
        Amount = (TextView)itemView.findViewById(R.id.list_amount);
    }
}
