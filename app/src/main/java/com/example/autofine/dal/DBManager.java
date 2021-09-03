package com.example.autofine.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager  extends SQLiteOpenHelper {

    public static final String appDBName ="cars.db";
    public static final int appDBVer = 1;
    //для создания пустой БД


    public DBManager(Context context, String dbname, int dbVersion )
    {
        super(context,dbname, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Cars(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "plate_number TEXT , " +
                "document_number TEXT"+
                 ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
