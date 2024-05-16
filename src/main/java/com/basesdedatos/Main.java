package com.basesdedatos;

import java.sql.Connection;
import java.sql.SQLException;

import com.basesdedatos.config.DatabaseConnection;
import com.basesdedatos.model.PuntosInteres;
import com.basesdedatos.repository.PuntosInteresRepository;
import com.basesdedatos.repository.Repository;
import com.basesdedatos.view.SwingApp;

public class Main {
    public static void main(String[] args)throws SQLException {
      
      SwingApp app = new SwingApp();
      app.setVisible(true);
    }
}