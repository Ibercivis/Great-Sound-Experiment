package com.example.ges;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CircularProgressButton boton1 = findViewById(R.id.button);
        final CircularProgressButton boton2 = findViewById(R.id.button2);


        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton1.startAnimation();

                esperarYRegistro(boton1, 650);


            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boton2.startAnimation();
                esperarYLogin(boton2, 650);
            }
        });



    }

    public void aRegistro() {
        Intent intent = new Intent (this, Registro.class);
        startActivity(intent);
        finish();
    }

    public void aLogin() {
        Intent intent = new Intent (this, Login.class);
        startActivity(intent);
        finish();

    }

    public void esperarYRegistro(final CircularProgressButton btn, int milisegundos) {
        final Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.juego_blanco_def);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                btn.doneLoadingAnimation(R.color.colorAccent, bm);
                aRegistro();
            }
        }, milisegundos);
    }

    public void esperarYLogin(final CircularProgressButton btn, int milisegundos) {
        final Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.juego_blanco_def);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                btn.doneLoadingAnimation(R.color.colorAccent, bm);
                aLogin();
            }
        }, milisegundos);
    }
}
