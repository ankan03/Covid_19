package com.example.covid_19;


//{
//        "state": "Andaman and Nicobar Islands",
//        "statecode": "AN",
//        "districtData": [
//        {
//        "district": "North and Middle Andaman",
//        "notes": "",
//        "active": 0,
//        "confirmed": 1,
//        "deceased": 0,
//        "recovered": 1,
//        "delta": {
//        "confirmed": 0,
//        "deceased": 0,
//        "recovered": 0
//        }
//        },
//        {
//        "district": "South Andaman",
//        "notes": "",
//        "active": 0,
//        "confirmed": 32,
//        "deceased": 0,
//        "recovered": 32,
//        "delta": {
//        "confirmed": 0,
//        "deceased": 0,
//        "recovered": 0
//        }
//        }
//        ]
//        }
public class StateData {
    private String state;
    private String statecode;
    private String district;
    private String confirmed;
    private String todayConfirmed;
    private String deceased;
    private String todayDeceased;
    private String recovered;
    private String todayRecovered;

    public StateData(String state, String statecode, String district, String confirmed, String todayConfirmed, String deceased, String todayDeceased, String recovered, String todayRecovered) {
        this.state = state;
        this.statecode = statecode;
        this.district = district;
        this.confirmed = confirmed;
        this.todayConfirmed = todayConfirmed;
        this.deceased = deceased;
        this.todayDeceased = todayDeceased;
        this.recovered = recovered;
        this.todayRecovered = todayRecovered;
    }

    public String getState() {
        return state;
    }

    public String getStatecode() {
        return statecode;
    }

    public String getDistrict() {
        return district;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getTodayConfirmed() {
        return todayConfirmed;
    }

    public String getDeceased() {
        return deceased;
    }

    public String getTodayDeceased() {
        return todayDeceased;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }
}
