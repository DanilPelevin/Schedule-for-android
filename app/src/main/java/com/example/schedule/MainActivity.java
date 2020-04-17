package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//hi
    private ListView list;
    private TextView textView;
    private TextView textView2;
    public Document doc = null;
    public String str = "ПН";
    int numberofgroup = 5;
    ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
    List<String> listt = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //textView = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        list = (ListView) findViewById(R.id.listtime);
        MyTask mt = new MyTask();
        mt.execute();

    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                doc = Jsoup.connect("http://fkn.univer.omsk.su/academics/Schedule/schedule2_2.htm").get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    public void  m () {
        if (doc != null) {
            table = ParserWorkWithTable.basik(doc, textView);
            listt = ParserTestOutput.execute(table, str, numberofgroup);
            //title = doc.title();
            //textView2.setText(title);
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

    public void ClickMe(View v) {
        MyTask mt = new MyTask();
        mt.execute();
        m();
    }


}
