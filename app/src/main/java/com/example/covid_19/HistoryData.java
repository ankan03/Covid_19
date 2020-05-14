package com.example.covid_19;

public class HistoryData {

//			        "an": "0",
//                    "ap": "1",
//                    "ar": "0",
//                    "as": "0",
//                    "br": "0",
//                    "ch": "0",
//                    "ct": "0",
//                    "date": "14-Mar-20",
//                    "dd": "0",
//                    "dl": "7",
//                    "dn": "0",
//                    "ga": "0",
//                    "gj": "0",
//                    "hp": "0",
//                    "hr": "14",
//                    "jh": "0",
//                    "jk": "2",
//                    "ka": "6",
//                    "kl": "19",
//                    "la": "0",
//                    "ld": "0",
//                    "mh": "14",
//                    "ml": "0",
//                    "mn": "0",
//                    "mp": "0",
//                    "mz": "0",
//                    "nl": "0",
//                    "or": "0",
//                    "pb": "1",
//                    "py": "0",
//                    "rj": "3",
//                    "sk": "0",
//                    "status": "Confirmed",
//                    "tg": "1",
//                    "tn": "1",
//                    "tr": "0",
//                    "tt": "81",
//                    "up": "12",
//                    "ut": "0",
//                    "wb": "0"


    private String selectedState;
    private String date;
    private String status;
    private String noOfIssue;

    public HistoryData(String selectedState, String date, String status, String noOfIssue) {
        this.selectedState = selectedState;
        this.date = date;
        this.status = status;
        this.noOfIssue = noOfIssue;
    }

    public String getSelectedState() {
        return selectedState;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getNoOfIssue() {
        return noOfIssue;
    }
}
