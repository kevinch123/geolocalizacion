package com.basesdedatos.model;

public class PuntosInteres {
    private Integer ID;
    private String Nombre;
    private String Tipo;
    private Integer Latitud;
    private String Longitud;
    private String Disponibilidad;
    
    public PuntosInteres() {
        
    }

    public PuntosInteres(Integer iD, String nombre, String tipo, Integer latitud, String longitud,
            String disponibilidad) {
        this.ID = iD;
        this.Nombre = nombre;
        this.Tipo = tipo;
        this.Latitud = latitud;
        this.Longitud = longitud;
        this.Disponibilidad = disponibilidad;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer iD) {
        ID = iD;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public Integer getLatitud() {
        return Latitud;
    }

    public void setLatitud(Integer latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public String getDisponibilidad() {
        return Disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        Disponibilidad = disponibilidad;
    }

    private int totalNoDisponibles;

    // Constructor y otros métodos de la clase

    public int getTotalNoDisponibles() {
        return totalNoDisponibles;
    }

    public void setTotalNoDisponibles(int totalNoDisponibles) {
        this.totalNoDisponibles = totalNoDisponibles;
    }
    private int totalDisponibles;

    // Constructor y otros métodos de la clase

    public int getTotalDisponibles() {
        return totalDisponibles;
    }

    public void setTotalDisponibles(int totalNoDisponibles) {
        this.totalDisponibles = totalDisponibles;
    }

    

    @Override
    public String toString() {
        return "PuntosInteres [ID=" + ID + ", Nombre=" + Nombre + ", Tipo=" + Tipo + ", Latitud=" + Latitud
                + ", Longitud=" + Longitud + ", Disponibilidad=" + Disponibilidad + "]";
    }
    
    



}