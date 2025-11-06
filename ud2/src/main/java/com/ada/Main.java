package com.ada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        GestorBD conexion=new GestorBD();

        //conexion.ejecutarOperacionDML("UPDATE persona SET nombre='Pablito Pérez' WHERE id=2");

        //conexion.ejecutarOperacionDML("DELETE FROM es_padre WHERE id_padre=3");
        
        //conexion.consultarTodoPersona();

        //conexion.consultarPadresDeUnaPersona(1);

        //conexion.verMetadatos();

        //conexion.consultarPadresDeUnaPersonaProced(1);

        conexion.insertarFamilia();

        conexion.cerrarConexion();

    }
}