package com.example.login_sql_php;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

public class shopLogin extends AppCompatActivity {

    private EditText logId, logPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_login);

        logId = findViewById(R.id.shopLoginId);
        logPassword = findViewById(R.id.shopLoginPassword);


    }

    private void userLogin(){
        final String id = logId.getText().toString().trim();
        final String password = logPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_SHOPLOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            System.out.println(response);
                            if (!(obj.getBoolean("error"))){

                                SharedPrefmanager.getInstance(getApplicationContext())
                                        .shopLogin(
                                                obj.getString("id"),
                                                obj.getString("ownerName"),
                                                obj.getString("contactNumber")
                                        );

                                Toast.makeText(
                                        shopLogin.this,
                                        "Shop login successful",
                                        Toast.LENGTH_LONG
                                ).show();
                                startActivity(new Intent(shopLogin.this, Profile.class));
                            }else{
                                Toast.makeText(
                                        shopLogin.this,
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();

                            }
                        }catch (JSONException e){

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(
                                shopLogin.this,
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("password", password);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void Login(View view) {
        userLogin();
    }
}
