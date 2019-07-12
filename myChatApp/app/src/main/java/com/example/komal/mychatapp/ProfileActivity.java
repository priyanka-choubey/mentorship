package com.example.komal.mychatapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ProfileActivity extends AppCompatActivity {

    private userDetails UserData;
    TextView userName, City;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_profile);

        userName = findViewById(R.id.name);
        City = findViewById(R.id.address);

        String url = "https://mychatapp-268ff.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(ProfileActivity.this);
        rQueue.add(request);
    }

    public void doOnSuccess(String s) {
        Log.e("res output", "doOnSuccess: " + s);
        try {

            JSONObject res = new JSONObject(s);
            //     JSONObject res1 = new JSONObject(s);
            Log.e("res output", "doOnSuccess1: " + res);

            Iterator<String> keys = res.keys();
            while (keys.hasNext()) {
                String key = keys.next();


                JSONObject object = res.getJSONObject(key);
                String city = object.getString("city");
                Log.e("res output", "doOnSuccess: " + object.getString("designation"));

                if (key.equals(UsersDetailsActivity.username)) {
                    userName.setText(key);
                    City.setText(city);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}