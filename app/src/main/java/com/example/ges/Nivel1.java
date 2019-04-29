package com.example.ges;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.HashMap;

public class Nivel1 extends AppCompatActivity {

    public MediaPlayer mp1, mp2;
    public ImageView bplay1, bplay2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel1);
        bplay1 = findViewById(R.id.boton1);
        bplay2 = findViewById(R.id.boton2);

    }


    public void generarPares(int elnivel){
        String nombre;
        HashMap<String,Partida> listaPartidas = new HashMap<String,Partida>();

        for (int i = 1; i < 14; i++) {

            nombre = ""+elnivel+i;
            Partida partida = new Partida(elnivel, i);
            listaPartidas.put(nombre, partida); // Generada una lista de pares de audios de nivel 1.


        }

    }

    public void play1() {
        bplay1.setImageResource(R.drawable.volume_azul);
        bplay2.setImageResource(R.drawable.mute_blanco);


    }


}


