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

public class adminLogin extends AppCompatActivity {

    EditText shopName, seatCapacity, openingTime, closingTime, leaveDays, ownerName, contactNumber, haircut, shaving, hairshave, headshave, hairchild;
    String id, pricing, shopNameS, seatCapacityS, openingTimeS, closingTimeS, leaveDaysS, ownerNameS, contactNumberS, haircutS, shavingS, hairshaveS, headshaveS, hairchildS, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        shopName = findViewById(R.id.adminShopName);
        seatCapacity = findViewById(R.id.adminSeatcapacity);
        openingTime = findViewById(R.id.adminOpeningTime);
        closingTime = findViewById(R.id.adminClosingTime);
        leaveDays = findViewById(R.id.adminleaveDays);
        ownerName = findViewById(R.id.adminOwnerName);
        contactNumber = findViewById(R.id.adminContactNumber);
        haircut = findViewById(R.id.haircutET);
        shaving = findViewById(R.id.shavingET);
        hairshave = findViewById(R.id.hairShaveET);
        headshave = findViewById(R.id.headShaveET);
        hairchild = findViewById(R.id.hairChildET);




        findViewById(R.id.adminSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            shopReg();

            }
        });

    }

    private void shopReg() {

        shopNameS = shopName.getText().toString().trim();
        seatCapacityS = seatCapacity.getText().toString().trim();
        openingTimeS = openingTime.getText().toString().trim();
        closingTimeS = closingTime.getText().toString().trim();
        leaveDaysS = leaveDays.getText().toString().trim();
        ownerNameS = ownerName.getText().toString().trim();
        contactNumberS = contactNumber.getText().toString().trim();
        haircutS = haircut.getText().toString().trim();
        shavingS = shaving.getText().toString().trim();
        hairshaveS = hairshave.getText().toString().trim();
        headshaveS = headshave.getText().toString().trim();
        hairchildS = hairchild.getText().toString().trim();

        id = genID(contactNumberS, ownerNameS, shopNameS);

        System.out.println(id);

        pricing = "Haircut-" + haircutS + ", " + "Shaving-" + shavingS + ", " + "Haircut+Shaving-" + hairshaveS + ", " + "Head Shaving-" + headshaveS + ", "  + "Haircut Child-" + hairchildS;

        System.out.println(pricing);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_ADMIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {

                            e.printStackTrace();

                            System.out.println(e);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        System.out.println(error.getMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("password", id);
                params.put("shopName", shopNameS);
                params.put("seatCapacity", seatCapacityS);
                params.put("openingTime", openingTimeS);
                params.put("closingTime", closingTimeS);
                params.put("leaveDay", leaveDaysS);
                params.put("ownerName", ownerNameS);
                params.put("contactNumber", contactNumberS);
                params.put("Pricing", pricing);
                return  params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);




    }

    private String genID(String contactNo, String Name, String ShopName){

        String id;

        id = contactNo.substring(contactNo.length() - 3, contactNo.length() - 1) + Name.substring(0, 2) + ShopName.substring(0, 2);

        return id;
    }
}
