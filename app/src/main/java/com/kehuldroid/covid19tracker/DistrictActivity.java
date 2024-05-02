package com.kehuldroid.covid19tracker;

import android.os.Bundle;
import android.widget.TextView;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DistrictActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView statename;
    private DistrictAdapter adapter;
    private List<DistrictModel> districtList , searchList;

    private static final String URL = "https://data.covid19india.org/state_district_wise.json";
    private RequestQueue requestQueue;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);

        statename=findViewById(R.id.statename);
        searchView = findViewById(R.id.sv);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        districtList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        String stateName = getIntent().getStringExtra("stateName");
        statename.setText(""+stateName+"");


        if (stateName != null) {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject stateData = response.getJSONObject(stateName);
                                JSONObject districtData = stateData.getJSONObject("districtData");
                                Iterator<String> keys = districtData.keys();
                                while (keys.hasNext()) {
                                    String districtName = keys.next();
                                    int activeCases = districtData.getJSONObject(districtName).getInt("active");
                                    int confirmedCases = districtData.getJSONObject(districtName).getInt("confirmed");
                                    int decreasedCases = districtData.getJSONObject(districtName).getInt("deceased");
                                    int recoveredCases = districtData.getJSONObject(districtName).getInt("recovered");
                                    districtList.add(new DistrictModel(districtName, activeCases,confirmedCases,decreasedCases,recoveredCases));
                                }
                                adapter = new DistrictAdapter(DistrictActivity.this, districtList);
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList = new ArrayList<>();
                if(query.length()>0){
                    for(int i=0 ; i<districtList.size() ; i++){
                        if(districtList.get(i).getDistrictName().toUpperCase().contains(query.toUpperCase())){
                            DistrictModel dm = new DistrictModel(districtList.get(i).getDistrictName() , districtList.get(i).getActiveCases() , districtList.get(i).getConfirmedCases(), districtList.get(i).getDecreasedCases(), districtList.get(i).getRecoveredCases());
                            searchList.add(dm);
                        }
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(DistrictActivity.this));
                    DistrictAdapter da = new DistrictAdapter(DistrictActivity.this , searchList);
                    recyclerView.setAdapter(da);

                }else{
                    recyclerView.setLayoutManager(new LinearLayoutManager(DistrictActivity.this));
                    DistrictAdapter da = new DistrictAdapter(DistrictActivity.this , districtList);
                    recyclerView.setAdapter(da);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList = new ArrayList<>();
                if(newText.length()>0){
                    for(int i=0 ; i<districtList.size() ; i++){
                        if(districtList.get(i).getDistrictName().toUpperCase().contains(newText.toUpperCase())){
                            DistrictModel dm = new DistrictModel(districtList.get(i).getDistrictName() , districtList.get(i).getActiveCases() , districtList.get(i).getConfirmedCases(), districtList.get(i).getDecreasedCases(), districtList.get(i).getRecoveredCases());
                            searchList.add(dm);
                        }
                    }
                    recyclerView.setLayoutManager(new LinearLayoutManager(DistrictActivity.this));
                    DistrictAdapter da = new DistrictAdapter(DistrictActivity.this , searchList);
                    recyclerView.setAdapter(da);

                }else{
                    recyclerView.setLayoutManager(new LinearLayoutManager(DistrictActivity.this));
                    DistrictAdapter da = new DistrictAdapter(DistrictActivity.this , districtList);
                    recyclerView.setAdapter(da);
                }
                return false;
            }
        });


    }
}
