package com.example.konghon.backpackapp;

import android.app.Activity;
import android.content.Intent;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CheckList extends Activity {

    private Databasehandler databasehandler;
    public final static String EXTRA_ID = "com.example.konghon.backpackapp";
    private int listid = 0;

    private final String[][] techList = new String[][]{
            new String[]{
                    NfcA.class.getName(),
                    NfcB.class.getName(),
                    NfcF.class.getName(),
                    NfcV.class.getName(),
                    IsoDep.class.getName(),
                    MifareClassic.class.getName(),
                    MifareUltralight.class.getName(), Ndef.class.getName()
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        //enabling database
        databasehandler = new Databasehandler(this);

        //recieving intent
        Intent intent = getIntent();
        listid = intent.getIntExtra(MainActivityListOverview.EXTRA_INT, 0);

        //update list
        UpdateList2(listid);

        Toast tostie = Toast.makeText(getApplicationContext(), "id" + listid, Toast.LENGTH_SHORT);
        tostie.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        UpdateList2(listid);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_new_item) {
            Toast toast = Toast.makeText(getApplicationContext(), "New Item", Toast.LENGTH_SHORT);
            toast.show();
            Intent i = new Intent(this, CreateNewItem.class);
            i.putExtra(EXTRA_ID, listid);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_remove_item) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void UpdateList2(int id) {
        ListView listView2 = (ListView) findViewById(R.id.listView2);
        final List<String> items = new ArrayList<String>();
        final List<ItemMetaData> dataItems = databasehandler.getItems(id);
        //Log.d("dbughsize", Integer.toString(datalist.size()));
        for (int i = 0; i < dataItems.size(); i++) {
            items.add(dataItems.get(i).Name);
            Log.d("dbugname", dataItems.get(i).Name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);

        /*listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                Toast toast = Toast.makeText(getApplicationContext(),datalist.get(i).NfcIDTag, Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getApplication(), CheckList.class);
                startActivity(intent);
            }
        });*/
        listView2.setAdapter(adapter);
    }




}
