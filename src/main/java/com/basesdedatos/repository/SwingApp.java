package com.basesdedatos.repository;

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

import com.basesdedatos.model.PuntosDemograficos;
import com.basesdedatos.repository.ReposDemograficos;
import com.basesdedatos.repository.Repository;

public class SwingApp extends JFrame{
    private final Repository<PuntosDemograficos> ReposDemograficos;
    private final JTable PuntoDTable;

    public SwingApp(){
        setTitle("Gestion Geolocalizacion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,300);

        PuntoDTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(PuntoDTable);
        add(scrollPane,BorderLayout.CENTER);

        JButton addButton = new JButton("Agregar");
        JButton updateButton = new JButton("Actualizar");
        JButton deleteButton = new JButton("Borrar");
        JButton poblacion = new JButton("Buscar por Poblacion");
        JButton poblacionMenor = new JButton("Buscar por Po & N.Edu");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(poblacion);
        buttonPanel.add(poblacionMenor);

        add(buttonPanel,BorderLayout.SOUTH);

        ReposDemograficos = new ReposDemograficos();

        listPuntosD();

        poblacionMenor.addActionListener(e->{
            try {
                poblacionMenor();
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }

        });

        addButton.addActionListener(e-> {
            try {
            addPuntosD();
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }
        });

        updateButton.addActionListener(e-> {
            try {
                actualizarPuntoD();
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }
        });

        deleteButton.addActionListener(e-> {
            try {
                eliminarPuntoD();
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }
        });

        poblacion.addActionListener(e-> {
            try {
            getMayorPoblacion();
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }
        });
    }

    private void poblacionMenor() throws SQLException {
        JTextField poblacionField = new JTextField();
        JTextField nivelEducativoField = new JTextField();
        Object[] fields = {
            "Población máxima:", poblacionField,
            "Nivel Educativo mínimo:", nivelEducativoField
        };
        int result = JOptionPane.showConfirmDialog(this, fields, "Filtrar por Población y Nivel Educativo", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int poblacion = Integer.parseInt(poblacionField.getText());
                float nivelEducativo = Float.parseFloat(nivelEducativoField.getText());
                List<PuntosDemograficos> resultDemografico = ReposDemograficos.poblacionMenor(poblacion, nivelEducativo);

                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("ID");
                tableModel.addColumn("Nombre");
                tableModel.addColumn("Poblacion");
                tableModel.addColumn("Edad Promedio");
                tableModel.addColumn("Nivel Educativo");
                tableModel.addColumn("Datos Demograficos");

                for (PuntosDemograficos puntoD : resultDemografico) {
                    Object[] dataRow = {
                        puntoD.getID(),
                        puntoD.getNombre(),
                        puntoD.getPoblacion(),
                        puntoD.getEdadPromedio(),
                        puntoD.getNivelEducativo(),
                        puntoD.getDatosDemograficos(),
                    };
                    tableModel.addRow(dataRow);
                }
                PuntoDTable.setModel(tableModel);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, introduce valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

    private void getMayorPoblacion() throws SQLException {
        JTextField poblacion = new JTextField();
        Object[] fields = {
            "poblacion int:", poblacion,
        };
        int result = JOptionPane.showConfirmDialog(this, fields, "Agregar punto de interés", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int numberPoblacion = Integer.parseInt(poblacion.getText());
            List<PuntosDemograficos> resultDemografico = ReposDemograficos.getMayorPoblacion(numberPoblacion);

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("ID");
            tableModel.addColumn("Nombre");
            tableModel.addColumn("Poblacion");
            tableModel.addColumn("Edad Promedio");
            tableModel.addColumn("Nivel Educativo");
            tableModel.addColumn("Datos Demograficos");

            for (PuntosDemograficos puntoD : resultDemografico) {
                Object[] dataRow = {
                    puntoD.getID(),
                    puntoD.getNombre(),
                    puntoD.getPoblacion(),
                    puntoD.getEdadPromedio(),
                    puntoD.getNivelEducativo(),
                    puntoD.getDatosDemograficos(),
                };
                tableModel.addRow(dataRow);
            }
            PuntoDTable.setModel(tableModel);
        }
    }

    private void addPuntosD() throws SQLException {
        JTextField nombre = new JTextField();
        JTextField poblacion = new JTextField();
        JTextField edadpromedio = new JTextField();
        JTextField niveleducativo = new JTextField();
        JTextField datosdemograficos = new JTextField();

        Object[] fields= {
            "nombre", nombre,
            "poblacion", poblacion,
            "edad promedio", edadpromedio,
            "nivel educativo", niveleducativo,
            "datos demograficos", datosdemograficos
        };

        int result = JOptionPane.showConfirmDialog(this, fields, "add puntoD", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            PuntosDemograficos puntoD = new PuntosDemograficos();
            puntoD.setNombre(nombre.getText());
            puntoD.setPoblacion(Integer.parseInt(poblacion.getText()));
            puntoD.setEdadPromedio(edadpromedio.getText());
            puntoD.setNivelEducativo(niveleducativo.getText());
            puntoD.setDatosDemograficos(datosdemograficos.getText());

            ReposDemograficos.save(puntoD);

            listPuntosD();

            JOptionPane.showMessageDialog(this, "punto demografico agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    
    private void actualizarPuntoD() {
        String puntoDIDSTR = JOptionPane.showInputDialog(this, "Ingrese el ID del punto demografico a actualizar:", "Actualizar Punto Demografico", JOptionPane.QUESTION_MESSAGE);
        if (puntoDIDSTR != null) {
            try {
                int puntoID = Integer.parseInt(puntoDIDSTR);

                PuntosDemograficos puntoD = ReposDemograficos.getById(puntoID);

                if (puntoD != null) {
                    JTextField nombreField = new JTextField(puntoD.getNombre());
                    JTextField poblacionField = new JTextField(puntoD.getPoblacion());
                    JTextField edadPromedioField = new JTextField(puntoD.getEdadPromedio());
                    JTextField nivelEducativoField = new JTextField(puntoD.getEdadPromedio());
                    JTextField datosDemograficosField = new JTextField(puntoD.getDatosDemograficos());
                
                    Object[] fields = {
                        "Nombre:", nombreField,
                        "Poblacion:", poblacionField,
                        "Edadpromedio:", edadPromedioField,
                        "Niveleducativo:", nivelEducativoField,
                        "Datosdemograficos:", datosDemograficosField,
                    };

                    int confirmResult = JOptionPane.showConfirmDialog(this, fields, "Actualizar Punto Demografico", JOptionPane.OK_CANCEL_OPTION);
                    if (confirmResult == JOptionPane.OK_OPTION) {
                        puntoD.setNombre(nombreField.getText());
                        puntoD.setPoblacion(Integer.parseInt(poblacionField.getText()));
                        puntoD.setEdadPromedio(edadPromedioField.getText());
                        puntoD.setNivelEducativo(nivelEducativoField.getText());
                        puntoD.setDatosDemograficos(datosDemograficosField.getText());

                        ReposDemograficos.save(puntoD);

                        listPuntosD();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún punto demografico con el ID especificado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el ID", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al obtener los datos del punto demografico de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarPuntoD() {
        String PuntoDIdStr = JOptionPane.showInputDialog(this, "Ingrese el ID a eliminar:", "Eliminar Punto Demografico", JOptionPane.QUESTION_MESSAGE);
        if (PuntoDIdStr != null) {
            try {
                int puntoId = Integer.parseInt(PuntoDIdStr);

                int confirmResult = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el punto demograficos?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmResult == JOptionPane.YES_OPTION) {
                    ReposDemograficos.delete(puntoId);

                    listPuntosD();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor para el ID del actor", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
        private void listPuntosD() {
        try {
            List<PuntosDemograficos> puntosD = ReposDemograficos.findAll();

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("ID");
            tableModel.addColumn("Nombre");
            tableModel.addColumn("Poblacion");
            tableModel.addColumn("Edad Promedio");
            tableModel.addColumn("Nivel Educativo");
            tableModel.addColumn("Datos Demograficos");

            for (PuntosDemograficos puntoD : puntosD) {
                Object[] dataRow = {
                    puntoD.getID(),
                    puntoD.getNombre(),
                    puntoD.getPoblacion(),
                    puntoD.getEdadPromedio(),
                    puntoD.getNivelEducativo(),
                    puntoD.getDatosDemograficos(),
                };
                tableModel.addRow(dataRow);
            }
            PuntoDTable.setModel(tableModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"Error getAll","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

