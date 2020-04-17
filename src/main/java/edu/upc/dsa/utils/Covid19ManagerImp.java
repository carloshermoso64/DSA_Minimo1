package edu.upc.dsa.utils;

import edu.upc.dsa.models.Brote;
import edu.upc.dsa.models.Caso;
import org.apache.log4j.Logger;

import java.util.*;

public class Covid19ManagerImp implements Covid19Manager {

    private HashMap<String, Brote> allBrotes;
    protected List<Caso> allCasos;
    final static Logger logger = Logger.getLogger(Covid19ManagerImp.class);

    private static Covid19Manager instance;


    private Covid19ManagerImp() {

        this.allBrotes = new HashMap<String, Brote>();
        this.allCasos = new LinkedList<>();
    }

    public static Covid19Manager getInstance() {
        if (instance == null) instance = new Covid19ManagerImp();
        return instance;
    }

    @Override
    public List<Brote> getBrotes() {
        logger.info("getBrotes");

        List<Brote> broteList = new LinkedList<Brote>(this.allBrotes.values());

        logger.info("brotelist");
        return broteList;
    }

    @Override
    public void addBrote(String ubicacion, int fecha, String id) {
        logger.info(ubicacion + " " + fecha + "  id: " + id);
        Brote b = new Brote(ubicacion, fecha, id);
        this.allBrotes.put(b.getId(), b);
        logger.info(b.getUbicacion() + " " + b.getFecha() + " registered with id: " + b.getId());
    }

    @Override
    public Brote getBrote(String stringid) {
        return this.allBrotes.get(stringid);
    }

    @Override
    public void addCasoToBrote(String idbrote, String nombre, String apellido, String id, int fechanacimiento, int fechainforme, String riesgo, String genero, String mail, String telefono, String direccion, String clasificacion) {

        Brote b = this.allBrotes.get(idbrote);
        Caso c = new Caso(nombre, apellido, id, fechanacimiento, fechainforme, riesgo, genero, mail, telefono, direccion, clasificacion);
        b.addCaso(c);
        this.allBrotes.put(idbrote, b);
        logger.info(b.getId() + " received: " + c.getNombre());
    }

    @Override
    public List<Caso> getCasos(String idbrote) {

        logger.info(idbrote + " idbrote");
        Brote b = this.allBrotes.get(idbrote);
        this.allCasos = b.getCasos();
        Collections.sort(allCasos);
        logger.info("allCasos");

        return allCasos;

        //Collections.sort(broteList);


    }



    @Override
    public void clear() {
        instance = null;
        this.allBrotes.clear();
        this.allCasos.clear();
        logger.info("Closing..........");
    }
}
