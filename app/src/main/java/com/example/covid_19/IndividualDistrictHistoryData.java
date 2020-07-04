package com.example.covid_19;


public class IndividualDistrictHistoryData {
    private String Confirmed;
    private String Deaths;
    private String Recovered;
    private String Date;
    private String Active;

    public IndividualDistrictHistoryData(String confirmed, String deaths, String recovered, String date, String active) {
        Confirmed = confirmed;
        Deaths = deaths;
        Recovered = recovered;
        Date = date;
        Active = active;
    }

    public String getConfirmed() {
        return Confirmed;
    }

    public String getDeaths() {
        return Deaths;
    }

    public String getRecovered() {
        return Recovered;
    }

    public String getDate() {
        return Date;
    }

    public String getActive() {
        return Active;
    }
}

