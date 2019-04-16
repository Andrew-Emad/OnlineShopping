package com.example.roza.onlineshopping;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

import static java.security.AccessController.getContext;

public class Barcode extends AppCompatActivity implements ZBarScannerView.ResultHandler  {


    private static final int PERMISSIONS_REQUEST_CODE = 0;
    private ZBarScannerView mScannerView;

    /** Called when the activity is first created. */
    static final String SCAN = "com.google.zxing.client.android.SCAN";
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
         Log.v("tag", rawResult.getContents()); // Prints scan results
        Log.v("tag", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        CameraFragment.tvresult.setText(rawResult.getContents());
        onBackPressed();



        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }



}
