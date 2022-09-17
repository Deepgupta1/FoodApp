package com.example.android.myfoodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
     public static final String DBNAME="mydatabase.db";
     final static int DBVERSION =1;


    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table orders(id integer primary key autoincrement,name text,phone text,price integer,image integer,description text,foodname text,quantity integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists orders");
        onCreate(db);

    }
    public boolean insertOrder(String name,String phone,String description,String foodname,int image,int price,int quantity){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("name",name);
        c.put("phone",phone);
        c.put("description",description);
        c.put("foodname",foodname);
        c.put("image",image);
        c.put("price",price);
        c.put("quantity",quantity);
        long r=db.insert("orders",null,c);
        if(r==-1)
            return false;
        else
            return true;
    }
    public ArrayList<orderModel> getOrders(){
        ArrayList<orderModel> orders=new ArrayList<>();
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select id,foodname,image,price from orders ",null);
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                orderModel model=new orderModel();
                model.setOrderName(cursor.getInt(0)+"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3)+"");
                orders.add(model);

            }
        }
        cursor.close();
        db.close();
        return orders;
    }
    public Cursor getOrderById(int id){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from orders where id= "+id,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }

        return cursor;

    }
    public boolean updateOrder(String name,String phone,String description,String foodname,int image,int price,int quantity,int id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("name",name);
        c.put("phone",phone);
        c.put("description",description);
        c.put("foodname",foodname);
        c.put("image",image);
        c.put("price",price);
        c.put("quantity",quantity);
        c.put("id",id);
        long r=db.update("orders",c,"id="+id,null);
        if(r==-1)
            return false;
        else
            return true;
    }
    public int deleteOrder(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("orders","id="+id,null);
    }

}
