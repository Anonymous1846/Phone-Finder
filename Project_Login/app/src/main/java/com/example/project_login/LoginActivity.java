package com.example.project_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText pass,username;
    Button log;
    DataBaseAssist dataBaseAssist;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;
    TextView textView;
    Boolean savedLogin;
    CheckBox rememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        log=findViewById(R.id.Logbtn);
        pass=findViewById(R.id.passLog);
        username=findViewById(R.id.userLog);
        textView=findViewById(R.id.signPrompt);
        rememberMe=findViewById(R.id.rememberMe);
        sharedPreferences=getSharedPreferences("loginRef",MODE_PRIVATE);
        sharedPreferencesEditor=sharedPreferences.edit();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If The USer Clicks on
                startActivity(new Intent(LoginActivity.this,SignUp.class));
            }
        });
        //Initializing the DatabaseAssist Object !
        dataBaseAssist=new DataBaseAssist(LoginActivity.this);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        savedLogin=sharedPreferences.getBoolean("saveLogin",true);
        if(savedLogin==true){
            username.setText(sharedPreferences.getString("username",null));
            pass.setText(sharedPreferences.getString("password",null));
        }
    }

    private void login() {
        //Setting The String Values
        String user=username.getText().toString();
        String password=pass.getText().toString();
        if(!password.equals("") && !user.equals("")){
            if(dataBaseAssist.checkCurrentUser(user).moveToNext()){
                if (dataBaseAssist.authUser(user,password).moveToNext()){
                    Toast.makeText(LoginActivity.this,"Okay !",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),SelectPhone.class));
                    //Checks if the Remember Me Check Box is checked if the Remember Me
                    if(rememberMe.isChecked()){
                        sharedPreferencesEditor.putBoolean("saveLogin",true);
                        //Setting The Username.
                        sharedPreferencesEditor.putString("username",user);
                        //Setting The Password
                        sharedPreferencesEditor.putString("password",password);
                        sharedPreferencesEditor.commit();

                    }

                }
                else {
                    Toast.makeText(LoginActivity.this,"Wrong Password !",Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(),"Username Doesn't Exists !",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(getApplicationContext(),"Fields Cannot Be Left Blank !",Toast.LENGTH_LONG).show();
        }
    }
}