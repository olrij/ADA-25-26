package com.dam.ada;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class DocumentosXML {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {

        Document doc = pasarDeXMLaDOM("personas.xml");

        // buscarUnaPersona(doc,2);
        //verContenidoPersona(doc);
        // verContenido(doc);


        // Preparamos la persona a añadir
        Element nombre=doc.createElement("nombre");
        Element edad=doc.createElement("edad");


        nombre.setTextContent("Pepe");
        edad.setTextContent("65");


        Element pepe=doc.createElement("persona");

        pepe.appendChild(nombre);
        pepe.appendChild(edad);

        pepe.setAttribute("id", "12");


        // La añadimos
        anadirPersona(doc, pepe);

    }

    public static void verContenido(Document doc) {

        // Obtener la raíz

        Element raiz = doc.getDocumentElement();

        // Hacer la operación
        NodeList hijos = raiz.getChildNodes();

        System.out.print(raiz.getTagName());

        for (int i = 0; i < hijos.getLength(); i++) {
            Node n = hijos.item(i);

            switch (n.getNodeType()) {
                case Node.ELEMENT_NODE:
                    Element e = (Element) n;
                    System.out.print(e.getTagName() + ":");

                    NodeList hijos2 = e.getChildNodes();
                    if (hijos2.getLength() > 0) {
                        for (int j = 0; j < hijos2.getLength(); j++) {
                            Node n2 = hijos2.item(j);

                            switch (n2.getNodeType()) {
                                case Node.ELEMENT_NODE:
                                    Element e2 = (Element) n2;
                                    System.out.print(e2.getTagName() + ":" + e2.getTextContent());

                                    break;
                                case Node.TEXT_NODE:
                                    Text t2 = (Text) n2;
                                    System.out.print(t2.getWholeText());
                                    break;
                            }

                        }
                    }

                    break;
                case Node.TEXT_NODE:
                    Text t = (Text) n;
                    System.out.print(t.getWholeText());
                    break;
            }

        }

    }


    public static void anadirPersona(Document doc, Element p) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException{


        // Obtener la raíz

        Element raiz = doc.getDocumentElement();

        // Hacer la operación

       raiz.appendChild(p);

       // Actualizamos el documento
       pasarDeDOMaXML("personas.xml",doc);

    }

    public static void verContenidoPersona(Document doc) {

        // Obtener la raíz

        Element raiz = doc.getDocumentElement();

        // Hacer la operación

        NodeList personas = raiz.getElementsByTagName("persona");

        System.out.println(personas.getLength());

        System.out.print(raiz.getTagName());

        for (int i = 0; i < personas.getLength(); i++) {
            Node n = personas.item(i);
            Element p = (Element) n;
            System.out.print(p.getTagName() + ":");

            NodeList hijosP = p.getChildNodes();

            for (int j = 0; j < hijosP.getLength(); j++) {
                Node n2 = hijosP.item(j);

                switch (n2.getNodeType()) {
                    case Node.ELEMENT_NODE:
                        Element e2 = (Element) n2;
                        System.out.print(e2.getTagName() + ":"+e2.getTextContent());

                        break;
                    case Node.TEXT_NODE:
                        Text t = (Text) n2;
                        System.out.print(t.getWholeText());
                        break;
                }

            }

        }

    }

    public static void buscarUnaPersona(Document doc, int id) {

        // Obtener la raíz

        Element raiz = doc.getDocumentElement();

        NodeList listaPersona = raiz.getElementsByTagName("persona");

        boolean existe = false;

        for (int i = 0; i < listaPersona.getLength(); i++) {
            Element persona = (Element) listaPersona.item(i);

            if (persona.getAttribute("id").equals("" + id)) {
                existe = true;
            }

        }

        if (existe) {
            System.out.println("He encontrado la persona.");
        } else {
            System.out.println("No existe en el documento.");
        }

    }

    public static Document pasarDeXMLaDOM(String ruta) throws SAXException, IOException, ParserConfigurationException {
        // 1o Creamos una nueva instancia de una fabrica de constructores de documentos.
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // 2o A partir de la instancia anterior, fabricamos un constructor de
        // documentos, que procesará el XML.
        DocumentBuilder db = dbf.newDocumentBuilder();
        // 3o Procesamos el documento (almacenado en un archivo) y lo convertimos en un
        // árbol DOM.
        Document doc = db.parse(ruta);
        return doc;
    }

    public static void pasarDeDOMaXML(String ruta, Document doc)
            throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError,
            TransformerException {
        // 1o Creamos una instancia de la clase File para acceder al archivo donde
        // guardaremos el
        // XML.
        File f = new File(ruta);
        // 2o Creamos una nueva instancia del transformador a través de la fábrica de
        // transformadores.
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        // 3o Establecemos algunas opciones de salida, como por ejemplo, la codificación
        // de salida.
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        // 4o Creamos el StreamResult, intermediaria entre el transformador y el archivo
        // de destino.
        StreamResult result = new StreamResult(f);
        // 5o Creamos el DOMSource, intermediaria entre el transformador y el árbol DOM.
        DOMSource source = new DOMSource(doc);
        // 6o Realizamos la transformación.
        transformer.transform(source, result);
    }

}
