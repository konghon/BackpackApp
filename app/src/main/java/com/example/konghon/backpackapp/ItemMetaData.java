package com.example.konghon.backpackapp;

/**
 * Created by Konghon on 02/11/2014.
 * Does magic just kidding
 * holds the data of item
 */
public class ItemMetaData {
    public String NfcIDTag;
    public String Name;
    public String Description;
    public int ListID;
    public int Checked = 0;

    ItemMetaData(String name, String description, String nfcIDTag, int listID, int checked){
        NfcIDTag = nfcIDTag;
        Name = name;
        Description = description;
        ListID = listID;
        Checked = checked;
    }
}
