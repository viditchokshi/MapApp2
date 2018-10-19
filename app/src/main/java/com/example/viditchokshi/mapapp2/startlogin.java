package com.example.viditchokshi.mapapp2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class startlogin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startlogin);


    }

    public void login(View view) {
        MyDb myDb=new MyDb(this);
        SQLiteDatabase db=myDb.getReadableDatabase();

        EditText Username=findViewById(R.id.etUsername);
        EditText Password=findViewById(R.id.etUsername);
        String Uname=Username.getText().toString();

        String [] columns={"Username","Password"};
        String [] cValues={Username.getText().toString(),Password.getText().toString()};


        Cursor cursor = db.query("User", columns, "Username = ? AND Password = ?",cValues, null, null, null);
        if(cursor!=null)
        {
            //Toast.makeText(this, "checking", Toast.LENGTH_SHORT).show();
            if(cursor.moveToFirst())
           {
               Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,Homepage.class);
                intent.putExtra("Username",Username.getText().toString());
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Wrong details. Register first!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this,"Something Wrong",Toast.LENGTH_SHORT).show();
        }
    }

    public void registration(View view) {
        Intent intent=new Intent(this,Registration.class);
        startActivity(intent);
    }


}