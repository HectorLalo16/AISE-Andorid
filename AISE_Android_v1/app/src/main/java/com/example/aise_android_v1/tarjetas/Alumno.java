package com.example.aise_android_v1.tarjetas;

public class Alumno {
    String Nombre;
    String Apellidos;
    String Hora;
    String Ubicacion;
    String Matricula;

    public Alumno() {
    }

    public Alumno(String nombre, String apellidos, String hora, String ubicacion, String matricula) {
        Nombre = nombre;
        Apellidos = apellidos;
        Hora = hora;
        Ubicacion = ubicacion;
        Matricula = matricula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
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

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String matricula) {
        Matricula = matricula;
    }
}
