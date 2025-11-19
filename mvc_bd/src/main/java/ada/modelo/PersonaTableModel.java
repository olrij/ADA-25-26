package ada.modelo;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class PersonaTableModel extends AbstractTableModel {
    private final String[] columnas = {"ID", "Nombre", "G�nero", "Edad"};
    private final List<String[]> personas;

    public PersonaTableModel(List<String[]> personas) {
        this.personas = new ArrayList<>(personas);
    }

    @Override
    public int getRowCount() {
        return personas.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return personas.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    public void agregarPersona(String[] persona) {
        personas.add(persona);
        fireTableRowsInserted(personas.size() - 1, personas.size() - 1);
    }

    public void eliminarPersona(int rowIndex) {
        personas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void actualizarPersona(int rowIndex, String[] persona) {
        personas.set(rowIndex, persona);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
    
    public void setPersonas(List<String[]> nuevasPersonas) {
        this.personas.clear();
        this.personas.addAll(nuevasPersonas);
        fireTableDataChanged();
    }
}
