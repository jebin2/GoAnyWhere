package com.example.jebineinstein.travel;

import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by jebineinstein on 17/12/16.
 */

public class travelactivity extends AppCompatActivity implements View.OnClickListener{

    Button search;
    EditText from,to,dd,mm,yyyy;
    String sfrom,sto,sdd,smm,syyyy;
    ArrayList<String> b = new ArrayList<>();
    int date,day,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        search=(Button)findViewById(R.id.searchbutton);
        from=(EditText)findViewById(R.id.starteditText);
        to=(EditText)findViewById(R.id.stopeditText);
        dd=(EditText)findViewById(R.id.ddeditText);
        mm=(EditText)findViewById(R.id.mmeditText);
        yyyy=(EditText)findViewById(R.id.yyyyeditText);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchbutton:
                if(from.getText().toString().equals("") &&to.getText().toString().equals("") &&dd.getText().toString().equals("") &&mm.getText().toString().equals("") &&yyyy.getText().toString().equals("")){
                Toast.makeText(travelactivity.this,"Fill All The Field",Toast.LENGTH_LONG).show();
        }
        else{
            new Buses(travelactivity.this).execute();
        }
            break;
        }
    }

    class Buses extends AsyncTask<Void, Void, String> {
        Context context;
        ProgressDialog dialog = null;

        Buses(Context c) {
            context = c;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            sfrom=from.getText().toString();
            sto=to.getText().toString();
            sdd=dd.getText().toString();
            smm=mm.getText().toString();
            syyyy=yyyy.getText().toString();
            dialog = new ProgressDialog(context);
            dialog.setMessage("Waiting for verification sms");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected String doInBackground(Void... params) {
            StringBuilder sb = new StringBuilder();

            try {
                String link = "http://" + getString(R.string.website) + "/buses.php";
                String data = "from=" + URLEncoder.encode(sfrom, "UTF-8") + "&to=" + URLEncoder.encode(sto, "UTF-8") + "&date=" + URLEncoder.encode(sdd, "UTF-8") + "&day=" + URLEncoder.encode(smm, "UTF-8") + "&year=" + URLEncoder.encode(syyyy, "UTF-8");
                URL url = new URL(link);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                OutputStreamWriter writer = new OutputStreamWriter(httpURLConnection.getOutputStream());
                writer.write(data);
                writer.flush();
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    Log.i("jebin", line);
                }
                httpURLConnection.disconnect();
            } catch (Exception e) {

            }

            return sb.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result.equals("ok")) {
                startActivity(new Intent(travelactivity.this, Buses.class));
                finish();
            } else {
                Snackbar.make(findViewById(R.id.travelactivity), "Please try again", Snackbar.LENGTH_LONG).show();

            }

            dialog.dismiss();
        }
    }
}
