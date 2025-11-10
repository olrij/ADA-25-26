package com.ada.main;

import com.ada.controlador.ControladorVentanaPersona;
import com.ada.modelo.Conexion;
import com.ada.modelo.PersonaDAO;
import com.ada.vista.VistaVentanaPersona;

public class Main {
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        PersonaDAO personaDAO = new PersonaDAO(conexion.getConexion());
        VistaVentanaPersona vista = new VistaVentanaPersona();
        new ControladorVentanaPersona(vista, personaDAO);
    }
}
