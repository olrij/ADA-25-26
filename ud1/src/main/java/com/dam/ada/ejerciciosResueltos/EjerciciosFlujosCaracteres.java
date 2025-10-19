package com.dam.ada.ejerciciosResueltos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.text.StyledEditorKit.BoldAction;

public class EjerciciosFlujosCaracteres {

    public static void main(String[] args) {

        // Ejercicio 4
        // File archivo = new File("C:\\Users\\JOLMRIV\\Downloads\\p\\A.txt");
        // int n = contarPalabras(archivo);
        // System.out.println("En el archivo " + archivo.getName() + " hay " + n + "
        // palabras.");

        // Ejercicio 5
        File archivo = new File("C:\\Users\\JOLMRIV\\Downloads\\p\\A.txt");

        copiaSinEspaciosYEnMayus(archivo);
    }

    public static int contarPalabras(File archivo) {
        // Se consideran palabras todos los elementos diferentes al espacio (32), al
        // salto de línea (10) y sl retorno de carro (13)

        int palabras = 0;

        // Pongo el valor de uno de esos 3 elementos como valor inicial para que se
        // tenga en cuanta la primera palabra
        int ultimoCaracter = 10;

        // Comprobamos que es un fichero y existe
        if (!archivo.exists() || !archivo.isFile()) {
            System.out.println("El archivo pasado por parámetro no es válido.");
        } else {
            try (FileReader fr = new FileReader(archivo)) {

                int caracter = 0;

                caracter = fr.read();

                while (caracter != -1) {

                    // Si el nuevo caracter es difente a un espacio, salto de línea o retorno de
                    // carro y el último caracter era uno de estos significa que hay una nueva
                    // palabra
                    if ((caracter != 32 && caracter != 10 && caracter != 13)
                            && (ultimoCaracter == 32 || ultimoCaracter == 10 || ultimoCaracter == 13)) {
                        palabras++;
                    }
                    ultimoCaracter = caracter;

                    caracter = fr.read();
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return palabras;

    }

    public static void copiaSinEspaciosYEnMayus(File archivo) {

        String mensaje = "";
        String mensajeModificado = "";

        // Comprobamos que es un fichero y existe
        if (!archivo.exists() || !archivo.isFile()) {
            System.out.println("El archivo pasado por parámetro no es válido.");
        } else {

            try {
                // Creamos un fichero para copiar el resultado
                FileWriter fw = new FileWriter(archivo.getParentFile().getAbsolutePath()+ File.separator + "copia.txt");

                // Leemos línea a línea y vamos escribiendo el mensaje modificado
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);


                mensaje = br.readLine();

                while(mensaje!=null){

                    // Modificamos el mensaje
                    mensajeModificado=mensaje.replace(" ","").toUpperCase();
                    System.out.println(mensajeModificado);

                    // Escribimos el mensaje
                    fw.write(mensajeModificado+"\n");

                    // Leemos la siguiente línea
                    mensaje=br.readLine();


                }

                fr.close();
                br.close();
                fw.flush();
                fw.close();


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
