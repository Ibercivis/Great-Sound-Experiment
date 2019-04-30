package com.example.ges;

public class Partida {

    public String idPartida;
    public int nivel;
    public String audio1;
    public String audio2;
    public String audioBueno;
    public int cantidadAudios = 13;

    public String getIdPartida()
    {
        return idPartida;
    }

    public int getNivel() {
        return nivel;
    }

    public String getAudio1() {
        return audio1;
    }

    public String getAudio2() {
        return audio2;
    }

    public String getAudioBueno() {
        return audioBueno;
    }

    public void setIdPartida(String id_partida) {
        idPartida = id_partida;
    }

    public void setNivel(int elnivel) {
        nivel = elnivel;
    }

    public void setAudio1(String elaudio1) {
        audio1 = elaudio1;
    }

    public void setAudio2(String elaudio2) {
        audio2 = elaudio2;
    }

    public void setAudioBueno(String elaudio3) {
        audioBueno = elaudio3;
    }

    public Partida (int elnivel, int contador){

        //Constructor al que le vamos a pasar el nivel y un contador (entre 1 y cantidadAudios), y nos va a crear la pareja que corresponda a ese nivel y al número del contador.

        int i = contador; //Contador para castear a String y poder llamar a los audios.
        nivel = elnivel;
        String level = ""+nivel+contador;
        idPartida = level;
        int grupo1;
        int grupo2;


        if(nivel==1){
            grupo1 = 0;
            grupo2 = 1;
            audio1 = "a"+grupo1+contador;
            audio2 = "a"+grupo2+contador;
            audioBueno = audio2;
        }
        if(nivel==2){
            grupo1 = 2;
            grupo2 = 1;
            audio1 = "a"+grupo1+contador;
            audio2 = "a"+grupo2+contador;
            audioBueno = audio2;
        }
        if(nivel==3){
            grupo1 = 2;
            grupo2 = 3;
            audio1 = "a"+grupo1+contador;
            audio2 = "a"+grupo2+contador;
            audioBueno = audio2;
        }
        if(nivel==4){
            grupo1 = 4;
            grupo2 = 5;
            audio1 = "a"+grupo1+contador;
            audio2 = "a"+grupo2+contador;
            audioBueno = audio2;
        }
        if(nivel==5){
            grupo1 = 5;
            grupo2 = 1;
            audio1 = "a"+grupo1+contador;
            audio2 = "a"+grupo2+contador;
            audioBueno = audio2;
        }



    }


}
