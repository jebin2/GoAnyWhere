package com.example.jebineinstein.travel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by jebineinstein on 7/1/17.
 */

public class bussdetails extends AppCompatActivity {

    //    travelactivity ta = new travelactivity();
    ArrayList<String> b1 = travelactivity.b;
    int i2 = Buses.i1,i=0;
    EditText busid,busname,from,to,dtime,atime,bpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busdetails);
        from  = (EditText)findViewById(R.id.editText11);
        to = (EditText)findViewById(R.id.editText12);
        bpoint = (EditText)findViewById(R.id.editText13);
        dtime = (EditText)findViewById(R.id.editText14);
        atime = (EditText)findViewById(R.id.editText15);
        busname = (EditText)findViewById(R.id.editText16);
        busid = (EditText)findViewById(R.id.editText17);

        for(int j=0;j<i2;j++){
            i=i+7;
        }

        from.setText(b1.get(5+i));
        to.setText(b1.get(6+i));
        bpoint.setText(b1.get(4+i));
        dtime.setText(b1.get(2+i));
        atime.setText(b1.get(3+i));
        busname.setText(b1.get(1+i));
        busid.setText(b1.get(0+i));
    }
}