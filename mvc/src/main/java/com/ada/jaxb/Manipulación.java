package com.ada.jaxb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class Manipulación {

	
	public static void main(String[] args) throws JAXBException, IOException {
		// TODO Auto-generated method stub
		File f=new File("personas.xml");
		anadirPersona(new Persona("5","FG",44),f);
		leer(f);

	}
	
	public static void leer(File f) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Personas.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Personas personas = (Personas) unmarshaller.unmarshal(f);

		ArrayList<Persona> lPersonas = personas.getPersonas();
		// Leer
		for(Persona p:lPersonas) {
			System.out.println(p.getId()+" "+p.getNombre()+" "+p.getEdad());
		}


	}
	
	public static void anadirPersona(Persona pNueva,File f) throws JAXBException, IOException {
		
		//Leer doc
		JAXBContext context = JAXBContext.newInstance(Personas.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Personas personas = (Personas) unmarshaller.unmarshal(new File("personas.xml"));

		// Manipulo
		ArrayList<Persona> lPersonas = personas.getPersonas();
		
		lPersonas.add(pNueva);

		
		// Cambio personas
		personas.setPersonas(lPersonas);
		
		// Escribo en XML
		

		// Creamos un contexto de JAXB
		// Usando el contexto creado anteriormente para leer el documento 
		// creamos un Marshaller
		Marshaller marshaller=context.createMarshaller();		
		//Establecemos el formato
		 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// Establecemos la codificación (la codificación predeterminada es // utf-8)       
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		// Si se debe omitir la información del encabezado xml, no se omite // por defecto (falso)
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
		marshaller.marshal(personas, new FileWriter(f,StandardCharsets.UTF_8));
		// Se crea el archivo XML

		
	}

}
