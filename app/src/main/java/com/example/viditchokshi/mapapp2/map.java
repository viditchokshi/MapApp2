package com.example.viditchokshi.mapapp2;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class map extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener{

    LocationManager locationManager;
    LocationListener locationListener;
    private GoogleMap mMap;
    static String Uname;

    public void centerMap(Location location,String title)
    {
        LatLng userLocation=new LatLng(location.getLatitude(),location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(userLocation).title(title));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,12));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
            {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);
        Intent intent=getIntent();
        if(intent.getIntExtra("placeNumber",0)==0)
        {
            //zoom to user
            locationManager=(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            locationListener=new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    //centerMap(location,Uname);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
            {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                centerMap(lastLocation,Uname);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

            }
        }
        else
        {
            Location placeLocation=new Location(LocationManager.GPS_PROVIDER);
            placeLocation.setLatitude(Homepage.locations.get(intent.getIntExtra("placeNumber",0)).latitude);
            placeLocation.setLongitude(Homepage.locations.get(intent.getIntExtra("placeNumber",0)).longitude);

            //centerMap(placeLocation,Uname);

        }


    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Uname= getIntent().getStringExtra("username");
        mMap.addMarker(new MarkerOptions().position(latLng).title(Uname));
        Homepage.locations.add(latLng);
        Homepage.arrayAdapter.notifyDataSetChanged();

        //Toast.makeText(this, "Location Saved!", Toast.LENGTH_SHORT).show();

        databaseUser dbUser=new databaseUser(this);
        SQLiteDatabase db2= dbUser.getWritableDatabase();
        ContentValues values2=new ContentValues();

        values2.put("Username",Uname);
        values2.put("Longitude",latLng.longitude);
        values2.put("Latitude",latLng.latitude);

        db2.insert("Location",null,values2);
        Toast.makeText(this,"Location Saved and in database!",Toast.LENGTH_LONG).show();

    }
}
