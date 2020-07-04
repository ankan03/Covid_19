package com.example.covid_19;


public class IndividualCountryHistoryData {
    private String Confirmed;
    private String Deaths;
    private String Recovered;
    private String Date;
    private String Active;

    public IndividualCountryHistoryData(String confirmed, String deaths, String recovered, String date, String active) {
        Confirmed = confirmed;
        Deaths = deaths;
        Recovered = recovered;
        Date = date;
        Active = active;
    }

    public String getActive() {
        return Active;
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
}


