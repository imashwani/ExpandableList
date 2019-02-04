package com.example.expandablelist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class SignatureActivity extends AppCompatActivity implements ExperienceBottomSheetFragment.ExperienceBSfragListener, ThankYouBottomSheetFragment.ThankYouBottomSheetInterface {

    SignaturePad signaturePad;
    Button submitFeedbackBt, clearBtImage;
    ImageButton sad, neutral, happy;
    boolean isExperienceSelected = false, isSadSelected = false;

    TextView qtyTv, costTv;
    String orderId = "#QWL182105618006";

    ExperienceBottomSheetFragment experienceBottomSheetFragment;
    ThankYouBottomSheetFragment thankYouBottomSheetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        experienceBottomSheetFragment = new ExperienceBottomSheetFragment();
        experienceBottomSheetFragment.setListener(this);
        thankYouBottomSheetFragment = new ThankYouBottomSheetFragment();
        thankYouBottomSheetFragment.addListener(this);

        signaturePad = findViewById(R.id.signature_pad);
        clearBtImage = findViewById(R.id.sign_refresh_iv);
        submitFeedbackBt = findViewById(R.id.sign_submit_feedback);
        sad = findViewById(R.id.face_sad);
        happy = findViewById(R.id.face_happy);
        neutral = findViewById(R.id.face_neutral);
        qtyTv = findViewById(R.id.sign_qty_total);
        costTv = findViewById(R.id.sign_total_cost);

        costTv.setText(getIntent().getStringExtra("cost"));
        qtyTv.setText(getIntent().getStringExtra("qty"));

        clearFace();

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFace(0, sad);
            }
        });
        neutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFace(1, neutral);
            }
        });
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFace(2, happy);
            }
        });


        submitFeedbackBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signaturePad.isEmpty()) {
                    Toast.makeText(SignatureActivity.this, "Please Sign", Toast.LENGTH_SHORT).show();
                    return;
                } else if (isExperienceSelected == false) {
                    Toast.makeText(SignatureActivity.this, "Please Select Experience", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //getting bitmap of the signature
                    Bitmap bitmap = signaturePad.getSignatureBitmap();

                    String root = Environment.getExternalStorageDirectory().toString();
                    File myDir = new File(root + "/req_images");
                    myDir.mkdirs();
                    Random generator = new Random();
                    int n = 10000;
                    n = generator.nextInt(n);
                    String fname = "Image-" + n + ".jpg";
                    File file = new File(myDir, fname);
                    System.out.println("" + file);
                    if (file.exists())
                        file.delete();
                    try {
                        FileOutputStream out = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (isSadSelected) {
                        experienceBottomSheetFragment.show(getSupportFragmentManager(), "this");
                    } else {
                        showConfirmationScreen();
                    }

                }
            }
        });

        clearBtImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
                Toast.makeText(SignatureActivity.this, "Clear", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void clearFace() {
        isExperienceSelected = false;
        isSadSelected = false;
        sad.setImageDrawable(getResources().getDrawable(R.drawable.ic_exprience_icon_sad));
        neutral.setImageDrawable(getResources().getDrawable(R.drawable.ic_exprience_icon_neutral));
        happy.setImageDrawable(getResources().getDrawable(R.drawable.ic_exprience_icon_happy));
    }

    void setFace(int x, ImageButton imageButton) {
        clearFace();
        switch (x) {
            case 0:
                imageButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_exprience_icon_sad_checked));
                isSadSelected = true;
                break;
            case 1:
                imageButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_exprience_icon_neutral_checked));
                isSadSelected = false;
                break;
            case 2:
                imageButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_exprience_icon_happy_checked));
                isSadSelected = false;
                break;
        }
        isExperienceSelected = true;

    }

    void showConfirmationScreen() {
        Intent intent = new Intent(SignatureActivity.this, ConfirmationActivity.class);
        intent.putExtra("orderId", orderId);
        intent.putExtra("orderAmount", costTv.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void showThankyoukBS() {
        experienceBottomSheetFragment.dismiss();
        thankYouBottomSheetFragment.show(getSupportFragmentManager(), "this");

    }

    @Override
    public void showConfirmActivity() {
        showConfirmationScreen();
    }
}
