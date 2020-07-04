package com.example.covid_19;

import java.util.ArrayList;
import java.util.List;

public class CountryData {

//    {
//        "updated":1589020011636,
//            "country":"India",
//            "countryInfo":{
//        "_id":356,
//                "iso2":"IN",
//                "iso3":"IND",
//                "lat":20,
//                "long":77,
//                "flag":"https://disease.sh/assets/img/flags/in.png"
//    },
//        "cases":59765,
//            "todayCases":70,
//            "deaths":1986,
//            "todayDeaths":1,
//            "recovered":17897,
//            "active":39882,
//            "critical":0,
//            "casesPerOneMillion":43,
//            "deathsPerOneMillion":1,
//            "tests":1523213,
//            "testsPerOneMillion":1104,
//            "continent":"Asia"
//    }

    private String country;
    private String flag;
    private String cases;
    private String todayCases;
    private String deaths;
    private String todayDeaths;
    private String recovered;
    private String todayRecovered;
    private String active;
//    private List<String> countryList;

    public CountryData(String country, String flag, String cases, String todayCases, String deaths, String todayDeaths, String recovered, String todayRecovered, String active/*, String individualCountryName,int index*/) {
        this.country = country;
        this.flag = flag;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.active = active;
        this.todayRecovered = todayRecovered;
//        countryList.add(index,individualCountryName);

    }

//    public List<String> getCountryList() {
//        return countryList;
//    }

    public String getCountry() {
        return country;
    }

    public String getFlag() {
        return flag;
    }

    public String getCases() {
        return cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public String getActive() {
        return active;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }


}
