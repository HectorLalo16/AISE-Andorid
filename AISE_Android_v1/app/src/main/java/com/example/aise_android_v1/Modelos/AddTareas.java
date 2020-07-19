package com.example.aise_android_v1.Modelos;

public class AddTareas {

    private String id;
    private String Materia;
    private String Valor;
    private String Tarea;
    private String fechaRegistrada;
    private String fechaDeEntrega;

    public AddTareas() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTarea() {
        return Tarea;
    }

    public void setTarea(String tarea) {
        Tarea = tarea;
    }

    public String getFechaRegistrada() {
        return fechaRegistrada;
    }

    public void setFechaRegistrada(String fechaRegistrada) {
        this.fechaRegistrada = fechaRegistrada;
    }

    public String getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(String fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    @Override
    public String toString() {
        return "AddTareas{" +
                "id='" + id + '\'' +
                '}';
    }
}
