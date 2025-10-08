package com.dam.ada;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class ConsultasXPath {
    public static void main(String[] args)
            throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {

        Document doc = pasarDeXMLaDOM("personas.xml");

        consultarPersonaMenosDeX(doc,20);

        //consultarPersonaLlamadasX(doc, "Juan");

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

    public static void verContenidoPersona(NodeList nodos) {

        for (int i = 0; i < nodos.getLength(); i++) {
            Node n = nodos.item(i);
            Element p = (Element) n;
            System.out.print(p.getTagName() + ":");

            NodeList hijosP = p.getChildNodes();

            for (int j = 0; j < hijosP.getLength(); j++) {
                Node n2 = hijosP.item(j);

                switch (n2.getNodeType()) {
                    case Node.ELEMENT_NODE:
                        Element e2 = (Element) n2;
                        System.out.print(e2.getTagName() + ":" + e2.getTextContent());

                        break;
                    case Node.TEXT_NODE:
                        Text t = (Text) n2;
                        System.out.print(t.getWholeText());
                        break;
                }

            }

        }

    }


    public static void consultarPersonaMenosDeX(Document doc, int edadRef) throws XPathExpressionException {
        // Crea el objeto XpathFactory
        XPath xpath = XPathFactory.newInstance().newXPath();

        String xPathExpression = "/personas/persona[./edad<" + edadRef + "]";
        // String xPathExpression="/personas/persona[./edad<50]";

        // Lanzo la consulta
        NodeList nodos = (NodeList) xpath.evaluate(xPathExpression, doc, XPathConstants.NODESET);

        System.out.println(nodos.getLength());

        // Ver resultados
        verContenidoPersona(nodos);

    }

    public static void consultarPersonaLlamadasX(Document doc, String nombreRef) throws XPathExpressionException {
        // Crea el objeto XpathFactory
        XPath xpath = XPathFactory.newInstance().newXPath();

        String xPathExpression = "/personas/persona[./nombre='" + nombreRef + "']";

        // Lanzo la consulta
        NodeList nodos = (NodeList) xpath.evaluate(xPathExpression, doc, XPathConstants.NODESET);

        System.out.println(nodos.getLength());

        // Ver resultados
        verContenidoPersona(nodos);
    }

}
