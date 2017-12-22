package com.example.suyash.tastry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Suyash on 23-12-2017.
 */

public class DataBasehelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "members.db";
    public final static String DATABASE_PATH = "/data/data/com.example.suyash.tastry/databases/";
    public static final int DATABASE_VERSION = 1;
    SQLiteDatabase db;
    public DataBasehelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
