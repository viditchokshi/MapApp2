package com.example.viditchokshi.mapapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {

    static ArrayList<String> places=new ArrayList<String>();
    static ArrayList<LatLng> locations=new ArrayList<LatLng>();
    static ArrayAdapter arrayAdapter;
    static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        username=getIntent().getStringExtra("Username");
        TextView Uname=findViewById(R.id.tvUsername);
        Uname.setText("Welcome "+username);

        ListView listView=findViewById(R.id.listView);


        places.add(username);
        locations.add(new LatLng(0,0));
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,locations);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(Homepage.this,Integer.toString(position),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),map.class);
                intent.putExtra("placeNumber",position);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        });
    }
}
