package com.example.covid_19;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class dateGenarater {

    private static String startDate, endDate;

    public dateGenarater() {

    }


    public static ArrayList<Date> generateDateBetween2Dates(String start_date, String end__date) {
        // TODO Auto-generated method stub
        ArrayList<Date> dates = new ArrayList<Date>();

        startDate = start_date;
        endDate = end__date;

        String str_date = startDate;
        String end_date = endDate;

        DateFormat formatter;

        formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = (Date) formatter.parse(str_date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = (Date) formatter.parse(end_date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
        long endTime = endDate.getTime(); // create your endtime here, possibly using Calendar or Date
        long curTime = startDate.getTime();
        while (curTime <= endTime) {
            dates.add(new Date(curTime));
            curTime += interval;
        }
//            for(int i=0;i<dates.size();i++){
//                Date lDate =(Date)dates.get(i);
//                String ds = formatter.format(lDate);
//                System.out.println(" Date is ..." + ds);
//            }

        return dates;

    }

}
