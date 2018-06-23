package com.example.wsizoprojekt.absolwent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class profil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        final API api = new API();

        try {
            api.pobierzDaneUzytkownika(autoryzacja.user_id);
            final TextView imie_ = findViewById(R.id.imie);
            final TextView nick_ = findViewById(R.id.nick);
            final TextView miejscowosc_ = findViewById(R.id.miejscowosc);
            final TextView opis_ = findViewById(R.id.opis);

            imie_.setText(api.response.optString("imie"));
            nick_.setText(api.response.optString("nick"));
            miejscowosc_.setText(api.response.optString("miejscowosc"));
            opis_.setText(api.response.optString("opis"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
