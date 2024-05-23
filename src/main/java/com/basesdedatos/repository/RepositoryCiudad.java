package com.basesdedatos.repository;

import java.sql.SQLException;
import java.util.List;

import com.basesdedatos.model.Ubicacion;

public interface RepositoryCiudad<T> {
    List<Ubicacion> getCiudad(String filtro) throws SQLException;
    

}
