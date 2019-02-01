package com.example.expandablelist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder>  {

    Context context;
    ChildListener childListener;
    ArrayList<String> clothNames=new ArrayList<>();
    ArrayList<Boolean> booleans=new ArrayList<>();

    public ChildAdapter(Context context, ArrayList<String> clothNames, ArrayList<Boolean> booleans) {
        this.context = context;
        this.clothNames = clothNames;
        this.booleans = booleans;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_single_item, viewGroup, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChildViewHolder childViewHolder, final int i) {
        childViewHolder.clothName.setText(clothNames.get(i) + " " +(int)( i + 1));

        childViewHolder.checkBox.setChecked(booleans.get(i));

        childViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    childListener.costUpdate(100);
                else
                    childListener.costUpdate(-100);
            }
        });

    }


    interface ChildListener {
        void costUpdate(int cost);
    }
    void addListener(ChildListener childListener){
        this.childListener=childListener;
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView clothName, clothDesc, itemCost;
        CheckBox checkBox;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            clothDesc = itemView.findViewById(R.id.el_cloth_description);
            clothName = itemView.findViewById(R.id.el_clothName);
            checkBox = itemView.findViewById(R.id.child_checkbox);
            itemCost=itemView.findViewById(R.id.child_item_cost);
        }
    }
}
