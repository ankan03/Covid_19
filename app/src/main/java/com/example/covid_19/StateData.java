package com.example.covid_19;

public class StateData {
    private String confirmed;
    private String deltaconfirmed;
    private String recovered;
    private String deltarecovered;
    private String deaths;
    private String deltadeaths;
    private String state;
    private String statecode;
    private String statenotes;
    private String lastupdatedtime;
    private String active;

    public StateData(String confirmed, String deltaconfirmed, String recovered, String deltarecovered, String deaths, String deltadeaths, String state, String statecode, String statenotes, String lastupdatedtime, String active) {
        this.confirmed = confirmed;
        this.deltaconfirmed = deltaconfirmed;
        this.recovered = recovered;
        this.deltarecovered = deltarecovered;
        this.deaths = deaths;
        this.deltadeaths = deltadeaths;
        this.state = state;
        this.statecode = statecode;
        this.statenotes = statenotes;
        this.lastupdatedtime = lastupdatedtime;
        this.active = active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getDeltaconfirmed() {
        return deltaconfirmed;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getDeltarecovered() {
        return deltarecovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getDeltadeaths() {
        return deltadeaths;
    }

    public String getState() {
        return state;
    }

    public String getStatecode() {
        return statecode;
    }

    public String getStatenotes() {
        return statenotes;
    }

    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    public String getActive() {
        return active;
    }
}
