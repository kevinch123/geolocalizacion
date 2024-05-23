package com.basesdedatos.repository;

import java.sql.SQLException;
import java.util.List;

import com.basesdedatos.model.Ubicacion;

public interface RepositoryPais<T> {
    List<Ubicacion> getPais(String filtro) throws SQLException;

}
