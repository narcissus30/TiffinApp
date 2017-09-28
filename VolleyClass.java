package com.apkglobal.tiffin7;

/**
 * Created by Acer on 17-08-2017.
 */

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;





public class VolleyClass {


    private Context activityContext;
    //private Context applicationContext;
    private VolleyInterface resultInterface;
    Map<String, String> params;
    RequestQueue queue;


    public VolleyClass(Context c1, Context c2) {
        activityContext = c1;
        //applicationContext = c2;
        this.resultInterface = (VolleyInterface) c1;
    }

    public VolleyClass(Context activityContext) {
        this.activityContext = activityContext;
        //applicationContext = c2;
        this.resultInterface = (VolleyInterface) activityContext;
    }

    public VolleyClass(Fragment f, Context c1, Context c2) {
        activityContext = c1;
        //applicationContext = c2;
        this.resultInterface = (VolleyInterface) f;
    }

    public void makeRequest(String url, Map<String, String> parameters) {
        params = parameters;
        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(activityContext);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        resultInterface.onTaskComplete(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        resultInterface.onTaskComplete("Unexpected Error!" + error.toString());
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        stringRequest.setTag("request");

        int socketTimeout = 20000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, -1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);
    }

    public void makeRequestGet(String url, Map<String, String> parameters) {
        params = parameters;
        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(activityContext);
        // Request a string response from the provided URL.
        String values = "";
        Uri.Builder uri = Uri.parse(url).buildUpon();
        for (String key : parameters.keySet()) {
            uri.appendQueryParameter(key, parameters.get(key));
        }
        values = uri.build().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, values,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("DHU", "In volley class: " + response);
                        // Display the first 500 characters of the response string.
                        resultInterface.onTaskComplete(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("DHU", error.toString());
                        resultInterface.onTaskComplete("Unexpected Error!" + error.toString());
                        error.printStackTrace();
                    }
                }) {
        };
        stringRequest.setTag("request");
        // Add the request to the RequestQueue.
        int socketTimeout = 20000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, -1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);
    }

    public void cancelAllRequest() {
        if (queue != null)
            queue.cancelAll("request");
    }
}

