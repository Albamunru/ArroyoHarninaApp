package com.example.albaunidadtres;

import java.util.ArrayList;

public class RepositorioPaises {
    private ArrayList<Pais> miListaPaises;


    public RepositorioPaises() {
        this.miListaPaises = rellenarPaises();
    }


    public ArrayList<Pais> getMiListaPaises() {
        return miListaPaises;
    }

    public void setMiListaPaises(ArrayList<Pais> miListaPaises) {
        this.miListaPaises = miListaPaises;
    }

    private ArrayList<Pais> rellenarPaises(){

        ArrayList<Pais> repositorio= new ArrayList<>();
        repositorio.add(new Pais("Argentina",R.drawable.argentina,1));
        repositorio.add(new Pais("Bolivia",R.drawable.bolivia,1));
        repositorio.add(new Pais("Brasil",R.drawable.brasil,1));
        repositorio.add(new Pais("China",R.drawable.china,1));
        repositorio.add(new Pais("Colombia",R.drawable.colombia,1));
        repositorio.add(new Pais("Italia",R.drawable.italia,1));
        repositorio.add(new Pais("Marruecos",R.drawable.marruecos,13));
        repositorio.add(new Pais("Nicaragua",R.drawable.nicaragua,3));
        repositorio.add(new Pais("Portugal",R.drawable.portugal,3));
        repositorio.add(new Pais("Rumania",R.drawable.rumania,11));
        repositorio.add(new Pais("Ucrania",R.drawable.ucrania,1));
        repositorio.add(new Pais("Uruguay",R.drawable.uruguay,2));
        repositorio.add(new Pais("Venezuela",R.drawable.venezuela,4));
        repositorio.add(new Pais("Honduras",R.drawable.honduras,3));



        return  repositorio;
    }


}
