package com.basesdedatos.model;

public class Ubicacion {
    private Integer ID;
    private String Nombre;
    private String Latitud;
    private String Longitud;
    private String Direccion;
    private String Ciudad;
    private String Pais;
    private String CodigoPostal;
    
    public Ubicacion(){
        
    }

    
    public Ubicacion(Integer iD, String nombre, String Latitud, String Longitud, String Direccion, String Ciudad,
            String Pais, String CodigoPostal) {
        ID = iD;
        Nombre = nombre;
        this.Latitud = Latitud;
        this.Longitud = Longitud;
        this.Direccion = Direccion;
        this.Ciudad = Ciudad;
        this.Pais = Pais;
        this.CodigoPostal = CodigoPostal;
    }


    public Integer getID() {
        return ID;
    }

    public void setID(Integer iD) {
        ID = iD;
    }

   

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String Latitud) {
        this.Latitud = Latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String Longitud) {
        this.Longitud = Longitud;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }


    public String getCodigoPostal() {
        return CodigoPostal;
    }


    public void setCodigoPostal(String codigoPostal) {
        CodigoPostal = codigoPostal;
    }


    public String getNombre() {
        return Nombre;
    }


    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    

    

   
  

}
