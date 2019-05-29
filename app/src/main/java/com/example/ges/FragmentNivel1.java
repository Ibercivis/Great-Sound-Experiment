package com.example.ges;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNivel1 extends Fragment {

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


    public FragmentNivel1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_nivel1, container, false);

        bplay1 = view.findViewById(R.id.boton1);
        bplay2 = view.findViewById(R.id.boton2);
        voto1 = view.findViewById(R.id.audio1);
        voto2 = view.findViewById(R.id.audio2);
        voto1.setVisibility(View.GONE);
        voto2.setVisibility(View.GONE);
        result1= generarPares(1);
        barra = view.findViewById(R.id.barraprogreso);

        bplay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play1();
            }
        });

        bplay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play2();
            }
        });

        voto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                votar1();
            }
        });

        voto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                votar2();
            }
        });



        return view;
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
        int id1 = getActivity().getResources().getIdentifier(partidafinal.getAudio1(), "raw", getActivity().getPackageName());
        int id2 = getActivity().getResources().getIdentifier(partidafinal.getAudio2(), "raw", getActivity().getPackageName());

        double aleatorio2 = (Math.random());
        if(aleatorio2<=0.5){
            mp1 = MediaPlayer.create(getActivity(), id1);
            mp2 = MediaPlayer.create(getActivity(), id2);
            audioganador = 2;
        }
        if (aleatorio2>0.5){
            mp2 = MediaPlayer.create(getActivity(), id1);
            mp1 = MediaPlayer.create(getActivity(), id2);
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

    public void play2() {

        sonando2 = 1;

        if(sonando1==1){
            voto1.setVisibility(View.VISIBLE);
            voto2.setVisibility(View.VISIBLE);
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

    public void votar1(){
        int elresultado1, elresultado2, elresultado3;
        String laclave1, laclave2, laclave3;
        mp1.stop();
        mp2.stop();
        if(partidas==1){
            elresultado1 = result1.getGanador();
            laclave1 = result1.getKey();
            if(elresultado1==1){
                gameRequest(1,1); //ACIERTO 1, 1
                Toast toast1 = Toast.makeText(getActivity(), "¡Has acertado. Sigue compitiendo!", Toast.LENGTH_SHORT);
                barra.setImageResource(R.drawable.barranivel2);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                result2=generarPares(1);
                laclave2=result2.getKey();
                bplay2.setImageResource(R.drawable.audio2_off);
                bplay1.setImageResource(R.drawable.audio1_off);
                if (laclave2.equals(laclave1)){
                    result2=generarPares(1);
                    bplay2.setImageResource(R.drawable.audio2_off);
                    bplay1.setImageResource(R.drawable.audio1_off);
                }
            }
            if(elresultado1==2){
                gameRequest(1,0); //FALLO 1, 1
                Toast toast1 = Toast.makeText(getActivity(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aStats();
            }
        }
        if(partidas==2){
            elresultado2 = result2.getGanador();
            laclave2 = result2.getKey();
            if(elresultado2==1){
                gameRequest(1,1); //ACIERTO 1, 2
                Toast toast1 = Toast.makeText(getActivity(), "¡Has acertado. Sigue compitiendo!", Toast.LENGTH_SHORT);
                barra.setImageResource(R.drawable.barranivel3);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                result3=generarPares(1);
                laclave3=result3.getKey();
                bplay2.setImageResource(R.drawable.audio2_off);
                bplay1.setImageResource(R.drawable.audio1_off);
                if (laclave3.equals(laclave2)){
                    result3=generarPares(1);
                    bplay2.setImageResource(R.drawable.audio2_off);
                    bplay1.setImageResource(R.drawable.audio1_off);
                }
            }
            if(elresultado2==2){
                gameRequest(1,0); //FALLO 1, 2
                Toast toast1 = Toast.makeText(getActivity(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aStats();
            }
        }
        if(partidas==3){
            elresultado3 = result3.getGanador();
            if(elresultado3==1){
                gameRequest(1,1); //ACIERTO 1, 3
                Toast toast1 = Toast.makeText(getActivity(), "¡Pasas al siguiente nivel!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aNivel2();

            }
            if(elresultado3==2){
                gameRequest(1,0); //FALLO 1, 3
                Toast toast1 = Toast.makeText(getActivity(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
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
                gameRequest(1,1); //ACIERTO 1, 3
                Toast toast1 = Toast.makeText(getActivity(), "¡Has acertado. Sigue compitiendo!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();

                result2=generarPares(1);
                laclave2=result2.getKey();
                bplay2.setImageResource(R.drawable.audio2_off);
                bplay1.setImageResource(R.drawable.audio1_off);
                if (laclave2.equals(laclave1)){
                    result2=generarPares(1);
                    bplay2.setImageResource(R.drawable.audio2_off);
                    bplay1.setImageResource(R.drawable.audio1_off);
                }
            }
            if(elresultado1==1){
                gameRequest(1,0); //FALLO 1, 3
                Toast toast1 = Toast.makeText(getActivity(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();

                aStats();
            }
        }
        if(partidas==2){
            elresultado2 = result2.getGanador();
            laclave2 = result2.getKey();
            if(elresultado2==2){
                gameRequest(1,1); //ACIERTO 1, 3
                Toast toast1 = Toast.makeText(getActivity(), "¡Has acertado. Sigue compitiendo!", Toast.LENGTH_SHORT);
                barra.setImageResource(R.drawable.barranivel3);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                result3=generarPares(1);
                laclave3=result3.getKey();
                bplay2.setImageResource(R.drawable.audio2_off);
                bplay1.setImageResource(R.drawable.audio1_off);
                if (laclave3.equals(laclave2)){
                    result3=generarPares(1);
                    bplay2.setImageResource(R.drawable.audio2_off);
                    bplay1.setImageResource(R.drawable.audio1_off);
                }
            }
            if(elresultado2==1){
                gameRequest(1,0); //FALLO 1, 3
                Toast toast1 = Toast.makeText(getActivity(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aStats();
            }
        }
        if(partidas==3){
            elresultado3 = result3.getGanador();
            if(elresultado3==2){
                gameRequest(1,1); //ACIERTO 1, 3
                Toast toast1 = Toast.makeText(getActivity(), "¡Pasas al siguiente nivel!", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
                aNivel2();

            }
            if(elresultado3==1){
                gameRequest(1,0); //FALLO 1, 3
                Toast toast1 = Toast.makeText(getActivity(), "Has fallado... ¡Vuelves a empezar!", Toast.LENGTH_SHORT);
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



    public void aNivel2(){

        setFragment(new FragmentNivel2());

    }



    void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();

    }


    public void gameRequest (final int elnivel, final int acierto) {



            // Input data ok, so go with the request

            // Url for the webservice
            String url = getString(R.string.base_url) + "/game.php";

            RequestQueue queue = Volley.newRequestQueue(getActivity());
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
                            toast = Toast.makeText(getActivity(), text, duration);
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
                    SessionManager session = new SessionManager(getActivity());
                    login_params.put("idUser", String.valueOf(session.getIdUser()));
                    login_params.put("token", session.getToken());
                    login_params.put("level", String.valueOf(elnivel));
                    login_params.put("succes", String.valueOf(acierto));

                    return login_params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            queue.add(sr);
        }

    }


