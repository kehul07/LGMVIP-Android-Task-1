package com.kehuldroid.covid19tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kehuldroid.covid19tracker.DistrictActivity;
import com.kehuldroid.covid19tracker.R;
import com.kehuldroid.covid19tracker.StateAdapter;
import com.kehuldroid.covid19tracker.StateModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StateAdapter adapter;
    private List<StateModel> stateList , searchList;
    private RequestQueue requestQueue;
    private SearchView sv;
    private static final String URL = "https://data.covid19india.org/state_district_wise.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sv = findViewById(R.id.sv);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        stateList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        fetchData();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList = new ArrayList<>();
                if(query.length()>0){
                    for(int i=0 ; i<stateList.size() ; i++){
                        if(stateList.get(i).getStateName().toUpperCase().contains(query.toUpperCase())){
                            StateModel sm = new StateModel(stateList.get(i).getStateName() , stateList.get(i).getDistrictData());
                            searchList.add(sm);
                        }
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    StateAdapter sa = new StateAdapter(MainActivity.this ,searchList,new StateAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(String stateName) {
                            // Handle item click
                            Intent intent = new Intent(MainActivity.this, DistrictActivity.class);
                            intent.putExtra("stateName", stateName);
                            startActivity(intent);
                        }
                        });
                    recyclerView.setAdapter(sa);

                }else{
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    StateAdapter sa = new StateAdapter(MainActivity.this ,stateList,new StateAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(String stateName) {
                            // Handle item click
                            Intent intent = new Intent(MainActivity.this, DistrictActivity.class);
                            intent.putExtra("stateName", stateName);
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(sa);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList = new ArrayList<>();
                if(newText.length()>0){
                    for(int i=0 ; i<stateList.size() ; i++){
                        if(stateList.get(i).getStateName().toUpperCase().contains(newText.toUpperCase())){
                            StateModel sm = new StateModel(stateList.get(i).getStateName() , stateList.get(i).getDistrictData());
                            searchList.add(sm);
                        }
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    StateAdapter sa = new StateAdapter(MainActivity.this ,searchList,new StateAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(String stateName) {
                            // Handle item click
                            Intent intent = new Intent(MainActivity.this, DistrictActivity.class);
                            intent.putExtra("stateName", stateName);
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(sa);

                }else{
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    StateAdapter sa = new StateAdapter(MainActivity.this ,stateList,new StateAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(String stateName) {
                            // Handle item click
                            Intent intent = new Intent(MainActivity.this, DistrictActivity.class);
                            intent.putExtra("stateName", stateName);
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(sa);

                }
                return false;
            }
        });


    }

    private void fetchData() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Iterator<String> keys = response.keys();
                            while (keys.hasNext()) {
                                String stateName = keys.next();
                                JSONObject stateData = response.getJSONObject(stateName);
                                JSONObject districtData = stateData.getJSONObject("districtData");
                                HashMap<String, Integer> districtMap = new HashMap<>();
                                Iterator<String> districtKeys = districtData.keys();
                                while (districtKeys.hasNext()) {
                                    String districtName = districtKeys.next();
                                    JSONObject district = districtData.getJSONObject(districtName);
                                    int activeCases = district.getInt("active");
                                    districtMap.put(districtName, activeCases);
                                }
                                stateList.add(new StateModel(stateName, districtMap));
                            }
                            adapter = new StateAdapter(MainActivity.this, stateList, new StateAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(String stateName) {
                                    // Handle item click
                                    Intent intent = new Intent(MainActivity.this, DistrictActivity.class);
                                    intent.putExtra("stateName", stateName);
                                    startActivity(intent);
                                }
                            });
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}
