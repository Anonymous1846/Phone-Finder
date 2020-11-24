package com.example.project_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText password,username,email;
    Button sign;
    TextView log;
    InternetConnectivityCheck internetConnectivityCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        username=findViewById(R.id.username);
        sign=findViewById(R.id.SignBtn);
        log=findViewById(R.id.logIntent);
        internetConnectivityCheck=new InternetConnectivityCheck(this);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,LoginActivity.class));
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(internetConnectivityCheck.checkConn()){
                    //Getting the String Values from the Edit Texts
                    String usernameText,passText,emailText;
                    usernameText=username.getText().toString();
                    passText=password.getText().toString();
                    emailText=email.getText().toString();

                    if(!emailText.equals("")&&!passText.equals("")&&!usernameText.equals("")){
                        DataBaseAssist dataBaseAssist=new DataBaseAssist(SignUp.this);
                        if(dataBaseAssist.checkCurrentUser(usernameText).moveToNext()){
                            Toast.makeText(getApplicationContext(),"Username Already Exists !",Toast.LENGTH_LONG).show();
                        }
                        else {
                            if(dataBaseAssist.addUser(usernameText,emailText,passText)){
                                Toast.makeText(SignUp.this,"Added",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(SignUp.this,LoginActivity.class));
                            }
                            else {
                                Toast.makeText(SignUp.this,"Failed !",Toast.LENGTH_LONG).show();
                            }
                        }

                    }else {
                        Toast.makeText(getApplicationContext(),"Fields Cannot Be Left Blank !",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    AlertDialog.Builder networkAlertBuilder= new AlertDialog.Builder(SignUp.this);
                    networkAlertBuilder.setTitle("No Network")
                            .setMessage("Check Network Connectivty !")
                            .setPositiveButton("Network Settings", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(getApplicationContext(),SignUp.class));
                                    finish();
                                }
                            });
                    AlertDialog alertDialog=networkAlertBuilder.create();
                    alertDialog.show();

                }
                }

        });

    }
}