package com.example.wsizoprojekt.absolwent;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class rejestracjaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final API api = new API();

        /*if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }*/

        Button bPotwierdzRejestracje = findViewById(R.id.bPotwierdzRejestracje);

        bPotwierdzRejestracje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText login_ = findViewById(R.id.login);
                String login = login_.getText().toString();

                EditText  imie_ = findViewById(R.id.imie);
                String imie = imie_.getText().toString();

                EditText  haslo_ = findViewById(R.id.haslo);
                String haslo = haslo_.getText().toString();

                EditText  data_urodzenia_ = findViewById(R.id.data_urodzenia);
                String data_urodzenia = data_urodzenia_.getText().toString();

                api.rejestruj(login, imie, haslo, "", "", data_urodzenia);
                Toast.makeText(rejestracjaActivity.this, "TODO", Toast.LENGTH_SHORT).show();
                /*try {
                    api.request();
                } catch (IOException e) {

                    Toast.makeText(rejestracjaActivity.this, "TODO", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }
}
