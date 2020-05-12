package com.example.covid_19;

public class WorldData {

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
        private Long recovered;
        private String active;


    public WorldData(String country, String flag,String cases, String todayCases, String deaths,String todayDeaths, Long recovered, String active) {
        this.country = country;
        this.flag = flag;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.active = active;

    }

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

    public Long getRecovered() {
        return recovered;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public String getActive() {
        return active;
    }
}
