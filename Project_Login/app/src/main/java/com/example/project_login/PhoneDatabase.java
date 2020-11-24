  package com.example.project_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.zip.CheckedOutputStream;

public class PhoneDatabase extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="phones.db";
    private static final int version=1;
    private static final String TABLE="phone_info";
    private static final String PHONE_MAKE="make";
    private static final String PHONE_NAME="name";
    private static final String PHONE_PRICE="price";
    private static final String BUY_URL="buy";
    private static final String PHONE_IMAGE="image";
    private static final String RATING="rating";
    private static final String RAM="ram";
    private static final String ROM="rom";
    public PhoneDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+TABLE+" ("+
                PHONE_MAKE+" TEXT, "+
                PHONE_NAME+" TEXT, "+
                PHONE_IMAGE+" TEXT, "+
                PHONE_PRICE+" DOUBLE, "+
                BUY_URL+" TEXT, "+
                RATING+" TEXT, "+
                RAM+" TEXT, "+
                ROM+" TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE);
            onCreate(db);
    }
    public void addPhoneInfo(String make,String name,String image,Double price,String buy_url_link,String rating,String ram,String rom){
            SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(PHONE_MAKE,make);
        contentValues.put(PHONE_NAME,name);
        contentValues.put(PHONE_IMAGE,image);
        contentValues.put(PHONE_PRICE,price);
        contentValues.put(BUY_URL,buy_url_link);
        contentValues.put(RATING,rating);
        contentValues.put(RAM,ram);
        contentValues.put(ROM,rom);
        long result=sqLiteDatabase.insert(TABLE,null,contentValues);
        if(result!=-1){
            Log.d("Success DB","Adding Phones To Db Success !");
        }
        else {
            Log.d("Failed DB","Adding Phones To Db Failed !");
        }
    }
    Cursor selectAllPhones(){
        String sql="SELECT * FROM "+TABLE;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=null;
        if(sqLiteDatabase!=null){
            cursor=sqLiteDatabase.rawQuery(sql,null);
        }
        return  cursor;
    }
    Cursor selectAllPhonesByNamePrice(String make,double price){
        String sql="SELECT * FROM "+TABLE+" WHERE "+PHONE_MAKE+" ='"+make+"' AND "+PHONE_PRICE+" <"+price+";";
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=null;
        if(sqLiteDatabase!=null){
            cursor=sqLiteDatabase.rawQuery(sql,null);
        }
        return  cursor;
    }
}
