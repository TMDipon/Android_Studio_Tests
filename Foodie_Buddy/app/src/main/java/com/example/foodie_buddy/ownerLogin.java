package com.example.foodie_buddy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ownerLogin extends AppCompatActivity {

    public void owner_log_in(View v)
    {

        EditText t1 = (EditText)findViewById(R.id.ologmail);
        EditText t2 = (EditText)findViewById(R.id.ologpass);

        final String s1 = t1.getText().toString();
        final String s2 = t2.getText().toString();

        final ProgressDialog p = new ProgressDialog(this);

        if(s1.isEmpty() || s2.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Provide necessary information for owner login", Toast.LENGTH_LONG).show();
        }
        else {

            p.setMessage("Please Wait....");
            p.show();

            StringRequest s3 = new StringRequest(Request.Method.POST, constants.lowner_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    p.dismiss();


                    try {
                        JSONObject j = new JSONObject(response);
                        if(!j.getBoolean("estat"))
                        {
                            sharedOwnerManager.getInstance(getApplicationContext()).ownerLogin(j.getInt("id"),j.getString("name"),j.getString("email"));
                            Toast.makeText(getApplicationContext(), j.getString("info"), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),ownerProfile.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), j.getString("info"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    p.hide();
                    Toast.makeText(getApplicationContext(), error.getMessage()+"haha", Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> m = new HashMap<>();
                    m.put("email", s1);
                    m.put("password", s2);
                    return m;
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(s3);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);

        if(sharedOwnerManager.getInstance(this).isOwnerLoggedIn())
        {
            finish();
            startActivity(new Intent(this, ownerProfile.class));
        }
    }
}
