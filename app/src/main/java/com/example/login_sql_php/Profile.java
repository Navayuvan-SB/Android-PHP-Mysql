package com.example.login_sql_php;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView username, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(!(SharedPrefmanager.getInstance(this).isLoggedIn())){
            finish();
            startActivity(new Intent(this, Login.class));
        }


        username = findViewById(R.id.profileUsername);
        email = findViewById(R.id.profileEmail);

        username.setText(SharedPrefmanager.getInstance(this).getUserName());
        email.setText(SharedPrefmanager.getInstance(this).getUserEmail());

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefmanager.getInstance(Profile.this).logout();
                finish();
                startActivity(new Intent(Profile.this, Login.class));
            }
        });
    }
}
