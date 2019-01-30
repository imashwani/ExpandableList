package com.example.expandablelist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Order> orders = null;
    RecyclerView washAndironELV, washAndFoldELV, dryCleanELV,rv4;
    Button signButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signButton = findViewById(R.id.bt_sign);
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignatureActivity.class));
            }
        });
        initOrder();


        washAndironELV = findViewById(R.id.wash_rv);
        dryCleanELV = findViewById(R.id.dry_clean_rv);
        washAndFoldELV = findViewById(R.id.alteration_rv);
        rv4=findViewById(R.id.alteration_rv4);


        ParentAdapter parentAdapter = new ParentAdapter(orders.get(0).getClothArrayList(), this);
//        parentAdapter.setActionListener(this);

        washAndFoldELV.setLayoutManager(new LinearLayoutManager(this));
        washAndFoldELV.setAdapter(parentAdapter);

        dryCleanELV.setLayoutManager(new LinearLayoutManager(this));
        dryCleanELV.setAdapter(parentAdapter);

        washAndironELV.setLayoutManager(new LinearLayoutManager(this));
        washAndironELV.setAdapter(parentAdapter);

        rv4.setLayoutManager(new LinearLayoutManager(this));
        rv4.setAdapter(parentAdapter);

    }

    private void initOrder() {
        orders = new ArrayList<>();
        List<Cloth> clothList = new ArrayList<>();
        clothList.add(new Cloth("Shirt", "this is desc", 5));
        clothList.add(new Cloth("Trouser", "this is desc", 3));
        clothList.add(new Cloth("Drap", "this is desc", 3));
        clothList.add(new Cloth("Muffler", "this is desc", 2));
        clothList.add(new Cloth("Shirt", "this is desc", 5));
        orders.add(new Order((ArrayList<Cloth>) clothList, "Press and Press", clothList.size(), (float) 122.4));

        orders.add(new Order((ArrayList<Cloth>) clothList, "Wash and Fold", clothList.size(), (float) 100.48));
    }


}
