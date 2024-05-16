package com.basesdedatos.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.basesdedatos.model.PuntosInteres;
import com.basesdedatos.repository.PuntosInteresRepository;
import com.basesdedatos.repository.Repository;

public class SwingApp extends JFrame {
    private final Repository<PuntosInteres> puntosInteresRepository;
    private final JTable puntosInteresTable;

    public SwingApp() {
        setTitle("Gestión de puntos de interés");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);

        puntosInteresTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(puntosInteresTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Agregar");
        JButton updateButton = new JButton("Actualizar");
        JButton deleteButton = new JButton("Eliminar");
        JButton poblacion = new JButton("Buscar por > poblacion");
        JButton filtrar= new JButton("filtrar por tipo");
        JButton localizacion= new JButton("localizacion latitud,longitud");


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(poblacion);
        buttonPanel.add(filtrar);
        buttonPanel.add(localizacion);

        add(buttonPanel, BorderLayout.SOUTH);

        puntosInteresRepository = new PuntosInteresRepository();

        listPuntosInteres();

        poblacion.addActionListener(e -> {
            try {
                getPoblacion();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        filtrar.addActionListener(e -> {
            try {
                getFiltro();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        localizacion.addActionListener(e -> {
            try {
                getlocalizacion();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        addButton.addActionListener(e -> {
            try {
                addPuntoInteres();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        updateButton.addActionListener(e -> {
            try {
                actualizarPuntoInteres(puntosInteresRepository.findAll());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                eliminarPuntoInteres();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void getPoblacion() throws SQLException {

        JTextField poblacion = new JTextField();

        Object[] fields = {
            "poblacion int:", poblacion,
        };
        int result = JOptionPane.showConfirmDialog(this, fields, "Agregar punto de interés", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            int numberPoblacion = Integer.parseInt(poblacion.getText());

             puntosInteresRepository.getMayorPoblacion(numberPoblacion);
        }
      }
      private void getFiltro() {
        String tipoFiltro = JOptionPane.showInputDialog(this, "Ingrese el tipo de filtro:", "Filtrar Puntos de Interés", JOptionPane.QUESTION_MESSAGE);
        if (tipoFiltro != null && !tipoFiltro.isEmpty()) {
            try {
                List<PuntosInteres> puntosFiltrados = puntosInteresRepository.getFiltro(tipoFiltro);
                if (!puntosFiltrados.isEmpty()) {
                    DefaultTableModel tableModel = new DefaultTableModel();
                    tableModel.addColumn("ID");
                    tableModel.addColumn("Nombre");
                    tableModel.addColumn("Tipo");
                    tableModel.addColumn("Latitud");
                    tableModel.addColumn("Longitud");
                    tableModel.addColumn("Disponibilidad");
        
                    for (PuntosInteres puntoInteres : puntosFiltrados) {
                        Object[] dataRow = {
                            puntoInteres.getID(),
                            puntoInteres.getNombre(),
                            puntoInteres.getTipo(),
                            puntoInteres.getLatitud(),
                            puntoInteres.getLongitud(),
                            puntoInteres.getDisponibilidad()
                        };
                        tableModel.addRow(dataRow);
                    }
                    puntosInteresTable.setModel(tableModel);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontraron puntos de interés con el tipo especificado.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al obtener puntos de interés filtrados: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar un tipo de filtro válido.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void getlocalizacion() {
        try {
            List<PuntosInteres> puntosInteres = puntosInteresRepository.findAll();
    
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("ID");
            tableModel.addColumn("Latitud");
            tableModel.addColumn("Longitud");
    
            for (PuntosInteres puntoInteres : puntosInteres) {
                Object[] dataRow = {
                    puntoInteres.getID(),
                    puntoInteres.getLatitud(),
                    puntoInteres.getLongitud()
                };
                tableModel.addRow(dataRow);
            }
            puntosInteresTable.setModel(tableModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener los puntos de interés", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    
    
        
    

    private void addPuntoInteres() throws SQLException {
        JTextField nombreField = new JTextField();
        JTextField tipoField = new JTextField();
        JTextField latitudField = new JTextField();
        JTextField longitudField = new JTextField();
        JTextField disponibilidadField = new JTextField();

        Object[] fields = {
            "Nombre:", nombreField,
            "Tipo:", tipoField,
            "Latitud:", latitudField,
            "Longitud:", longitudField,
            "Disponibilidad:", disponibilidadField
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "Agregar punto de interés", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            PuntosInteres puntoInteres = new PuntosInteres();
            puntoInteres.setNombre(nombreField.getText());
            puntoInteres.setTipo(tipoField.getText());
            puntoInteres.setLatitud(Integer.parseInt(latitudField.getText()));
            puntoInteres.setLongitud(longitudField.getText());
            puntoInteres.setDisponibilidad(disponibilidadField.getText());

            puntosInteresRepository.save(puntoInteres);

            listPuntosInteres();

            JOptionPane.showMessageDialog(this, "Punto de interés agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actualizarPuntoInteres(List<PuntosInteres>puntos) {
        String puntoInteresIDSTR = JOptionPane.showInputDialog(this, "Ingrese el ID del punto de interés a actualizar:", "Actualizar Punto de Interés", JOptionPane.QUESTION_MESSAGE);
        if (puntoInteresIDSTR != null) {
            try {
                int puntoInteresID = Integer.parseInt(puntoInteresIDSTR);
    
                PuntosInteres puntoInteres = puntosInteresRepository.getById(puntoInteresID);
    
                if (puntoInteres != null) {
                    JTextField nombreField = new JTextField(puntoInteres.getNombre());
                    JTextField tipoField = new JTextField(puntoInteres.getTipo());
                    JTextField latitudField = new JTextField(String.valueOf(puntoInteres.getLatitud()));
                    JTextField longitudField = new JTextField(puntoInteres.getLongitud());
                    JTextField disponibilidadField = new JTextField(puntoInteres.getDisponibilidad());
                
                    Object[] fields = {
                            "Nombre:", nombreField,
                            "Tipo:", tipoField,
                            "Latitud:", latitudField,
                            "Longitud:", longitudField,
                            "Disponibilidad:", disponibilidadField
                    };
    
                    int confirmResult = JOptionPane.showConfirmDialog(this, fields, "Actualizar Punto de Interés", JOptionPane.OK_CANCEL_OPTION);
                    if (confirmResult == JOptionPane.OK_OPTION) {
                        puntoInteres.setNombre(nombreField.getText());
                        puntoInteres.setTipo(tipoField.getText());
                        puntoInteres.setLatitud(Integer.parseInt(latitudField.getText()));
                        puntoInteres.setLongitud(longitudField.getText());
                        puntoInteres.setDisponibilidad(disponibilidadField.getText());
                        puntosInteresRepository.save(puntoInteres);
    
                        listPuntosInteres();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún punto de interés con el ID especificado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el ID", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al obtener los datos del punto de interés de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    


    private void eliminarPuntoInteres() {
        String puntoInteresIdStr = JOptionPane.showInputDialog(this, "Ingrese el ID a eliminar:", "Eliminar Punto de Interés", JOptionPane.QUESTION_MESSAGE);
        if (puntoInteresIdStr != null) {
            try {
                int puntoInteresId = Integer.parseInt(puntoInteresIdStr);
    
                int confirmResult = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el punto de interés?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmResult == JOptionPane.YES_OPTION) {
                    puntosInteresRepository.delete(puntoInteresId);
    
                    listPuntosInteres();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor para el ID del punto de interés", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    

    private void listPuntosInteres() {
        try {
            List<PuntosInteres> puntosInteres = puntosInteresRepository.findAll();

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("ID");
            tableModel.addColumn("Nombre");
            tableModel.addColumn("Tipo");
            tableModel.addColumn("Latitud");
            tableModel.addColumn("Longitud");
            tableModel.addColumn("Disponibilidad");

            for (PuntosInteres puntoInteres : puntosInteres) {
                Object[] dataRow = {
                    puntoInteres.getID(),
                    puntoInteres.getNombre(),
                    puntoInteres.getTipo(),
                    puntoInteres.getLatitud(),
                    puntoInteres.getLongitud(),
                    puntoInteres.getDisponibilidad()
                };
                tableModel.addRow(dataRow);
            }
            puntosInteresTable.setModel(tableModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener los puntos de interés", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
