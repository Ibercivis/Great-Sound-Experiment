package com.example.ges;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Enhorabuena extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enhorabuena);
    }

    public void aJuego(View view){
        Intent intent = new Intent (this, Nivel1.class);
        startActivityForResult(intent, 0);
    }

    public void aStats(View view){
        Intent intent = new Intent (this, Estadisticas.class);
        startActivityForResult(intent, 0);
    }
}
