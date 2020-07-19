package com.example.aise_android_v1.tarjetas;

public class Avisos {

    private String Texto;
    private String Fecha;

    public Avisos() {
    }

    public Avisos(String Texto, String Fecha) {
        this.Texto = Texto;
        this.Fecha = Fecha;
    }

    public String getTexto() {
        return Texto;
    }

    public void setTexto(String Texto) {
        this.Texto = Texto;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
}
