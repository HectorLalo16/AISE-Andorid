package com.example.aise_android_v1.tarjetas;

public class Tareas {

    private String Tarea;
    private String Materia;
    private String Valor;
    private String FechaRegistrada;
    private String FechaDeEntrega;
    private String Id;

    public Tareas() {
    }


    public Tareas(String tarea, String materia, String valor, String fechaRegistrada, String fechaDeEntrega, String id) {
        Tarea = tarea;
        Materia = materia;
        Valor = valor;
        FechaRegistrada = fechaRegistrada;
        FechaDeEntrega = fechaDeEntrega;
        Id = id;
    }


    public String getTarea() {
        return Tarea;
    }

    public void setTarea(String tarea) {
        Tarea = tarea;
    }

    public String getMateria() {
        return Materia;
    }

    public void setMateria(String materia) {
        Materia = materia;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }

    public String getFechaRegistrada() {
        return FechaRegistrada;
    }

    public void setFechaRegistrada(String fechaRegistrada) {
        FechaRegistrada = fechaRegistrada;
    }

    public String getFechaDeEntrega() {
        return FechaDeEntrega;
    }

    public void setFechaDeEntrega(String fechaDeEntrega) {
        FechaDeEntrega = fechaDeEntrega;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
