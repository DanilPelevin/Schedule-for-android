package com.example.schedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ScheduleDB";
    public static final String TABLE_CONTACTS = "Schedule";

    public static final String KEY_ID = "_id";
    public static final String KEY_SGROUP = "sgroup";
    public static final String KEY_PODGROUP = "podgroup";
    public static final String KEY_WEEK = "week";
    public static final String KEY_DAY = "day";
    public static final String KEY_TIME = "time";
    public static final String KEY_LESSON = "lesson";
    public static final String KEY_TEACHER = "teacher";
    public static final String KEY_CABINET = "cabinet";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID + " integer primary key," + KEY_SGROUP + " text," + KEY_PODGROUP + " text," + KEY_WEEK + " text,"  + KEY_DAY + " text," + KEY_TIME + " text," + KEY_LESSON + " text," + KEY_TEACHER + " text," + KEY_CABINET + " integer" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);

    }
}
