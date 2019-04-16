package com.example.roza.onlineshopping;

import android.content.Context;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class Location implements LocationListener {
    private Context context;

    public Location(Context con)

    {
        context=con;
    }
    @Override
    public void onLocationChanged(android.location.Location location) {
        Toast.makeText(context, location.getLatitude()+" , "+location.getLongitude(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(context, "GPS Enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(context, "GPS Disabled", Toast.LENGTH_SHORT).show();

    }
}
