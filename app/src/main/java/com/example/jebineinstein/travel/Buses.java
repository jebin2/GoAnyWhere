package com.example.jebineinstein.travel;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

/**
 * Created by jebineinstein on 18/12/16.
 */

public class Buses extends AppCompatActivity{

//    travelactivity ta = new travelactivity();
    ArrayList<String> b1 = travelactivity.b;
    public static int i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buslistlayout);
        Log.i("jebin", String.valueOf(b1.size()));
//        String[] busarray = {"Android","IPhone","WindowsMobile","Blackberry",
//                "WebOS","Ubuntu","Windows7","Max OS X"};
//
//        ArrayAdapter adapter = new ArrayAdapter<String>(this,
//                R.layout.listviewlayout, busarray);
//
//        ListView listView = (ListView) findViewById(R.id.bus_list);
//        listView.setAdapter(adapter);
        ListView listView;
        listView=(ListView)findViewById(R.id.bus_list);
        ArrayList<String> lst = new ArrayList<String>();
        for(int i=0;i<b1.size();i+=7)
        lst.add(b1.get(i));
//        lst.add(b1.get(7));
        ArrayAdapter <String> adapter = new ArrayAdapter <String>( this, android.R.layout.simple_list_item_1, lst);
//        ListAdapter adapter1 = new SimpleAdapter(
//                Buses.this, contactList,
//                R.layout.listviewlayout, new String[]{"name", "email",
//                "mobile"}, new int[]{R.id.name,
//                R.id.email, R.id.mobile});

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("jebin", String.valueOf(i+(String)adapterView.getItemAtPosition(i) +l));
//                name = (String)adapterView.getItemAtPosition(i);
                i1 = i;
                startActivity(new Intent(Buses.this,bussdetails.class));
            }
        });
    }
}

