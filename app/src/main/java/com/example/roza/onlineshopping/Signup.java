package com.example.roza.onlineshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Signup extends AppCompatActivity {
    int id=-1;
    String selected="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final EditText name=(EditText)findViewById(R.id.editText3);
        final EditText username=(EditText)findViewById(R.id.editText4);
        final EditText pass=(EditText)findViewById(R.id.editText5);
        final Spinner gender=(Spinner)findViewById(R.id.spinner);
        final CalendarView calendar=(CalendarView)findViewById(R.id.calendarView2);

        final EditText job=(EditText)findViewById(R.id.editText8);
        final Button btreg= (Button)findViewById(R.id.button2);
       final SqlDatabase database=new SqlDatabase(this);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                selected=String.valueOf(dayOfMonth)+"-"+String.valueOf(month+1)+"-"+String.valueOf(year);

            }
        });
        btreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                database.AddCustomer(name.getText().toString(),username.getText().toString(),pass.getText().toString(),gender.getSelectedItem().toString(),selected,job.getText().toString());
                id=database.checklogin(username.getText().toString(),pass.getText().toString());
                Customer newCustomer=new Customer(id);
                Toast.makeText(Signup.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Signup.this,Products.class);
                startActivity(i);
            }
        });

              final Button bt= (Button)findViewById(R.id.button3);
              bt.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent i=new Intent(Signup.this,ShowAllUsers.class);
                      startActivity(i);
                  }
              });

    }
}
