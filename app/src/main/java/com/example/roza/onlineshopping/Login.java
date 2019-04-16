package com.example.roza.onlineshopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    SqlDatabase databases;
    private static final String MY_PREFS_NAME = "MY";
    private SharedPreferences pref;
    boolean remembers;
    public static Customer Customers;
    int id=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       final EditText username=(EditText)findViewById(R.id.editText);
      final   EditText password=(EditText)findViewById(R.id.editText2);
        final   EditText fname=(EditText)findViewById(R.id.editText10);
        final   EditText fbirth=(EditText)findViewById(R.id.editText11);

        Button    login=(Button)findViewById(R.id.button);
        TextView   signup=(TextView)findViewById(R.id.textView);
      final CheckBox remembercheck=(CheckBox)findViewById(R.id.checkBox);
      TextView forget=(TextView)findViewById(R.id.textView2);
      forget.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i=new Intent(Login.this,Forget.class);
              startActivity(i);

          }
      });
        pref=getSharedPreferences("MY",MODE_PRIVATE);
        remembers=pref.getBoolean("remember",false);
        if(remembers==true)
        {
            username.setText(pref.getString("username",""));
            password.setText(pref.getString("pass",""));

        }



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,Signup.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              pref = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                databases=new SqlDatabase(getApplicationContext());
                if(!(username.getText().toString().equals(""))&&!(password.getText().toString().equals("")))
                {
                         if(remembercheck.isChecked())
                         {
                             editor.putBoolean("remember",true);
                             editor.putString("username", username.getText().toString());
                             editor.putString("pass",password.getText().toString());

                             editor.apply();
                         }
                         else
                         {
                             editor.clear();
                             editor.apply();
                         }
                         id=databases.checklogin(username.getText().toString(), password.getText().toString());
                        if (id!=-1)
                            {

                                 Customers=new Customer(id);

                                Toast.makeText(Login.this,"Your ID IS "+ String.valueOf(Customers.getId()), Toast.LENGTH_SHORT).show();

                                  Intent i=new Intent(Login.this,Products.class);
                                  i.putExtra("username",username.getText().toString());
                                startActivity(i);
                             }
                    else
                        Toast.makeText(Login.this, "Sign up ?", Toast.LENGTH_SHORT).show();

                }
                else Toast.makeText(Login.this, "Enter Username and Password", Toast.LENGTH_SHORT).show();
            }
        });



    }

}
