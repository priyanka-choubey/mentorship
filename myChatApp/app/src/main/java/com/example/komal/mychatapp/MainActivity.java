package com.example.komal.mychatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecycler;
    private GridLayoutManager mLayoutManager;
    private portalAdapter mAdapter;
    private List<portalDetails> data = new ArrayList<>();
    private portalDetails UserData;
    private String question,user;

    TextView userName,Degn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UserData = new portalDetails("Komal","Coding","Program to add two numbers?");
        data.add(UserData);

        UserData = new portalDetails("Aditi","Maths","What is the formula to find factorial of a number?");
        data.add(UserData);

        UserData = new portalDetails("CrazyM","Maths","What is 15 out of 25 as percentage?");
        data.add(UserData);

        UserData = new portalDetails("Komal","Maths","If a warehouse received 250 orders in April and 300 in May, what was the percentage of increase in orders from April to May?");
        data.add(UserData);
        UserData = new portalDetails("Aditi","Physics","How long can you survive in space without spacesuit?");
        data.add(UserData);

        mRecycler=findViewById(R.id.recycle);

        mLayoutManager = new GridLayoutManager(this, 1);
        mRecycler.setLayoutManager(mLayoutManager);

        mAdapter = new portalAdapter(this, data);
        mRecycler.setAdapter(mAdapter);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,UsersActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        userName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.Nav_Name);
        Degn = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_degn);

        Log.e("In start", "doOnSuccess: " + userName.getText());

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

        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_ques) {


        } else if (id == R.id.starred) {

        } else if (id == R.id.profile) {
            startActivity(new Intent(this,ProfileActivity.class));

        } else if (id == R.id.add_ques) {

             addQuestionDialog();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addQuestionDialog() {
        DialogFragment newFragment = new NewQuestionActivity();
        newFragment.show(getSupportFragmentManager(), "newQues");


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
                String city = object.getString("designation");
               Log.e("res output", "doOnSuccess: " + userName.getText());

                if (key.equals(UsersDetailsActivity.username)) {
                    if(key!=null)
                    userName.setText(key);
                    if(city!=null)
                    Degn.setText(city);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void Connect(View v){
        startActivity(new Intent(MainActivity.this,UsersActivity.class));
    }
}
