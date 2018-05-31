package com.example.wsizoprojekt.absolwent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class oaplikacji extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oaplikacji);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button bOaplikacji_regulamin = findViewById(R.id.bOaplikacji_regulamin);
        Button bOaplikacji_politykaprywatności = findViewById(R.id.bOaplikacji_politykaprywatności);
        Button bOaplikacji_Autorzy = findViewById(R.id.bOaplikacji_Autorzy);

        bOaplikacji_regulamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(oaplikacji.this, oaplikacji_regulamin.class);
                oaplikacji.this.startActivity(intent);
            }
        });

        bOaplikacji_politykaprywatności.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(oaplikacji.this, oaplikacji_politykaprywatnosci.class);
                oaplikacji.this.startActivity(intent);
            }
        });

        bOaplikacji_Autorzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(oaplikacji.this, oaplikacji_autorzy.class);
                oaplikacji.this.startActivity(intent);
            }
        });
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();

        return true;
    }

}