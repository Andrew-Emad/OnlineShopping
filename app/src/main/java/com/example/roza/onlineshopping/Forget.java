package com.example.roza.onlineshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Forget extends AppCompatActivity {
    String selectedDate="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
         final CalendarView calendar=(CalendarView)findViewById(R.id.calendarView);
        final EditText fname=(EditText)findViewById(R.id.editText10);
        final EditText newpass=(EditText)findViewById(R.id.editText11);
      final   CheckBox robot=(CheckBox)findViewById(R.id.checkBox2);
      final Button submit=(Button)findViewById(R.id.button4);
      final  SqlDatabase databases=new SqlDatabase(this);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                 selectedDate=String.valueOf(dayOfMonth)+"-"+String.valueOf(month+1)+"-"+String.valueOf(year);

            }
        });


      final  TextView signup=(TextView)findViewById(R.id.textView3);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Forget.this,Signup.class);
                startActivity(i);
            }
        });
        robot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(robot.isChecked())
                {

                    if (!(fname.getText().toString().equals("")) && !(selectedDate.equals("")))
                    {
                        Toast.makeText(Forget.this, selectedDate, Toast.LENGTH_SHORT).show();
                        if (databases.getphone(fname.getText().toString(), selectedDate))
                        {
                            Toast.makeText(Forget.this, selectedDate, Toast.LENGTH_SHORT).show();
                            signup.setVisibility(View.INVISIBLE);
                            calendar.setVisibility(View.INVISIBLE);
                            fname.setVisibility(View.INVISIBLE);
                            robot.setVisibility(View.INVISIBLE);
                            newpass.setVisibility(View.VISIBLE);
                            submit.setVisibility(View.VISIBLE);


                        }
                        else Toast.makeText(Forget.this, "Wrong Data", Toast.LENGTH_SHORT).show();


                    }

                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databases.updatepass(fname.getText().toString(),selectedDate,newpass.getText().toString());
                Toast.makeText(Forget.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Forget.this,Products.class);
                startActivity(i);
            }
        });


    }
}
