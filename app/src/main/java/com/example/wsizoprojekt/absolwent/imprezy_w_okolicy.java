package com.example.wsizoprojekt.absolwent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class imprezy_w_okolicy extends AppCompatActivity {

    API api = new API();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imprezy_w_okolicy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button bpoprzednia = findViewById(R.id.bpoprzednia);
        Button bnastepna = findViewById(R.id.bnastepna);
        try {


            api.pobierzImpreze("Podkarpackie");
            JSONObject requestJSON = api.response;
            final JSONArray recs = requestJSON.getJSONArray("data");
            ///String data_json = api.response.optString("data");
            //final JSONObject recs = new JSONObject(data_json);

            //final JSONArray recs = temp.getJSONArray("data");
            //final JSONObject recs = requestJSON.getJSONObject("data");


            /*for (int i = 0; i < recs.length(); ++i) {
                JSONObject rec = recs.getJSONObject(String.valueOf(i));
                int id = rec.getInt("id");
                String loc = rec.getString("loc");
                // ...
            }*/
            final int max = recs.length()-1;
            final int start = 0;
            final int[] aktualna = {start};


            wyswietlImpreze(recs, aktualna[0]);


            bpoprzednia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(imprezy_w_okolicy.this, "pop", Toast.LENGTH_SHORT).show();
                    aktualna[0]--;
                    if(aktualna[0] <start){
                        aktualna[0] = max;
                    }
                    try {
                        wyswietlImpreze(recs, aktualna[0]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            bnastepna.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(imprezy_w_okolicy.this, "nast", Toast.LENGTH_SHORT).show();
                    aktualna[0]++;
                    if(aktualna[0] >max){
                        aktualna[0] = start;
                     }
                    try {
                        wyswietlImpreze(recs, aktualna[0]);
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                }
            });



        } catch (Exception e) {
            Log.d("E","ERROR");
            e.printStackTrace();
        }
    }

    protected void wyswietlImpreze(JSONArray recs, int numer) throws JSONException {
       JSONObject rec = recs.getJSONObject(numer);
       // JSONObject obj = rec.getJSONObject(1);

        TextView nazwaImprezy = findViewById(R.id.nazwaimprezy);
        nazwaImprezy.setText(rec.optString("nazwa"));

        TextView data = findViewById(R.id.data);
        data.setText(rec.optString("data"));

        TextView miejsceimprezy = findViewById(R.id.miejsceimprezy);
        miejsceimprezy.setText(rec.optString("miejsce"));

        TextView wojewodztwoimprezy = findViewById(R.id.wojewodztwoimprezy);
        wojewodztwoimprezy.setText(rec.optString("wojewodztwo"));

        TextView opisimprezy = findViewById(R.id.opisimprezy);
        opisimprezy.setText(rec.optString("opis"));


        Log.d("NOWE", String.valueOf(numer));
        Log.d("NOWE", rec.getString("nazwa"));

    }
}

