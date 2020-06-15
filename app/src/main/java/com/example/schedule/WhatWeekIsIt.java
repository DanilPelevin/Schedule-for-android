package com.example.schedule;

import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.nodes.Document;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


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
        }else Toast.makeText(null,"Пустой док в поиске недель...",Toast.LENGTH_LONG).show();
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


    public static Integer switchingDays(TextView textView, TextView day1date, TextView day2date, TextView day3date, TextView day4date, TextView day5date, TextView day6date, Integer numberWeek, Integer multiplier){
        //TODO: Этот код стоит переделать, он не правильный.
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Calendar c = Calendar.getInstance();
        String dateText = dateFormat.format(new Date());
        String datePN = recurs(dateText,c,dateFormat);
        try {
            c.setTime(dateFormat.parse(datePN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //c.add(Calendar.DATE, 7*multiplier);
        //c.add(Calendar.DATE, 1);
        datePN = dateFormat.format(c.getTime());
        day1date.setText(datePN);
        c.add(Calendar.DATE, 1);
        String dateVT = dateFormat.format(c.getTime());
        day2date.setText(dateVT);
        c.add(Calendar.DATE, 1);
        String dateSR = dateFormat.format(c.getTime());
        day3date.setText(dateSR);
        c.add(Calendar.DATE, 1);
        String dateCHT = dateFormat.format(c.getTime());
        day4date.setText(dateCHT);
        c.add(Calendar.DATE, 1);
        String datePT = dateFormat.format(c.getTime());
        day5date.setText(datePT);
        c.add(Calendar.DATE, 1);
        String dateSB = dateFormat.format(c.getTime());
        day6date.setText(dateSB);

//        int n = numberWeek-multiplier*-1;
//        String numWeek = String.valueOf(numberWeek);
//        textView.setText(numWeek+" неделя");
        return numberWeek;
    }
}
