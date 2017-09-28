package com.apkglobal.tiffin7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class LunchActivity extends AppCompatActivity implements VolleyInterface {

    ImageView imageViewlunch;
    Button btnlunch;
    String url = "http://18.220.195.13/api/lunch";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        btnlunch = (Button) findViewById(R.id.btnlunch);
        btnlunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MMM", "huha");
                getlunchdetails();
            }
        });

    }

    private void getlunchdetails() {
        Log.d("MMM", "hello");
        VolleyClass volleyClass = new VolleyClass(this, getApplicationContext());
        volleyClass.makeRequest(url, new HashMap<String, String>());
        Log.d("MMM", "world");
    }


    @Override
    public void onTaskComplete(String result) {
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
