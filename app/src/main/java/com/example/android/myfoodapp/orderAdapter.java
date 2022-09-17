package com.example.android.myfoodapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.viewHolder> {
    ArrayList<orderModel> list;
    Context context;
    public orderAdapter(Context context,ArrayList<orderModel> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ordersample,parent,false);
        viewHolder viewHolder=new viewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final orderModel orderModel=list.get(position);
        holder.orderNumber.setText(orderModel.getOrderNumber());
        holder.orderPrice.setText(orderModel.getPrice());
        holder.orderImage.setImageResource(orderModel.getOrderImage());
        holder.solItemName.setText(orderModel.getSoldItemName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("id",Integer.parseInt( orderModel.getOrderNumber()));
                intent.putExtra("type", 2);
                context.startActivity(intent);
            }
        });
        
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DBHelper helper=new DBHelper(context);
                AlertDialog dialog=new AlertDialog.Builder(context).
                        setTitle("Delete Item")
                        .setIcon(R.drawable.ic_baseline_warning_24)
                        .setMessage("Are you sure You want to delete this order")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(helper.deleteOrder(orderModel.orderNumber)>0){
                                    Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
                return false;
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView orderImage;
        TextView solItemName,orderPrice,orderNumber;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            orderImage=itemView.findViewById(R.id.orderimage);
            solItemName=itemView.findViewById(R.id.orderName);
            orderPrice=itemView.findViewById(R.id.orderPrice);
            orderNumber=itemView.findViewById(R.id.orderNumber);

        }
    }
}
