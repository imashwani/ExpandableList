package com.example.expandablelist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChildAdapter.ChildListener {

    ArrayList<Order> orders = null;
    RecyclerView washAndironELV, washAndFoldELV, dryCleanELV, rv4;
    Button signButton;
    TextView costTv, qtyTv;
    static int finalDeliveryCost = 0;
    static int totalItems = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        costTv = findViewById(R.id.final_delivery_cost_tv);
        qtyTv = findViewById(R.id.qty_total_item);

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


        ParentAdapter parentAdapter = new ParentAdapter(orders.get(0).getClothArrayList(), this);
//        parentAdapter.setActionListener(this);

        washAndFoldELV.setLayoutManager(new LinearLayoutManager(this));
        washAndFoldELV.setAdapter(parentAdapter);

        dryCleanELV.setLayoutManager(new LinearLayoutManager(this));
        dryCleanELV.setAdapter(parentAdapter);

        washAndironELV.setLayoutManager(new LinearLayoutManager(this));
        washAndironELV.setAdapter(parentAdapter);


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

    public static int getFinalDeliveryCost() {
        return finalDeliveryCost;
    }

    public static void setFinalDeliveryCost(int finalDeliveryCost) {
        MainActivity.finalDeliveryCost = finalDeliveryCost;
    }

    public static int getTotalItems() {
        return totalItems;
    }

    public static void setTotalItems(int totalItems) {
        MainActivity.totalItems = totalItems;
    }

    @Override
    public synchronized void costUpdate(int cost) {

        finalDeliveryCost=(getFinalDeliveryCost()+cost);

//        finalDeliveryCost += cost;
        System.out.println(
                "cost hai= " + String.valueOf(cost) + " totla=" + finalDeliveryCost
        );

        totalItems=(getTotalItems()+cost/100);

        costTv.setText("Rs. " + String.valueOf(finalDeliveryCost));
        qtyTv.setText("Qty: " + String.valueOf(totalItems));

    }
}
