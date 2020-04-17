package edu.upc.dsa.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Brote {
    String ubicacion;
    int fecha;
    String id;
    List<Caso> casos;




    public Brote(String ubicacion, int fecha, String id) {
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.id = id;
        this.casos = new LinkedList<Caso>();
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public List<Caso> getCasos() {
        return casos;
    }

    public void setCasos(List<Caso> casos) {
        this.casos = casos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addCaso(Caso caso) {
        this.casos.add(caso);
    }
}
