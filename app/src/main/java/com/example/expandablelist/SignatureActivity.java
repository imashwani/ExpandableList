package com.example.expandablelist;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class SignatureActivity extends AppCompatActivity {


    SignaturePad signaturePad;

    Button saveBt, clearBtImage;
    ImageButton sad, neutral, happy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        signaturePad = findViewById(R.id.signature_pad);
        clearBtImage = findViewById(R.id.sign_refresh_iv);
        saveBt = findViewById(R.id.save);
        sad = findViewById(R.id.face_sad);
        happy = findViewById(R.id.face_happy);
        neutral = findViewById(R.id.face_neutral);

        clearFace();

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFace(sad);
            }
        });
        neutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFace(neutral);
            }
        });
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFace(happy);
            }
        });


        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signaturePad.isEmpty()) {
                    Toast.makeText(SignatureActivity.this, "ClearBt", Toast.LENGTH_SHORT).show();
                    return;
                } else {
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
        sad.setBackgroundColor(Color.parseColor("#ffffff"));
        neutral.setBackgroundColor(Color.parseColor("#ffffff"));
        happy.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    void setFace(ImageButton imageButton) {
        clearFace();
        imageButton.setBackgroundColor(Color.parseColor("#008577"));
    }
}
