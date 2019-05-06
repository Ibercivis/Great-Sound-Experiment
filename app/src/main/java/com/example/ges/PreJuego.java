package com.example.ges;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PreJuego extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_juego);
    }

    public void aJuego(View view){
        Intent intent = new Intent (this, Nivel1.class);
        startActivity(intent);
        finish();
    }

    public void aStats(View view){
        Intent intent = new Intent (this, Estadisticas.class);
        startActivity(intent);
        finish();
    }

    public void aPerfil(View view){
        Intent intent = new Intent (this, Perfil.class);
        startActivity(intent);
        finish();
    }
}
