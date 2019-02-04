package com.example.expandablelist;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sackcentury.shinebuttonlib.ShineButton;

public class ConfirmationActivity extends AppCompatActivity {

    TextView orderIdTv, orderAmountTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        orderIdTv = findViewById(R.id.conf_order_id);
        orderAmountTv = findViewById(R.id.conf_order_amount);

        final ShineButton shineButton = findViewById(R.id.btn_shine);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shineButton.setChecked(true, true);
                shineButton.setClickable(false);
            }
        }, 520);

        orderAmountTv.setText(getIntent().getStringExtra("orderAmount"));
        orderIdTv.setText(getIntent().getStringExtra("orderId"));

    }
}
