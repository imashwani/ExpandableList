package com.example.expandablelist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.expandablelist.model.SubClothItem;

import java.util.ArrayList;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {

    ChildCheckListener childCheckListener;
    int parentItemIndex;
    Context context;
    ChildListener childListener;
    ArrayList<SubClothItem> subClothItemArrayList;

    public ChildAdapter(Context context, ArrayList<SubClothItem> subClothItemArrayList, int parentItemIndex) {
        this.parentItemIndex = parentItemIndex;
        this.context = context;
        this.subClothItemArrayList = subClothItemArrayList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_single_item, viewGroup, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChildViewHolder childViewHolder, final int i) {
        childViewHolder.clothName.setText(subClothItemArrayList.get(i).getSubClothName() + " " + (int) (i + 1));

        childViewHolder.checkBox.setChecked(subClothItemArrayList.get(i).isChecked());

        childViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childViewHolder.checkBox.isChecked()){
                    subClothItemArrayList.get(i).setChecked(true);
                    childListener.costUpdate(100);
                }
                else{
                    subClothItemArrayList.get(i).setChecked(false);
                    childListener.costUpdate(-100);
                }

                int c=0;
                for (SubClothItem s:subClothItemArrayList ) {
                    if(s.isChecked()){
                        c++;
                        childCheckListener.singleChildItemChecked(parentItemIndex,subClothItemArrayList);
                        break;
                    }
                }
                if(c==0){
                    childCheckListener.allItemUnselected(parentItemIndex);
                }
            }
        });
    }

    interface ChildListener {
        void costUpdate(int cost);
    }

    void addChildCheckListener(ChildCheckListener c) {
        childCheckListener = c;
    }

    void addListener(ChildListener childListener) {
        this.childListener = childListener;
    }

    interface ChildCheckListener {
        void singleChildItemChecked(int parentItemIndex,ArrayList<SubClothItem> subClothItemArrayList);

        void allItemUnselected(int index);
    }
    @Override
    public int getItemCount() {
        return subClothItemArrayList.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView clothName, clothDesc, itemCost;
        CheckBox checkBox;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            clothDesc = itemView.findViewById(R.id.el_cloth_description);
            clothName = itemView.findViewById(R.id.el_clothName);
            checkBox = itemView.findViewById(R.id.child_checkbox);
            itemCost = itemView.findViewById(R.id.child_item_cost);
        }
    }
}
