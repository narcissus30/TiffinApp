package com.apkglobal.tiffin7;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class BreakfastActivity extends AppCompatActivity implements VolleyInterface {

    ImageView imageViewbreakfast;
    Button btnbreakfast;
    ProgressBar progressBar;
    String url = "http://18.220.195.13/api/breakfast";
    ProgressDialog loading;
    TextView tv_b_name, tv_b_des, tv_b_dailyprice, tv_b_weeklyprice, tv_b_monthlyprice;
    LinearLayout linearLayout_prev, linearLayout_breakfastdetails;
    RecyclerView recyclerView_breakfast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);

        linearLayout_prev = (LinearLayout) findViewById(R.id.linearLayout_prev);
        linearLayout_breakfastdetails = (LinearLayout) findViewById(R.id.linearLayout_breakfastdetails);

        TextView tv_b_name = (TextView) findViewById(R.id.tv_b_name);
        TextView tv_b_des = (TextView) findViewById(R.id.tv_b_des);
        TextView tv_b_dailyprice = (TextView) findViewById(R.id.tv_b_dailyprice);
        TextView tv_b_weeklyprice = (TextView) findViewById(R.id.tv_b_weeklyprice);
        TextView tv_b_monthlyprice = (TextView) findViewById(R.id.tv_b_monthlyprice);


        imageViewbreakfast = (ImageView) findViewById(R.id.imageViewBreakfast);
        btnbreakfast = (Button) findViewById(R.id.btnbreakfast);
        recyclerView_breakfast = (RecyclerView) findViewById(R.id.recyclerView_breakfast);


        btnbreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MMM", "again");
                getbreakfastdetails();
            }
        });
    }

    private void getbreakfastdetails() {
        Log.d("MMM", "hello");
        VolleyClass volleyClass = new VolleyClass(this, getApplicationContext());
        volleyClass.makeRequest(url, new HashMap<String, String>());
        Log.d("MMM", "world");
    }


    @Override
    public void onTaskComplete(String result) {
        Log.d("MMM", result);
      /*  linearLayout_prev.setVisibility(View.GONE);
        linearLayout_breakfastdetails.setVisibility(View.VISIBLE);*/

        ArrayList<ModelFood> food = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < result.length(); i++) {
                JSONObject json_data = jsonArray.getJSONObject(i);

                ModelFood foodlist = new ModelFood();
                foodlist.food_name = json_data.getString("name");
                foodlist.food_des = json_data.getString("description");
                foodlist.food_dailyprice = json_data.getString("dailyprice");
                foodlist.food_weeklyprice = json_data.getString("weeklyprice");
                foodlist.food_monthlyprice = json_data.getString("monthlyprice");
                food.add(foodlist);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView_breakfast.setLayoutManager(layoutManager);
                recyclerView_breakfast.setAdapter(new RecyclerViewAdapterFoodDetails(this, food));
                //setListViewHeightBasedOnChildren(ListViewStores);
                Log.d("MMM", json_data.optString("K"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}



