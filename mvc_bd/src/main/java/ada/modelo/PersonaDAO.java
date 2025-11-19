package ada.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * CREATE DATABASE ejemplo_mvc;

USE ejemplo_mvc;

CREATE TABLE persona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero ENUM('M', 'F') NOT NULL,
    edad INT NOT NULL
);
INSERT INTO persona (nombre, genero, edad) VALUES 
('Juan P�rez', 'M', 30),
('Ana L�pez', 'F', 25),
('Carlos Mart�nez', 'M', 40);

 */

public class PersonaDAO {
    private final Connection conexion;

    public PersonaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public List<String[]> obtenerPersonas() {
        List<String[]> personas = new ArrayList<>();
        try {
            String query = "SELECT * FROM persona";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                personas.add(new String[]{
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nombre"),
                    rs.getString("genero"),
                    String.valueOf(rs.getInt("edad"))
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }

    public void insertarPersona(String nombre, String genero, int edad) {
        try {
            String query = "INSERT INTO persona (nombre, genero, edad) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, nombre);
            pstmt.setString(2, genero);
            pstmt.setInt(3, edad);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarPersona(int id, String nombre, String genero, int edad) {
        try {
            String query = "UPDATE persona SET nombre = ?, genero = ?, edad = ? WHERE id = ?";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setString(1, nombre);
            pstmt.setString(2, genero);
            pstmt.setInt(3, edad);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPersona(int id) {
        try {
            String query = "DELETE FROM persona WHERE id = ?";
            PreparedStatement pstmt = conexion.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
