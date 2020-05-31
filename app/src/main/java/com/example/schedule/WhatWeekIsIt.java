package com.example.schedule;

import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.nodes.Document;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;


public class WhatWeekIsIt {
    public static String recurs(String dateText, Calendar c, DateFormat dateFormat) {
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek != 2){
            try {
                c.setTime(dateFormat.parse(dateText));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, -1);
            dateText = dateFormat.format(c.getTime());
            return recurs(dateText,c,dateFormat);
        }else return dateText;
    }
    public static int search (Document tabWeek, TextView textView, String datePN){
        ArrayList<ArrayList<String>> tableW = new ArrayList<ArrayList<String>>();
        if (tabWeek != null) {
            tableW = ParserWorkWithTable.basik(tabWeek);
        }else Toast.makeText(null,"私たちはすべてを失いました！in search week",Toast.LENGTH_LONG).show();
        int number = 0;
        for (int j = 0; j < tableW.size(); j++) {
            String date = tableW.get(j).get(1);
            if(datePN.equals(date)){
                String week = tableW.get(j).get(0);
                textView.setText(week);
                String subweek = week.substring(0, 2);
                subweek = subweek.trim();
                number = Integer.parseInt(subweek);
            }
        }
        return number;
    }

    public static Integer setWeek (Document tabWeek1, Document tabWeek2, TextView textView,String dateText, Calendar c, DateFormat dateFormat){

        String datePN = recurs(dateText,c,dateFormat);
        int nubberWeek = 0;

        nubberWeek = search(tabWeek1,textView,datePN);
        if(nubberWeek == 0) {
            nubberWeek = search(tabWeek2, textView, datePN);
        }
        return nubberWeek;
    }
}
