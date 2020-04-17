package edu.upc.dsa.utils;

import edu.upc.dsa.models.Brote;
import edu.upc.dsa.models.Caso;

import java.util.Date;
import java.util.List;

public interface Covid19Manager {
    public List<Brote> getBrotes();
    public Brote getBrote(String stringid);
    public void addBrote(String ubicacion, int fecha, String id);
    public void addCasoToBrote(String idbrote,String nombre, String apellido, String id, int fechanacimiento, int fechainforme, String riesgo, String genero, String mail, String telefono, String direccion, String clasificacion);
    public List<Caso> getCasos(String idbrote);
    public void clear();
}
