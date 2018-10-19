package com.example.viditchokshi.mapapp2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText etName;
    EditText etSurname;
    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


    }

    public void register(View view) {
        etName=findViewById(R.id.name);
        etSurname=findViewById(R.id.surname);
        etUsername=findViewById(R.id.username);
        etPassword=findViewById(R.id.password);
        String name= etName.getText().toString();
        String surname=etSurname.getText().toString();
        String username=etUsername.getText().toString();
        String password=etPassword.getText().toString();

        MyDb myDb=new MyDb(this);
        SQLiteDatabase db= myDb.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("Name",name);
        values.put("Surname",surname);
        values.put("Username",username);
        values.put("Password",password);

        db.insert("User",null,values);
        Toast.makeText(this,"New User Registered!",Toast.LENGTH_LONG).show();


    }
}
