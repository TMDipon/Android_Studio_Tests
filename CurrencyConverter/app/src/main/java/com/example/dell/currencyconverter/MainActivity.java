package com.example.dell.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert(View v)
    {
        EditText t = (EditText)findViewById(R.id.dollar);
        String t1 = t.getText().toString();
        Double dol = Double.parseDouble(t1);
        Double pound = dol * 0.79;
        TextView x = (TextView)findViewById(R.id.pound);
        x.setText("Amount in Pound: " + pound.toString());
        Toast.makeText(this, "Dollar to Pound converted", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
