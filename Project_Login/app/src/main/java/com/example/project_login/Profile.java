package com.example.project_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    EditText username,email,password;
    Button delBtn,upBtn;
    String name,emailStr,pass;
    DataBaseAssist dataBaseAssist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBaseAssist=new DataBaseAssist(Profile.this);
        setContentView(R.layout.activity_profile);
        delBtn=findViewById(R.id.delBtn);
        upBtn=findViewById(R.id.upBtn);
        username=findViewById(R.id.usernameEdit);
        email=findViewById(R.id.emailEdit);
        password=findViewById(R.id.pass);

        if(getIntent()!=null){
            name=getIntent().getStringExtra("username");
            emailStr=getIntent().getStringExtra("email");
            pass=getIntent().getStringExtra("pass");
            username.setText(name);
            email.setText(emailStr);
            password.setText(pass);
        }
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dataBaseAssist.deleteUser(name)){
                    Toast.makeText(getApplicationContext(),"User Deleted ! Please Log In",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Profile.this,LoginActivity.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Something Went Wrong !",Toast.LENGTH_SHORT).show();
                }
            }
        });
        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dataBaseAssist.upDateUser(name,emailStr,pass)){
                    Toast.makeText(getApplicationContext(),"User Credentials Updated !",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Profile.this,LoginActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(),"Updation Failed !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}