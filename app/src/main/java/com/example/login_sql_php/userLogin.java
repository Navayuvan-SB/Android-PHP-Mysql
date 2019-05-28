package com.example.login_sql_php;

import android.app.ProgressDialog;
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

public class userLogin extends AppCompatActivity {

    EditText logUserName, logPassword;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);


        logUserName = findViewById(R.id.logUserName);
        logPassword = findViewById(R.id.logPassword);

        findViewById(R.id.logToReg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(userLogin.this, MainActivity.class));


                progressDialog = new ProgressDialog(userLogin.this);
                progressDialog.setMessage("Please wait..");


            }
        });
    }

    private void userLogin(){
        final String username = logUserName.getText().toString().trim();
        final String password = logPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            System.out.println(response);
                            if (!(obj.getBoolean("error"))){

                                SharedPrefmanager.getInstance(getApplicationContext())
                                        .userLogin(
                                                obj.getInt("id"),
                                                obj.getString("username"),
                                                obj.getString("email")
                                        );

                                Toast.makeText(
                                        userLogin.this,
                                        "User login successful",
                                        Toast.LENGTH_LONG
                                ).show();
                                startActivity(new Intent(userLogin.this, Profile.class));
                            }else{
                                Toast.makeText(
                                        userLogin.this,
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
                                userLogin.this,
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("username", username);
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
