package com.example.foodie_buddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class userProfile extends AppCompatActivity {

    public ListView lv;
    public ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        if(!sharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(this, loginActivity.class));
        }

        lv = (ListView)findViewById(R.id.restList);
        items = new ArrayList<String>();
        ArrayList<Restaurants> r= sharedRestaurant.getInstance(getApplicationContext()).getRest();

        getRestaurants();

        for(int i=0;i<r.size();i++)
        {
            items.add(r.get(i).format());
        }
        ArrayAdapter<String> tem = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,items);
        lv.setAdapter(tem);
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
                sharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this,loginActivity.class));
                break;
        }
        return true;
    }

    public void getRestaurants()
    {
        StringRequest s3 = new StringRequest(Request.Method.GET, constants.showres_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try
                {
                    JSONArray rests = new JSONArray(response);
                    for(int i=0; i < rests.length(); i++)
                    {
                        JSONObject k = rests.getJSONObject(i);
                        sharedRestaurant.getInstance(getApplicationContext()).add(k.getString("name"),k.getString("type"),k.getString("starts_at"),k.getString("closes_at"));
                    }

                }
                catch (JSONException e)
                {
                    //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestHandler.getInstance(this).addToRequestQueue(s3);
    }
}
