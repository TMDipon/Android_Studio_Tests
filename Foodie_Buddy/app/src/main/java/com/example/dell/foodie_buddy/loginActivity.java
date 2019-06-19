package com.example.dell.foodie_buddy;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class loginActivity extends AppCompatActivity {

    public void log_in(View v)
    {

        EditText t1 = (EditText)findViewById(R.id.logmail);
        EditText t2 = (EditText)findViewById(R.id.logpass);

        final String s1 = t1.getText().toString();
        final String s2 = t2.getText().toString();

        final ProgressDialog p = new ProgressDialog(this);

        if(s1.isEmpty() || s2.isEmpty())
        {
            Toast.makeText(loginActivity.this, "Provide necessary information to login", Toast.LENGTH_LONG).show();
        }
        else {

            p.setMessage("Please Wait....");
            p.show();

            StringRequest s3 = new StringRequest(Request.Method.POST, constants.luser_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    p.dismiss();


                    try {
                        JSONObject j = new JSONObject(response);
                        if(!j.getBoolean("estat"))
                        {
                            sharedPrefManager.getInstance(getApplicationContext()).userLogin(j.getString("name"),j.getString("email_id"));
                            Toast.makeText(loginActivity.this, j.getString("info"), Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(loginActivity.this, j.getString("info"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(loginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    p.hide();
                    Toast.makeText(loginActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> m = new HashMap<>();
                    m.put("email_id", s1);
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
        setContentView(R.layout.activity_login);
    }
}
