package com.example.aise_android_v1.tarjetas;

public class Calificaciones {

    private String Periodo;
    private String Materia;
    private String Resultado;
    private String Fecha;

    public Calificaciones() {
    }

    public Calificaciones(String periodo, String materia, String resultado, String fecha) {
        Periodo = periodo;
        Materia = materia;
        Resultado = resultado;
        Fecha = fecha;
    }

    public String getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(String periodo) {
        Periodo = periodo;
    }

    public String getMateria() {
        return Materia;
    }

    public void setMateria(String materia) {
        Materia = materia;
    }

    public String getResultado() {
        return Resultado;
    }

    public void setResultado(String resultado) {
        Resultado = resultado;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
