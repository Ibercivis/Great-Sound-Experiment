package com.example.ges;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Nivel5 extends AppCompatActivity {

    public MediaPlayer mp1, mp2;
    public ImageView bplay1, bplay2;
    int cantidadAudios=25;
    int sonando = 0;
    int sonando1 = 0;
    int sonando2 = 0;
    Button voto1, voto2;
    public Resultado result1, result2, result3;
    int partidas = 1;
    ImageView barra;
    LinearLayout votovoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel5);
        bplay1 = findViewById(R.id.boton1);
        bplay2 = findViewById(R.id.boton2);
        voto1 = findViewById(R.id.audio1);
        voto2 = findViewById(R.id.audio2);
        int int1;
        result1=generarPares(5);
        barra = findViewById(R.id.barraprogreso);
        votovoto = findViewById(R.id.paravotar);



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
            mp1 = MediaPlayer.create(this, id1);
            mp2 = MediaPlayer.create(this, id2);
            audioganador = 2;
        }
        if (aleatorio2>0.5){
            mp2 = MediaPlayer.create(this, id1);
            mp1 = MediaPlayer.create(this, id2);
            audioganador = 1;
        }

        Resultado result = new Resultado(KEY, audioganador);


        return result;

    }

    public void play1(View view) {

        sonando1=1;

        if(sonando2==1){
            voto1.setVisibility(View.VISIBLE);
            voto2.setVisibility(View.VISIBLE);
            votovoto.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        }

        if(mp1.isPlaying()==false){

            bplay1.setImageResource(R.drawable.audio1_on);
            bplay2.setImageResource(R.drawable.audio2_off);
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
                bplay1.setImageResource(R.drawable.audio1_on);
                bplay2.setImageResource(R.drawable.audio2_off);
                mp1.setVolume(1,1);
                mp2.setVolume(0, 0);
                sonando=1;

            }
        }
    }

    public void play2(View view) {

        sonando2 = 1;

        if(sonando1==1){
            voto1.setVisibility(View.VISIBLE);
            voto2.setVisibility(View.VISIBLE);
            votovoto.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        }

        if(mp2.isPlaying()==false){
            sonando = 2;
            bplay2.setImageResource(R.drawable.audio2_on);
            bplay1.setImageResource(R.drawable.audio1_off);
            mp1.start();
            mp2.start();
            mp1.setVolume(0,0);
            mp2.setVolume(1, 1);


        }



        if(mp2.isPlaying()==true){
            if(sonando==2){


            }
            if(sonando==1){
                bplay2.setImageResource(R.drawable.audio2_on);
                bplay1.setImageResource(R.drawable.audio1_off);
                mp1.setVolume(0,0);
                mp2.setVolume(1, 1);
                sonando=2;


            }
        }
    }

    public void votar1(View view){
        int elresultado1, elresultado2, elresultado3;
        String laclave1, laclave2, laclave3;
        mp1.stop();
        mp2.stop();
        if(partidas==1){
            elresultado1 = result1.getGanador();
            laclave1 = result1.getKey();
            if(elresultado1==1){
                gameRequest(5,1); //ACIERTO 5, 1
                Toast toast1 = Toast.makeText(getApplicationContext(), "¡Has acertado. Sigue compitiendo!", Toast.LENGTH_SHORT);
                barra.setImageResource(R.drawable.barranivel2);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                result2=generarPares(5);
                laclave2=result2.getKey();
                bplay2.setImageResource(R.drawable.mute_blanco);
                bplay1.setImageResource(R.drawable.mute_blanco);
                if (laclave2.equals(laclave1)){
                    result2=generarPares(5);
                    bplay2.setImageResource(R.drawable.mute_blanco);
                    bplay1.setImageResource(R.drawable.mute_blanco);
                }
            }
            if(elresultado1==2){
                gameRequest(5,0); //FALLO 5, 1
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
                gameRequest(5,1); //ACIERTO 5, 1
                Toast toast1 = Toast.makeText(getApplicationContext(), "¡Has acertado. Sigue compitiendo!", Toast.LENGTH_SHORT);
                barra.setImageResource(R.drawable.barranivel3);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                result3=generarPares(5);
                laclave3=result3.getKey();
                bplay2.setImageResource(R.drawable.mute_blanco);
                bplay1.setImageResource(R.drawable.mute_blanco);
                if (laclave3.equals(laclave2)){
                    result3=generarPares(5);
                    bplay2.setImageResource(R.drawable.mute_blanco);
                    bplay1.setImageResource(R.drawable.mute_blanco);
                }
            }
            if(elresultado2==2){
                gameRequest(5,0); //FALLO 5, 1
                Toast toast1 = Toast.makeText(getApplicationContext(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aStats();
            }
        }
        if(partidas==3){
            elresultado3 = result3.getGanador();
            if(elresultado3==1){
                gameRequest(5,1); //ACIERTO 5, 1
                Toast toast1 = Toast.makeText(getApplicationContext(), "¡RETO SUPERADO!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aEnhorabuena();

            }
            if(elresultado3==2){
                gameRequest(5,0); //FALLO 5, 1
                Toast toast1 = Toast.makeText(getApplicationContext(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aStats();
            }
        }
        partidas = (partidas + 1);
    }

    public void votar2(View view){
        int elresultado1, elresultado2, elresultado3;
        String laclave1, laclave2, laclave3;
        mp1.stop();
        mp2.stop();
        if(partidas==1){
            elresultado1 = result1.getGanador();
            laclave1 = result1.getKey();
            if(elresultado1==2){
                gameRequest(5,1); //ACIERTO 5, 1
                Toast toast1 = Toast.makeText(getApplicationContext(), "¡Has acertado. Sigue compitiendo!", Toast.LENGTH_SHORT);
                barra.setImageResource(R.drawable.barranivel2);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                result2=generarPares(5);
                laclave2=result2.getKey();
                bplay2.setImageResource(R.drawable.mute_blanco);
                bplay1.setImageResource(R.drawable.mute_blanco);
                if (laclave2.equals(laclave1)){
                    result2=generarPares(5);
                    bplay2.setImageResource(R.drawable.mute_blanco);
                    bplay1.setImageResource(R.drawable.mute_blanco);
                }
            }
            if(elresultado1==1){
                gameRequest(5,0); //FALLO 5, 1
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
                gameRequest(5,1); //ACIERTO 5, 1
                Toast toast1 = Toast.makeText(getApplicationContext(), "¡Has acertado. Sigue compitiendo!", Toast.LENGTH_SHORT);
                barra.setImageResource(R.drawable.barranivel3);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                result3=generarPares(5);
                laclave3=result3.getKey();
                bplay2.setImageResource(R.drawable.mute_blanco);
                bplay1.setImageResource(R.drawable.mute_blanco);
                if (laclave3.equals(laclave2)){
                    result3=generarPares(5);
                    bplay2.setImageResource(R.drawable.mute_blanco);
                    bplay1.setImageResource(R.drawable.mute_blanco);
                }
            }
            if(elresultado2==1){
                gameRequest(5,0); //FALLO 5, 1
                Toast toast1 = Toast.makeText(getApplicationContext(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aStats();
            }
        }
        if(partidas==3){
            elresultado3 = result3.getGanador();
            if(elresultado3==2){
                gameRequest(5,1); //ACIERTO 5, 1
                Toast toast1 = Toast.makeText(getApplicationContext(), "¡RETO SUPERADO!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aEnhorabuena();

            }
            if(elresultado3==1){
                gameRequest(5,0); //FALLO 5, 1
                Toast toast1 = Toast.makeText(getApplicationContext(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aStats();
            }
        }
        partidas = (partidas + 1);

    }


    public void aStats(){

        Intent intent = new Intent (this, Jugar.class);
        startActivityForResult(intent, 0);
        finish();

    }



    public void aEnhorabuena(){

        Intent intent = new Intent (this, Jugar.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        finish();
        startActivityForResult(intent, 0);


    }


    public void aPerfil(View view){
        Intent intent = new Intent (this, Perfil.class);
        startActivity(intent);
        finish();
    }

    public void gameRequest (final int elnivel, final int acierto) {



        // Input data ok, so go with the request

        // Url for the webservice
        String url = getString(R.string.base_url) + "/game.php";

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println(response.toString());

                    JSONObject responseJSON = new JSONObject(response);

                    if ((int) responseJSON.get("result") == 1) {
                        Log.println(1, "Game added succesfully", "Game added succesfully");



                    } else {
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast;
                        CharSequence text;

                        text = "Error: " + responseJSON.get("message") + ".";
                        toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();

                        // Clean the text fields for new entries

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> login_params = new HashMap<String, String>();
                SessionManager session = new SessionManager(getApplicationContext());
                login_params.put("idUser", String.valueOf(session.getIdUser()));
                login_params.put("token", session.getToken());
                login_params.put("level", String.valueOf(elnivel));
                login_params.put("success", String.valueOf(acierto));

                return login_params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        sr.setShouldCache(false);
        queue.add(sr);
    }

}




