package com.example.wsizoprojekt.absolwent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class tablica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablica);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView test_ = findViewById(R.id.test);
        test_.setText("Witaj "+autoryzacja.nick+"("+autoryzacja.user_id+")");


        Button bEdycjaProfil = findViewById(R.id.bEdycjaProfil);

        Button bWyloguj = findViewById(R.id.bWyloguj);

        Button bRegulamin = findViewById(R.id.bRegulamin);

        Button bProfil = findViewById(R.id.bProfil);

        Button bPrywatnosc = findViewById(R.id.bPrywatnosc);

        Button bImprezy = findViewById(R.id.bImprezy);

        Button bDodajimpreze = findViewById(R.id.bDodajimpreze);
        Button bChetni = findViewById(R.id.bChetni);

        bWyloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    autoryzacja.wyloguj();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(tablica.this, MainActivity.class);
                tablica.this.startActivity(intent);
            }
        });

        bRegulamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(tablica.this, oaplikacji_regulamin.class);
                tablica.this.startActivity(intent);
            }
        });

        bPrywatnosc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tablica.this, oaplikacji_politykaprywatnosci.class);
                tablica.this.startActivity(intent);
            }
        });

        bEdycjaProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tablica.this, edycja_profilu_activity.class);
                tablica.this.startActivity(intent);
            }
        });

        bProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tablica.this, profil.class);
                tablica.this.startActivity(intent);
            }
        });

        bImprezy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tablica.this, imprezy_w_okolicy.class);
                tablica.this.startActivity(intent);
            }
        });

        bChetni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tablica.this, chetni.class);
                tablica.this.startActivity(intent);
            }
        });

        bDodajimpreze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tablica.this, dodaj_impreze.class);
                tablica.this.startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }
}
