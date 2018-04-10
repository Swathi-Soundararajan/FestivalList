package com.example.user.festivallist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {
   TextView name,add,pho,ema,opdate,endate;
   Button share;
   String name1,add1,pho1,ema1,opdate1,endate1,shareurl;
   ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading");
        pDialog.setCancelable(false);
        name = (TextView) findViewById(R.id.det_name);
        ema= (TextView) findViewById(R.id.emailid);
        add = (TextView)findViewById(R.id.address);
        pho = (TextView)findViewById(R.id.phone);
        opdate = (TextView)findViewById(R.id.opendate);
        endate = (TextView)findViewById(R.id.extedate);
        share = (Button) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,name1);
                sendIntent.putExtra(Intent.EXTRA_TEXT,shareurl);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,"Share using"));
            }
        });
        loadData();
    }

    public void loadData(){
        showpDialog();
        String URL ="http://api.shortfundly.com/festivals/searchByDate?type=thismonth";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                            Log.e("Name", object.getString("headline"));
                            Log.e("Id", object.getInt("pk_id") + " ");
                            name1 = object.getString("theme");
                            add1= object.getString("contact_address");
                            pho1=object.getString("phone");
                            opdate1 = object.getString("opening_date");
                            endate1 = object.getString("extended_deadline");
                            shareurl=object.getString("sharableurl");
                    }
                    name.setText(name1);
                    add.setText(add1);
                    pho.setText(pho1);
                    opdate.setText(opdate1);
                    endate.setText(endate1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Month Fragment"," "+error.getMessage());
            }
        }) {@Override
        public Map< String, String > getHeaders() throws AuthFailureError {
            HashMap< String, String > headers = new HashMap< String, String >();
            headers.put("X-Api-Key","9e6ef43dbc0ed1bc1005014515cabaa3c4a2ae15");

            return headers;
        }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
