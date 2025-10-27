package com.ada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    private String url="jdbc:mysql://localhost:3306/pruebas";
    private String user="root";
    private String password="1234";
    private Connection con;

    public Conexion() throws SQLException{
        con = DriverManager.getConnection(url, user, password);

    }

    public void ejecutarOperacionDML(String sql) throws SQLException{

        Statement st=con.createStatement();

        int n=st.executeUpdate(sql);
        if(n>0){
            System.out.println("Se ha realizado la operación y ha afectado a "+n+" registros");
        }

        st.close();


    }

    public void cerrarConexion() throws SQLException{
        con.close();
    }



}
