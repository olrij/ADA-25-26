package com.ada.mvc;

import java.io.File;
import java.io.IOException;

public class Prueba {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		LibreriaModelo modelo=new LibreriaModelo();
		LibreriaVista vista=new LibreriaVista();
		LibreriaControlador controlador=new LibreriaControlador(modelo,vista);
		
		controlador.start();
		

	}

}
