package com.basesdedatos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.basesdedatos.config.DatabaseConnection;
import com.basesdedatos.model.Ubicacion;

public class UbicacionRepo implements Repository<Ubicacion>,RepositoryPais<Ubicacion>,RepositoryCiudad<Ubicacion>,RepositoryOrganizar<Ubicacion>{

    private Connection getConnection()throws SQLException{
        return DatabaseConnection.getInstance();
    }
    @Override
    public List<Ubicacion> findAll() throws SQLException {
        List<Ubicacion> ubicaciones = new ArrayList<>();
        String sql = "Select * from ubicacion";
        try(Statement myStat = getConnection().createStatement())
        {
            ResultSet myResultSet = myStat.executeQuery(sql);
            while (myResultSet.next()) {
                Ubicacion ubicacion = createubicacion(myResultSet);
                ubicaciones.add(ubicacion);
            }
        } 
        
        return ubicaciones;
    }

    @Override
    public Ubicacion getById(Integer id) throws SQLException {
        Ubicacion ubicacion = null;
        String sql  = "Select * from ubicacion where ubicacion_id = ?";
        try(PreparedStatement myStat = getConnection().prepareStatement(sql)){
            myStat.setInt(1, id);
            try(ResultSet myRes = myStat.executeQuery()){
                if(myRes.next()){   
                    ubicacion = createubicacion(myRes);
                }
            }
        }
        return ubicacion;
    }

    @Override
    public void save(Ubicacion ubicacion) throws SQLException {
        String sql;
        if(ubicacion.getID()!= null && ubicacion.getID()>0) {
            sql = "UPDATE ubicacion SET first_name =?, last_name=? WHERE ubicacion_id= ?";
        }else {
            sql = "INSERT INTO ubicacion (first_name, last_name) values(?,?)";
        }

       
        try(PreparedStatement myStat = getConnection().prepareStatement(sql)){
            myStat.setString(1, ubicacion.getNombre());
            myStat.setString(2, ubicacion.getLatitud());
            myStat.setString(2, ubicacion.getLongitud());
            myStat.setString(2, ubicacion.getDireccion());
            myStat.setString(2, ubicacion.getCiudad());
            myStat.setString(2, ubicacion.getPais());
        myStat.setString(2, ubicacion.getCodigoPostal());
            if(ubicacion.getID() != null && ubicacion.getID()>0) {
                myStat.setInt(3, ubicacion.getID());
            }
            myStat.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try(PreparedStatement myStamt = getConnection().prepareStatement("DELETE FROM ubicacion WHERE ubicacion_id=?")){
            myStamt.setInt(1,id);
            myStamt.executeUpdate();
        }
    }

    private Ubicacion createubicacion(ResultSet myResult)throws SQLException{
       Ubicacion ubicacion = new Ubicacion();
        ubicacion.setID(myResult.getInt("ID"));
        ubicacion.setNombre(myResult.getString("Nombre"));
        ubicacion.setLatitud(myResult.getString("Latitud"));
        ubicacion.setLongitud(myResult.getString("Longitud"));
        ubicacion.setDireccion(myResult.getString("Direccion"));
        ubicacion.setCiudad(myResult.getString("Ciudad"));
        ubicacion.setPais(myResult.getString("Pais"));
        ubicacion.setCodigoPostal(myResult.getString("CodigoPostal"));
        return ubicacion;
    }

    @Override
    public  List<Ubicacion> getPais(String filtro) throws SQLException {
        List<Ubicacion> resultados = new ArrayList<>();
        String sql = "SELECT ID, Nombre, Latitud, Longitud, Direccion, Ciudad, Pais, CodigoPostal FROM ubicacion WHERE Pais = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, filtro);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ubicacion ubicacion = new Ubicacion();
                    ubicacion.setID(resultSet.getInt("ID"));
                    ubicacion.setNombre(resultSet.getString("Nombre"));
                    ubicacion.setLatitud(resultSet.getString("Latitud"));
                    ubicacion.setLongitud(resultSet.getString("Longitud"));
                    ubicacion.setDireccion(resultSet.getString("Direccion"));
                    ubicacion.setCiudad(resultSet.getString("Ciudad"));
                    ubicacion.setPais(resultSet.getString("Pais"));
                    ubicacion.setCodigoPostal(resultSet.getString("CodigoPostal"));
                    resultados.add(ubicacion);
                }
            }
        }
        return resultados;
    }

    @Override
    public  List<Ubicacion> getCiudad(String filtro) throws SQLException {
        List<Ubicacion> resultados = new ArrayList<>();
        String sql = "SELECT ID, Nombre, Latitud, Longitud, Direccion, Ciudad, Pais, CodigoPostal FROM ubicacion WHERE Ciudad = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, filtro);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ubicacion ubicacion = new Ubicacion();
                    ubicacion.setID(resultSet.getInt("ID"));
                    ubicacion.setNombre(resultSet.getString("Nombre"));
                    ubicacion.setLatitud(resultSet.getString("Latitud"));
                    ubicacion.setLongitud(resultSet.getString("Longitud"));
                    ubicacion.setDireccion(resultSet.getString("Direccion"));
                    ubicacion.setCiudad(resultSet.getString("Ciudad"));
                    ubicacion.setPais(resultSet.getString("Pais"));
                    ubicacion.setCodigoPostal(resultSet.getString("CodigoPostal"));
                    resultados.add(ubicacion);
                }
            }
        }
        return resultados;
    }

    @Override
    public  List<Ubicacion> organizarCiudad(String filtro) throws SQLException {
        List<Ubicacion> resultados = new ArrayList<>();
        String sql = "select pais,ciudad from geolocalizacion.ubicacion  Order BY ciudad";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, filtro);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ubicacion ubicacion = new Ubicacion();
                    ubicacion.setID(resultSet.getInt("ID"));
                    ubicacion.setNombre(resultSet.getString("Nombre"));
                    ubicacion.setLatitud(resultSet.getString("Latitud"));
                    ubicacion.setLongitud(resultSet.getString("Longitud"));
                    ubicacion.setDireccion(resultSet.getString("Direccion"));
                    ubicacion.setCiudad(resultSet.getString("Ciudad"));
                    ubicacion.setPais(resultSet.getString("Pais"));
                    ubicacion.setCodigoPostal(resultSet.getString("CodigoPostal"));
                    resultados.add(ubicacion);
                }
            }
        }
        return resultados;
    }
    @Override
    public Integer getMayorPoblacion(int poblacion) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMayorPoblacion'");
    }
    @Override
    public List<Ubicacion> getFiltro(String filtro) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFiltro'");
    }
    @Override
    public List<Ubicacion> getLocalizacion(String localizacion) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLocalizacion'");
    }
    

   
}


