package com.example.wsizoprojekt.absolwent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
            Map<String, String> dane_uzytkownika = api.pobierzDaneUzytkownika(autoryzacja.user_id);
            EditText imie_ = findViewById(R.id.imie);
            EditText miejscowosc_ = findViewById(R.id.miejscowosc);
            EditText opis_ = findViewById(R.id.opis);
            Log.d("TEST","EDYCJA: "+api.response.optString("imie"));
            imie_.setText(api.response.optString("imie"));
            miejscowosc_.setText(api.response.optString("miejscowosc"));
            opis_.setText(api.response.optString("opis"));
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
