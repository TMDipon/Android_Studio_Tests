package com.example.dell.login_2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public void register(View v)
    {
        EditText t1 = (EditText)findViewById(R.id.username);
        EditText t2 = (EditText)findViewById(R.id.password);
        EditText t3 = (EditText)findViewById(R.id.email);
        EditText t4 = (EditText)findViewById(R.id.phone);

        final String s1 = t1.getText().toString().trim();
        final String s2 = t2.getText().toString().trim();
        final String s3 = t3.getText().toString().trim();
        final String s5 = t4.getText().toString();

        final ProgressDialog p = new ProgressDialog(this);

        p.setMessage("Registering User");
        p.show();

        StringRequest s4 = new StringRequest(Request.Method.POST, constants.cuser_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                p.dismiss();


                try {
                    JSONObject j = new JSONObject(response);
                    Toast.makeText(MainActivity.this, j.getString("info"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

                //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                p.hide();
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> m = new HashMap<>();
                m.put("name",s1);
                m.put("email_id",s3);
                m.put("password",s2);
                return m;
            }
        };

        RequestQueue r = Volley.newRequestQueue(this);
        r.add(s4);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
