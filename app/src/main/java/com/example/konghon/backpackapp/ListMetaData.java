package com.example.konghon.backpackapp;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Konghon on 01/11/2014.
 */
public class ListMetaData {
    public String Name;
    public String Description;
    public int ID;

    ListMetaData(int id, String name, String description)
    {
        Name = name;
        Description = description;
        ID = id;
    }
}
