package com.example.project_login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context context;
    ArrayList<Phone>phones;
    public CustomAdapter(Context context,ArrayList<Phone>phones){
        this.context=context;
        this.phones=phones;
    }
    @NonNull
    @Override

    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.row,null,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
            final Phone phone=phones.get(position);
            holder.name.setText(phone.getMake()+" "+phone.getPhoneName());
            holder.price.setText(phone.getPrice()+"");
            Picasso.get().load(phone.imageUrl).into(holder.phoneImage);
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,PhoneDetails.class);
                    intent.putExtra("link",phone.getBuy_link());
                    intent.putExtra("rating",phone.getRating());
                    intent.putExtra("img",phone.getImageUrl());
                    intent.putExtra("name",phone.getMake()+" "+phone.getPhoneName());
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layout;
        TextView name,price;
        ImageView phoneImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.linear);
            name=itemView.findViewById(R.id.nameRow);
            price=itemView.findViewById(R.id.priceRow);
            phoneImage=itemView.findViewById(R.id.imageView);
        }

    }
}
