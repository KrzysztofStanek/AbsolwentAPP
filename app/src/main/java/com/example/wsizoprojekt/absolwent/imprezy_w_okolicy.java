package com.example.wsizoprojekt.absolwent;

import android.graphics.Color;
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
            api.pobierzDaneUzytkownika(autoryzacja.user_id);
            String wojewodztwo_uzytkownika = api.response.optString("wojewodztwo");

            api.pobierzImpreze(wojewodztwo_uzytkownika);
            JSONObject requestJSON = api.response;
            final JSONArray recs = requestJSON.getJSONArray("data");

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
                    } catch (Exception e) {
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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });



        } catch (Exception e) {
            Log.d("E","ERROR");
            e.printStackTrace();
        }
    }

    protected void wyswietlImpreze(JSONArray recs, int numer) throws Exception {
       final JSONObject rec = recs.getJSONObject(numer);
       // JSONObject obj = rec.getJSONObject(1);

        final TextView nazwaImprezy = findViewById(R.id.nazwaimprezy);
        nazwaImprezy.setText(rec.optString("nazwa"));

        TextView data = findViewById(R.id.data);
        data.setText(rec.optString("data"));

        TextView miejsceimprezy = findViewById(R.id.miejsceimprezy);
        miejsceimprezy.setText(rec.optString("miejsce"));

        TextView wojewodztwoimprezy = findViewById(R.id.wojewodztwoimprezy);
        wojewodztwoimprezy.setText(rec.optString("wojewodztwo")+")");

        TextView opisimprezy = findViewById(R.id.opisimprezy);
        opisimprezy.setText(rec.optString("opis"));
        final Button bDolacz = findViewById(R.id.bdolaczdoimprezy);

        api.bierzeUdzial(rec.optString("id_imprezy"), autoryzacja.user_id);

        if(api.response.optString("data").equals("TRUE")){
            bDolacz.setEnabled(false);
            bDolacz.setBackgroundColor(Color.GRAY);
            bDolacz.setText("BIERZESZ UDZIAŁ");
        }
        else{
            bDolacz.setEnabled(true);
            bDolacz.setBackgroundColor(this.getResources().getColor(android.R.color.holo_orange_dark));
            bDolacz.setText("Dołącz do imprezy");
        }

        api.ileWezmieUdzial(rec.optString("id_imprezy"));
        String ile = api.response.optString("data");
        Button bInfoImpreza = findViewById(R.id.binfoimpreza);
        bInfoImpreza.setText("Weźmie udział - "+ile);

        bDolacz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    api.dolaczDoImprezy(rec.optString("id_imprezy"), autoryzacja.user_id);
                    bDolacz.setEnabled(false);
                    bDolacz.setBackgroundColor(Color.GRAY);
                    bDolacz.setText("BIERZESZ UDZIAŁ");
                    Toast.makeText(imprezy_w_okolicy.this, "Dołączyłeś do "+rec.optString("nazwa"), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

        Log.d("NOWE", String.valueOf(numer));
        Log.d("NOWE", rec.getString("nazwa"));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }
}

