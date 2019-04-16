package com.example.roza.onlineshopping;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import static com.example.roza.onlineshopping.Login.Customers;

public class Preview extends AppCompatActivity {
SqlDatabase data;
int quantity;
float price;
int selectedquantity;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        TextView title=(TextView)findViewById(R.id.textView4);
        TextView prices=(TextView)findViewById(R.id.textView5);
        TextView quaninty=(TextView)findViewById(R.id.textView6);
        Button submit=(Button)findViewById(R.id.button5);
        Button submits=(Button)findViewById(R.id.button6);
       final Spinner spin=(Spinner)findViewById(R.id.spinner2);
        title.setText(getIntent().getExtras().getString("product"));
        data=new SqlDatabase(this);
        Cursor cur=data.getinfo(getIntent().getExtras().getString("product"));
        while (!cur.isAfterLast())
        {
            quantity=cur.getInt(1);
            price=cur.getFloat(0);
            cur.moveToNext();

        }
        prices.append(" "+String.valueOf(price));
        quaninty.append(" "+String.valueOf(quantity));
        Integer[] numbers= new Integer[quantity];
        for (int i=0;i<quantity;i++)
        {
            numbers[i]=i+1;
        }
        ArrayAdapter<Integer> arr=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,numbers);
        spin.setAdapter(arr);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 selectedquantity=Integer.valueOf( spin.getSelectedItem().toString());
                Customers.ADD(getIntent().getExtras().getString("product"),selectedquantity);
                Toast.makeText(Preview.this, String.valueOf(Customers.getvalue(getIntent().getExtras().getString("product"))), Toast.LENGTH_SHORT).show();

            }
        });
        submits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Preview.this,Cart.class);
                startActivity(i);
            }
        });




    }
}
