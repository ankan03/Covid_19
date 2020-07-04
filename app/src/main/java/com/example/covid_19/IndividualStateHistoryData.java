package com.example.covid_19;

class IndividualStateHistoryData {
    private String date;
    private String noOfIssues;
    private String status;


    public IndividualStateHistoryData(String date, String status, String noOfIssues) {
        this.date = date;
        this.noOfIssues = noOfIssues;
        this.status = status;

    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getNoOfIssues() {
        return noOfIssues;
    }


}
