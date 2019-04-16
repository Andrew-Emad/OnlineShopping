package com.example.roza.onlineshopping;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.roza.onlineshopping.Login.Customers;

public class Cart extends Shopping {
    SqlDatabase database;
    HashMap<String,Integer> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


       data=new HashMap<String, Integer>();
        data=Customers.getProducts();



        customarray arr=new customarray(data);
        final ListView list=(ListView)findViewById(R.id.listpro);
        list.setAdapter(arr);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Button submit=(Button)findViewById(R.id.submitcart);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent i=new Intent(Cart.this,MapsActivity.class);
                    Date c=Calendar.getInstance().getTime();
                    SimpleDateFormat df=new SimpleDateFormat("dd-MMM-yyyy");
                    String curdate=df.format(c);
                    int id=Customers.getId();
                    i.putExtra("date",curdate);
                    i.putExtra("hash",data);
                    i.putExtra("id",id);

                    startActivity(i);
            }
        });






    }
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_products) {
            Intent i=new Intent(this,Products.class);
            startActivity(i);

        } else if (id == R.id.nav_search) {
            Intent i=new Intent(this,Search.class);
            startActivity(i);

        } else if (id == R.id.nav_cart) {
            Intent i=new Intent(this,Cart.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

}

