package io.pure.bmical;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayBmiInfoActivity extends AppCompatActivity {

    TextView tvSex;
    TextView tvHeightInfo;
    TextView tvWeightInfo;
    TextView tvBMIInfo;
    TextView tvBodyInfo;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bmi_info);


        tvSex = (TextView)findViewById(R.id.tvSex);
        tvHeightInfo = (TextView)findViewById(R.id.tvHeightInfo);
        tvWeightInfo= (TextView)findViewById(R.id.tvWeightInfo);
        tvBMIInfo = (TextView)findViewById(R.id.tvBMIInfo);
        tvBodyInfo = (TextView)findViewById(R.id.tvBodyInfo);


        Bundle bundle = getIntent().getExtras();
        String sexInfo = bundle.getString("sexInfo");
        tvSex.setText(sexInfo);
        double heightInfo = bundle.getDouble("heightInfo");
        tvHeightInfo.setText(String.valueOf(heightInfo) + "cm");
        double weightInfo = bundle.getDouble("weightInfo");
        tvWeightInfo.setText(String.valueOf(weightInfo) + "kg");
        double bmiInfo = bundle.getDouble("bmiInfo");
        tvBMIInfo.setText(String.valueOf(bmiInfo));
        String bodyInfo = bundle.getString("bodyInfo");
        tvBodyInfo.setText(bodyInfo);


    }
}