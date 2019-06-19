package com.example.dell.foodie_buddy;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class user_activity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 234;

    public void loginActivity(View v)
    {
        startActivity(new Intent(this,loginActivity.class));
    }

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
            Toast.makeText(user_activity.this, "Provide all the information to register", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(user_activity.this, j.getString("info"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        Toast.makeText(user_activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    p.hide();
                    Toast.makeText(user_activity.this, error.getMessage(), Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.activity_user_activity);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}
