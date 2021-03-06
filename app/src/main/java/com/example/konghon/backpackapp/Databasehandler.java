package com.example.konghon.backpackapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konghon on 31/10/2014.
 * does some magic database thingies ....
 */
public class Databasehandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;                // Database Version
    private static final String DATABASE_NAME = "Backpack";    // Database Name
    private static final String TABLE_ITEMS = "items";    // Items table name
    private static final String TABLE_LIST = "lists";   //Lists tablename

    public Databasehandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //query to make list Table
        String CREATE_LIST_TABLE = "CREATE TABLE " + TABLE_LIST + "(" +
                "UID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "NAME TEXT," +
                "DESCRIPTION TEXT)";

        //query to make item table
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_ITEMS + "(" +
                "UID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "NAME TEXT," +
                "DESCRIPTION TEXT," +
                "NFCTAGID TEXT," +
                "LIST INTEGER," +
                "CHECKED INTEGER)";
        db.execSQL(CREATE_LIST_TABLE);
        db.execSQL(CREATE_ITEM_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_LIST);
        onCreate(db);
    }

    public void addNewList(String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("DESCRIPTION", description);
        db.insert(TABLE_LIST, null, values);
        db.close();
    }

    public void addNewItem(String name, String description, String nfcId, int listId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("DESCRIPTION", description);
        values.put("NFCTAGID", nfcId);
        values.put("LIST", listId);
        values.put("CHECKED", 0);
        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    public void dumpToLogCat() {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ITEMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            int columnCount = cursor.getColumnCount();
            Log.d("sqlite", Integer.toString(columnCount));

            do {
                for (int i = 0; i < columnCount; i++) {
                    Log.d("sqlite", cursor.getColumnName(i) + " --> " + cursor.getString(i));
                }
                Log.i("sqlite", "===================================");
            } while (cursor.moveToNext());
        }
    }

    public List<ListMetaData> getList() {
        String selectQuery = "SELECT  * FROM " + TABLE_LIST;
        List<ListMetaData> lists = new ArrayList<ListMetaData>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            //Log.d("sqlite", Integer.toString(columnCount));

            do {
                ListMetaData toAddList = new ListMetaData(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                lists.add(toAddList);
            } while (cursor.moveToNext());
        }
        return lists;
    }

    public List<ItemMetaData> getItems(int id) {
        String selectQuery = "SELECT  * FROM " + TABLE_ITEMS + " WHERE LIST=" + Integer.toString(id);
        List<ItemMetaData> items = new ArrayList<ItemMetaData>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            do {
                ItemMetaData toAddList = new ItemMetaData(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5));
                items.add(toAddList);
            } while (cursor.moveToNext());
        }
        return items;

    }

    public void CheckItem(String nfcTagId, int listid) {
        String updateQuery = "UPDATE " + TABLE_ITEMS + " SET CHECKED=1 WHERE NFCTAGID='" + nfcTagId + "' AND LIST="+listid;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(updateQuery);
    }

    public void UnCheckAll (int listid) {
        String updateQuery = "UPDATE " +TABLE_ITEMS + " SET CHECKED=0 WHERE LIST=" + listid;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(updateQuery);
    }

    public void DeleteLists() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_ITEMS);
        db.execSQL("delete from " + TABLE_LIST);
        db.execSQL("vacuum");
    }

    public void DeleteItems(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_ITEMS + " WHERE LIST=" + id);
    }

    public void DeleteSpecificItem(int listId, String nfcTagId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_ITEMS + " WHERE LIST=" + listId + " AND NFCTAGID='" + nfcTagId + "';");
    }


}
