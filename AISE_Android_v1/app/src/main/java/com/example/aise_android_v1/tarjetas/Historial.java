package com.example.aise_android_v1.tarjetas;

public class Historial {

    private String Fecha;
    private String Hora;
    private String Ubicacion;

    public Historial() {
    }

    public Historial(String fecha, String hora, String ubicacion) {
        Fecha = fecha;
        Hora = hora;
        Ubicacion = ubicacion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }
}
