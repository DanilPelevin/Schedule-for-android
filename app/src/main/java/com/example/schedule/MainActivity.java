package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
//hi
    private ListView list;
    public ListView list2;
    private TextView textView2;
    public TextView day1date;
    public TextView day2date;
    public TextView day3date;
    public TextView day4date;
    public TextView day5date;
    public TextView day6date;
    public Document doc = null;
    public Document weekTable = null;
    public Document weekTable2 = null;
    public Integer numberWeek;
    public Integer numWeek;
    public Integer multiplier = 0;
    public String str = "ПН";
    int numberofgroup = 5;
    ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    List<String> listt = new ArrayList<>();

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        day1date = (TextView) findViewById(R.id.day1date);
        day2date = (TextView) findViewById(R.id.day2date);
        day3date = (TextView) findViewById(R.id.day3date);
        day4date = (TextView) findViewById(R.id.day4date);
        day5date = (TextView) findViewById(R.id.day5date);
        day6date = (TextView) findViewById(R.id.day6date);
        textView2 = (TextView) findViewById(R.id.textView2);
        list = (ListView) findViewById(R.id.listtime);
        list2 = (ListView) findViewById(R.id.listtime2);
        MyTask mt = new MyTask();
        mt.execute();
        //numberWeek = WhatWeekIsIt.setWeek(weekTable,weekTable2,textView2);

    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                doc = Jsoup.connect("http://fkn.univer.omsk.su/academics/Schedule/schedule2_2.htm").get();
                weekTable2 = Jsoup.connect("http://fkn.univer.omsk.su/academics/weeks2.htm").get();
                weekTable = Jsoup.connect("http://fkn.univer.omsk.su/academics/weeks.htm").get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String dateText = dateFormat.format(new Date());
            Calendar c = Calendar.getInstance();
            m();
            numberWeek = WhatWeekIsIt.setWeek(weekTable,weekTable2,textView2,dateText,c,dateFormat);
            numWeek = WhatWeekIsIt.switchingDays(textView2, day1date, day2date, day3date, day4date, day5date, day6date, numberWeek, multiplier);
        }
    }

    public void  m () {
        if (doc != null) {
            table = ParserWorkWithTable.basik(doc);
            listt = ParserTestOutput.execute(table, str, numberofgroup);
        }else Toast.makeText(MainActivity.this,"私たちはすべてを失いました！",Toast.LENGTH_LONG).show();
        String[] myArray = new String[listt.size()];
        for (int i = 0; i < listt.size(); i++) {
            myArray[i] = listt.get(i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.fortestoutput, myArray);
        list.setAdapter(adapter);
    }


    public void Clickday1(View v) {
        str = "ПН";
        MyTask mt = new MyTask();
        mt.execute();
        m();
    }

    public void Clickday2(View v) {
        str = "ВТ";
        MyTask mt = new MyTask();
        mt.execute();
        m();
    }

    public void Clickday3(View v) {
        str = "СР";
        MyTask mt = new MyTask();
        mt.execute();
        m();
    }

    public void Clickday4(View v) {
        str = "ЧТ";
        MyTask mt = new MyTask();
        mt.execute();
        m();
    }

    public void Clickday5(View v) {
        str = "ПТ";
        MyTask mt = new MyTask();
        mt.execute();
        m();
    }

    public void Clickday6(View v) {
        str = "СБ";
        MyTask mt = new MyTask();
        mt.execute();
        m();
    }

    //TODO: C этими кнопками что то не так!!!

    public void ClickLeft(View v) {
        MyTask mt = new MyTask();
        mt.execute();
        multiplier--;
        numWeek = WhatWeekIsIt.switchingDays(textView2, day1date, day2date, day3date, day4date, day5date, day6date, numberWeek, multiplier);
    }

    public void ClickRight(View v) {
        MyTask mt = new MyTask();
        mt.execute();
        multiplier++;
        numWeek = WhatWeekIsIt.switchingDays(textView2, day1date, day2date, day3date, day4date, day5date, day6date, numberWeek, multiplier);
    }

    public void ClickMe(View v) {
        if (numberofgroup == 6){
            numberofgroup = 2;
        }else numberofgroup ++;
    }


}
