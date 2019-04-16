package com.example.roza.onlineshopping;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


public class TextFragment extends Fragment {
    SqlDatabase  proddatabase;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_text, container, false);
       final EditText textsearch=(EditText)view.findViewById(R.id.editText9);
        ImageButton search=(ImageButton)view.findViewById(R.id.imageButton2);
        ListView list=(ListView)view.findViewById(R.id.list3);
       final ArrayAdapter<String> results=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);
        list.setAdapter(results);
        proddatabase= new SqlDatabase(getActivity());


      search.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              results.clear();
              Cursor cur=proddatabase.searchproducts(textsearch.getText().toString());
              if(cur!=null)
              {
                  while (!cur.isAfterLast())
                  {
                      results.add(cur.getString(0));
                      cur.moveToNext();
                  }

              }
              else Toast.makeText(getActivity(), "No Products Found", Toast.LENGTH_SHORT).show();

          }
      });




        return view;
    }






}
