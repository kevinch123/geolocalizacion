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

import com.basesdedatos.model.Ubicacion;
import com.basesdedatos.repository.UbicacionRepo;
import com.basesdedatos.repository.Repository;
import com.basesdedatos.repository.RepositoryPais;
import com.basesdedatos.repository.RepositoryCiudad;
import com.basesdedatos.repository.RepositoryOrganizar;



public class SwingApp extends JFrame{
    private final Repository<Ubicacion> ubicacionRepository;
    private final RepositoryPais<Ubicacion> ubicacionRepositoryPais;
    private final RepositoryCiudad<Ubicacion> ubicacionRepositoryCiudad;
    private final RepositoryOrganizar<Ubicacion> ubicacionRepositoryOrganizar;



    private final JTable ubicacionTable;

    public SwingApp(){
        setTitle("Gestion Sakila");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,300);

        ubicacionTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(ubicacionTable);
        add(scrollPane,BorderLayout.CENTER);

        JButton addButton = new JButton("Agregar");
        JButton updateButton = new JButton("Actualizar");
        JButton deleteButton = new JButton("Borrar");
        JButton filtroPais = new JButton("Filtro Pais");
        JButton filtroCiudad = new JButton("Filtro Ciudad");
        JButton OrganizarCiudad = new JButton("Organizar Ciudad");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(filtroPais);
        buttonPanel.add(filtroCiudad);
        buttonPanel.add(OrganizarCiudad);

        add(buttonPanel,BorderLayout.SOUTH);

        ubicacionRepository = new UbicacionRepo();
        ubicacionRepositoryPais = new UbicacionRepo();
        ubicacionRepositoryCiudad = new UbicacionRepo();
        ubicacionRepositoryOrganizar = (RepositoryOrganizar<Ubicacion>) new UbicacionRepo();

        listUbications();

        addButton.addActionListener(e-> {
            try {
               addubicacion();
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }
        });

        updateButton.addActionListener(e-> {
            try {
                actualizarUbicacion();
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }
        });

        deleteButton.addActionListener(e-> {
            try {
                eliminarUbicacion();
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }
        });

        filtroPais.addActionListener(e-> {
            try {
               getPais();
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }
        });

        filtroCiudad.addActionListener(e-> {
            try {
               getCiudad();
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }
        });

        OrganizarCiudad.addActionListener(e-> {
            try {
                organizarCiudad();
            } catch (Exception ex) {
                ex.printStackTrace(); 
            }
        });

    }

    private void organizarCiudad() {
        JTextField organizarField = new JTextField();
        
        Object[] fields= {
            "organizarField", organizarField,
        };
                try {

                    int result = JOptionPane.showConfirmDialog(this, fields, "Organizar ciudad alfabeticamente", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {

                                     
            
                    List<Ubicacion> organizarCiudad = ubicacionRepositoryOrganizar.organizarCiudad(organizarField.getText());
                    if (!organizarCiudad.isEmpty()) {
                        DefaultTableModel tableModel = new DefaultTableModel();
                        tableModel.addColumn("ID");
                        tableModel.addColumn("Nombre");
                        tableModel.addColumn("Latitud");
                        tableModel.addColumn("Longitud");
                        tableModel.addColumn("Direccion");
                        tableModel.addColumn("Ciudad");
                        tableModel.addColumn("Pais");
                        tableModel.addColumn("CodigoPostal");
            
                        for (Ubicacion ubicacion : organizarCiudad ) {
                            Object[] dataRow = {
                                ubicacion.getID(),
                                ubicacion.getNombre(),
                                ubicacion.getLatitud(),
                                ubicacion.getLongitud(),
                                ubicacion.getDireccion(),
                                ubicacion.getCiudad(),
                                ubicacion.getPais(),
                                ubicacion.getCodigoPostal(),
                            };
                            tableModel.addRow(dataRow);
                        }
                        ubicacionTable.setModel(tableModel);
                    }
                    } else {
                        JOptionPane.showMessageDialog(this, "No se organizo con el tipo especificado.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error al organizar ciudad filtrados: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
             
        }

    private void getPais() {
        JTextField paisField = new JTextField();
        
        Object[] fields= {
            "paisField", paisField,
        };
                try {

                    int result = JOptionPane.showConfirmDialog(this, fields, "Filtrar por pais", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {

                                     
            
                    List<Ubicacion> paisFiltrados = ubicacionRepositoryPais.getPais(paisField.getText());
                    if (!paisFiltrados.isEmpty()) {
                        DefaultTableModel tableModel = new DefaultTableModel();
                        tableModel.addColumn("ID");
                        tableModel.addColumn("Nombre");
                        tableModel.addColumn("Latitud");
                        tableModel.addColumn("Longitud");
                        tableModel.addColumn("Direccion");
                        tableModel.addColumn("Ciudad");
                        tableModel.addColumn("Pais");
                        tableModel.addColumn("CodigoPostal");
            
                        for (Ubicacion ubicacion : paisFiltrados ) {
                            Object[] dataRow = {
                                ubicacion.getID(),
                                ubicacion.getNombre(),
                                ubicacion.getLatitud(),
                                ubicacion.getLongitud(),
                                ubicacion.getDireccion(),
                                ubicacion.getCiudad(),
                                ubicacion.getPais(),
                                ubicacion.getCodigoPostal(),
                            };
                            tableModel.addRow(dataRow);
                        }
                        ubicacionTable.setModel(tableModel);
                    }
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontraron paises con el tipo especificado.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error al obtener el pais filtrados: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
             
        }
    

        private void getCiudad() {
            JTextField ciudadField = new JTextField();
            
            Object[] fields= {
                "ciudadField", ciudadField,
            };
                    try {
                        int result = JOptionPane.showConfirmDialog(this, fields, "Filtrar por ciudad", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
    
                        List<Ubicacion> ciudadFiltro = ubicacionRepositoryCiudad.getCiudad(ciudadField.getText());
                        if (!ciudadFiltro.isEmpty()) {
                            DefaultTableModel tableModel = new DefaultTableModel();
                            tableModel.addColumn("ID");
                            tableModel.addColumn("Nombre");
                            tableModel.addColumn("Latitud");
                            tableModel.addColumn("Longitud");
                            tableModel.addColumn("Direccion");
                            tableModel.addColumn("Ciudad");
                            tableModel.addColumn("Pais");
                            tableModel.addColumn("CodigoPostal");
                
                            for (Ubicacion ubicacion : ciudadFiltro ) {
                                Object[] dataRow = {
                                    ubicacion.getID(),
                                    ubicacion.getNombre(),
                                    ubicacion.getLatitud(),
                                    ubicacion.getLongitud(),
                                    ubicacion.getDireccion(),
                                    ubicacion.getCiudad(),
                                    ubicacion.getPais(),
                                    ubicacion.getCodigoPostal(),
                                };
                                tableModel.addRow(dataRow);
                            }
                            ubicacionTable.setModel(tableModel);
                        }
                        } else {
                            JOptionPane.showMessageDialog(this, "No se encontraron ciudades con el tipo especificado.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Error al obtener la ciudad filtrada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }  
            }

    private void addubicacion() throws SQLException {
        JTextField Nombre = new JTextField();
        JTextField Latitud = new JTextField();
        JTextField Longitud = new JTextField();
        JTextField Direccion = new JTextField();
        JTextField Ciudad = new JTextField();
        JTextField Pais = new JTextField();
        JTextField CodigoPostal = new JTextField();

        Object[] fields= {
            "nombre", Nombre,
            "latitud", Latitud,
            "longitud", Longitud,
            "direccion", Direccion,
            "ciudad", Ciudad,
            "pais", Pais,
            "codigoPostal", CodigoPostal,
            

        };

        int result = JOptionPane.showConfirmDialog(this, fields, "add ubicacion", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {

            Ubicacion ubicacion = new Ubicacion();
            ubicacion.setNombre(Nombre.getText());
            ubicacion.setLatitud(Latitud.getText());
            ubicacion.setLongitud(Longitud.getText());
            ubicacion.setDireccion(Direccion.getText());
            ubicacion.setCiudad(Ciudad.getText());
            ubicacion.setPais(Pais.getText());
            ubicacion.setCodigoPostal(CodigoPostal.getText());
            
            
            


            ubicacionRepository.save(ubicacion);

            listUbications();

            JOptionPane.showMessageDialog(this, "Ubicacion agregada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    
    private void actualizarUbicacion() {
        String ubicacionIDSTR = JOptionPane.showInputDialog(this, "Ingrese el ID del ubicacion a actualizar:", "Actualizar ubicacion", JOptionPane.QUESTION_MESSAGE);
        if (ubicacionIDSTR != null) {
            try {
                int ubicacionID = Integer.parseInt(ubicacionIDSTR);

                Ubicacion ubicacion = ubicacionRepository.getById(ubicacionID);

                if (ubicacion != null) {
                    JTextField nombreField = new JTextField();
                    JTextField latitudField = new JTextField();
                    JTextField longitudField = new JTextField();
                    JTextField direccionField = new JTextField();
                    JTextField ciudadField = new JTextField();
                    JTextField paisField = new JTextField();
                    JTextField codigoPostalField = new JTextField();
                
                    Object[] fields = {
                        "nombre", nombreField,
                        "latitud", latitudField,
                        "longitud", longitudField,
                        "direccion", direccionField,
                        "ciudad", ciudadField,
                        "pais", paisField,
                        "codigoPostal", codigoPostalField,
                    };

                    int confirmResult = JOptionPane.showConfirmDialog(this, fields, "Actualizar ubicacion", JOptionPane.OK_CANCEL_OPTION);
                    if (confirmResult == JOptionPane.OK_OPTION) {
                        ubicacion.setNombre(nombreField.getText());
                        ubicacion.setLatitud(latitudField.getText());
                        ubicacion.setLongitud(longitudField.getText());
                        ubicacion.setDireccion(direccionField.getText());
                        ubicacion.setCiudad(ciudadField.getText());
                        ubicacion.setPais(paisField.getText());
                        ubicacion.setCodigoPostal(codigoPostalField.getText());
                        ubicacionRepository.save(ubicacion);

                        listUbications();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ninguna ubicacion con el ID especificado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el ID", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al obtener los datos de la ubicacion de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarUbicacion() {
        String ubicacionIdStr = JOptionPane.showInputDialog(this, "Ingrese el ID a eliminar:", "Eliminar ubicacion", JOptionPane.QUESTION_MESSAGE);
        if (ubicacionIdStr != null) {
            try {
                int ubicacionID = Integer.parseInt(ubicacionIdStr);

                int confirmResult = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el ubicacion?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirmResult == JOptionPane.YES_OPTION) {
                    ubicacionRepository.delete(ubicacionID);

                    listUbications();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un valor para el ID del ubicacion", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void listUbications() {
        try {
            List<Ubicacion> ubications = ubicacionRepository.findAll();

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("ID");
            tableModel.addColumn("Nombre");
            tableModel.addColumn("Latitud");
            tableModel.addColumn("Longitud");
            tableModel.addColumn("Direccion");
            tableModel.addColumn("Ciudad");
            tableModel.addColumn("Pais");
            tableModel.addColumn("Codigo Postal");

            for (Ubicacion ubicacion : ubications) {
                Object[] dataRow = {
                    ubicacion.getID(),
                    ubicacion.getNombre(),
                    ubicacion.getLatitud(),
                    ubicacion.getLongitud(),
                    ubicacion.getDireccion(),
                    ubicacion.getCiudad(),
                    ubicacion.getPais(),
                    ubicacion.getCodigoPostal(),
                };
                tableModel.addRow(dataRow);
            }
            ubicacionTable.setModel(tableModel);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"Error getAll","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

