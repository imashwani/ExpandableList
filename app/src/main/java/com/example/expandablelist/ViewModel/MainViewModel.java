package com.example.expandablelist.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.expandablelist.model.Cloth;
import com.example.expandablelist.model.Order;
import com.example.expandablelist.model.SubClothItem;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Order>> orderListMutableLiveData;

    public LiveData<ArrayList<Order>> getOrderList() {
        if (orderListMutableLiveData == null) {
            orderListMutableLiveData = new MutableLiveData<>();
            ArrayList<Order> orderList = new ArrayList<>();
            orderList.add(loadOrders());
            orderListMutableLiveData.setValue(orderList);

        }
        return orderListMutableLiveData;
    }

    private Order loadOrders() {

        return initOrder();
    }

    private Order initOrder() {
        Order order = null;
        ArrayList<Cloth> clothList = new ArrayList<>();

        clothList.add(new Cloth("Shirt", "this is desc", 5, getClothList("Shirt")));
        clothList.add(new Cloth("Trouser", "this is desc", 3, getClothList("Trouser")));
        clothList.add(new Cloth("Drap", "this is desc", 3, getClothList("Drap")));
        clothList.add(new Cloth("Muffler", "this is desc", 2, getClothList("Muffler")));
        clothList.add(new Cloth("Inner", "this is desc", 5, getClothList("Inner")));
        order = new Order((ArrayList<Cloth>) clothList, "Press and Press", clothList.size(), (float) 122.4);
        return order;
    }

    public ArrayList<SubClothItem> getClothList(String clothName) {
        ArrayList<SubClothItem> subClothItemArrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) subClothItemArrayList.add(new SubClothItem(clothName, false));
        return subClothItemArrayList;
    }
}
