package com.example.konghon.backpackapp;

/**
 * Created by Konghon on 02/11/2014.
 */
public class ItemMetaData {
    public String NfcIDTag;
    public String Name;
    public String Description;
    public int ListID;

    ItemMetaData(String nfcIDTag, String name, String description, int listID){
        NfcIDTag = nfcIDTag;
        Name = name;
        Description = description;
        ListID = listID;
    }
}