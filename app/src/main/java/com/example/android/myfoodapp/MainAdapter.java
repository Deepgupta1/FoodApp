package com.example.android.myfoodapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {

    Context context;
    ArrayList<MainModel> list;
    public MainAdapter(Context context,ArrayList<MainModel> list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.samplecard,parent,false);
        viewHolder viewHolde=new viewHolder(view);
        return viewHolde;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final MainModel model =list.get(position);
        holder.image.setImageResource(model.getImage());
        holder.description.setText(model.getDescription());
        holder.price.setText(model.getPrice());
        holder.name.setText(model.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(context,
                        DetailActivity.class);

                intent.putExtra("image",model.getImage());
                intent.putExtra("price",model.getPrice());
                intent.putExtra("description",model.getDescription());
                intent.putExtra("name",model.getName());
                intent.putExtra("type",1);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name,description,price;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.name);
            description=itemView.findViewById(R.id.description);
            price=itemView.findViewById(R.id.orderPrice);
        }
    }
}
