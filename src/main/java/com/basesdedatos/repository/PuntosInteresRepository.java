package com.basesdedatos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.basesdedatos.config.DatabaseConnection;
import com.basesdedatos.model.PuntosDemograficos;
import com.basesdedatos.model.PuntosInteres;

public class PuntosInteresRepository implements Repository<PuntosInteres> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<PuntosInteres> findAll() throws SQLException {
        List<PuntosInteres> puntosInteresList = new ArrayList<>();
        String sql = "select * from puntosdeinteres";
        try (Statement statement = getConnection().createStatement()) {
         
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                PuntosInteres puntosInteres = createPuntosInteres(resultSet);
                puntosInteresList.add(puntosInteres);
            }
        }
        return puntosInteresList;
    }

    @Override
    public PuntosInteres getById(Integer id) throws SQLException {
        PuntosInteres puntosInteres = null;
        String sql = "SELECT * FROM puntosdeinteres WHERE ID = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    puntosInteres = createPuntosInteres(resultSet);
                }
            }
        }
        return puntosInteres;
    }

    @Override
    public void save(PuntosInteres puntosInteres) throws SQLException {
        String sql;
        if (puntosInteres.getID() != null && puntosInteres.getID() > 0) {
            sql = "UPDATE puntosdeinteres SET Nombre = ?, Tipo = ?, Latitud = ?, Longitud = ?, Disponibilidad = ? WHERE ID = ?";
        } else {
            sql = "INSERT INTO puntosdeinteres (Nombre, Tipo, Latitud, Longitud, Disponibilidad) VALUES (?, ?, ?, ?, ?)";
        }

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, puntosInteres.getNombre());
            statement.setString(2, puntosInteres.getTipo());
            statement.setInt(3, puntosInteres.getLatitud());
            statement.setString(4, puntosInteres.getLongitud());
            statement.setString(5, puntosInteres.getDisponibilidad());
            if (puntosInteres.getID() != null && puntosInteres.getID() > 0) {
                statement.setInt(6, puntosInteres.getID());
            }
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM puntosdeinteres WHERE ID = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
   

    @Override
public List<PuntosInteres> getFiltro(String filtro) throws SQLException {
    List<PuntosInteres> resultados = new ArrayList<>();
    String sql = "SELECT * FROM puntosdeinteres WHERE Tipo = ?";
    try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
        statement.setString(1, filtro);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                PuntosInteres puntosInteres = new PuntosInteres();
                puntosInteres.setID(resultSet.getInt("ID"));
                puntosInteres.setNombre(resultSet.getString("Nombre"));
                puntosInteres.setTipo(resultSet.getString("Tipo"));
                puntosInteres.setLatitud(resultSet.getInt("Latitud"));
                puntosInteres.setLongitud(resultSet.getString("Longitud"));
                puntosInteres.setDisponibilidad(resultSet.getString("Disponibilidad"));
                resultados.add(puntosInteres);
            }
        }
    }
    return resultados;
}


    @Override
    public List<PuntosInteres> getLocalizacion(String localizacion) throws SQLException {
        List<PuntosInteres> puntosInteresList = new ArrayList<>();
        // Modifica la consulta SQL según tus necesidades
        String sql = "SELECT ID, Latitud, Longitud FROM puntosdeinteres WHERE Localizacion = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, localizacion);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PuntosInteres puntosInteres = new PuntosInteres();
                puntosInteres.setID(resultSet.getInt("ID"));
                puntosInteres.setLatitud(resultSet.getInt("Latitud"));
                puntosInteres.setLongitud(resultSet.getString("Longitud"));
                puntosInteresList.add(puntosInteres);
            }
        }
        return puntosInteresList;
    }

    
    private PuntosInteres createPuntosInteres(ResultSet resultSet) throws SQLException {
        PuntosInteres puntosInteres = new PuntosInteres();
        puntosInteres.setID(resultSet.getInt("ID"));
        puntosInteres.setNombre(resultSet.getString("Nombre"));
        puntosInteres.setTipo(resultSet.getString("Tipo"));
        puntosInteres.setLatitud(resultSet.getInt("Latitud"));
        puntosInteres.setLongitud(resultSet.getString("Longitud"));
        puntosInteres.setDisponibilidad(resultSet.getString("Disponibilidad"));
        return puntosInteres;
    }

    @Override
    public List<PuntosDemograficos> getPoblacion(int poblacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPoblacion'");
    }

    @Override
    public List<PuntosDemograficos> getPointsByPopulation(int poblacion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPointsByPopulation'");
    }

    @Override
    public List<PuntosInteres> poblacionMenor(int poblacion, float nivelEducativo) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'poblacionMenor'");
    }

    @Override
    public List<PuntosDemograficos> getMayorPoblacion(int poblacion) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMayorPoblacion'");
    }
    

}
