package com.example.wsizoprojekt.absolwent;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class rejestracjaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final API api = new API();

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

                try {
                    if(api.czyKontoIstnieje(login)) {
                        Toast.makeText(rejestracjaActivity.this, "Konto o nazwie "+login+" już istnieje!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Map<String, String> data = api.rejestruj(login, imie, haslo, "", "", data_urodzenia);
                        Toast.makeText(rejestracjaActivity.this, "Zarejestrowano", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(rejestracjaActivity.this, MainActivity.class);
                        rejestracjaActivity.this.startActivity(intent);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }
}
