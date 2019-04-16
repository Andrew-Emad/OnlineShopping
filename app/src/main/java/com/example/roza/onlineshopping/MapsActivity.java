package com.example.roza.onlineshopping;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.media.Image;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    static final Integer LOCATION = 0x1;
    SqlDatabase database;
    private GoogleMap mMap;
    EditText address;
    LocationManager locmanager;
    Location locationlisten;
    ImageButton gps;
    Button submitorder;
    Button show;
    HashMap<String,Integer> hashdata;
    String orderdate;
    Integer customerid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        address=(EditText)findViewById(R.id.editText12) ;
        gps=(ImageButton)findViewById(R.id.imageButton5);
        submitorder=(Button)findViewById(R.id.button8);
        show=(Button)findViewById(R.id.button9);

        hashdata=(HashMap<String, Integer>) getIntent().getExtras().getSerializable("hash");
        orderdate=getIntent().getExtras().getString("date");
        customerid=getIntent().getExtras().getInt("id");
        database=new SqlDatabase(getApplicationContext());
        locationlisten=new Location(getApplicationContext());

       locmanager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);

       try {
           locmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER,6000,0,locationlisten);
       }
       catch (SecurityException ex)
       {
           Toast.makeText(this, "Location Accessing Error", Toast.LENGTH_SHORT).show();
       }





        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {

        mMap = googleMap;


        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.04441960,31.23571160),8));// Add a marker in Sydney and move the camera
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askForPermission(Manifest.permission.ACCESS_FINE_LOCATION,LOCATION);
                mMap.clear();
                Geocoder coder=new Geocoder(getApplicationContext());
                List<Address> addressList;
                android.location.Location loc=null;
                try {
                    loc=locmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                }
                catch (SecurityException x)
                {
                    Toast.makeText(getApplicationContext(), "Accessing Is Not Allowed", Toast.LENGTH_SHORT).show();

                }

                if(loc!=null)
                {
                    LatLng mypos=new LatLng(loc.getLatitude(),loc.getLongitude());
                    try
                    {
                        addressList=coder.getFromLocation(mypos.latitude,mypos.longitude,1);
                        if(!addressList.isEmpty())
                        {
                            String addre="";
                            for(int i=0;i<=addressList.get(0).getMaxAddressLineIndex();i++)
                                addre+=addressList.get(0).getAddressLine(i)+",";
                            mMap.addMarker(new MarkerOptions().position(mypos).title("MY LOCATION").snippet(addre)).setDraggable(true);
                            address.setText(addre);
                        }
                    }
                        catch (IOException e)
                        {
                            mMap.addMarker(new MarkerOptions().position(mypos).title("MY LOCATION"));
                        }
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mypos,15));
                    }
                else Toast.makeText(MapsActivity.this, "Please wait until your position is determined", Toast.LENGTH_SHORT).show();


                }


        });
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {


            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Geocoder coder=new Geocoder(getApplicationContext());
                    List<Address> addressList;
                    try {
                        addressList=coder.getFromLocation(marker.getPosition().latitude,marker.getPosition().longitude,1);
                        if(!addressList.isEmpty())
                        {
                            String add="";
                            for(int i=0;i<=addressList.get(0).getMaxAddressLineIndex();i++)
                                add+=addressList.get(0).getAddressLine(i)+",";
                            address.setText(add);

                        }
                        else {
                            Toast.makeText(MapsActivity.this, "No Address", Toast.LENGTH_SHORT).show();
                            address.getText().clear();
                        }
                    } catch (IOException e) {
                        Toast.makeText(MapsActivity.this, "Check Network", Toast.LENGTH_SHORT).show();
                    }
            }
        });
        submitorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!address.getText().toString().equals(""))
                {
                  int orderid=(int) database.insertorder(orderdate,customerid,address.getText().toString());
                    Set<String> keys=hashdata.keySet();
                    for (String item: keys)
                    {
                        int proid,quantity=0;
                        Cursor cu=database.getinfo(item);
                        proid=cu.getInt(2);
                        quantity=cu.getInt(1);
                       int check=database.orderdetails(orderid,proid,hashdata.get(item));
                       quantity-=hashdata.get(item);
                       if(quantity==0)
                           database.deleteproduct(item);
                       else database.updatequantity(item,quantity);

                       if(check==-1)
                           Toast.makeText(MapsActivity.this, "Product are Already Submitted", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(MapsActivity.this,Email.class);
                        startActivity(i);

                    }


                    Toast.makeText(MapsActivity.this, "Order Submitted Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MapsActivity.this, "Enter a Valid Address", Toast.LENGTH_SHORT).show();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MapsActivity.this,ShowAllUsers.class);
                startActivity(i);
            }
        });

    }
    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MapsActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(MapsActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(MapsActivity.this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }


}
