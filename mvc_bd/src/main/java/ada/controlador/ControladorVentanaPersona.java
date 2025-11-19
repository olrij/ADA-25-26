package ada.controlador;

import ada.modelo.PersonaDAO;
import ada.modelo.PersonaTableModel;
import ada.vista.VistaVentanaPersona;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorVentanaPersona implements ActionListener {
    private final VistaVentanaPersona vista;
    private final PersonaDAO personaDAO;
    private final PersonaTableModel tableModel;

    public ControladorVentanaPersona(VistaVentanaPersona vista, PersonaDAO personaDAO) {
        this.vista = vista;
        this.personaDAO = personaDAO;
        this.tableModel = new PersonaTableModel(personaDAO.obtenerPersonas());
        vista.setTableModel(tableModel);
        vista.getAgregarButton().addActionListener(this);
        vista.getEditarButton().addActionListener(this);
        vista.getEliminarButton().addActionListener(this);
        vista.getBuscarButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getAgregarButton()) {
            agregarPersona();
        } else if (e.getSource() == vista.getEditarButton()) {
            editarPersona();
        } else if (e.getSource() == vista.getEliminarButton()) {
            eliminarPersona();
        } else if (e.getSource() == vista.getBuscarButton()) {
            buscarPersona();
        }
    }

    private void agregarPersona() {
        String nombre = JOptionPane.showInputDialog("Nombre:");
        String genero = JOptionPane.showInputDialog("G�nero (M/F):");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
        personaDAO.insertarPersona(nombre, genero, edad);
        tableModel.agregarPersona(new String[]{String.valueOf(tableModel.getRowCount() + 1), nombre, genero, String.valueOf(edad)});
    }

    private void editarPersona() {
        int filaSeleccionada = vista.getTable().getSelectedRow();
        if (filaSeleccionada >= 0) {
            int id = Integer.parseInt((String) tableModel.getValueAt(filaSeleccionada, 0));
            String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", tableModel.getValueAt(filaSeleccionada, 1));
            String nuevoGenero = JOptionPane.showInputDialog("Nuevo g�nero (M/F):", tableModel.getValueAt(filaSeleccionada, 2));
            int nuevaEdad = Integer.parseInt(JOptionPane.showInputDialog("Nueva edad:", tableModel.getValueAt(filaSeleccionada, 3)));

            personaDAO.actualizarPersona(id, nuevoNombre, nuevoGenero, nuevaEdad);
            tableModel.actualizarPersona(filaSeleccionada, new String[]{String.valueOf(id), nuevoNombre, nuevoGenero, String.valueOf(nuevaEdad)});
        } else {
            JOptionPane.showMessageDialog(vista, "Selecciona una fila para editar.");
        }
    }

    private void eliminarPersona() {
        int filaSeleccionada = vista.getTable().getSelectedRow();
        if (filaSeleccionada >= 0) {
            int id = Integer.parseInt((String) tableModel.getValueAt(filaSeleccionada, 0));
            int confirmacion = JOptionPane.showConfirmDialog(vista, "�Est�s seguro de eliminar esta persona?", "Confirmaci�n", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                personaDAO.eliminarPersona(id);
                tableModel.eliminarPersona(filaSeleccionada);
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Selecciona una fila para eliminar.");
        }
    }

    private void buscarPersona() {
        String textoBusqueda = vista.getTextoBusqueda().toLowerCase();
        List<String[]> resultados = personaDAO.obtenerPersonas().stream()
                .filter(persona -> persona[1].toLowerCase().contains(textoBusqueda))
                .toList();

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No se encontraron resultados.");
        } else {
            tableModel.setPersonas(resultados);
        }
    }
}
