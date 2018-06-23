package com.example.wsizoprojekt.absolwent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Map;

public class edycja_profilu_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edycja_profilu_activity);

        Button bzatwierdz = findViewById(R.id.bzatwierdz);
        Button banuluj = findViewById(R.id.banuluj);

        API api = new API();

        try {
            Spinner spinner = (Spinner) findViewById(R.id.wojewodztwa);

            Map<String, String> dane_uzytkownika = api.pobierzDaneUzytkownika(autoryzacja.user_id);
            EditText imie_ = findViewById(R.id.imie);
            EditText miejscowosc_ = findViewById(R.id.miejscowosc);
            EditText opis_ = findViewById(R.id.opis);
            Log.d("TEST","EDYCJA: "+api.response.optString("imie"));
            imie_.setText(api.response.optString("imie"));
            miejscowosc_.setText(api.response.optString("miejscowosc"));
            opis_.setText(api.response.optString("opis"));
            int nr_selecta = 0;
            switch(api.response.optString("wojewodztwo")){
                case "Dolnośląskie" : {nr_selecta = 0; break;}
                case "Kujawsko-pomorskie" : {nr_selecta = 1; break;}
                case "Lubelskie" : {nr_selecta = 2; break;}
                case "Lubuskie" : {nr_selecta = 3; break;}
                case "Łódzkie" : {nr_selecta = 4; break;}
                case "Małopolskie" : {nr_selecta = 5; break;}
                case "Mazowieckie" : {nr_selecta = 6; break;}
                case "Opolskie" : {nr_selecta = 7; break;}
                case "Podkarpackie" : {nr_selecta = 8; break;}
                case "Podlaskie" : {nr_selecta = 9; break;}
                case "Pomorskie" : {nr_selecta = 10; break;}
                case "Śląskie" : {nr_selecta = 11; break;}
                case "Świętokrzyskie" : {nr_selecta = 12; break;}
                case "Warmińsko-mazurskie" : {nr_selecta = 13; break;}
                case "Wielkopolskie" : {nr_selecta = 14; break;}
                case "Zachodniopomorskie" : {nr_selecta = 15; break;}
            }

            spinner.setSelection(nr_selecta);

        } catch (Exception e) {
            e.printStackTrace();
        }


        banuluj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(edycja_profilu_activity.this, tablica.class);
                edycja_profilu_activity.this.startActivity(intent);
            }
        });

        bzatwierdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(edycja_profilu_activity.this, tablica.class);
                edycja_profilu_activity.this.startActivity(intent);
            }
        });



    }
}
