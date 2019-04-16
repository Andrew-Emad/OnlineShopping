package com.example.roza.onlineshopping;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ShowAllUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_users);
      ListView list=(ListView)findViewById(R.id.list1);
        ArrayAdapter<String> arr=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1);
        list.setAdapter(arr);
        SqlDatabase data=new SqlDatabase(this);
        Cursor cu=data.fetch();
        while(!cu.isAfterLast()) {
            arr.add(String.valueOf(cu.getInt(0)));
            cu.moveToNext();
        }

    }
}
