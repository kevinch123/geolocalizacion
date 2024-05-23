package com.basesdedatos.model;

public class PuntosDemograficos {
    private Integer ID;
    private String Nombre;
    private int Poblacion;
    private String EdadPromedio;
    private String NivelEducativo;
    private String DatosDemograficos;
    

    public PuntosDemograficos() {
    }

    public PuntosDemograficos(Integer iD, String nombre, int poblacion, String edadPromedio, String nivelEducativo,
            String datosDemograficos) {
        ID = iD;
        Nombre = nombre;
        Poblacion = poblacion;
        EdadPromedio = edadPromedio;
        NivelEducativo = nivelEducativo;
        DatosDemograficos = datosDemograficos;
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
    public int getPoblacion() {
        return Poblacion;
    }
    public void setPoblacion(int poblacion) {
        Poblacion = poblacion;
    }
    public String getEdadPromedio() {
        return EdadPromedio;
    }
    public void setEdadPromedio(String edadPromedio) {
        EdadPromedio = edadPromedio;
    }
    public String getNivelEducativo() {
        return NivelEducativo;
    }
    public void setNivelEducativo(String nivelEducativo) {
        NivelEducativo = nivelEducativo;
    }
    public String getDatosDemograficos() {
        return DatosDemograficos;
    }
    public void setDatosDemograficos(String datosDemograficos) {
        DatosDemograficos = datosDemograficos;
    }
    @Override
    public String toString() {
        return "PuntosDemograficos [ID=" + ID + ", Nombre=" + Nombre + ", Poblacion=" + Poblacion + ", EdadPromedio="
                + EdadPromedio + ", NivelEducativo=" + NivelEducativo + ", DatosDemograficos=" + DatosDemograficos
                + "]";
    }


}