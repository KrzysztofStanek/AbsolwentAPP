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


        Button bWyloguj = findViewById(R.id.bWyloguj);


        Button bRegulamin = findViewById(R.id.bRegulamin);


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
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }
}
