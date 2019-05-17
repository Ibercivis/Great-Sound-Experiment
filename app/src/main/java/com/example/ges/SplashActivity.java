package com.example.ges;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ProgressBar pd = findViewById(R.id.progressdialog);
        pd = new ProgressBar(SplashActivity.this);

        esperarYMain(2000);

    }

    public void transi1(){
        ProgressBar pd = findViewById(R.id.progressdialog);
        pd.setVisibility(View.INVISIBLE);
        ImageView logo = findViewById(R.id.imageView);
        Intent intent = new Intent(this, MainActivity.class);
        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this, logo, "mainlogo");
        startActivity(intent, transitionActivityOptions.toBundle());

    }

    public void esperarYMain(int milisegundos) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos

                transi1();
            }
        }, milisegundos);
    }

}
