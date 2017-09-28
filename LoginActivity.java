package com.apkglobal.tiffin7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements VolleyInterface {

    private EditText inputPhone, inputPassword;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    String loginurl = "http://18.220.195.13/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputPhone = (EditText) findViewById(R.id.phone);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = inputPhone.getText().toString();
                final String password = inputPassword.getText().toString();


                if (TextUtils.isEmpty(mobile)) {
                    Toast.makeText(getApplicationContext(), "Enter phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }


                Log.d("MMM", "hello");
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("mobile", mobile);
                map.put("password", password);
                VolleyClass volleyClass = new VolleyClass(LoginActivity.this, getApplicationContext());
                volleyClass.makeRequest(loginurl, map);

                Log.d("MMM", "world");
            }


        });
    }

    @Override
    public void onTaskComplete(String result) {
        Log.d("MMM", result);
        try {
            JSONObject jsonObject = new JSONObject(result);
            if(jsonObject.optString("success").equals("1"))
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            else{
                Log.d("MMM", "LOGIN ERROR");
            }

        } catch (Exception e) {
        }
    }
}

