package com.apkglobal.tiffin7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity implements VolleyInterface {

    private EditText inputPhone, inputPassword, inputName, inputEmail, et_otp;
    private TextView tv_otp;
    private Button btnSignIn, btnSignUp, btnResetPassword, btn_otp;
    private ProgressBar progressBar;
    LinearLayout linearLayout_otp;
    RelativeLayout relativeLayout_otp;
    String registerurl = "http://18.220.195.13/api/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPhone = (EditText) findViewById(R.id.phone);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        et_otp = (EditText) findViewById(R.id.et_otp);
        tv_otp = (TextView) findViewById(R.id.tv_otp);
        btn_otp = (Button) findViewById(R.id.btn_otp);
        linearLayout_otp = (LinearLayout) findViewById(R.id.linearLayout_otp);
        relativeLayout_otp = (RelativeLayout) findViewById(R.id.relativeLayout_otp);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = inputName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String mobile = inputPhone.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();


                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter your username!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Email-Id!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(mobile)) {
                    Toast.makeText(getApplicationContext(), "Enter phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 4) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 4 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);

                Log.d("MMM", "hello");
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("name", name);
                map.put("email", email);
                map.put("mobile", mobile);
                map.put("password", password);
                VolleyClass volleyClass = new VolleyClass(SignupActivity.this, getApplicationContext());
                volleyClass.makeRequest(registerurl, map);
                Log.d("MMM", "world");
            }
        });
    }

    @Override
    public void onTaskComplete(String result) {
        Log.d("MMM", result);
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < result.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                Log.d("MMM", jsonObject1.optString("K"));
            }

        } catch (Exception e) {
        }

        relativeLayout_otp.setVisibility(View.GONE);
        linearLayout_otp.setVisibility(View.VISIBLE);


        btn_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}


