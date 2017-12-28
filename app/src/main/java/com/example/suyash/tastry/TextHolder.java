package com.example.suyash.tastry;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Suyash on 27-12-2017.
 */

public class TextHolder extends RecyclerView.ViewHolder {
    CheckBox checkBox;
    TextView meal;
    ItemClickListner itemClickListner;

    public TextHolder(View itemView) {
        super(itemView);
        checkBox = (CheckBox) itemView.findViewById(R.id.list_i1);
        meal = (TextView) itemView.findViewById(R.id.list_text);
    }
}
