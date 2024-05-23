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

public class ReposDemograficos implements Repository<PuntosDemograficos> {
    
    
    private Connection geConnection()throws SQLException{
        
        return DatabaseConnection.getInstance();
    }
    @Override
    public List<PuntosDemograficos> findAll() throws SQLException {
        List<PuntosDemograficos> puntosD = new ArrayList<>();
        String sql = "SELECT * FROM geolocalizacion.puntosdemograficos"; // Corregido el nombre del esquema
        try (Statement myStat = geConnection().createStatement()) {
            ResultSet myResultSet = myStat.executeQuery(sql);
            while (myResultSet.next()) {
                PuntosDemograficos puntoD = createPuntoD(myResultSet);
                puntosD.add(puntoD);
            }
        }
        return puntosD;
    }


    @Override
    public void save(PuntosDemograficos puntoD) throws SQLException {
        String sql;
        if (puntoD.getID() != null && puntoD.getID() > 0) {
            sql = "UPDATE puntosdemograficos SET Nombre=?, Poblacion=?, EdadPromedio=?, NivelEducativo=?, DatosDemograficos=? WHERE ID=?";
        } else {
            sql = "INSERT INTO puntosdemograficos (Nombre, Poblacion, EdadPromedio, NivelEducativo, DatosDemograficos) VALUES (?, ?, ?, ?, ?)";
        }

        try (PreparedStatement myStat = geConnection().prepareStatement(sql)) {
            myStat.setString(1, puntoD.getNombre());
            myStat.setInt(2, puntoD.getPoblacion()); // Asegúrate de usar getPoblacion en lugar de getEdadPromedio
            myStat.setString(3, puntoD.getEdadPromedio());
            myStat.setString(4, puntoD.getNivelEducativo());
            myStat.setString(5, puntoD.getDatosDemograficos());
            if (puntoD.getID() != null && puntoD.getID() > 0) {
                myStat.setInt(6, puntoD.getID());
            }
            myStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public PuntosDemograficos getById(Integer id) throws SQLException {
        PuntosDemograficos puntoD = null;
        String sql  = "Select * from puntosdemograficos where ID = ?";
        try(PreparedStatement myStat = geConnection().prepareStatement(sql)){
            myStat.setInt(1, id);
            try(ResultSet myRes = myStat.executeQuery()){
                if(myRes.next()){   
                    puntoD = createPuntoD(myRes);
                }
            }
        }
        return puntoD;
    }

    @Override
    public List<PuntosDemograficos> getMayorPoblacion(int poblacion) throws SQLException {
        List<PuntosDemograficos> puntosD = new ArrayList<>();
        String sql = "select * from puntosdemograficos where poblacion > ?";
        try (PreparedStatement statement = geConnection().prepareStatement(sql)) {
            statement.setInt(1, poblacion);

            try(ResultSet myRes = statement.executeQuery()){
                while(myRes.next()){   
                    PuntosDemograficos puntoD = createPuntoD(myRes);
                    puntosD.add(puntoD);
                }
            }
        }
        return puntosD;
    }

    @Override
    public List<PuntosDemograficos> poblacionMenor(int poblacion, float nivelEducativo) throws SQLException {
        List<PuntosDemograficos> puntosD = new ArrayList<>();
        String sql = "SELECT * FROM geolocalizacion.puntosdemograficos WHERE NivelEducativo > ? and Poblacion < ?";
        try (PreparedStatement statement = geConnection().prepareStatement(sql)) {
            statement.setFloat(1, nivelEducativo);
            statement.setInt(2, poblacion);

            try (ResultSet myRes = statement.executeQuery()) {
                while (myRes.next()) {
                    PuntosDemograficos puntoD = createPuntoD(myRes);
                    puntosD.add(puntoD);
                }
            }
        }
        return puntosD;
    }


    @Override
    public void delete(Integer id) throws SQLException {
        try(PreparedStatement myStamt = geConnection().prepareStatement("DELETE FROM puntosdemograficos WHERE ID=?")){
            myStamt.setInt(1,id);
            myStamt.executeUpdate();
        }
    }
    

    private PuntosDemograficos createPuntoD(ResultSet myResult) throws SQLException {
        PuntosDemograficos puntoD = new PuntosDemograficos();
        puntoD.setID(myResult.getInt("id"));
        puntoD.setNombre(myResult.getString("nombre"));
        puntoD.setPoblacion(myResult.getInt("poblacion"));
        puntoD.setEdadPromedio(myResult.getString("edadPromedio"));
        puntoD.setNivelEducativo(myResult.getString("nivelEducativo"));
        puntoD.setDatosDemograficos(myResult.getString("datosDemograficos"));
        return puntoD;
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
    public List<PuntosDemograficos> getFiltro(String filtro) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFiltro'");
    }
    @Override
    public List<PuntosDemograficos> getLocalizacion(String localizacion) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLocalizacion'");
    }
    
}
