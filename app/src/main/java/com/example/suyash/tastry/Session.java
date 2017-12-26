package com.example.suyash.tastry;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Suyash on 26-12-2017.
 */

public class Session {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public Session(Context context){
        this.context=context;
        sharedPreferences = context.getSharedPreferences("members",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLoggedIn(boolean loggedIn){
        editor.putBoolean("loggedInmode",loggedIn);
        editor.commit();
    }

    public boolean loggedIn(){
        return sharedPreferences.getBoolean("loggedInmode",false);
    }
}
