package com.basesdedatos.repository;

import java.sql.SQLException;
import java.util.List;

import com.basesdedatos.model.PuntosDemograficos;
import com.basesdedatos.model.PuntosInteres;

public interface Repository<T> {
    List<T> findAll() throws SQLException;
    T getById(Integer id) throws SQLException;
    void save(T entidad) throws SQLException;
    void delete(Integer id) throws SQLException;
    List<T> getFiltro(String filtro) throws SQLException;
    List<T> getLocalizacion(String localizacion) throws SQLException;


    List<PuntosDemograficos> getPoblacion(int poblacion);
    List<PuntosDemograficos> getPointsByPopulation(int poblacion);
    List<PuntosDemograficos> getMayorPoblacion(int poblacion) throws SQLException;
    List<T> poblacionMenor(int poblacion, float nivelEducativo) throws SQLException;


}

