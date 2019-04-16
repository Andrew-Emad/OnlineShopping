package com.example.roza.onlineshopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;


public class MobileFragment extends Fragment {
    SqlDatabase data;
    int quantity=0;
    float price=0;
    ArrayAdapter<String> arr;
private SharedPreferences pref;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref=getActivity().getSharedPreferences("Cart",MODE_PRIVATE);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater=new MenuInflater(getActivity());
        inflater.inflate(R.menu.list,menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mobile, container, false);

        arr=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);
        ListView list=(ListView)view.findViewById(R.id.listmobile);
        list.setAdapter(arr);
         data=new SqlDatabase(getActivity());
        registerForContextMenu(list);

        Cursor cur=data.fetchproducts(1);
        if(cur!=null)
        {
            while (!cur.isAfterLast()) {
                arr.add(cur.getString(0));
                cur.moveToNext();

            }
        }
        else
            Toast.makeText(getActivity(), "No Items", Toast.LENGTH_SHORT).show();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected=((TextView)view).getText().toString();
                Intent i=new Intent(getActivity(),Preview.class);
                i.putExtra("product",selected);
                startActivity(i);
            }
        });

        return view;
    }




    }



