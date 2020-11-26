package com.example.project_login;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    CheckBox three,four,six,eight,twel;
    RadioGroup radioGroup;
    RadioButton getSelectedRAM;
    double price;
    String selectRamMessageFlag="";
    //Optional Parameters

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_phone);
        radioGroup=findViewById(R.id.radioGroup);

        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        six=findViewById(R.id.six);
        eight=findViewById(R.id.eight);
        twel=findViewById(R.id.twel);
        //Setting the Spinner to Display The Phone Names
        spinnerPhoneSelect=findViewById(R.id.phoneSpinner);
        //button to test and search !
        button=findViewById(R.id.searchbutton);
        //TextView To Show The Change In The Price On The Spot
        textView=findViewById(R.id.price);
        //Progress bar To setting The price range in The Order of The 1000s
        priceBar=findViewById(R.id.priceBar);
        //Setting The Progres Value Initially To 8 So That Resulting Price Will Be 1000.00 x 8,000 Rs
        priceBar.setProgress(8);
        textView.setText((priceBar.getProgress()*1000.00)+"");
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

                if(three.isChecked()){
                    selectRamMessageFlag+="1";
                }
                if (four.isChecked()){
                    selectRamMessageFlag+="2";
                }
                if(six.isChecked()){
                    selectRamMessageFlag+="3";
                }
                if (eight.isChecked()){
                    selectRamMessageFlag+="4";
                }
                if (twel.isChecked()){
                    selectRamMessageFlag+="5";
                }
                price=1000*priceBar.getProgress();
                getSelectedRAM=findViewById(radioGroup.getCheckedRadioButtonId());
                Toast.makeText(getApplicationContext(),getSelectedRAM.getText().toString(), Toast.LENGTH_LONG).show();
                selectRamMessageFlag="";

                Intent intent=new Intent(SelectPhone.this,ListView.class);
                //Passing The Intend Values to The PhoneDetails Class so that We Can Use the Phone make and price Information to Segregate The Dat from the Database !
                intent.putExtra("make",spinnerPhoneSelect.getSelectedItem().toString());
                intent.putExtra("price",price);
                intent.putExtra("ram",getSelectedRAM.getText().toString());
                    startActivity(intent);
            }
        });
    }
}