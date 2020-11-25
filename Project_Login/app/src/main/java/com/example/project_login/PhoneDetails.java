package com.example.project_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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
        ratingBar=findViewById(R.id.ratingBar);
        imageView=findViewById(R.id.phoneDetailsImage);
        name=findViewById(R.id.phoneDetailsPrice);
        registerForContextMenu(imageView);
        price=findViewById(R.id.phoneDetailsPrice);
        specs =findViewById(R.id.url);
        button=findViewById(R.id.buyBtn);
        if(getIntent()!=null){
            specs.setText(getIntent().getStringExtra("specs"));
           ratingBar.setRating(Float.parseFloat(getIntent().getStringExtra("rating")));
            Picasso.get().load(getIntent().getStringExtra("img")).into(imageView);
            name.setText(getIntent().getStringExtra("name"));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = getIntent().getStringExtra("link");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);

        }
    }
}