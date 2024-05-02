package com.kehuldroid.covid19tracker;

import java.util.HashMap;

public class StateModel {
    private String stateName;

    private HashMap<String ,Integer> districtData;

    public StateModel(String stateName, HashMap<String, Integer> districtData) {
        this.stateName = stateName;
        this.districtData = districtData;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public HashMap<String, Integer> getDistrictData() {
        return districtData;
    }

    public void setDistrictData(HashMap<String, Integer> districtData) {
        this.districtData = districtData;
    }
}
