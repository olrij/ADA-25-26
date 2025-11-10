package com.ada.vista;

import javax.swing.*;
import javax.swing.table.TableModel;

public class VistaVentanaPersona extends JFrame {
    private JTable jTablePersonas;
    private JTextField jTextFieldBuscar;
    private JButton jButtonBuscar, jButtonAgregar, jButtonEditar, jButtonEliminar;

    public VistaVentanaPersona() {
        setTitle("Gesti�n de Personas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Tabla
        jTablePersonas = new JTable();
        JScrollPane scrollPane = new JScrollPane(jTablePersonas);
        panel.add(scrollPane);

        // Controles
        JPanel panelControles = new JPanel();
        jTextFieldBuscar = new JTextField(15);
        jButtonBuscar = new JButton("Buscar");
        jButtonAgregar = new JButton("Agregar");
        jButtonEditar = new JButton("Editar");
        jButtonEliminar = new JButton("Eliminar");

        panelControles.add(jTextFieldBuscar);
        panelControles.add(jButtonBuscar);
        panelControles.add(jButtonAgregar);
        panelControles.add(jButtonEditar);
        panelControles.add(jButtonEliminar);

        panel.add(panelControles);

        add(panel);
        setVisible(true);
    }

    public void setTableModel(TableModel model) {
        jTablePersonas.setModel(model);
    }

    public JTable getTable() {
        return jTablePersonas;
    }

    public JButton getAgregarButton() {
        return jButtonAgregar;
    }

    public JButton getEditarButton() {
        return jButtonEditar;
    }

    public JButton getEliminarButton() {
        return jButtonEliminar;
    }

    public JButton getBuscarButton() {
        return jButtonBuscar;
    }

    public String getTextoBusqueda() {
        return jTextFieldBuscar.getText();
    }
}
