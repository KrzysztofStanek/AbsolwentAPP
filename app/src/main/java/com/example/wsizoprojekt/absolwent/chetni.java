package com.example.wsizoprojekt.absolwent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class chetni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chetni);

        API api = new API();

        try {
            api.pobierzChętnych();
            JSONObject requestJSON = api.response;
            final JSONArray recs = requestJSON.getJSONArray("data");
            String chetni="";
            for(int i = 0; i<recs.length();i++){
                final JSONObject rec = recs.getJSONObject(i);
                chetni += Integer.toString((i+1))+"."+rec.optString("nick")+" - "+rec.optString("ilosc")+" imprez"+System.lineSeparator();
            }

            TextView chetniTxt = findViewById(R.id.chetnidopicialista);
            chetniTxt.setText(chetni);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
