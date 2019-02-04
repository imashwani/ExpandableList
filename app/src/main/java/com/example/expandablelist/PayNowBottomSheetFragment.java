package com.example.expandablelist;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class PayNowBottomSheetFragment extends BottomSheetDialogFragment {


    @BindView(R.id.btn_cash)
    Button cash;

    @BindView(R.id.btn_card)
    Button card;

    @BindView(R.id.btn_walltet)
    Button wallet;

    @BindView(R.id.btn_paynow)
    Button paynow;

    PaynowFragmentListener listener;

    public PayNowBottomSheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pay_now_bottom_sheet, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    @OnTouch(R.id.btn_cash)
    boolean onCashClick(View view) {
        cash.setPressed(true);
        card.setPressed(false);
        wallet.setPressed(false);

        return true;
    }


    @OnTouch(R.id.btn_card)
    boolean onCardClick(View view) {

        cash.setPressed(false);
        card.setPressed(true);
        wallet.setPressed(false);

        return true;
    }


    @OnTouch(R.id.btn_walltet)
    boolean onWalletClick(View view) {
        cash.setPressed(false);
        card.setPressed(false);
        wallet.setPressed(true);

        return true;
    }


    @OnClick(R.id.btn_paynow)
    void onPayNowClick(View view) {

        if (cash.isPressed()) {
            Toast.makeText(getActivity(), "cash", Toast.LENGTH_SHORT).show();

            listener.onCashPayment();
            dismiss();

        } else if (card.isPressed()) {
            Toast.makeText(getActivity(), "card", Toast.LENGTH_SHORT).show();
            listener.onCardPayment();
            dismiss();

        } else if (wallet.isPressed()) {
            Toast.makeText(getActivity(), "wallet", Toast.LENGTH_SHORT).show();
            listener.onWalletPayment();
            dismiss();
        } else
            Toast.makeText(getActivity(), "Choose any mode of payment", Toast.LENGTH_SHORT).show();


    }


    public void setListener(PaynowFragmentListener listener) {
        this.listener = listener;
    }

    interface PaynowFragmentListener {

        void onCashPayment();

        void onCardPayment();

        void onWalletPayment();
    }

}
