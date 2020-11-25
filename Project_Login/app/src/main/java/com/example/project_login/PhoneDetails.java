package com.example.project_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class PhoneDetails extends AppCompatActivity {
    TextView specs,name,price;
    RatingBar ratingBar;
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_details);
        ratingBar = findViewById(R.id.ratingBar);
        imageView = findViewById(R.id.phoneDetailsImage);
        name = findViewById(R.id.phoneDetailsPrice);
        registerForContextMenu(imageView);
        price = findViewById(R.id.phoneDetailsPrice);
        specs = findViewById(R.id.url);
        button = findViewById(R.id.buyBtn);
        if (getIntent() != null) {
            specs.setText(getIntent().getStringExtra("specs"));
            ratingBar.setRating(Float.parseFloat(getIntent().getStringExtra("rating")));
            Picasso.get().load(getIntent().getStringExtra("img")).into(imageView);
            name.setText(getIntent().getStringExtra("name"));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    goToLink(getIntent().getStringExtra("link"));
                }
            });
        }
    }
    //Context Menu Declaration and The Toast message
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Phone Image Actions");
    }
    //When The Context Menu is clicked the We Will The Do The Specific Action !
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==R.id.download){
                goToLink(getIntent().getStringExtra("img"));
        }
        else if(item.getItemId()==R.id.flip){
            String gotToFlipkart=String.format("https://www.flipkart.com/search?q=%s",getIntent().getStringExtra("name").replace(" ","+"));
            goToLink(gotToFlipkart);
        }
        else if(item.getItemId()==R.id.gsm){
            String gotToGsm=String.format("https://www.gsmarena.com/res.php3?sSearch=%s",getIntent().getStringExtra("name").replace(" ","+"));
            goToLink(gotToGsm);
        }
        else{
            return false;
        }
        return true;
    }
    private void goToLink(String linkStr){
        String url = linkStr;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menubar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                Toast.makeText(getApplicationContext(),"lkdf",Toast.LENGTH_LONG).show();
                return true;
            case R.id.profile:
                Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
