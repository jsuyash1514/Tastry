package com.example.suyash.tastry;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Suyash on 23-12-2017.
 */

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    public DatabaseAccess(Context context){
        this.openHelper = new DataBasehelper(context);
    }


    public void open(){
        this.database = openHelper.getReadableDatabase();
    }

    public void close(){
        if (database != null){
            this.database.close();
        }
    }

    public String searchPass(String uname){
        String query = "SELECT * FROM MessMembers";
        Cursor cursor = database.rawQuery(query,null);
        String em , pw="NotFound";
        if (cursor.moveToFirst()){
            do {
                em = cursor.getString(0);
                if (em.equals(uname)){
                    pw = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return pw;
    }
}
