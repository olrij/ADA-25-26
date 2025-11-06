package com.ada;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBD {

    private String url="jdbc:mysql://localhost:3306/pruebas";
    private String user="root";
    private String password="1234";
    private Connection con;

    public GestorBD() throws SQLException{
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

    public void verMetadatos() throws SQLException{
        DatabaseMetaData metadatos = con.getMetaData();

        System.out.println(metadatos.getDatabaseProductName());
        System.out.println(metadatos.getDriverName());


        ResultSet rs = metadatos.getTables("pruebas", null, null, new String[] {"TABLE"});

        while(rs.next()){
            System.out.println(rs.getString("TABLE_NAME"));
        }


        rs=metadatos.getColumns("pruebas", null, "es_padre", null);

        while(rs.next()){
            System.out.println(rs.getString("COLUMN_NAME"));
            System.out.println(rs.getString("TYPE_NAME"));
        }
        




    }


    public void consultarPadresDeUnaPersonaProced(int id) throws SQLException{

            // Creamos la operación 
            CallableStatement pc = con.prepareCall("CALL verPadres(?)");

            // Preparamos la operación 'seteando' los parámetros
            pc.setInt(1, id);

            // Ejecutamos la consulta
            ResultSet rs = pc.executeQuery();

            // Procesamos los resultados
            while(rs.next()){
                System.out.print("id: "+rs.getString(1));
                System.out.println();

            }

            // Liberamos los recursos
            rs.close();
            pc.close();





            

    }


    public void insertarFamilia(){


        try {


            con.setAutoCommit(false);


            Statement st = con.createStatement();

            st.execute("INSERT INTO persona(id,nombre,direccion,fecha_nac) \n" + //
                                "values (4,\"lUCAS\",\"Calle Falsa 123\",\"1980-5-29\");");
            st.execute("INSERT INTO persona(id,nombre,direccion,fecha_nac) \n" + //
                                "values (5,\"MARIANO\",\"Calle Falsa 123\",\"1980-5-29\");");

            st.execute("INSERT INTO persona(id,nombre,direccion,fecha_nac) \n" + //
                                "values (6,\"MANUELA\",\"Calle Falsa 123\",\"1980-5-29\");");

            st.execute("INSERT INTO es_padre VALUES (4,6)");
            st.execute("INSERT INTO es_padre VALUES (5,6)");

            con.commit();

            System.out.println("Se ha insertado la familia");


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            try {
                con.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


        }

        try {
            con.setAutoCommit(true);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }






    public void cerrarConexion() throws SQLException{
        con.close();
    }



}
