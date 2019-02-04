package com.example.expandablelist;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ExperienceBottomSheetFragment extends BottomSheetDialogFragment {

    @BindView(R.id.bs_submit_feedback)
    Button submitFeedbackBt;

    ExperienceBSfragListener experienceBSfragListener;

    public ExperienceBottomSheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_experience_bottom_sheet, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    @OnClick(R.id.bs_submit_feedback)
    void clickFeedback(View view) {
        experienceBSfragListener.showThankyoukBS();
    }

    void setListener(ExperienceBSfragListener experienceBSfragListener) {
        this.experienceBSfragListener = experienceBSfragListener;
    }

    interface ExperienceBSfragListener {
        void showThankyoukBS();
    }
}
