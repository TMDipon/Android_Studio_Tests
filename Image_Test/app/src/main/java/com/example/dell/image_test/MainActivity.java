package com.example.dell.image_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void show(View v)
    {
        EditText t = (EditText)findViewById(R.id.name);
        String t1 = t.getText().toString();
        ImageView t2 = (ImageView)findViewById(R.id.car);

        if(t1.equalsIgnoreCase("porshe"))
        {
            t2.setImageResource(R.drawable.porshe);
        }
        else if(t1.equalsIgnoreCase("audi"))
        {
            t2.setImageResource(R.drawable.audi);
        }
        else if(t1.equalsIgnoreCase("bugatti"))
        {
            t2.setImageResource(R.drawable.bugatti);
        }
        else
        {
            t2.setImageResource(R.drawable.toyota);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
