package com.example.roza.onlineshopping;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class customarray extends BaseAdapter implements ListAdapter {
    private final ArrayList mData;



    public customarray(HashMap<String, Integer> map) {
        mData = new ArrayList();
        mData.addAll(map.entrySet());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public HashMap.Entry<String, Integer> getItem(int position) {
        return (HashMap.Entry) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO implement you own logic with ID
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlist, parent, false);
        } else {
            result = convertView;
        }

        final HashMap.Entry<String, Integer> item = getItem(position);

        // TODO replace findViewById by ViewHolder
        final TextView name=(TextView)result.findViewById(R.id.text1);
        name.setText(item.getKey());
      //  ((TextView) result.findViewById(R.id.text1)).setText(item.getKey());
       final TextView quanti=(TextView)result.findViewById(R.id.text2);
        quanti.setText(String.valueOf(item.getValue()));
        //((TextView) result.findViewById(R.id.text2)).setText(String.valueOf(item.getValue()));

        Button deleteBtn = (Button)result.findViewById(R.id.delete);
        Button minus = (Button) result.findViewById(R.id.minus);
        Button add=(Button)result.findViewById(R.id.add);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                mData.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               int quan=(item.getValue()) ;
               quan--;
               if (quan==0)
                   mData.remove(position);


                else
                    item.setValue(quan);


                notifyDataSetChanged();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quan=(item.getValue());
                SqlDatabase database=new SqlDatabase(result.getContext());
                Cursor cur=database.searchproducts(item.getKey());
                int max=Integer.valueOf(cur.getInt(1));
                quan++;
                if(quan<=max) {
                    item.setValue(quan);
                    notifyDataSetChanged();
                }
                else Toast.makeText(result.getContext(), "Max Quantity", Toast.LENGTH_SHORT).show();
            }
        });

        return result;
    }
}
