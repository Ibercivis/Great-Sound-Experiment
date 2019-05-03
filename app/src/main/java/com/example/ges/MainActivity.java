package com.example.ges;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void aRegistro(View view) {
        Intent intent = new Intent (this, Registro.class);
        startActivity(intent);
        finish();
    }

    public void aLogin(View view) {
        Intent intent = new Intent (this, Login.class);
        startActivity(intent);
        finish();

    }
}
