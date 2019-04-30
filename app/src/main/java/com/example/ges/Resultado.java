package com.example.ges;

public class Resultado {

    public String Key;
    public int ganador;

    public String getKey() {
        return Key;
    }

    public int getGanador() {return ganador;}

    public void setKey(String clave) {
        Key = clave;
    }

    public void setGanador(int bueno) {
        ganador = bueno;
    }

    public Resultado (String laKey, int elganador){

        Key = laKey;
        ganador = elganador;

    }

}
