package edu.upc.dsa.models;


import java.util.Date;

public class Caso implements Comparable<Caso> {
    String nombre;
    String apellido;
    String id;
    int fechanacimiento;
    int fechainforme;
    String riesgo; //A M B
    String genero; // M F
    String mail;
    String telefono;
    String direccion;
    String clasificacion;

    public Caso(){}

    public Caso(String nombre, String apellido, String id, int fechanacimiento, int fechainforme, String riesgo, String genero, String mail, String telefono, String direccion, String clasificacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.fechanacimiento = fechanacimiento;
        this.fechainforme = fechainforme;
        this.riesgo = riesgo;
        this.genero = genero;
        this.mail = mail;
        this.telefono = telefono;
        this.direccion = direccion;
        this.clasificacion = clasificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(int fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public int getFechainforme() {
        return fechainforme;
    }

    public void setFechainforme(int fechainforme) {
        this.fechainforme = fechainforme;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    @Override
    public int compareTo(Caso c) {
        if (getApellido() == null || c.getApellido() == null) {
            return 0;
        }
        return 1;
    }
}
