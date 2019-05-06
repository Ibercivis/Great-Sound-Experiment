package com.example.ges;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Spinner sexospinner = (Spinner) findViewById(R.id.spinnersexo);
        Spinner edadspinner = (Spinner) findViewById(R.id.spinneredad);
        Spinner cascosspinner = (Spinner) findViewById(R.id.spinnerauriculares);
        Spinner preciospinner = (Spinner) findViewById(R.id.spinnerprecios);
        Spinner formaspinner = (Spinner) findViewById(R.id.spinnerformacion);
// Creamos adaptador, fijamos la vista dropdown y asociamos adaptador a array y spinner
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.sexo_array, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sexospinner.setAdapter(adapter1);
        // Creamos adaptador, fijamos la vista dropdown y asociamos adaptador a array y spinner
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.edades_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edadspinner.setAdapter(adapter2);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.tipocascos_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cascosspinner.setAdapter(adapter3);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.precios_array, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        preciospinner.setAdapter(adapter4);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.formacion_array, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formaspinner.setAdapter(adapter5);
    }

    public void aJuego(View view){
        Intent intent = new Intent (this, PreJuego.class);
        startActivity(intent);
        finish();
    }

    public void aStats(View view){
        Intent intent = new Intent (this, Estadisticas.class);
        startActivity(intent);
        finish();
    }
}
