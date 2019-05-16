package com.example.ges;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

public class Jugar extends AppCompatActivity {

    public MediaPlayer mp1, mp2;
    public ImageView bplay1, bplay2;
    int cantidadAudios=13;
    int sonando = 0;
    int sonando2= 0;
    int sonando1 = 0;
    Button voto1, voto2;
    public Resultado result1, result2, result3;
    int partidas = 1;
    ImageView barra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        BottomNavigationView bottomView = findViewById(R.id.bottom_bar);


        final StatsFragment statsFragment = new StatsFragment();
        final ProfileFragment profileFragment = new ProfileFragment();
        final JugarFragment jugarFragment = new JugarFragment();

        setFragment(statsFragment);

        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.jugarmenu){
                    setFragment(jugarFragment);
                    return true;
                } else if(id == R.id.perfilmenu){
                    setFragment(profileFragment);
                    return true;
                } else if(id == R.id.estadisticasmenu){
                    setFragment(statsFragment);
                    return true;
                }
                return false;
            }
        });
    }

    void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();

    }

    public Resultado generarPares(int elnivel){
        int aleatorio;
        String nombre, KEY;
        HashMap<String,Partida> listaPartidas = new HashMap<String,Partida>();
        aleatorio = (int) (Math.random() * cantidadAudios) + 1;
        KEY=""+elnivel+aleatorio;
        int audioganador=2;



        for (int i = 1; i < (cantidadAudios+1); i++) {

            nombre = ""+elnivel+i; // Esta es la KEY para sacar parejas del HashMap
            Partida partida = new Partida(elnivel, i);
            listaPartidas.put(nombre, partida); // Generada una lista de pares de audios de nivel 1.
        }
        Partida partidafinal = listaPartidas.get(KEY);
        int id1 = getApplicationContext().getResources().getIdentifier(partidafinal.getAudio1(), "raw", getApplicationContext().getPackageName());
        int id2 = getApplicationContext().getResources().getIdentifier(partidafinal.getAudio2(), "raw", getApplicationContext().getPackageName());

        double aleatorio2 = (Math.random());
        if(aleatorio2<=0.5){
            mp1 = MediaPlayer.create(getApplicationContext(), id1);
            mp2 = MediaPlayer.create(getApplicationContext(), id2);
            audioganador = 2;
        }
        if (aleatorio2>0.5){
            mp2 = MediaPlayer.create(getApplicationContext(), id1);
            mp1 = MediaPlayer.create(getApplicationContext(), id2);
            audioganador = 1;
        }

        Resultado result = new Resultado(KEY, audioganador);

        return result;

    }

    public void play1() {

        sonando1=1;

        if(sonando2==1){
            voto1.setVisibility(View.VISIBLE);
            voto2.setVisibility(View.VISIBLE);
        }

        if(mp1.isPlaying()==false){

            bplay1.setImageResource(R.drawable.volume_azul);
            bplay2.setImageResource(R.drawable.mute_blanco);
            mp1.start();
            mp2.start();
            mp1.setVolume(1,1);
            mp2.setVolume(0, 0);
            sonando = 1;

        }



        if(mp1.isPlaying()==true){
            if(sonando==1){


            }
            if(sonando==2){
                bplay1.setImageResource(R.drawable.volume_azul);
                bplay2.setImageResource(R.drawable.mute_blanco);
                mp1.setVolume(1,1);
                mp2.setVolume(0, 0);
                sonando=1;

            }
        }
    }

    public void play2() {

        sonando2 = 1;

        if(sonando1==1){
            voto1.setVisibility(View.VISIBLE);
            voto2.setVisibility(View.VISIBLE);
        }

        if(mp2.isPlaying()==false){
            sonando = 2;
            bplay2.setImageResource(R.drawable.volume_azul);
            bplay1.setImageResource(R.drawable.mute_blanco);
            mp1.start();
            mp2.start();
            mp1.setVolume(0,0);
            mp2.setVolume(1, 1);


        }



        if(mp2.isPlaying()==true){
            if(sonando==2){


            }
            if(sonando==1){
                bplay2.setImageResource(R.drawable.volume_azul);
                bplay1.setImageResource(R.drawable.mute_blanco);
                mp1.setVolume(0,0);
                mp2.setVolume(1, 1);
                sonando=2;


            }
        }
    }

    public void votar1(){
        int elresultado1, elresultado2, elresultado3;
        String laclave1, laclave2, laclave3;
        mp1.stop();
        mp2.stop();
        if(partidas==1){
            elresultado1 = result1.getGanador();
            laclave1 = result1.getKey();
            if(elresultado1==1){

                Toast toast1 = Toast.makeText(getApplicationContext(), "¡Has acertado. Sigue compitiendo!", Toast.LENGTH_SHORT);
                barra.setImageResource(R.drawable.barranivel2);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                result2=generarPares(1);
                laclave2=result2.getKey();
                bplay2.setImageResource(R.drawable.mute_blanco);
                bplay1.setImageResource(R.drawable.mute_blanco);
                if (laclave2.equals(laclave1)){
                    result2=generarPares(1);
                    bplay2.setImageResource(R.drawable.mute_blanco);
                    bplay1.setImageResource(R.drawable.mute_blanco);
                }
            }
            if(elresultado1==2){
                Toast toast1 = Toast.makeText(getApplicationContext(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aStats();
            }
        }
        if(partidas==2){
            elresultado2 = result2.getGanador();
            laclave2 = result2.getKey();
            if(elresultado2==1){

                Toast toast1 = Toast.makeText(getApplicationContext(), "¡Has acertado. Sigue compitiendo!", Toast.LENGTH_SHORT);
                barra.setImageResource(R.drawable.barranivel3);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                result3=generarPares(1);
                laclave3=result3.getKey();
                bplay2.setImageResource(R.drawable.mute_blanco);
                bplay1.setImageResource(R.drawable.mute_blanco);
                if (laclave3.equals(laclave2)){
                    result3=generarPares(1);
                    bplay2.setImageResource(R.drawable.mute_blanco);
                    bplay1.setImageResource(R.drawable.mute_blanco);
                }
            }
            if(elresultado2==2){
                Toast toast1 = Toast.makeText(getApplicationContext(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aStats();
            }
        }
        if(partidas==3){
            elresultado3 = result3.getGanador();
            if(elresultado3==1){

                Toast toast1 = Toast.makeText(getApplicationContext(), "¡Pasas al siguiente nivel!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aNivel2();

            }
            if(elresultado3==2){
                Toast toast1 = Toast.makeText(getApplicationContext(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aStats();
            }
        }
        partidas = (partidas + 1);
    }

    public void votar2(){
        int elresultado1, elresultado2, elresultado3;
        String laclave1, laclave2, laclave3;
        mp1.stop();
        mp2.stop();
        if(partidas==1){
            elresultado1 = result1.getGanador();
            laclave1 = result1.getKey();
            if(elresultado1==2){

                Toast toast1 = Toast.makeText(getApplicationContext(), "¡Has acertado. Sigue compitiendo!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();

                result2=generarPares(1);
                laclave2=result2.getKey();
                bplay2.setImageResource(R.drawable.mute_blanco);
                bplay1.setImageResource(R.drawable.mute_blanco);
                if (laclave2.equals(laclave1)){
                    result2=generarPares(1);
                    bplay2.setImageResource(R.drawable.mute_blanco);
                    bplay1.setImageResource(R.drawable.mute_blanco);
                }
            }
            if(elresultado1==1){
                Toast toast1 = Toast.makeText(getApplicationContext(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();

                aStats();
            }
        }
        if(partidas==2){
            elresultado2 = result2.getGanador();
            laclave2 = result2.getKey();
            if(elresultado2==2){

                Toast toast1 = Toast.makeText(getApplicationContext(), "¡Has acertado. Sigue compitiendo!", Toast.LENGTH_SHORT);
                barra.setImageResource(R.drawable.barranivel3);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                result3=generarPares(1);
                laclave3=result3.getKey();
                bplay2.setImageResource(R.drawable.mute_blanco);
                bplay1.setImageResource(R.drawable.mute_blanco);
                if (laclave3.equals(laclave2)){
                    result3=generarPares(1);
                    bplay2.setImageResource(R.drawable.mute_blanco);
                    bplay1.setImageResource(R.drawable.mute_blanco);
                }
            }
            if(elresultado2==1){
                Toast toast1 = Toast.makeText(getApplicationContext(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aStats();
            }
        }
        if(partidas==3){
            elresultado3 = result3.getGanador();
            if(elresultado3==2){

                Toast toast1 = Toast.makeText(getApplicationContext(), "¡Pasas al siguiente nivel!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aNivel2();

            }
            if(elresultado3==1){
                Toast toast1 = Toast.makeText(getApplicationContext(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aStats();
            }
        }
        partidas = (partidas + 1);

    }


    public void aStats(){

        setFragment(new StatsFragment());

    }

    public void aStatsB(View view){

        Intent intent = new Intent (getApplicationContext(), Estadisticas.class);
        startActivityForResult(intent, 0);

    }

    public void aNivel2(){

        Intent intent = new Intent (getApplicationContext(), Nivel2.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);


    }

    public void aJuego(View view){
        Intent intent = new Intent (getApplicationContext(), Nivel1.class);
        startActivity(intent);

    }


    public void aPerfil(View view){
        Intent intent = new Intent (getApplicationContext(), Perfil.class);
        startActivity(intent);

    }





}
