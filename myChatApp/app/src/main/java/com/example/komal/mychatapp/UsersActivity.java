package com.example.komal.mychatapp;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsersActivity extends AppCompatActivity{

    TextView noUsersText;
    int totalUsers = 0;
    ProgressDialog pd;

    private RecyclerView mRecycler;
    private GridLayoutManager mLayoutManager;
    private UserAdapter mAdapter;
    private List<userDetails> data = new ArrayList<>();
    private userDetails UserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        noUsersText =findViewById(R.id.noUsersText);
        mRecycler= findViewById(R.id.recycler_view);

        pd = new ProgressDialog(UsersActivity.this);
        pd.setMessage("Loading...");
        pd.show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarLogin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MentorMe");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.Black, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.Black));
        }
        String url = "https://mychatapp-268ff.firebaseio.com/users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(UsersActivity.this);
        rQueue.add(request);

        mLayoutManager = new GridLayoutManager(this, 1);
        mRecycler.setLayoutManager(mLayoutManager);

        mAdapter = new UserAdapter(this, data);
        mRecycler.setAdapter(mAdapter);

    }

    public void doOnSuccess(String s){
        Log.e("res output", "doOnSuccess: "+s);
        try {

            JSONObject res = new JSONObject(s);
       //     JSONObject res1 = new JSONObject(s);
            Log.e("res output", "doOnSuccess1: "+res);

            Iterator<String> keys = res.keys();
            while( keys.hasNext() ) {
                String key = keys.next();


                    JSONObject object = res.getJSONObject(key);
                    String desn=object.getString("designation");
                Log.e("res output", "doOnSuccess: "+object.getString("designation"));

                    UserData =new userDetails(key,desn);
                    if(!key.equals(UsersDetailsActivity.username)) {
                        data.add(UserData);

                     }

                    totalUsers++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
       // Log.e("res output", "doOnSuccess: "+data.get(1).getUsername());
        if(totalUsers <=1){
            noUsersText.setVisibility(View.VISIBLE);
            mRecycler.setVisibility(View.GONE);
        }
        else{
            noUsersText.setVisibility(View.GONE);
            mRecycler.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
        pd.dismiss();
    }

}
