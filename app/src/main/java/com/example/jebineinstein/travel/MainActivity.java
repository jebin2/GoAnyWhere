package com.example.jebineinstein.travel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button Login ,Signup;
    EditText username = null,password = null;
    SharedPreferences sp;
    String u1,p1;
    ArrayList<String> a =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login=(Button)findViewById(R.id.button1);
        Signup=(Button)findViewById(R.id.button2);
        Login.setOnClickListener(this);
        Signup.setOnClickListener(this);
        username=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button1:
                new Login(MainActivity.this).execute();
                break;
            case R.id.button2:
                startActivity(new Intent(MainActivity.this,Signup.class));
                break;
        }
    }

    class Login extends AsyncTask<Void, Void, String> {
        Context context;
        ProgressDialog dialog = null;

        Login(Context c) {
            context = c;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a.clear();
            a.add(username.getText().toString());
            a.add(password.getText().toString());
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putString("name", a.get(0));
//            editor.putString("pass", a.get(1));
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
                String link = "http://" + getString(R.string.website) + "/GoAnyWhere/login.php";
                String data = "username=" + URLEncoder.encode(a.get(0), "UTF-8") + "&password=" + URLEncoder.encode(a.get(1), "UTF-8");
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
                return "no";
            }

            return sb.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result.equals("ok")) {
                startActivity(new Intent(MainActivity.this, travelactivity.class));
                finish();
            } else {
                Snackbar.make(findViewById(R.id.activity_main), result, Snackbar.LENGTH_LONG).show();

            }

            dialog.dismiss();
        }
    }
}
