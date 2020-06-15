package com.example.schedule;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
//TODO: Нужно написать код который бы записывать имеющуюся таблицу в базу данных с полным распределением.
public class WriteToTheDatabase {
    public WriteToTheDatabase(ArrayList<ArrayList<String>> table){
        DBHelper dbHelper;
        dbHelper = new DBHelper(null);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        for (int j = 2;j < table.size();j++){
            for (int i=1;i < table.size();i++){
                String grup = table.get(0).get(j);
                String day = table.get(i).get(0);
                String tinme = table.get(i).get(1);
                //...
            }
        }
    }
}
