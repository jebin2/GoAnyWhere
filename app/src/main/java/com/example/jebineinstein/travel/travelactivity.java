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

public class travelactivity extends AppCompatActivity implements View.OnClickListener{

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
        setContentView(R.layout.fragment_main);
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
                Toast.makeText(travelactivity.this,"Fill All The Field Correctly",Toast.LENGTH_LONG).show();
        }
        else{
            new Buses1(travelactivity.this).execute();
//                    startActivity(new Intent(travelactivity.this, Buses.class));
//                    startActivity(new Intent(travelactivity.this, Buses.class));
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
            dialog.setMessage("Waiting for verification sms");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected String doInBackground(Void... params) {
            StringBuilder sb = new StringBuilder();

            try {
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
                    Log.i("jebin", String.valueOf(line.charAt(3)));
                }
                httpURLConnection.disconnect();
            } catch (Exception e) {

            }

            return sb.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

//            String in = null;
            try {
                JSONObject jsonObj = new JSONObject(result);

                // Getting JSON Array node
                JSONArray details = jsonObj.getJSONArray("details");
                Log.i("jebin", String.valueOf(details)+"dsdsadfsdf");
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

                    Log.i("jebin", id+name+email+address+gender+address1+gender1);

                    b.add(id);
                    b.add(name);
                    b.add(email);
                    b.add(address);
                    b.add(gender);
                    b.add(address1);
                    b.add(gender1);



//                    // Phone node is JSON Object
//                    JSONObject phone = c.getJSONObject("phone");
//                    String mobile = phone.getString("mobile");
//                    String home = phone.getString("home");
//                    String office = phone.getString("office");

                    // tmp hash map for single contact
//                    HashMap<String, String> contact = new HashMap<>();
//
//                    // adding each child node to HashMap key => value
//                    contact.put("id", id);
//                    contact.put("name", name);
//                    contact.put("email", email);
//                    contact.put("mobile", mobile);

                    // adding contact to contact list
//                    contactList.add(contact);
                }
            }catch (Exception e){

            }

            if (b.size()==0) {
                Log.i("jebin", String.valueOf(result)+"ds1232dsadfsdf");
                Snackbar.make(findViewById(R.id.travelactivity), "Please try again no bus", Snackbar.LENGTH_LONG).show();
//                startActivity(new Intent(travelactivity.this, Buses.class));
//                finish();
            } else {
                Log.i("jebin", String.valueOf(result)+"dsdsadfsdf");
                startActivity(new Intent(travelactivity.this, Buses.class));
//                finish();
            }

            dialog.dismiss();
        }
    }
}
