package com.example.expandablelist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.expandablelist.model.Cloth;
import com.example.expandablelist.model.Order;
import com.example.expandablelist.model.SubClothItem;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> implements ChildAdapter.ChildCheckListener , ChildAdapter.ChildListener {

    Order order;
    Context context;
    ActionListener actionListener;

    public ParentAdapter(Order order, Context context) {
        this.order = order;
        this.context = context;
    }

    void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_recycler_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Cloth cloth = order.getClothArrayList().get(i);

        viewHolder.noOfClothTv.setText(String.valueOf(cloth.getNoOfCloth()) + "Items ");
        viewHolder.clothNameTv.setText(cloth.getName());
        viewHolder.clothDescTv.setText(cloth.getDescription());

        if (!cloth.isChecked())
            viewHolder.childRecyclerView.setVisibility(View.GONE);


        viewHolder.childRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        final ChildAdapter childAdapter = new ChildAdapter(context, order.getClothArrayList().get(i).getSubClothItemArrayList(), i);
        //adding listener b/w activity and child rv
        childAdapter.addListener(this);
        childAdapter.addChildCheckListener(this);

        viewHolder.childRecyclerView.setAdapter(childAdapter);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.childRecyclerView.getVisibility() != View.VISIBLE) {
                    viewHolder.childRecyclerView.setVisibility(View.VISIBLE);
                } else viewHolder.childRecyclerView.setVisibility(View.GONE);


            }
        });

        viewHolder.parentCheckbox.setChecked(order.getClothArrayList().get(i).isChecked());

        viewHolder.parentCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = 0;
                boolean isChecked = viewHolder.parentCheckbox.isChecked();

                order.getClothArrayList().get(i).setChecked(isChecked);

                for (int i = 0; i < order.getClothArrayList().size(); i++) {
                    View view = viewHolder.childRecyclerView.getChildAt(i);
                    if (view instanceof ConstraintLayout) {
                        CheckBox checkBox = (CheckBox) ((ConstraintLayout) view).getViewById(R.id.child_checkbox);
                        if (checkBox.isChecked()) {
                            check++;
                        }
                    }
                }

                ArrayList<SubClothItem> subClothItems = order.getClothArrayList().get(i).getSubClothItemArrayList();
                for (int t = 0; t < subClothItems.size(); t++)
                    subClothItems.get(t).setChecked(isChecked);

                order.getClothArrayList().get(i).setSubClothItemArrayList(subClothItems);
                childAdapter.notifyDataSetChanged();

                int change = subClothItems.size() - check;
                if (isChecked) {
                    actionListener.checkListener(change);
                } else {
                    actionListener.checkListener(check * -1);
                }
                System.out.println("notifying items changed bro " + isChecked);
            }
        });

    }

    @Override
    public int getItemCount() {
        return order.getClothArrayList().size();
    }


    @Override
    public void singleChildItemChecked(int parentItemIndex, ArrayList<SubClothItem> subClothItemArrayList) {
        //todo: make the item indexed at index position checkbox checked
        order.getClothArrayList().get(parentItemIndex).setSubClothItemArrayList(subClothItemArrayList);
        Log.d(TAG, "singleChildItemChecked: "+subClothItemArrayList.toString());
        if (!order.getClothArrayList().get(parentItemIndex).isChecked()) {
            order.getClothArrayList().get(parentItemIndex).setChecked(true);

            notifyItemChanged(parentItemIndex);
        }
    }

    @Override
    public synchronized void allItemUnselected(int index) {
        if (order.getClothArrayList().get(index).isChecked()) {
            order.getClothArrayList().get(index).setChecked(false);
            notifyItemChanged(index);
        }
    }

    @Override
    public synchronized void costUpdate(int cost) {
        actionListener.checkListener(cost/100);
    }
//synchronized  void updateItem(int choice,)

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView clothNameTv, clothDescTv, noOfClothTv;
        RecyclerView childRecyclerView;
        CardView cardView;
        CheckBox parentCheckbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            clothNameTv = itemView.findViewById(R.id.parent_ClothName);
            clothDescTv = itemView.findViewById(R.id.parent_itemDescription);
            noOfClothTv = itemView.findViewById(R.id.parent_itemCount);
            cardView = itemView.findViewById(R.id.card_parent_rv);
            childRecyclerView = itemView.findViewById(R.id.child_rv);
            parentCheckbox = itemView.findViewById(R.id.parent_checkBox);
        }
    }

    public interface ActionListener {
        void checkListener(int noOfChildItems);
    }
}
