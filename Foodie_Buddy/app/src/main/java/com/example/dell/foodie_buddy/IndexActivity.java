package com.example.dell.foodie_buddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class IndexActivity extends AppCompatActivity {

    public void User(View v)
    {
        Intent i = new Intent("com.example.dell.foodie_buddy.user_activity");
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }
}
