package com.example.jebineinstein.travel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class Signup extends AppCompatActivity implements View.OnClickListener{

    EditText name,username,password,dob,gender,email,phonenumber;
    SharedPreferences sp;
    ArrayList<String> a=new ArrayList<>();
    Button Signup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        name=(EditText)findViewById(R.id.editText3);
        username=(EditText)findViewById(R.id.editText4);
        password=(EditText)findViewById(R.id.editText5);
        gender=(EditText)findViewById(R.id.editText7);
        phonenumber=(EditText)findViewById(R.id.editText9);
        dob=(EditText)findViewById(R.id.editText10);
        email=(EditText)findViewById(R.id.editText8);
        Signup1=(Button)findViewById(R.id.button3);
        Signup1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if((name.getText().toString().equals("")) &&(username.getText().toString().equals("")) &&(password.getText().toString().equals("")) &&(gender.getText().toString().equals("")) &&(phonenumber.getText().toString().equals("")) &&(dob.getText().toString().equals(""))) {

            Toast.makeText(Signup.this,"Fill All The Field",Toast.LENGTH_LONG).show();
        }
        else{
            new SignUp(Signup.this).execute();
        }
    }

    class SignUp extends AsyncTask<Void, Void, String> {
        Context context;
        ProgressDialog dialog = null;

        SignUp(Context c) {
            context = c;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a.clear();
            a.add(name.getText().toString());
            a.add(username.getText().toString());
            a.add(password.getText().toString());
            a.add(gender.getText().toString());
            a.add(phonenumber.getText().toString());
            a.add(dob.getText().toString());
            a.add(email.getText().toString());
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putString("name", username.getText().toString());
//            editor.apply();
            dialog = new ProgressDialog(context);
            dialog.setMessage("Waiting for verification sms");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
        }

        @Override
        protected String doInBackground(Void... params) {
            StringBuilder sb = new StringBuilder();

            try {
                Log.i("jebin", "hjgj");
                String link = "http://" + getString(R.string.website) + "/GoAnyWhere/signup.php";
                String data = "name=" + URLEncoder.encode(a.get(0), "UTF-8") + "&username=" + URLEncoder.encode(a.get(1), "UTF-8") + "&password=" + URLEncoder.encode(a.get(2), "UTF-8") + "&gender=" + URLEncoder.encode(a.get(3), "UTF-8") + "&phonenumber=" + URLEncoder.encode(a.get(4), "UTF-8") + "&dateofbirth=" + URLEncoder.encode(a.get(5), "UTF-8") + "&mailid=" + URLEncoder.encode(a.get(6), "UTF-8");
                URL url = new URL(link);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                OutputStreamWriter writer = new OutputStreamWriter(httpURLConnection.getOutputStream());
                writer.write(data);
                Log.i("jebin", "bgghg");
                writer.flush();
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    Log.i("jebin", line+"jrfs");
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
                Snackbar.make(findViewById(R.id.signlayout), "Successfully Registried", Snackbar.LENGTH_LONG).show();
                startActivity(new Intent(Signup.this, travelactivity.class));
                finish();
            } else {
                Snackbar.make(findViewById(R.id.signlayout), result, Snackbar.LENGTH_LONG).show();

            }

            dialog.dismiss();
        }
    }
}
