package com.example.jebineinstein.travel;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jebineinstein on 31/1/17.
 */

public class Payment extends AppCompatActivity implements View.OnClickListener {

    TextView costtextview;
    EditText cardnumber, cvv, expiraydate, pin;
    Button button;
    ArrayList<String> b2 = SearchBuses.b;
    int i22 = BusesList.i1,i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        costtextview = (TextView)findViewById(R.id.textView201);
        cardnumber = (EditText)findViewById(R.id.editText111);
        cvv = (EditText)findViewById(R.id.editText121);
        expiraydate = (EditText)findViewById(R.id.editText131);
        pin = (EditText)findViewById(R.id.editText141);
        button = (Button) findViewById(R.id.paynow);
        button.setOnClickListener(this);

        for(int j=0;j<i22;j++){
            i=i+10;
        }
        costtextview.setText(b2.get(i+7));
    }
    @Override
    public void onClick(View view) {
        if(cardnumber.getText().equals("")||cardnumber.getText().length()<12||cvv.getText().equals("")||cvv.getText().length()<2||expiraydate.getText().equals("")||expiraydate.getText().equals("")||pin.getText().equals("")) {
            Snackbar.make(findViewById(R.id.pay), "Fill all the field correctly", Snackbar.LENGTH_LONG).show();
        }
        else{
            Snackbar.make(findViewById(R.id.pay), "payment completed", Snackbar.LENGTH_LONG).show();
        }
    }
}

