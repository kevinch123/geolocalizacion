package com.basesdedatos.repository;

import java.sql.SQLException;
import java.util.List;

import com.basesdedatos.model.PuntosInteres;
import com.basesdedatos.model.Ubicacion;

public interface Repository<T> {
    List<T> findAll() throws SQLException;
    T getById(Integer id) throws SQLException;
    void save(T entidad) throws SQLException;
    void delete(Integer id) throws SQLException;
    Integer getMayorPoblacion(int poblacion) throws SQLException;
    List<T> getFiltro(String filtro) throws SQLException;
    List<T> getLocalizacion(String localizacion) throws SQLException;

}

