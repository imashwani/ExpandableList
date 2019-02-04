package com.example.expandablelist;


import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ThankYouBottomSheetFragment extends BottomSheetDialogFragment {


    Button continueBt;
    ThankYouBottomSheetInterface thankYouBottomSheetInterface;

    public ThankYouBottomSheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thank_you_bottom_sheet, container, false);
        continueBt = view.findViewById(R.id.thankyou_continue);
        continueBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thankYouBottomSheetInterface.showConfirmActivity();
            }
        });
        return view;
    }

    void addListener(ThankYouBottomSheetInterface thankYouBottomSheetInterface) {
        this.thankYouBottomSheetInterface = thankYouBottomSheetInterface;

    }

    interface ThankYouBottomSheetInterface {
        void showConfirmActivity();
    }
}
