package com.example.roza.onlineshopping;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public  class Customer {
    private  int id;
    private  HashMap<String,Integer> products;
    public Customer(int id)
    {
        this.id=id;
        products=new HashMap<String, Integer>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ADD(String k, Integer v) {
       if(this.products.containsKey(k))
       {
           this.products.remove(k);
           this.products.put(k,v);
       }
       else this.products.put(k, v);
    }

    public HashMap<String, Integer> getProducts() {
        return products;
    }
    public List<String> getkey(int x)
    {
        List<String> keys=new ArrayList<String>();
        for (HashMap.Entry<String,Integer> entry:products.entrySet()) {
            if(entry.getValue().equals(x))
                keys.add(entry.getKey());
            
        }

        return keys;
    }
    public int getvalue(String s)
    {
        return products.get(s);
    }
  public List<Integer> getvalues(List<String> s)
  {
      List<Integer> keys=new ArrayList<Integer>();
      for (HashMap.Entry<String,Integer> entry:products.entrySet()) {
          if(entry.getKey().equals(s))
              keys.add(entry.getValue());

      }
      return keys;
  }
}
