package com.basesdedatos.repository;

import java.sql.SQLException;
import java.util.List;

import com.basesdedatos.model.Ubicacion;

public interface RepositoryOrganizar<T> {
    List<Ubicacion> organizarCiudad(String filtro) throws SQLException;

}
