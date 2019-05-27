package com.example.login_sql_php;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {



    EditText regUserName, regPassword, regEmail;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (SharedPrefmanager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, Profile.class));
            return;
        }

        progressDialog = new ProgressDialog(this);

        regUserName = findViewById(R.id.regUserName);
        regPassword = findViewById(R.id.regPassword);
        regEmail = findViewById(R.id.regEmail);

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regUser();
            }
        });

        findViewById(R.id.regToLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

    }

    private void regUser() {

        progressDialog.setMessage("Please wait");

        final String username = regUserName.getText().toString().trim();
        final String password = regPassword.getText().toString().trim();
        final String email = regEmail.getText().toString().trim();

        if (!(username.isEmpty())){

            if (!(password.isEmpty())){

                if (!(email.isEmpty())){

                    progressDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            Constants.URL_REGISTER,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                        progressDialog.dismiss();
                                        startActivity(new Intent(MainActivity.this, Profile.class));

                                    } catch (JSONException e) {
                                        e.printStackTrace();

                                        System.out.println(e);
                                        progressDialog.dismiss();
                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    progressDialog.dismiss();

                                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                                    System.out.println(error.getMessage());
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("username", username);
                            params.put("password", password);
                            params.put("email", email);
                            return  params;

                        }
                    };

                    RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

                }else{
                    regEmail.setError("Enter your Email ID");
                }

            }else{
                regPassword.setError("Enter your Password");
            }

        }else{
            regUserName.setError("Enter Username");
        }

    }
}
