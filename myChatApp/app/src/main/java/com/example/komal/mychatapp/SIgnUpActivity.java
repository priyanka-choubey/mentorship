package com.example.komal.mychatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SIgnUpActivity extends AppCompatActivity {

    EditText username, password,designation,city;
    Button registerButton;
    String user, pass,designtn,City;
    TextView login;
    int countMentor=0, countMentee=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        designation=findViewById(R.id.designation_input);
        city = findViewById(R.id.city);
        registerButton = (Button)findViewById(R.id.registerButton);
        login = (TextView)findViewById(R.id.login);

        Firebase.setAndroidContext(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SIgnUpActivity.this, LoginActivity.class));
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();
                designtn=designation.getText().toString();
                City= city.getText().toString();

                if(user.equals("")){
                    username.setError("can't be blank");
                }
                else if(pass.equals("")){
                    password.setError("can't be blank");
                }
                else if(!user.matches("[A-Za-z0-9]+")){
                    username.setError("only alphabet or number allowed");
                }
                else if(user.length()<5){
                    username.setError("at least 5 characters long");
                }
                else if(pass.length()<5){
                    password.setError("at least 5 characters long");
                }
                else {
                    final ProgressDialog pd = new ProgressDialog(SIgnUpActivity.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    String url = "https://mychatapp-268ff.firebaseio.com/users.json";
                    String urlSneha="http://22f36594.ngrok.io/";

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {
                            Firebase reference = new Firebase("https://mychatapp-268ff.firebaseio.com/users");

                            if(s.equals("null")) {
                                reference.child(user).child("password").setValue(pass);
                                reference.child(user).child("designation").setValue(designtn);
                                reference.child(user).child("city").setValue(City);
                                    if(designtn.equals("Mentee")) {

                                        reference.child(user).child("U_Id").setValue(countMentee);
                                        countMentee++;
                                    }else {
                                        reference.child(user).child("U_Id").setValue(countMentor);
                                        countMentor++;
                                    }
                                Toast.makeText(SIgnUpActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                            }
                            else {
                                try {
                                    JSONObject obj = new JSONObject(s);

                                    if (!obj.has(user)) {
                                        reference.child(user).child("password").setValue(pass);
                                        reference.child(user).child("designation").setValue(designtn);
                                        reference.child(user).child("city").setValue(City);
                                        if(designtn.equals("Mentee")) {

                                            reference.child(user).child("U_Id").setValue(countMentee);
                                            countMentee++;
                                        }else {
                                            reference.child(user).child("U_Id").setValue(countMentor);
                                            countMentor++;
                                        }
                                        Toast.makeText(SIgnUpActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(SIgnUpActivity.this, LoginActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(SIgnUpActivity.this, "username already exists", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            pd.dismiss();
                        }

                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError );
                            pd.dismiss();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(SIgnUpActivity.this);
                    rQueue.add(request);

                    Log.e("URL SNEHA",designtn);
                    if(designtn.equals("Mentee")==true){
                        urlSneha=urlSneha.concat("mentee/");
                    }
                    else if(designtn.equals("Mentor")==true){
                        urlSneha=urlSneha.concat("mentor/");
                    }

                    Log.e("URL SNEHA",urlSneha);
                    try {
                        RequestQueue requestQueue = Volley.newRequestQueue(SIgnUpActivity.this);
                        JSONObject jsonBody = new JSONObject();
                        if(designtn.equals("Mentee")){
                            jsonBody.put("menteename", user);
                            jsonBody.put("city", City);
                            jsonBody.put("email_id","null");
                            jsonBody.put("subject_of_interest","null");
                            jsonBody.put("phone","9045245562");
                            jsonBody.put("bio","null");



                        }
                        else{
                            jsonBody.put("mentorname", user);
                            jsonBody.put("city", City);
                            jsonBody.put("email_id","null");
                            jsonBody.put("subject_of_expertise","null");
                            jsonBody.put("experience","null");
                            jsonBody.put("bio","null");
                            jsonBody.put("phone","9045245562");

                        }



                        final String requestBody = jsonBody.toString();


                        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlSneha, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("VOLLEY", response);
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    Log.e("hhhhh",obj.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("VOLLEY", error.toString());
                            }
                        }) {
                            @Override
                            public String getBodyContentType() {
                                return "application/json; charset=utf-8";
                            }

                            @Override
                            public byte[] getBody() throws AuthFailureError {
                                try {
                                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                                } catch (UnsupportedEncodingException uee) {
                                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                                    return null;
                                }
                            }

                            @Override
                            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                                String responseString = "";
                                if (response != null) {
                                    responseString = String.valueOf(response.data);
                                    Log.e("response",responseString);
                                    // can get more details such as response.headers
                                }
                                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                            }
                        };

                        requestQueue.add(stringRequest);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}