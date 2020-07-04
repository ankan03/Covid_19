package com.example.covid_19;

public class ContractData {

    private String number;
    private String number_tollfree;
    private String email;
    private String twitter;
    private String facebook;
    private String loc;
    private String number_individual;
    private String lastRefreshed;
    private String lastOriginUpdate;

//    public ContractData(String number, String number_tollfree, String email, String twitter, String facebook, String loc, String number_individual, String lastRefreshed, String lastOriginUpdate) {
//        this.number = number;
//        this.number_tollfree = number_tollfree;
//        this.email = email;
//        this.twitter = twitter;
//        this.facebook = facebook;
//        this.loc = loc;
//        this.number_individual = number_individual;
//        this.lastRefreshed = lastRefreshed;
//        this.lastOriginUpdate = lastOriginUpdate;
//    }

    public ContractData() {
    }

    public ContractData(String loc, String number_individual) {
        this.loc = loc;
        this.number_individual = number_individual;
    }

    public ContractData(String number, String number_tollfree, String email, String twitter, String facebook, String lastRefreshed, String lastOriginUpdate) {
        this.number = number;
        this.number_tollfree = number_tollfree;
        this.email = email;
        this.twitter = twitter;
        this.facebook = facebook;
        this.lastRefreshed = lastRefreshed;
        this.lastOriginUpdate = lastOriginUpdate;
    }

    public String getNumber() {
        return number;
    }

    public String getNumber_tollfree() {
        return number_tollfree;
    }

    public String getEmail() {
        return email;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getLoc() {
        return loc;
    }

    public String getNumber_individual() {
        return number_individual;
    }

    public String getLastRefreshed() {
        return lastRefreshed;
    }

    public String getLastOriginUpdate() {
        return lastOriginUpdate;
    }
}
