package com.example.android.myfoodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.android.myfoodapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailBinding.inflate(getLayoutInflater());

       // int q;
        setContentView(binding.getRoot());
        DBHelper helper = new DBHelper(this);
        if(getIntent().getIntExtra("type",0)==1) {
            int image = getIntent().getIntExtra("image", 0);
            int price = Integer.parseInt(getIntent().getStringExtra("price"));
            String foodname = getIntent().getStringExtra("name");
            String description = getIntent().getStringExtra("description");




            binding.detailDesciption.setText(description);
            binding.detailImage.setImageResource(image);
            binding.foodName.setText(foodname);
            binding.detailPrice.setText(String.format("%d", price));
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   int q;
                    q = Integer.parseInt(binding.quantity.getText().toString());
                    q++;

                    binding.quantity.setText(q+"");
                }
            });

            binding.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int q;
                    q = Integer.parseInt(binding.quantity.getText().toString());
                    if(q>0)
                    q--;
                    else
                        Toast.makeText(DetailActivity.this, "Quantity cannot be negative", Toast.LENGTH_SHORT).show();
                    binding.quantity.setText(q+"");
                }
            });


            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertOrder(binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            description,
                            foodname,
                            image,
                            price,
                            Integer.parseInt(binding.quantity.getText().toString()));
                    if (isInserted)
                    {
                        Toast.makeText(DetailActivity.this, "Data succes", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(DetailActivity.this,OrderActivity.class);
                        startActivity(intent);}
                    else
                        Toast.makeText(DetailActivity.this, "error", Toast.LENGTH_SHORT).show();

                }
            });



        }
        else{
            int id= getIntent().getIntExtra("id",0);
            Cursor cursor=helper.getOrderById(id);
            int image=cursor.getInt(4);
            
            binding.detailDesciption.setText(cursor.getString(5));
            binding.detailImage.setImageResource(image);
            binding.foodName.setText(cursor.getString(6));
            binding.detailPrice.setText(String.format(cursor.getString(3)));
            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            
            binding.insertButton.setText("Update Now");
            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated= helper.updateOrder(binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            binding.detailDesciption.getText().toString(),
                            binding.foodName.getText().toString(),
                            image,
                            Integer.parseInt(binding.detailPrice.getText().toString()),
                            1,
                            id);
                            if(isUpdated){
                                Toast.makeText(DetailActivity.this, "updated", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(DetailActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                    
                }
            });
        }
    }
}