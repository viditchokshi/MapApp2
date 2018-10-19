package com.example.viditchokshi.mapapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDb extends SQLiteOpenHelper {
    public MyDb(Context context) {
        super(context, "myDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTable="Create TABLE User(Name VARCHAR, Surname VARCHAR, Username VARCHAR primary key, Password VARCHAR);";
        db.execSQL(userTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
    public boolean insert(String username,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Username",username);
        contentValues.put("Password",password);
        long ins=db.insert("User",null,contentValues);
        if(ins==-1) return false;
        else return true;

    }

    //Check if email(user) exixts
    public boolean chkemail(String username)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from User where Username=?",new String[]{username});
        if(cursor.getCount()>0) return true;
        else return false;
    } */
}
