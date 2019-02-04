package com.example.expandablelist;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sackcentury.shinebuttonlib.ShineButton;

public class ConfirmationActivity extends AppCompatActivity {

    TextView orderIdTv, orderAmountTv;
    Button newOrderBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        orderIdTv = findViewById(R.id.conf_order_id);
        orderAmountTv = findViewById(R.id.conf_order_amount);
        newOrderBt=findViewById(R.id._finish_act_new_order_bt);

        newOrderBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
