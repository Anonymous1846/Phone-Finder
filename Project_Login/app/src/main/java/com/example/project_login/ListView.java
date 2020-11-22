package com.example.project_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ListView extends AppCompatActivity {
        RecyclerView recyclerView;
        ArrayList<Phone>phones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        recyclerView=findViewById(R.id.recycle);
        phones=new ArrayList<>();
        phones.add(new Phone("Oppo F11 Pro","dsf","erw","23455.0"));
        CustomAdapter customAdapter=new CustomAdapter(this,phones);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public class Contend extends 
}