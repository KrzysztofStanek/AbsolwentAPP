package com.example.wsizoprojekt.absolwent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class dodaj_impreze extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_impreze);

        Button bdodaj = findViewById(R.id.bdodaj);

        Button banuluj = findViewById(R.id.banuluj);



        bdodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText nazwaimprezy_ = findViewById(R.id.nazwaimprezy);
                final String nazwaimprezy = nazwaimprezy_.getText().toString();

                DatePicker dataimprezy_ = findViewById(R.id.dataimprezy);
                String dzien = String.format("%02d", dataimprezy_.getDayOfMonth());
                String miesiac = String.format("%02d", dataimprezy_.getMonth()+1);
                String rok= String.format("%04d", dataimprezy_.getYear());
                final String dataimprezy = rok+"-"+miesiac+"-"+dzien;

                EditText miejscowoscimprezy_ = findViewById(R.id.miejscowoscimprezy);
                final String miejscowoscimprezy = miejscowoscimprezy_.getText().toString();

                Spinner spinner = (Spinner) findViewById(R.id.wojewodztwoimpreza);
                final String wojewodztwoimpreza = spinner.getSelectedItem().toString();

                EditText opisimprezy_ = findViewById(R.id.opisimprezy);
                final String opisimprezy = opisimprezy_.getText().toString();

                final API api = new API();

                try {
                    api.dodaj_impreze(nazwaimprezy, dataimprezy, miejscowoscimprezy, wojewodztwoimpreza, opisimprezy);
                } catch (Exception e) {
                    e.printStackTrace();
                }
               // Toast.makeText(edycja_profilu_activity.this, "Zaktualizowano", Toast.LENGTH_SHORT).show();

                //Intent intent = new Intent(edycja_profilu_activity.this, tablica.class);
                //edycja_profilu_activity.this.startActivity(intent);
            }
        });

        banuluj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dodaj_impreze.this, tablica.class);
                dodaj_impreze.this.startActivity(intent);
            }
        });
    }
}
