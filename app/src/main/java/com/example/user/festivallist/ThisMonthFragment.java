package com.example.user.festivallist;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * Created by user on 08-04-2018.
 */

public class ThisMonthFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<FestivalList> festivalLists=new ArrayList<>();;
    private RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
    private ProgressDialog pDialog;
    public ThisMonthFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("fragment created","this month");
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        View view = inflater.inflate(R.layout.thismonth_frag,container,false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        if(recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        festivalData();
        return view;
    }
    public void festivalData(){
        showpDialog();
        String URL ="http://api.shortfundly.com/festivals/searchByDate?type=thismonth";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                       JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("results");
                        HashSet<String> myMap = new HashSet<String>();//hashmap for unique values
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                                if (myMap.add(object.getString("pk_id")))//cheking for duplicates with id as primary keey
                                {
                                    Log.e("Name", object.getString("headline"));
                                    Log.e("Id", object.getInt("pk_id") + " ");
                                    FestivalList list = new FestivalList(object.getString("theme"),
                                            object.getString("contact_address"),
                                            object.getString("entry_fees"),object.getString("event_date"),
                                    object.getString("email"),object.getString("timeline_img"));
                                    festivalLists.add(list);
                                } else {
                                    Log.e("Item ", "Already present" + object.getInt("id"));
                                }
                        }


                    //creating custom adapter
                    RecyclerAdapter adapter = new RecyclerAdapter(getContext(),festivalLists);
                    //adding adapter to recycler view
                    recyclerView.setAdapter(adapter);


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
            HashMap < String, String > headers = new HashMap< String, String >();
            headers.put("X-Api-Key","9e6ef43dbc0ed1bc1005014515cabaa3c4a2ae15");

            return headers;
        }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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
