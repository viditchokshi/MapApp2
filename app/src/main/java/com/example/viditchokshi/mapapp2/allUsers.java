package com.example.viditchokshi.mapapp2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.viditchokshi.mapapp2.Homepage.username;

public class allUsers extends AppCompatActivity {
    ArrayAdapter adapter;
    databaseUser db;
    ArrayList<String> listItem;
    ListView userLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        db=new databaseUser(this);
        listItem=new ArrayList<>();
        userLocation=findViewById(R.id.locationlist);
        viewData();


    }

    private void viewData() {
        Cursor cursor=db.viewData();
        if(cursor.getCount()==0)
        {
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
        }
        else
        {

            while(cursor.moveToNext())
            {
                listItem.add(cursor.getString(0)+","+cursor.getString(1)+","+cursor.getString(2));

            }
            adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
            userLocation.setAdapter(adapter);

            

        }
    }

}
