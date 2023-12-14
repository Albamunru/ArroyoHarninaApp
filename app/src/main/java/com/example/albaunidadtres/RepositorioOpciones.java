package com.example.albaunidadtres;

import java.util.ArrayList;

public class RepositorioOpciones {
    private ArrayList<String> repositorioOpciones;


    public RepositorioOpciones() {
        this.repositorioOpciones = rellenarDatos();
    }

    @Override
    public String toString() {
        return "RepositorioOpciones{" +
                "repositorioOpciones=" + repositorioOpciones +
                '}';
    }

    public ArrayList<String> getRepositorioOpciones() {

        return repositorioOpciones;
    }

    public ArrayList<String>rellenarDatos(){
        ArrayList<String> misDatosSpinner= new ArrayList<>();
        misDatosSpinner.add("Elije  una opcion:");
        misDatosSpinner.add("Rayuela");
        misDatosSpinner.add("Enviar e-mail");
        misDatosSpinner.add("Llamar al centro");
        misDatosSpinner.add("Calendario");
        misDatosSpinner.add("Info");


        return misDatosSpinner;
    }


}
