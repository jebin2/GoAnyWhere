package com.example.jebineinstein.travel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by jebineinstein on 28/1/17.
 */

public class Bookdetails extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences sp;
    ArrayList<String> bb1 = travelactivity.b;
    int ii2 = Buses.i1,ii=0;
    EditText busid,busname,name,email,mobileno,dtime,bpoint,seatno;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookdetails);

        sp = getSharedPreferences("GoAnyWhere", Context.MODE_PRIVATE);
        name  = (EditText)findViewById(R.id.editText11);
        email = (EditText)findViewById(R.id.editText12);
        mobileno = (EditText)findViewById(R.id.editText13);
        bpoint = (EditText)findViewById(R.id.editText14);
        dtime = (EditText)findViewById(R.id.editText15);
        busname = (EditText)findViewById(R.id.editText16);
        busid = (EditText)findViewById(R.id.editText17);
        seatno = (EditText)findViewById(R.id.editText18);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        name.setText(sp.getString("Name",""));
        email.setText(sp.getString("Email",""));
        seatno.setText(String.valueOf(5));

        for(int j=0;j<ii2;j++){
            ii=ii+7;
        }

//        from.setText(bb1.get(5+ii));
//        to.setText(bb1.get(6+ii));
        bpoint.setText(bb1.get(4+ii));
        dtime.setText(bb1.get(2+ii));
//        atime.setText(bb1.get(3+ii));
        busname.setText(bb1.get(1+ii));
        busid.setText(bb1.get(0+ii));


    }

    public void onClick(View view) {

    }
}
