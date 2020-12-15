package io.pure.bmical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "io.pure.bmical.MESSAGE";
    RadioButton rbMale;
    RadioButton rbFemale;
    EditText edHeight;
    EditText edWeight;
    Button btnCalBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();

    }

    public void setViews() {
        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);     //判断男女的 RadioGroup
        edHeight = (EditText) findViewById(R.id.edHeight);
        //添加事件监听
        edHeight.addTextChangedListener(textWatcher);
        edWeight = (EditText) findViewById(R.id.edWeight);
        //添加事件监听
        edWeight.addTextChangedListener(textWatcher);
        btnCalBMI = (Button) findViewById(R.id.btnCalBMI);
        //添加事件监听
        btnCalBMI.setOnClickListener(this::onClick);

    }


    //对输入框的事件监听
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        //若两文本框都不为空，则按钮可用
        @Override
        public void afterTextChanged(Editable s) {
            if (edHeight.getText().length()==0||edWeight.getText().length()==0){
                btnCalBMI.setEnabled(false);
            }else {
                btnCalBMI.setEnabled(true);
            }
        }
    };


    public void onClick(View view) {
        double heightInfo = Double.parseDouble(edHeight.getText().toString());
        double weightInfo = Double.parseDouble(edWeight.getText().toString());
        double bmiInfo;
        double bmiMale;         //男性标准体重 = （（身高cm - 100）* 0.9)
        double bmiFemale;        //女性标准体重 = （（身高cm - 100）* 0.9)kg - 2.5kg
        double bodyNumMale;     //判断男性计算体型的数值百分比
        double bodyNumFemale;   //判断女性计算体型
        String sexInfo="";
        String bodyType="";        //体型

        //判断输入框中 输入的值是否小于等于零
        if(heightInfo <= 0|| weightInfo <= 0){
            String toasInfo = getResources().getString(R.string.toast_info);
            Toast.makeText(this, toasInfo, Toast.LENGTH_SHORT).show();
            return;
        }


         //功能：计算标准体重BMI 判断体型
        if (rbMale.isChecked()) {
            bmiMale = ((heightInfo - 100) * 0.9);       //男性的标准体重计算结果
            String bmi = String.format("%.2f",bmiMale);
            bodyNumMale = (1 - (bmiMale / weightInfo));     //计算百分比对体型进行判断

            if (bodyNumMale < 0.1 && bodyNumMale > -0.1)
                bodyType += "正常体重";
            else if (bodyNumMale < -0.5)
                bodyType += "严重瘦弱";
            else if (bodyNumMale < -0.3)
                bodyType += "轻度瘦弱";
            else if (bodyNumMale < -0.2)
                bodyType += "瘦弱";
            else if (bodyNumMale > 0.5)
                bodyType += "重度肥胖";
            else if (bodyNumMale > 0.3)
                bodyType += "中度肥胖";
            else if (bodyNumMale > 0.2)
                bodyType += "轻度肥胖";
            else if (bodyNumMale > 0.1)
                bodyType += "超重";
            bmiInfo = Double.parseDouble(bmi);
            sexInfo += "男";
        } else {
            bmiFemale = (((heightInfo - 100) * 0.9) - 2.5);     //女性的标准体重计算结果
            String bmi = String.format("%.2f",bmiFemale);
            bodyNumFemale = (1 - (bmiFemale / weightInfo));       //计算百分比 对体型进行判断

            if (bodyNumFemale < 0.1 && bodyNumFemale > -0.1)
                bodyType += "正常体重";
            else if (bodyNumFemale < -0.5)
                bodyType += "严重瘦弱";
            else if (bodyNumFemale < -0.3)
                bodyType += "轻度瘦弱";
            else if (bodyNumFemale < -0.2)
                bodyType += "瘦弱";
            else if (bodyNumFemale > 0.5)
                bodyType += "重度肥胖";
            else if (bodyNumFemale > 0.3)
                bodyType += "中度肥胖";
            else if (bodyNumFemale > 0.2)
                bodyType += "轻度肥胖";
            else if (bodyNumFemale > 0.1)
                bodyType += "超重";
            bmiInfo = Double.parseDouble(bmi);
            sexInfo += "女";
        }

        //使用intent进行 传输值
        Intent intent = new Intent(MainActivity.this,DisplayBmiInfoActivity.class);
        intent.putExtra("sexInfo",sexInfo)
                .putExtra("heightInfo", heightInfo)
                .putExtra("weightInfo", weightInfo)
                .putExtra("bmiInfo", bmiInfo)
                .putExtra("bodyInfo", bodyType);
        startActivity(intent);


    }
}
