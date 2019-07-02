package com.example.foodie_buddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ownerProfile extends AppCompatActivity {

    public void addRestaurant(View v)
    {
        Intent i = new Intent("com.example.foodie_buddy.createRestaurant");
        startActivity(i);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);

        if(!sharedOwnerManager.getInstance(this).isOwnerLoggedIn())
        {
            finish();
            startActivity(new Intent(this, ownerLogin.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menulogout:
                sharedOwnerManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this,ownerLogin.class));
                break;
        }
        return true;
    }
}
