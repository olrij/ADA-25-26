package com.ada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        // Creamos un statement
        Statement st=con.createStatement();

        // Ejecutamos la operaciçón
        int n=st.executeUpdate(sql);

        // Comprobamos los resultados
        if(n>0){
            System.out.println("Se ha realizado la operación y ha afectado a "+n+" registros");
        }


        // Liberamos los recursos
        st.close();


    }

    public void consultarTodoPersona() throws SQLException{

            // Creamos un statement
            Statement st=con.createStatement();

            // Ejecutamos la consulta
            ResultSet rs = st.executeQuery("SELECT * FROM persona");

            // Procesamos los resultados
            while(rs.next()){
                System.out.print("id: "+rs.getString(1));
                System.out.print(" nombre: "+rs.getString("nombre"));
                System.out.print(" dirección: "+rs.getString(3));
                System.out.print(" f_nac: "+rs.getString(4));

                System.out.println();

            }

            // Liberamos los recursos
            rs.close();
            st.close();





            

    }


    public void consultarPadresDeUnaPersona(int id) throws SQLException{

            // Creamos la consulta preparada
            PreparedStatement pst=con.prepareStatement("SELECT p.* FROM persona p INNER JOIN es_padre pa on pa.id_padre=p.id \n" + //
                                "where pa.id_hijo=?");

            // Preparamos la consulta 'seteando' los parámetros
            pst.setInt(1, id);

            // Ejecutamos la consulta
            ResultSet rs = pst.executeQuery();

            // Procesamos los resultados
            while(rs.next()){
                System.out.print("id: "+rs.getString(1));
                System.out.print(" nombre: "+rs.getString("nombre"));
                System.out.print(" dirección: "+rs.getString(3));
                System.out.print(" f_nac: "+rs.getString(4));

                System.out.println();

            }

            // Liberamos los recursos
            rs.close();
            pst.close();





            

    }




    public void cerrarConexion() throws SQLException{
        con.close();
    }



}
