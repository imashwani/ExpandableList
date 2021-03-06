package com.example.expandablelist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.expandablelist.model.Cloth;
import com.example.expandablelist.model.Order;
import com.example.expandablelist.model.SubClothItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements ParentAdapter.ActionListener, PayNowBottomSheetFragment.PaynowFragmentListener {

    RecyclerView washAndironELV, washAndFoldELV, dryCleanELV;
    Order orderWashIron,orderWashFold,orderDryClean;
    Button signButton;
    TextView costTv, qtyTv, orderIdTv;
    static int finalDeliveryCost = 0;
    ParentAdapter parentAdapter, p1, p2;
    static int totalItems = 0;
    String orderId = "#QWL182105618006";

    PayNowBottomSheetFragment payNowBottomSheetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        costTv = findViewById(R.id.final_delivery_cost_tv);
        qtyTv = findViewById(R.id.qty_total_item);
        orderIdTv = findViewById(R.id.order_id_main_tv);

        orderIdTv.setText(orderId);
        payNowBottomSheetFragment = new PayNowBottomSheetFragment();
        payNowBottomSheetFragment.setListener(this);

        signButton = findViewById(R.id.bt_sign);
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payNowBottomSheetFragment.show(getSupportFragmentManager(), "this");
//                startActivity(new Intent(MainActivity.this, SignatureActivity.class));
            }
        });

        initOrder();


        washAndironELV = findViewById(R.id.wash_rv);
        dryCleanELV = findViewById(R.id.dry_clean_rv);
        washAndFoldELV = findViewById(R.id.alteration_rv);


        orderWashFold=initOrder();
        parentAdapter = new ParentAdapter(orderWashFold, this);
        parentAdapter.setActionListener(this);

        washAndFoldELV.setLayoutManager(new LinearLayoutManager(this));
        washAndFoldELV.setHasFixedSize(true);
        washAndFoldELV.setAdapter(parentAdapter);

        orderDryClean=initOrder();
        p1 = new ParentAdapter(orderDryClean, this);
        p1.setActionListener(this);
        dryCleanELV.setLayoutManager(new LinearLayoutManager(this));
        dryCleanELV.setHasFixedSize(true);
        dryCleanELV.setAdapter(p1);

        orderDryClean=initOrder();
        p2 = new ParentAdapter(orderDryClean, this);
        p2.setActionListener(this);
        washAndironELV.setLayoutManager(new LinearLayoutManager(this));
        washAndironELV.setHasFixedSize(true);
        washAndironELV.setNestedScrollingEnabled(false);
        washAndironELV.setAdapter(p2);


    }

    private Order initOrder() {
        Order order = null;
        List<Cloth> clothList = new ArrayList<>();

        clothList.add(new Cloth("Shirt", "this is desc", 5, getClothList("Shirt")));
        clothList.add(new Cloth("Trouser", "this is desc", 3, getClothList("Trouser")));
        clothList.add(new Cloth("Drap", "this is desc", 3, getClothList("Drap")));
        clothList.add(new Cloth("Muffler", "this is desc", 2, getClothList("Muffler")));
        clothList.add(new Cloth("Inner", "this is desc", 5, getClothList("Inner")));
        order = new Order((ArrayList<Cloth>) clothList, "Press and Press", clothList.size(), (float) 122.4);
        return order;
    }

    public ArrayList<SubClothItem> getClothList(String clothName) {

//        SubClothItem subClothItem = new SubClothItem(clothName, false);
        ArrayList<SubClothItem> subClothItemArrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) subClothItemArrayList.add(new SubClothItem(clothName, false));
        return subClothItemArrayList;
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
    public void checkListener(int noOfChildItems) {
        totalItems = (getTotalItems() + noOfChildItems);
        finalDeliveryCost = (getFinalDeliveryCost() + noOfChildItems * 100);
        System.out.println(
                "cost hai= " + String.valueOf(noOfChildItems * 100) + " total=" + finalDeliveryCost
        );
        costTv.setText("₹ " + String.valueOf(finalDeliveryCost));
        qtyTv.setText("Qty: " + String.valueOf(totalItems));
    }


    @Override
    public void onCashPayment() {
        nextAct();
    }

    @Override
    public void onCardPayment() {
        nextAct();
    }

    @Override
    public void onWalletPayment() {
        nextAct();
    }

    void nextAct() {
        Intent intent = new Intent(MainActivity.this, SignatureActivity.class);
        intent.putExtra("qty", qtyTv.getText().toString());
        intent.putExtra("cost", costTv.getText().toString());
        intent.putExtra("orderId", orderId);

        startActivity(intent);
    }
}
