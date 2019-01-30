package com.example.expandablelist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> {

    ArrayList<Cloth> clothArrayList;
    Context context;
    ActionListener actionListener;

    public ParentAdapter(ArrayList<Cloth> clothArrayList, Context context) {
        this.clothArrayList = clothArrayList;
        this.context = context;
    }

    void setActionListener(ActionListener actionListner) {
        this.actionListener = actionListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_recycler_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        Cloth cloth = clothArrayList.get(i);
        viewHolder.noOfClothTv.setText(String.valueOf(cloth.getNoOfCloth()) + "Items |");
        viewHolder.clothNameTv.setText(cloth.getName());
        viewHolder.clothDescTv.setText(cloth.getDescription());

        viewHolder.childRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        ChildAdapter childAdapter = new ChildAdapter(context, cloth.getName());
        viewHolder.childRecyclerView.setAdapter(childAdapter);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.childRecyclerView.getVisibility() !=View.VISIBLE){
                    viewHolder.childRecyclerView.setVisibility(View.VISIBLE);
                }
                else viewHolder.childRecyclerView.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return clothArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView clothNameTv, clothDescTv, noOfClothTv;
        RecyclerView childRecyclerView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            clothNameTv=itemView.findViewById(R.id.parent_ClothName);
            clothDescTv=itemView.findViewById(R.id.parent_itemDescription);
            noOfClothTv=itemView.findViewById(R.id.parent_itemCount);

            cardView=itemView.findViewById(R.id.card_parent_rv);
            childRecyclerView = itemView.findViewById(R.id.child_rv);

        }
    }

    public interface ActionListener {
        void itemListener(int i, int height);

    }
}
