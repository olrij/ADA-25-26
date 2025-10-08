package com.dam.ada;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// Creamos una instancia de la fábrica de parsers SAXParserFactory
		SAXParserFactory factory = SAXParserFactory.newInstance();

		// Creamos una instancia de SAXParser
		SAXParser parser = factory.newSAXParser();

		// Iniciamos el proceso de análisis del archivo XML y
		// pasar un manejador personalizado (SAXParserHandler) para procesar los eventos
		parser.parse("personas.xml", new SAXParserHandler());
	}
}

//Clase manejadora personalizada que extiende DefaultHandler para procesar los eventos del parser SAX
class SAXParserHandler extends DefaultHandler {

	// Método que se desencadena al empezar a leer el documento
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		// Imprimimos un mensaje indicando que el proceso de lectura del archivo XML ha comenzado
		System.out.println("Comienza la lectura del fichero personas.xml");
	}

	// Método que se desencadena al encontrar la etiqueta de cierra del documento
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		// Imprimir mensaje indicando que el proceso de lectura del archivo XML ha finalizado
		System.out.println();
        System.out.println("Aquí finaliza la lectura del documento");
	}

	// Método que se ejecuta cada vez que se encuentra el inicio de un elemento
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);

		// Imprimimos la apertura de la etiqueta XML
		System.out.print("<" + qName);

		// Si el elemento tiene atributos, se recorren y se imprimen 
		if (attributes != null) {
			for (int i = 0; i < attributes.getLength(); i++) {
				System.out.print(" " + attributes.getQName(i) + "=\"" + attributes.getValue(i) + "\"");
			}
		}

		// Cerramos la etiqueta de apertura
		System.out.print(">");
	}

	// Método que se ejecuta cada vez que se encuentra el cierre de un elemento
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);

		// Imprimimos el cierre de la etiqueta XML
		System.out.print("</" + qName + ">");
	}

	// Método que se ejecuta cuando se encuentra contenido de texto entre etiquetas
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);

		// Creamos una cadena con el contenido de texto del XML
		String contenido = new String(ch, start, length);

		// Imprimimos el contenido de texto 
		System.out.print(contenido);
	}
}
