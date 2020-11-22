package com.example.project_login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
            Phone phone=phones.get(position);
            holder.name.setText(phone.getPhoneName());
            holder.price.setText(phone.getPrice());
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
