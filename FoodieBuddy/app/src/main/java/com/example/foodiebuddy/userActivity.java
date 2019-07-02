package com.example.foodiebuddy;

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

public class userActivity extends AppCompatActivity {

    public void register(View v)
    {
        EditText t1 = (EditText)findViewById(R.id.username);
        EditText t2 = (EditText)findViewById(R.id.password);
        EditText t3 = (EditText)findViewById(R.id.email);
        EditText t4 = (EditText)findViewById(R.id.phone);

        final String s1 = t1.getText().toString();
        final String s2 = t2.getText().toString();
        final String s3 = t3.getText().toString();
        final String s5 = t4.getText().toString();

        final ProgressDialog p = new ProgressDialog(this);

        if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s5.isEmpty())
        {
            Toast.makeText(userActivity.this, "Provide all the information to register", Toast.LENGTH_LONG).show();
        }
        else {

            p.setMessage("Registering User");
            p.show();

            StringRequest s4 = new StringRequest(Request.Method.POST, constants.cuser_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    p.dismiss();


                    try {
                        JSONObject j = new JSONObject(response);
                        Toast.makeText(userActivity.this, j.getString("info"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        Toast.makeText(userActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    p.hide();
                    Toast.makeText(userActivity.this, error.getMessage()+"haha", Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> m = new HashMap<>();
                    m.put("name", s1);
                    m.put("email_id", s3);
                    m.put("password", s2);
                    return m;
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(s4);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }
}
