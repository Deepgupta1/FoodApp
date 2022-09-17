package com.example.android.myfoodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<MainModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         list =new ArrayList<>();
        list.add(new MainModel(R.drawable.burger,"Burger","5","Veg Burger with extra cheez"));
        list.add(new MainModel(R.drawable.pizza,"Pizza","10","pizza with extra toppings"));
        list.add(new MainModel(R.drawable.momos,"Momos","10","pizza with  toppings"));
        list.add(new MainModel(R.drawable.noodle," Noodles","10","pizza with extra toppings"));
        list.add(new MainModel(R.drawable.rice,"Rice","10","pizza with extra toppings"));

        MainAdapter adapter =new MainAdapter(MainActivity.this,list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(this,OrderActivity.class));


        }
        return super.onOptionsItemSelected(item);
    }
}