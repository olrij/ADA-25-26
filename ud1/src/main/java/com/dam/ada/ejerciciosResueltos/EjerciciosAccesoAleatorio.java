package com.dam.ada.ejerciciosResueltos;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

public class EjerciciosAccesoAleatorio {

    public static void main(String[] args) {

        File archivo = new File("C:\\Users\\JOLMRIV\\Downloads\\p\\A.txt");
        archivoSinEspaciosYEnMayus(archivo);

    }

    public static void archivoSinEspaciosYEnMayus(File archivo) {

        // Comprobamos que es un fichero y existe
        if (!archivo.exists() || !archivo.isFile()) {
            System.out.println("El archivo pasado por parámetro no es válido.");
        } else {

            try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
                int car = 0;
                
                try {
                    while (true) {
                    // Leemos el caracter
                    car = raf.readByte();

                    // Si es un caracter minus lo paso a mayus
                    if(car>=97 && car<=122){
                        // Movemos el puntero hacia atrás y escribimos el caracter en Mayus
                        raf.seek(raf.getFilePointer()-1);
                        raf.writeByte((car-32));

                    }


                    //Si es un espacio desplazamos todo el contenido del archivo con un método auxiliar
                    if(car==32){
                        desplazarContenidoArchivo(raf, raf.getFilePointer());
                        // Retrocedemos una posición para que no se ignore este caracter
                        raf.seek(raf.getFilePointer()-1);
                    }
                    
                    
    
                    }
                } catch (EOFException e) {

                    System.out.println("Se ha leído el fichero por completo");
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    public static void desplazarContenidoArchivo(RandomAccessFile raf,long desdePos) throws IOException{

        int car=-1;
        
        try {
                    // Leemos el caracter
                    car = raf.readByte();

                    // Retrocedemos 2 pos para desplazar el texto 
                    raf.seek(raf.getFilePointer()-2);

                    // Escribimos
                    raf.writeByte(car);

                    // Retrocedemos
                    
                    while (true) {
                        // Sobreescribimos el archivo hasta el final (hay que tener mucho cuidado con el puntero...)
                        raf.seek(raf.getFilePointer()+1);
                        car=raf.readByte();
                    
                        raf.seek(raf.getFilePointer()-2);

                        raf.writeByte(car);
    
                    }
                } catch (EOFException e) {

                }

        // Eliminamos el último caracter del fichero (sobra al desplazar el contenido del documento)
        raf.setLength(raf.length()-1);

        // Devolvemos el puntero a la posición en la que estaba
        raf.seek(desdePos);
 
    }

}
