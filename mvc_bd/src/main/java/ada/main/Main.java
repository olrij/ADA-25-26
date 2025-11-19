package ada.main;

import ada.controlador.ControladorVentanaPersona;
import ada.modelo.Conexion;
import ada.modelo.PersonaDAO;
import ada.vista.VistaVentanaPersona;

public class Main {
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        PersonaDAO personaDAO = new PersonaDAO(conexion.getConexion());
        VistaVentanaPersona vista = new VistaVentanaPersona();
        new ControladorVentanaPersona(vista, personaDAO);
    }
}
