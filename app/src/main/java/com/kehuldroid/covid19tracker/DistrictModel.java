package com.kehuldroid.covid19tracker;

public class DistrictModel {
    private String districtName;
    private int activeCases;
    private int confirmedCases;
    private int decreasedCases;
    private int recoveredCases;


    public DistrictModel(String districtName, int activeCases, int confirmedCases, int decreasedCases, int recoveredCases) {
        this.districtName = districtName;
        this.activeCases = activeCases;
        this.confirmedCases = confirmedCases;
        this.decreasedCases = decreasedCases;
        this.recoveredCases = recoveredCases;
    }

    public String getDistrictName() {
        return districtName;
    }

    public int getActiveCases() {
        return activeCases;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public void setActiveCases(int activeCases) {
        this.activeCases = activeCases;
    }

    public int getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(int confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    public int getDecreasedCases() {
        return decreasedCases;
    }

    public void setDecreasedCases(int decreasedCases) {
        this.decreasedCases = decreasedCases;
    }

    public int getRecoveredCases() {
        return recoveredCases;
    }

    public void setRecoveredCases(int recoveredCases) {
        this.recoveredCases = recoveredCases;
    }
}
