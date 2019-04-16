package com.example.roza.onlineshopping;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ClothesFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_clothes, container, false);
        ArrayAdapter<String> arr=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);
        ListView list=(ListView)view.findViewById(R.id.listclothes);
        list.setAdapter(arr);
        SqlDatabase data=new SqlDatabase(getActivity());
        Cursor cur=data.fetchproducts(4);
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
