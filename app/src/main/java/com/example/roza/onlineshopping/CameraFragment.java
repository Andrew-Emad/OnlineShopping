package com.example.roza.onlineshopping;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;


public class CameraFragment extends Fragment {
    public static TextView tvresult;
    static final Integer CAMERA = 0x5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_bar, container, false);

        tvresult = (TextView)view.findViewById(R.id.tvresult);

        Button btn = (Button)view.findViewById(R.id.btn);
        Button perm = (Button)view.findViewById(R.id.button7);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Barcode.class);
                startActivity(intent);
            }
        });

        perm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askForPermission(Manifest.permission.CAMERA,CAMERA);
            }
        });
        return view;
    }


    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(getActivity(), "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

}
