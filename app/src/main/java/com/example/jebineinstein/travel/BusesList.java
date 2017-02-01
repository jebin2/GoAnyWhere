package com.example.jebineinstein.travel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jebineinstein on 18/12/16.
 */

public class BusesList extends AppCompatActivity {

    //    SearchBuses ta = new SearchBuses();
    ArrayList<String> b1 = SearchBuses.b;

    static RecyclerView.Adapter adapter1;
    RecyclerView.LayoutManager layoutManager1;
    static RecyclerView recyclerView1;
    static ArrayList<BusDataModel> data1;
    public static View.OnClickListener myOnClickListener;
    SharedPreferences sp;


    public static int i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buseslist);
        recyclerView1 = (RecyclerView) findViewById(R.id.my_recycler_view1);
        recyclerView1.setHasFixedSize(true);
        layoutManager1 = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        myOnClickListener = new MyOnClickListener();
        sp = getSharedPreferences("Care", MODE_PRIVATE);
        Log.i("jebin", String.valueOf(b1.size()));
//        String[] busarray = {"Android","IPhone","WindowsMobile","Blackberry",
//                "WebOS","Ubuntu","Windows7","Max OS X"};
//
//        ArrayAdapter adapter = new ArrayAdapter<String>(this,
//                R.layout.listviewlayout, busarray);
//
//        ListView listView = (ListView) findViewById(R.id.bus_list);
//        listView.setAdapter(adapter);
//        ListView listView;
//        listView = (ListView) findViewById(R.id.bus_list);
//        ArrayList<String> lst = new ArrayList<String>();
//        ArrayList<BusDataModel> lst1 = new ArrayList<>();
//        for (int i = 0; i < b1.size(); i += 7)
//            lst.add(b1.get(i));
//        lst.add(b1.get(7));
//        ArrayAdapter <String> adapter = new ArrayAdapter <String>( this, android.R.layout.simple_list_item_1, lst);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listviewlayout, R.id.name, lst);
        data1 = new ArrayList<>();
        for (int i = 0; i < b1.size(); i += 10){
            data1.add(new BusDataModel(b1.get(i), b1.get(i + 1), b1.get(i + 2), b1.get(i + 3), b1.get(i + 4), b1.get(i + 5), b1.get(i + 6), b1.get(i + 7), b1.get(i + 8), b1.get(i + 9)));}
//            data1.add(new BusDataModel("", "", "", "", "", "", "", "", ""));}
        adapter1 = new CustomAdapter(data1);

        recyclerView1.setAdapter(adapter1);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.i("jebin", String.valueOf(i + (String) adapterView.getItemAtPosition(i) + l));
////                name = (String)adapterView.getItemAtPosition(i);
//                i1 = i;
//                startActivity(new Intent(BusesList.this, bussdetails.class));
//            }
//        });
    }

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int selectedItemPosition2 = recyclerView1.getChildPosition(view);
            RecyclerView.ViewHolder viewHolder2 = recyclerView1.findViewHolderForPosition(selectedItemPosition2);
            TextView cost = (TextView) viewHolder2.itemView.findViewById(R.id.buscost);
            TextView name = (TextView) viewHolder2.itemView.findViewById(R.id.busname);
            TextView deptime = (TextView) viewHolder2.itemView.findViewById(R.id.depaturetime);
            for (int i = 0; i < b1.size(); i += 10) {
                if ((String.valueOf(name.getText()).equals(b1.get(i + 1))) && (String.valueOf(deptime.getText()).equals(b1.get(i + 2))) && (String.valueOf(cost.getText()).equals(b1.get(i + 7)))) {
//                    SharedPreferences.Editor editor = sp.edit();
//                    editor.putString("busid", b1.get(i));
//                    editor.putString("busname", b1.get(i+1));
//                    editor.putString("dtime", b1.get(i+2));
//                    editor.putString("atime", b1.get(i+3));
//                    editor.putString("btime", b1.get(i+4));
//                    editor.putString("from", b1.get(i+5));
//                    editor.putString("to", b1.get(i+6));
//                    editor.putInt("id", i);
//                    editor.apply();
                    i1 = i;
                    break;
                }
            }
            startActivity(new Intent(BusesList.this, bussdetails.class));
        }
    }
}

