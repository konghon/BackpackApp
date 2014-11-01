package com.example.konghon.backpackapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class CreateNewList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_list);
        final Databasehandler db = new Databasehandler(this);

        findViewById(R.id.buttonCreateNewList).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!((EditText)findViewById(R.id.editText)).getText().toString().contentEquals(""))
                {
                    db.addNewList(((EditText)findViewById(R.id.editText)).getText().toString(),((EditText)findViewById(R.id.editText2)).getText().toString());
                    finish();
                }
                else {Toast toast = Toast.makeText(getApplicationContext(), "Fill in a name", Toast.LENGTH_SHORT);
                toast.show();}
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_new_list, menu);
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

        return super.onOptionsItemSelected(item);
    }



}
