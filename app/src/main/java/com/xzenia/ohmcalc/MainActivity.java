package com.xzenia.ohmcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private ArrayList<String> colorList;
    private ArrayList<Integer> colorValueList;

    private Button btnRed;
    private Button btnBlue;
    private Button btnYellow;
    private Button btnGreen;
    private Button btnViolet;
    private Button btnOrange;
    private Button btnBrown;
    private Button btnWhite;
    private Button btnBlack;
    private Button btnGray;
    private Button btnGold;
    private Button btnSilver;

    private Button btnEquals;
    private Button btnBackSpace;
    private Button btnClearAll;

    private TextView colorTextView;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorList = new ArrayList<>();
        colorValueList = new ArrayList<>();

        btnRed = findViewById(R.id.btn_red);
        btnBlue = findViewById(R.id.btn_blue);
        btnYellow = findViewById(R.id.btn_yellow);
        btnGreen = findViewById(R.id.btn_green);
        btnViolet = findViewById(R.id.btn_violet);
        btnOrange = findViewById(R.id.btn_orange);
        btnBrown = findViewById(R.id.btn_brown);
        btnWhite = findViewById(R.id.btn_white);
        btnBlack = findViewById(R.id.btn_black);
        btnGray = findViewById(R.id.btn_gray);
        btnGold = findViewById(R.id.btn_gold);
        btnSilver = findViewById(R.id.btn_silver);

        btnRed.setOnClickListener(colorOnClickListener);
        btnBlue.setOnClickListener(colorOnClickListener);
        btnYellow.setOnClickListener(colorOnClickListener);
        btnGreen.setOnClickListener(colorOnClickListener);
        btnViolet.setOnClickListener(colorOnClickListener);
        btnOrange.setOnClickListener(colorOnClickListener);
        btnBrown.setOnClickListener(colorOnClickListener);
        btnWhite.setOnClickListener(colorOnClickListener);
        btnBlack.setOnClickListener(colorOnClickListener);
        btnGold.setOnClickListener(colorOnClickListener);
        btnGray.setOnClickListener(colorOnClickListener);
        btnSilver.setOnClickListener(colorOnClickListener);

        btnEquals = findViewById(R.id.btn_equal);
        btnBackSpace = findViewById(R.id.btn_back);
        btnClearAll = findViewById(R.id.btn_clear);

        btnEquals.setOnClickListener(operationOnClickListener);
        btnBackSpace.setOnClickListener(operationOnClickListener);
        btnClearAll.setOnClickListener(operationOnClickListener);

        colorTextView = findViewById(R.id.colorTextView);
        resultTextView = findViewById(R.id.resultTextView);
    }

    private View.OnClickListener colorOnClickListener = new View.OnClickListener(){
        public void onClick(View v){
            String chosenColor = btnBlack.getText().toString();
            int chosenValue = 0;

            switch(v.getId()){
                case R.id.btn_black:
                    chosenColor = btnBlack.getText().toString();
                    chosenValue = 0;
                    break;
                case R.id.btn_brown:
                    chosenColor = btnBrown.getText().toString();
                    chosenValue = 1;
                    break;
                case R.id.btn_red:
                    chosenColor = btnRed.getText().toString();
                    chosenValue = 2;
                    break;
                case R.id.btn_orange:
                    chosenColor = btnOrange.getText().toString();
                    chosenValue = 3;
                    break;
                case R.id.btn_yellow:
                    chosenColor = btnYellow.getText().toString();
                    chosenValue = 4;
                    break;
                case R.id.btn_green:
                    chosenColor = btnGreen.getText().toString();
                    chosenValue = 5;
                    break;
                case R.id.btn_blue:
                    chosenColor = btnBlue.getText().toString();
                    chosenValue = 6;
                    break;
                case R.id.btn_violet:
                    chosenColor = btnViolet.getText().toString();
                    chosenValue = 7;
                    break;
                case R.id.btn_gray:
                    chosenColor = btnGray.getText().toString();
                    chosenValue = 8;
                    break;
                case R.id.btn_white:
                    chosenColor = btnWhite.getText().toString();
                    chosenValue = 9;
                    break;

                case R.id.btn_gold:
                    chosenColor = btnGold.getText().toString();
                    chosenValue = -1;
                    break;
                case R.id.btn_silver:
                    chosenColor = btnSilver.getText().toString();
                    chosenValue = -2;
                    break;
                default:
                    colorList.add(btnRed.getText().toString());
                    Log.e(TAG, "Color button switch case defaulted!");
                    break;
            }

            if (colorList.size() < 6){
                colorList.add(chosenColor);
                colorValueList.add(chosenValue);

                updateScreen();
            }
        }
    };

    private View.OnClickListener operationOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_back:
                    if (colorList.size() > 0){
                        colorList.remove(colorList.size() - 1);
                        colorValueList.remove(colorValueList.size() - 1);
                    }
                    updateScreen();
                    break;
                case R.id.btn_clear:
                    colorList.clear();
                    colorValueList.clear();

                    resultTextView.setText("");
                    updateScreen();
                    break;
                case R.id.btn_equal:
                    calculateResistance(colorValueList);
                    break;
            }
        }
    };

    private void updateScreen(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int counter = 0; counter < colorList.size(); counter++){
            stringBuilder.append(colorList.get(counter));
            if (counter != colorList.size() - 1){
                stringBuilder.append(" + ");
            }
        }
        colorTextView.setText(stringBuilder.toString());
    }

    private void calculateResistance(ArrayList<Integer> input){
        if (input.size() == 4) {
            if (colorValueList.get(0) >= 0 && colorValueList.get(1) >= 0){
                resultTextView.setText(calculateFourBands(colorValueList));
            } else {
                resultTextView.setText("INVALID");
            }
        }

        else if (input.size() == 5){
            if (colorValueList.get(0) >= 0 && colorValueList.get(1) >= 0){
                resultTextView.setText(calculateFiveBands(colorValueList));
            } else {
                resultTextView.setText("INVALID");
            }
        }

        else if (input.size() == 6){
            if (colorValueList.get(0) >= 0 && colorValueList.get(1) >= 0){
                resultTextView.setText(calculateSixBands(colorValueList));
            } else {
                resultTextView.setText("INVALID");
            }
        }
        else{
            resultTextView.setText("INSUFFICIENT INPUTS");
        }

    }

    private String calculateFourBands(ArrayList<Integer> input){
        int digits = Integer.parseInt(input.get(0)+""+input.get(1));
        double tolerance = 0;
        double result;

        result = digits * Math.pow(10, input.get(2));

        if (input.get(3) < 5){
            tolerance = result * (Double.parseDouble(input.get(3).toString()) / 100);
        } else if (input.get(3) >= 5){
            switch(input.get(3)) {
                case 5:
                    tolerance = result * 0.05;
                    break;
                case 6:
                    tolerance = result * 0.025;
                    break;
                case 7:
                    tolerance = result * 0.10;
                    break;
                case 8:
                    tolerance = result * 0.005;
                    break;
                case 9:
                    tolerance = result * 0;
                    break;
            }
        } else if (input.get(3) == 10){
            tolerance = result * 0.05;
        } else {
            tolerance = result * 0.1;
        }

        return result+" Ω"+"\n"+"Tolerance: "+tolerance;
    }

    private String calculateFiveBands(ArrayList<Integer> input){
        int digits = Integer.parseInt(input.get(0)+""+input.get(1)+""+input.get(2));
        double tolerance = 0;
        double result;

        result = digits * Math.pow(10, input.get(3));

        if (input.get(4) < 5){
            tolerance = result * (Double.parseDouble(input.get(4).toString()) / 100);
        } else if (input.get(4) >= 5){
            switch(input.get(4)) {
                case 5:
                    tolerance = result * 0.05;
                    break;
                case 6:
                    tolerance = result * 0.025;
                    break;
                case 7:
                    tolerance = result * 0.10;
                    break;
                case 8:
                    tolerance = result * 0.005;
                    break;
                case 9:
                    tolerance = result * 0;
                    break;
            }
        } else if (input.get(4) == 10){
            tolerance = result * 0.05;
        } else {
            tolerance = result * 0.1;
        }

        return result+" Ω"+"\n"+"Tolerance: "+tolerance;
    }

    private String calculateSixBands(ArrayList<Integer> input) {
        int digits = Integer.parseInt(input.get(0)+""+input.get(1)+""+input.get(2));
        double tolerance = 0;
        double result;

        double tcr = 0;

        result = digits * Math.pow(10, input.get(3));

        if (input.get(4) < 5) {
            tolerance = result * (Double.parseDouble(input.get(4).toString()) / 100);
        } else if (input.get(4) >= 5){
            switch(input.get(4)) {
                case 5:
                    tolerance = result * 0.05;
                    break;
                case 6:
                    tolerance = result * 0.025;
                    break;
                case 7:
                    tolerance = result * 0.10;
                    break;
                case 8:
                    tolerance = result * 0.005;
                    break;
                case 9:
                    tolerance = result * 0;
                    break;
            }
        } else if (input.get(4) == 10){
            tolerance = result * 0.05;
        } else {
            tolerance = result * 0.10;
        }

        switch(input.get(5)){
            case 1:
                tcr = 100;
                break;
            case 2:
                tcr = 50;
                break;
            case 3:
                tcr = 15;
                break;
            case 4:
                tcr = 25;
                break;
            default:
                tcr = 0;
                break;
        }

        return result+" Ω"+"\n"+"Tolerance: "+tolerance+"\n"+"Temperature Coefficient: "+tcr+" ppm/K";
    }
}
