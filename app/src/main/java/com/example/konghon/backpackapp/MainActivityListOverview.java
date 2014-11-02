package com.example.konghon.backpackapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivityListOverview extends Activity {
    private Databasehandler databasehandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_list_overview);
        databasehandler = new Databasehandler(this);
        UpdateList();
    }

    @Override
    public void onResume(){
        super.onResume();
        UpdateList();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_list_overview, menu);
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
            Context context = getApplicationContext();
            CharSequence text = "Settings";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return true;
        }

        if (id == R.id.action_new_list) {
            Context context = getApplicationContext();
            CharSequence text = "Creating New List";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Intent i = new Intent(this, CreateNewList.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_backup) {
            Context context = getApplicationContext();
            CharSequence text = "Backing Up";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            databasehandler.dumpToLogCat();
            return true;
        }

        if (id == R.id.action_restore) {
            Context context = getApplicationContext();
            CharSequence text = "Restoring lists";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            databasehandler.addNewList("lol", "nyancat");
            return true;
        }

        if (id == R.id.action_remove_list) {
            Context context = getApplicationContext();
            CharSequence text = "Removing lists";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Intent i = new Intent(this, CheckList.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void UpdateList()
    {
        ListView listView1 = (ListView) findViewById(R.id.listView);
        List<String> lists = new ArrayList<String>();
        List<ListMetaData> datalist = databasehandler.getList();
        Log.d("dbughsize", Integer.toString(datalist.size()));
        for(int i = 0; i < datalist.size(); i++)
        {
            lists.add(datalist.get(i).Name);
            Log.d("dbugname", datalist.get(i).Name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, lists);
        listView1.setAdapter(adapter);
    }
}
