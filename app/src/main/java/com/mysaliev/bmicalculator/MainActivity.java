package com.mysaliev.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private EditText etAge;
    private EditText etFeet;
    private EditText etWeight;
    private EditText etInches;
    private Button btnCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setUpButtonClickListener();
    }


    private void findViews() {

        tvResult = findViewById(R.id.tvResult);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        etAge = findViewById(R.id.etAge);
        etFeet = findViewById(R.id.etFeet);
        etWeight = findViewById(R.id.etWeight);
        etInches = findViewById(R.id.etInches);
        btnCalculator = findViewById(R.id.btnCalculator);

    }

    private void setUpButtonClickListener() {
        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBmi();
                String ageText = etAge.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }

            }
        });
    }


    private double calculateBmi() {
        String feetText = etFeet.getText().toString();
        String weightText = etWeight.getText().toString();
        String inchesText = etInches.getText().toString();

        //Converting the number 'Strings' into 'int' variables

        int feet = Integer.parseInt(feetText);
        int weight = Integer.parseInt(weightText);
        int inches = Integer.parseInt(inchesText);

        int totalInches = (feet * 12) + inches;
        double heightInMetres = totalInches * 0.0254;

        return weight / (heightInMetres * heightInMetres);

    }

    private void displayResult(double bmi) {

        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if (bmi < 18.5) {
            fullResultString = bmiResult + " - You are underweight!";
        } else if (bmi > 25) {
            fullResultString = bmiResult + " - You are overweight!";
        } else {
            fullResultString = bmiResult + " - You are healthy!";
        }


        tvResult.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {

        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if (rbMale.isChecked()) {
            fullResultString = bmiResult + " - As you are under 18 please consult with your doctor for the healthy range for boys.";
        }
        else if (rbFemale.isChecked()) {
            fullResultString = bmiResult + " - As you are under 18 please consult with your doctor for the healthy range for girls.";
        }
        else {
            fullResultString = bmiResult + " - As you are under 18 please consult with your doctor for the healthy range.";
        }
        tvResult.setText(fullResultString);
    }
}