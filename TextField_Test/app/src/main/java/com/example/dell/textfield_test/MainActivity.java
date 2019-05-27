package com.example.dell.textfield_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void showup(View v)
    {
        EditText t = (EditText)findViewById(R.id.name);
        EditText t1 = (EditText)findViewById(R.id.password);

        Log.i("Log Info of username and password",t.getText().toString()+" "+t1.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
