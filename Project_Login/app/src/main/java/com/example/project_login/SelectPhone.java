package com.example.project_login;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;


public class SelectPhone extends AppCompatActivity {
    Spinner spinnerPhoneSelect;
    SeekBar priceBar;
    Button button;
    ArrayAdapter<String>arrayAdapterForPhones;
    String []phones;
    TextView textView;
    //Required Parameters
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
        //TextView To Show The Change In The Price On The Spot
        textView=findViewById(R.id.price);
        //Progress bar To setting The price range in The Order of The 1000s
        priceBar=findViewById(R.id.priceBar);
        //Setting The Progres Value Initially To 8 So That Resulting Price Will Be 1000.00 x 8,000 Rs
        priceBar.setProgress(8);

        priceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //Change in Progress Value  Will Be Reflected on the Spot !
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(Double.toString(1000.00*priceBar.getProgress()));
            }
            //Dead Methods
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        phones=new String[]{"Samsung","Oppo","Vivo","One Plus","Redmi","Show All"};
        //Initailisign The ArrayAdapter
        arrayAdapterForPhones=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,phones);
        spinnerPhoneSelect.setAdapter(arrayAdapterForPhones);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price=1000*priceBar.getProgress();
                //Toast.makeText(getApplicationContext(),"Price: "+(priceBar.getProgress()*1000)+" Name:"+spinnerPhoneSelect.getSelectedItem(),Toast.LENGTH_LONG).show();
                Intent intent=new Intent(SelectPhone.this,ListView.class);
                //Passing The Intend Values to The PhoneDetails Class so that We Can Use the Phone make and price Information to Segregate The Dat from the Database !
                intent.putExtra("make",spinnerPhoneSelect.getSelectedItem().toString());
                intent.putExtra("price",price);
                    startActivity(intent);
            }
        });
    }
}