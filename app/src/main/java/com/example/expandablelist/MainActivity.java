package com.example.expandablelist;


import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.expandablelist.ViewModel.MainViewModel;
import com.example.expandablelist.model.Cloth;
import com.example.expandablelist.model.Order;
import com.example.expandablelist.model.SubClothItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements ChildAdapter.ChildListener, ParentAdapter.ActionListener, PayNowBottomSheetFragment.PaynowFragmentListener {

    RecyclerView washAndironELV, washAndFoldELV, dryCleanELV, rv4;
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

        signButton = findViewById(R.id.bt_sign);
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payNowBottomSheetFragment.show(getSupportFragmentManager(), "this");
//                startActivity(new Intent(MainActivity.this, SignatureActivity.class));
            }
        });
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        washAndironELV = findViewById(R.id.wash_rv);
        dryCleanELV = findViewById(R.id.dry_clean_rv);
        washAndFoldELV = findViewById(R.id.alteration_rv);

        mainViewModel.getOrderList().observe(this, new Observer<ArrayList<Order>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Order> orders) {
                long cost = 0;
                for (Order order : orders
                ) {
                    ArrayList<Cloth> cloths = order.getClothArrayList();
                    for (Cloth cloth : cloths
                    ) {
                        ArrayList<SubClothItem> subClothItems = cloth.getSubClothItemArrayList();
                        for (SubClothItem su :
                                subClothItems) {
                            if (su.isChecked())
                                cost += 100;
                        }
                    }
                }
                costTv.setText("Rs: " + String.valueOf(cost));
                qtyTv.setText("Qty: " + String.valueOf(cost / 100));
            }
        });
        parentAdapter = new ParentAdapter(mainViewModel.getOrderList().getValue().get(0), this);
        parentAdapter.setActionListener(this);

        washAndFoldELV.setLayoutManager(new LinearLayoutManager(this));
        washAndFoldELV.setHasFixedSize(true);
        washAndFoldELV.setAdapter(parentAdapter);

//        p1=new ParentAdapter(initOrder(),this);
//        p1.setActionListener(this);
//        dryCleanELV.setLayoutManager(new LinearLayoutManager(this));
//        dryCleanELV.setHasFixedSize(true);
//        dryCleanELV.setAdapter(p1);
//
//        p2=new ParentAdapter(initOrder(),this);
//        p2.setActionListener(this);
//        washAndironELV.setLayoutManager(new LinearLayoutManager(this));
//        washAndironELV.setHasFixedSize(true);
//        washAndironELV.setNestedScrollingEnabled(false);
//        washAndironELV.setAdapter(p2);

        payNowBottomSheetFragment = new PayNowBottomSheetFragment();
        payNowBottomSheetFragment.setListener(this);
    }

//    private Order initOrder() {
//        Order order=null;
//        List<Cloth> clothList = new ArrayList<>();
//
//        clothList.add(new Cloth("Shirt", "this is desc", 5, getClothList("Shirt")));
//        clothList.add(new Cloth("Trouser", "this is desc", 3, getClothList("Trouser")));
//        clothList.add(new Cloth("Drap", "this is desc", 3, getClothList("Drap")));
//        clothList.add(new Cloth("Muffler", "this is desc", 2, getClothList("Muffler")));
//        clothList.add(new Cloth("Inner", "this is desc", 5, getClothList("Inner")));
//        order=new Order((ArrayList<Cloth>) clothList, "Press and Press", clothList.size(), (float) 122.4);
//        return order;
//    }

//    public ArrayList<SubClothItem> getClothList(String clothName){
//
////        SubClothItem subClothItem = new SubClothItem(clothName, false);
//        ArrayList<SubClothItem> subClothItemArrayList = new ArrayList<>();
//        for (int i = 0; i < 5; i++) subClothItemArrayList.add(new SubClothItem(clothName,false));
//        return subClothItemArrayList;
//    }

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

//        finalDeliveryCost = (getFinalDeliveryCost() + cost);
//
////        finalDeliveryCost += cost;
//        System.out.println(
//                "cost hai= " + String.valueOf(cost) + " totla=" + finalDeliveryCost
//        );
//
//        totalItems = (getTotalItems() + cost / 100);
//
//        costTv.setText("₹ " + String.valueOf(finalDeliveryCost));
//        qtyTv.setText("Qty: " + String.valueOf(totalItems));

    }

    @Override
    public synchronized void checkListener(int noOfChildItems) {

//        totalItems = (getTotalItems() + noOfChildItems);
//        finalDeliveryCost = (getFinalDeliveryCost() + noOfChildItems*100);
//
//        costTv.setText("₹ " + String.valueOf(finalDeliveryCost));
//        qtyTv.setText("Qty: " + String.valueOf(totalItems));

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
