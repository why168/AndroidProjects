package com.github.why168.volleystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.why168.volley.Request;
import com.github.why168.volley.RequestQueue;
import com.github.why168.volley.Response;
import com.github.why168.volley.VolleyError;
import com.github.why168.volley.toolbox.StringRequest;
import com.github.why168.volley.toolbox.Volley;

/**
 * https://developer.android.com/training/volley/index.html
 *
 * @author Edwin.Wu
 * @version 2017/2/28 23:13
 * @since JDK1.8
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET,
                "http://v.hao123.com/dianshi/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Edwin", "response = " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Edwin", "VolleyError = " + error);
                    }
                });

        requestQueue.add(request);
    }
}
