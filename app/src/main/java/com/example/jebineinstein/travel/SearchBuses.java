package com.example.jebineinstein.travel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by jebineinstein on 17/12/16.
 */

public class SearchBuses extends AppCompatActivity implements View.OnClickListener{

    Button search;
    EditText from,to,dd,mm,yyyy;
    String sfrom,sto,sdd,smm,syyyy;
    public static ArrayList<String> b = new ArrayList<>();
    int date,day,year;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calendar = Calendar.getInstance();
        setContentView(R.layout.searchbuses);
        search=(Button)findViewById(R.id.searchbutton);
        from=(EditText)findViewById(R.id.starteditText);
        to=(EditText)findViewById(R.id.stopeditText);
        dd=(EditText)findViewById(R.id.ddeditText);
        mm=(EditText)findViewById(R.id.mmeditText);
        yyyy=(EditText)findViewById(R.id.yyyyeditText);
        yyyy.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchbutton:
                if(from.getText().toString().equals("") ||to.getText().toString().equals("") ||dd.getText().toString().equals("") ||mm.getText().toString().equals("") ||yyyy.getText().toString().equals("")|| Integer.parseInt(mm.getText().toString())>12||Integer.parseInt(mm.getText().toString())==0||Integer.parseInt(dd.getText().toString())>31||Integer.parseInt(dd.getText().toString())==0||yyyy.getText().toString().equals((Calendar.YEAR))){
                Toast.makeText(SearchBuses.this,"Fill All The Field Correctly",Toast.LENGTH_LONG).show();
        }
        else{
            new Buses1(SearchBuses.this).execute();
        }
            break;
        }
    }

    class Buses1 extends AsyncTask<Void, Void, String> {
        Context context;
        ProgressDialog dialog = null;

        Buses1(Context c) {
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
            dialog.setMessage("Waiting for verif123ication sms");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected String doInBackground(Void... params) {
            StringBuilder sb = new StringBuilder();

            try {
                Log.i("jebin", "try");
                String link = "http://" + getString(R.string.website) + "/GoAnyWhere/buses.php";
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
                    Log.i("jebin", String.valueOf(line));
                }
                httpURLConnection.disconnect();
            } catch (Exception e) {

            }

            return sb.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jsonObj = new JSONObject(result);

                // Getting JSON Array node
                JSONArray details = jsonObj.getJSONArray("details");
                b.clear();
                for (int i = 0; i < details.length(); i++) {
                    JSONObject c = details.getJSONObject(i);

                    String id = c.getString("busid");
                    String name = c.getString("busname");
                    String email = c.getString("dtime");
                    String address = c.getString("atime");
                    String gender = c.getString("boardingpoint");
                    String address1 = c.getString("from");
                    String gender1 = c.getString("to");
                    String cost1 = c.getString("cost");
                    String date1 = c.getString("date");
                    String seat = c.getString("seat");

                    b.add(id);
                    b.add(name);
                    b.add(email);
                    b.add(address);
                    b.add(gender);
                    b.add(address1);
                    b.add(gender1);
                    b.add(cost1);
                    b.add(date1);
                    b.add(seat);

                }
            }catch (Exception e){

            }

            if (b.size()==0) {
                Snackbar.make(findViewById(R.id.travelactivity), "Please try again no bus", Snackbar.LENGTH_LONG).show();
            } else if(result.equals("nobuses")) {
                Snackbar.make(findViewById(R.id.travelactivity), "Please try again no bus", Snackbar.LENGTH_LONG).show();
            }
            else {
                startActivity(new Intent(SearchBuses.this, BusesList.class));
            }

            dialog.dismiss();
        }
    }
}
