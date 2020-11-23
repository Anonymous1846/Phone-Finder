 package com.example.project_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ListView extends AppCompatActivity {
        RecyclerView recyclerView;
        ArrayList<Phone>phones;
        ProgressBar progressBar;
    CustomAdapter customAdapter;
    PhoneDatabase phoneDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        recyclerView = findViewById(R.id.recycle);
        progressBar = findViewById(R.id.progressBar);
        phones = new ArrayList<>();


        phoneDatabase = new PhoneDatabase(ListView.this);
        phoneDatabase.addPhoneInfo("Samsung","Galaxy S20","https://www.gizmochina.com/wp-content/uploads/2020/02/Samsung-Galaxy-S20-Plus-500x500.jpg",23455.99,"Hello World !!","4.5","4GB","256GB");

        addToList();
        Log.d("SomeBuy",phones.get(0).getBuy_link());
        Toast.makeText(getApplicationContext(),"Size"+phoneDatabase.selectAllPhones().getCount()+""+phones.size(),Toast.LENGTH_LONG).show();
        customAdapter = new CustomAdapter(this, phones);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
    private void addToList(){
        Cursor cursor=phoneDatabase.selectAllPhones();
        while (cursor.moveToNext()){
            phones.add(new Phone(cursor.getString(0)+"",cursor.getString(1)+"",cursor.getString(2)+"",233.99,cursor.getString(4)+"",cursor.getString(5)+"",cursor.getString(6)+"",cursor.getString(7)+""));
            Log.d("buy",cursor.getString(4));
        }
    }
  }