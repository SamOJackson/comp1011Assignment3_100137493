package com.example.assignment4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ModifyActivity extends Cars {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        currentCar = (Cars) getIntent().getSerializableExtra("Current");
        ArrayList<Cars> carArray = (ArrayList<Cars>) getIntent().getSerializableExtra("Array");


        TextView textView = findViewById(R.id.CurrentName);
        textView.setText(currentCar.getCarName());
        TextView textView2 = findViewById(R.id.available2);
        String text;
        if (!currentCar.getIsAvailable()) {
            text = "Unavailable";
        } else {
            text = "Available";
        }
        textView2.setText(text);
        TextView details = findViewById(R.id.CurrentCompany2);
        details.setText(currentCar.getCarCompany());


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedItemIdM = view.getId();

                EditText editText = findViewById(R.id.CarName);
                EditText editCompany = findViewById(R.id.CarCompany);
                CheckBox simpleCheckBox = (CheckBox) findViewById(R.id.available);
                Button submit = (Button) findViewById(R.id.submit);
                Intent Intent;
                switch (selectedItemIdM) {
                    case R.id.vehicleButton2:
                        Intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(Intent);
                        break;
                    case R.id.Add2:
                        Intent = new Intent(getApplicationContext(), AddActivity.class);
                        startActivity(Intent);
                        Intent.putExtra("Array", carArray);

                        break;
                    case R.id.Modify2:
                        Intent = new Intent(getApplicationContext(), ModifyActivity.class);
                        Intent.putExtra("Current", currentCar);
                        startActivity(Intent);
                        break;
                    case R.id.submit:
                        Intent intent = submitting();
                        intent.putExtra("Current", currentCar);
                        intent.putExtra("Array", carArray);
                        startActivity(intent);
                        break;
                    case R.id.BackToView:
                        Intent = new Intent(getApplicationContext(), ViewActivity.class);
                        Intent.putExtra("Current", currentCar);
                        Intent.putExtra("Array", carArray);

                        startActivity(Intent);
                        break;
                    default:
                        Snackbar.make(view, "unknown item selected", Snackbar.LENGTH_LONG).show();
                }

            }
        };


        Button Vehicles2 = findViewById(R.id.vehicleButton2);
        Button Add2 = findViewById(R.id.Add2);
        Button Modify2 = findViewById(R.id.Modify2);

        Button View2 = findViewById(R.id.BackToView);


        View2.setOnClickListener(onClickListener);

        Vehicles2.setOnClickListener(onClickListener);
        Add2.setOnClickListener(onClickListener);
        Modify2.setOnClickListener(onClickListener);


    }

    private Intent submitting() {
        EditText editText = findViewById(R.id.CarName);
        EditText editCompany = findViewById(R.id.CarCompany);
        CheckBox simpleCheckBox = (CheckBox) findViewById(R.id.available);
        Button submit = (Button) findViewById(R.id.submit);

        currentCar.setCarName(editText.getText().toString());
        currentCar.setCarCompany(editCompany.getText().toString());

        TextView textView = findViewById(R.id.CurrentName);
        textView.setText(currentCar.getCarName());
        TextView textView2 = findViewById(R.id.available2);
        String text;
        if (!currentCar.getIsAvailable()) {
            text = "Unavailable";
        } else {
            text = "Available";
        }
        textView2.setText(text);
        TextView details = findViewById(R.id.CurrentCompany2);
        details.setText(currentCar.getCarCompany());

        Boolean checkBoxState = simpleCheckBox.isChecked();
        if (checkBoxState) {
            currentCar.setIsAvailable(true);
        } else {
            currentCar.setIsAvailable(false);
        }
        Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
        return intent;



    }
}
