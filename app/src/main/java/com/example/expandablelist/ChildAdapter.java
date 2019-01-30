package com.example.expandablelist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ChildViewHolder> {

    Context context;
//    ArrayList<Cloth> clothArrayList;
String clothName;
    public ChildAdapter(Context context,String clothName) {
        this.clothName=clothName;
        this.context = context;
//        this.clothArrayList = clothArrayList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_single_item,viewGroup,false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder childViewHolder, final int i) {
        childViewHolder.clothName.setText(clothName+ " "+i);

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView clothName, clothDesc, noOfCloth;
        CheckBox checkBox;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            clothDesc = itemView.findViewById(R.id.el_cloth_description);
            clothName = itemView.findViewById(R.id.el_clothName);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
