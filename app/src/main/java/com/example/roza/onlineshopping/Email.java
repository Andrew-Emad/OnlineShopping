package com.example.roza.onlineshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Email extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        final EditText edt=(EditText)findViewById(R.id.editText7);
        Button email=(Button)findViewById(R.id.button11);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt.getText().toString().equals(""))
                {
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL,new String[]{edt.getText().toString()});
                i.putExtra(Intent.EXTRA_SUBJECT,"Confirmation Mail");
                i.putExtra(Intent.EXTRA_TEXT,"Thanks For Using our app , Your order has been Successfully Confirmed");
                    try {
                    startActivity(Intent.createChooser(i,"Sending email..."));
                    }
                    catch (android.content.ActivityNotFoundException ex)
                    {
                    Toast.makeText(Email.this, "Enter a Valid Email Address", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(Email.this, "Enter A Valid Email", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
