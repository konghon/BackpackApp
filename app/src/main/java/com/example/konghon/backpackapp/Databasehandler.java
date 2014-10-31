package com.example.konghon.backpackapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Konghon on 31/10/2014.
 */
public class Databasehandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1; 				// Database Version
    private static final String DATABASE_NAME = "Backpack"; 	// Database Name
    private static final String TABLE_ITEMS = "items";	// Items table name
    private static final String TABLE_LIST = "lists";   //Lists tablename

    public Databasehandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
