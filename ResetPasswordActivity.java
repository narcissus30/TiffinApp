package com.apkglobal.tiffin7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;


public class ResetPasswordActivity extends AppCompatActivity implements VolleyInterface {

    private EditText inputPhone;
    private Button btnReset, btnBack;
    private ProgressBar progressBar;
    String resetpasswordurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        inputPhone = (EditText) findViewById(R.id.phone);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        btnBack = (Button) findViewById(R.id.btn_back);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = inputPhone.getText().toString().trim();

                if (TextUtils.isEmpty(mobile)) {
                    Toast.makeText(getApplication(), "Enter your registered phone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                Log.d("MMM", "hello");
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("mobile", mobile);
                // map.put("password", password);
                VolleyClass volleyClass = new VolleyClass(ResetPasswordActivity.this, getApplicationContext());
                volleyClass.makeRequest(resetpasswordurl, map);

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
    }
}
