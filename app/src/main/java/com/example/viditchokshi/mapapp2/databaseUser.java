package com.example.viditchokshi.mapapp2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseUser extends SQLiteOpenHelper {
    public databaseUser(Context context) {
        super(context, "dbLocation", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTable="Create TABLE Location(Username VARCHAR, Latitude TEXT,Longitude TEXT);";
        db.execSQL(userTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

