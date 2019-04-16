package com.example.roza.onlineshopping;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class VoiceFragment extends Fragment {
int req=1;
    EditText voiceresult;
    ListView list;
    SqlDatabase proddatabase;
    Cursor cur;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_voice, container, false);
      voiceresult=(EditText)view.findViewById(R.id.editText6);
        ImageButton voicesearch=(ImageButton)view.findViewById(R.id.imageButton);
        list=(ListView)view.findViewById(R.id.list2);
        voicesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(i,req);
            }
        });




        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==req&& resultCode==getActivity().RESULT_OK) {

            ArrayList<String> arr = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            voiceresult.setText(arr.get(0));


            ArrayAdapter<String> results=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);
            list.setAdapter(results);
            proddatabase= new SqlDatabase(getActivity());
            cur=proddatabase.searchproducts(arr.get(0));
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
        super.onActivityResult(requestCode, resultCode, data);
    }
}
