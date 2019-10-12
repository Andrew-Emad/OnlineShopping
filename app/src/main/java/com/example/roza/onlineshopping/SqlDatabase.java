package com.example.roza.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.view.Menu;
import android.view.MenuInflater;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class SqlDatabase extends SQLiteOpenHelper {
    private static String database="shopdatabase";
    SQLiteDatabase shopdatabase;
    String x;



    public SqlDatabase (Context context)
    {
        super(context,database,null,9);
        x="Trousers gded";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists Customers(custid integer primary key autoincrement ,custname text not null,username text not null," +
                "password text not null,gender text not null,birthdate text not null,job text)");
        db.execSQL("create table if not exists Categories(catid integer primary key autoincrement ,catname text not null)");
        db.execSQL("create table if not exists Orders (ordid integer primary key autoincrement ,orddate text not null,address text not null,custid integer,foreign key (custid) references Customers(custid))");
        db.execSQL("create table if not exists Products (proid integer primary key autoincrement ,proname text not null,price real,quantity integer,catid integer,foreign key (catid) references Categories (catid))");
        db.execSQL("create table if not exists OrderDeatails (ordid integer not null,proid integer not null,quantity integer,primary key (ordid,proid),foreign key (ordid) references Orders(ordid),foreign key(proid) references Products(proid) )");

        ContentValues value=new ContentValues();
        value.put("catname","Mobile");
        db.insert("Categories",null,value);


        value.put("catname","Laptops");
        db.insert("Categories",null,value);


        value.put("catname","Food");


        db.insert("Categories",null,value);

        value.put("catname","Clothes");
        db.insert("Categories",null,value);

        value.put("catname","Underwear");
        db.insert("Categories",null,value);

        //mobiles
        db.execSQL("insert into Products (proname,price,quantity,catid) values('IphoneX',15000,10,1)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('Iphone6',8000,8,1)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('Iphone5s',6000,9,1)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('Huawei M9',4000,10,1)");
        //laptops
        db.execSQL("insert into Products (proname,price,quantity,catid) values('Lenovo 310',15000,10,2)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('MacPro',40000,5,2)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('Dell 1550',11000,12,2)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('HP Z10',13800,8,2)");
        //Food
        db.execSQL("insert into Products (proname,price,quantity,catid) values('Domty',3.75,20,3)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('Bread ',6.5,32,3)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('Milk',20.25,9,3)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('Meat',75,50,3)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('Chicken',35.5,20,3)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('MacPro',40000,30,3)");

        //clothes
        db.execSQL("insert into Products (proname,price,quantity,catid) values('Trousers Blue',180,5,4)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('Trousers Black',180,12,4)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('T-Shirt Zara',220,25,4)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('Jacket Blue',1800,15,4)");
        db.execSQL("insert into Products (proname,price,quantity,catid) values('Shoes Black',580,9,4)");

        db.execSQL("insert into Products (proname,price,quantity,catid) values('"+x+"',120,2,2)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Customers");
        db.execSQL("drop table if exists Categories");
        db.execSQL("drop table if exists Orders");
        db.execSQL("drop table if exists Products");
        db.execSQL("drop table if exists OrderDetails");
        onCreate(db);

    }
    public void AddCustomer(String name,String username,String pass,String gen,String birth,String job)
    {
        ContentValues value=new ContentValues();
        value.put("custname",name);
        value.put("username",username);
        value.put("password",pass);
        value.put("gender",gen);
        value.put("birthdate",birth);
        value.put("job",job);
        shopdatabase=getWritableDatabase();
        shopdatabase.insert("Customers",null,value);
        shopdatabase.close();

    }
    public int checklogin(String user,String pass)
    {
        shopdatabase=getReadableDatabase();
        Cursor cur= shopdatabase.rawQuery("Select custid from Customers where username =? and password =?",new String[]{user,pass});

        int curcount=cur.getCount();

        if(cur.moveToNext())
            return cur.getInt(0);

        return -1;

    }

    public Cursor getmenu()
    {
        shopdatabase=getReadableDatabase();
        Cursor cur=shopdatabase.rawQuery("select catname from Categories",null);
        if(cur!=null)
            cur.moveToFirst();
        shopdatabase.close();
        // return category list
        return cur;
    }
    public Cursor fetch()
    {

        shopdatabase=getReadableDatabase();
        Cursor cur=shopdatabase.rawQuery("select quantity from OrderDeatails ",null);
        if(cur!=null)
            cur.moveToFirst();
        shopdatabase.close();
        return cur;

    }
    public Cursor searchproducts(String s)
    {

        shopdatabase=getReadableDatabase();
        String [] arr=new String[]{'%'+s+'%'};
        Cursor cur=shopdatabase.rawQuery("select proname,quantity from Products where proname like ?",arr);
        if(cur!=null)
            cur.moveToFirst();
        shopdatabase.close();
        return cur;
    }
    public Cursor fetchproducts(int categid)
    {
        shopdatabase=getReadableDatabase();
        Cursor cur=shopdatabase.rawQuery("select proname,price,quantity from Products where catid=? ",new String[]{String.valueOf(categid)});
        if(cur!=null)
            cur.moveToFirst();
        shopdatabase.close();
        return cur;
    }
    public boolean getphone(String fulname,String birthdate)

    {
        shopdatabase=getReadableDatabase();
        Cursor cur=shopdatabase.rawQuery("select custid from Customers where custname=? and birthdate=? ",new String[]{fulname,birthdate});

        int curcount=cur.getCount();
        if(curcount>0)
            return true;
        shopdatabase.close();
        return false;

    }
    public Cursor getinfo(String prodname)
    {
        shopdatabase=getReadableDatabase();
        Cursor cur=shopdatabase.rawQuery("select price,quantity,proid from Products where proname=? ",new String[]{prodname});
        if(cur!=null)
            cur.moveToFirst();
        shopdatabase.close();
        return cur;

    }
    public void updatepass(String name ,String birth,String newpass)
    {
        shopdatabase=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("password",newpass);
        shopdatabase.update("Customers",values,"custname=? and birthdate=?",new String[]{name,birth});
        shopdatabase.close();
    }
    public int insertorder(String dt,Integer custoid,String addres)
    {

        ContentValues row=new ContentValues();
        row.put("orddate",dt);
        row.put("address",addres);
        row.put("custid",custoid);
        shopdatabase=getWritableDatabase();
        int r= (int) shopdatabase.insert("Orders",null,row);
        shopdatabase.close();
        if(r<0)
            return -1;

        return r;

    }

    public int orderdetails(Integer ordeid,Integer proid,Integer quan)
    {
        shopdatabase=getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put("ordid",ordeid);
        value.put("proid",proid);
        value.put("quantity",quan);
        int r= (int) shopdatabase.insert("OrderDeatails",null,value);
        shopdatabase.close();
        if(r<0)
            return -1;

        return r;
    }
    public void updatequantity(String produname,Integer quanta)
    {
        shopdatabase=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("quantity",quanta);
        shopdatabase.update("Products",values,"proname like ?",new String[]{produname});
        shopdatabase.close();


    }
    public void deleteproduct(String name)
    {
        shopdatabase=getWritableDatabase();
        shopdatabase.delete("Products","proname'"+name+"'",null);
        shopdatabase.close();
    }

    }






