package com.example.wsizoprojekt.absolwent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class edycja_profilu_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edycja_profilu_activity);


        Button banuluj = findViewById(R.id.banuluj);



        banuluj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(edycja_profilu_activity.this, tablica.class);
                edycja_profilu_activity.this.startActivity(intent);
            }
        });



    }
}
