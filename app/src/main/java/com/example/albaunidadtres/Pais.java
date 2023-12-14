package com.example.albaunidadtres;

public class Pais {

    private String nombre;
    private  int imagen;
    private int numeroAlumno;


    public Pais(String nombre, int imagen, int numeroAlumno) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.numeroAlumno = numeroAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public int getNumeroAlumno() {
        return numeroAlumno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setNumeroAlumno(int numeroAlumno) {
        this.numeroAlumno = numeroAlumno;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
