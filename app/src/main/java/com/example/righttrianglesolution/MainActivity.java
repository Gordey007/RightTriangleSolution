package com.example.righttrianglesolution;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button calculate;
    EditText aText, bText;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv = findViewById(R.id.triangle);
        iv.setImageResource(R.drawable.triangle);

        calculate = findViewById(R.id.calculate);
        calculate.setOnClickListener(this);

        aText = findViewById(R.id.a);
        aText.setOnClickListener(this);

        bText = findViewById(R.id.b);
        bText.setOnClickListener(this);

        result = findViewById(R.id.result);
        result.setOnClickListener(this);

        // AdMob
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();

        AdView menu_banner = findViewById(R.id.menu_banner);
        menu_banner.loadAd(adRequest);

        AdView menu_banner2 = findViewById(R.id.menu_banner2);
        menu_banner2.loadAd(adRequest);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.calculate:
                try {
                    double a = Integer.parseInt(aText.getText().toString());
                    double b = Integer.parseInt(bText.getText().toString());
                    double A = Math.toDegrees(Math.atan(a / b));
                    double B = 180 - 90 - A;

                    Toast toast;
                    if(aText.getText().toString().length() < 1 || bText.getText().toString().length() < 1) {
                        toast = Toast.makeText(getApplicationContext(), "Заполните все поля",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    result.setText("A = " + A + "\n" + "B = " + B);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
        }
    }
}