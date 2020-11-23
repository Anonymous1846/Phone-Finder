package com.example.project_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SelectPhone extends AppCompatActivity {
    Spinner spinnerPhoneSelect;
    SeekBar priceBar;
    Button button;
    ArrayAdapter<String>arrayAdapterForPhones;
    String []phones;
    TextView textView;
    //Reuqired Paramters
    String phoneName;
    double price;
    //Optional Parameters

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_phone);
        //Setting the Spinner to Display The Phone Names
        spinnerPhoneSelect=findViewById(R.id.phoneSpinner);
        //button to test and search !
        button=findViewById(R.id.button);
        textView=findViewById(R.id.price);
        //Progress bar To setting The price range in The Order of The 1000s
        priceBar=findViewById(R.id.priceBar);
        priceBar.setProgress(8);
        priceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(priceBar.getProgress()*1000+"");
            }
            //Dead Methods
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        phones=new String[]{"Samsung","Oppo","Vivo","Asus","Xiaomi"};
        //Initailisign The ArrayAdapter
        arrayAdapterForPhones=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,phones);
        spinnerPhoneSelect.setAdapter(arrayAdapterForPhones);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Price: "+(priceBar.getProgress()*1000)+" Name:"+spinnerPhoneSelect.getSelectedItem(),Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),ListView.class));
            }
        });
    }
}