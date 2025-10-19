package com.dam.ada.ejerciciosResueltos;

import java.io.File;
import java.util.ArrayList;

public class EjerciciosClaseFile {

    public static void main(String[] args){

        // Ejercicio 3

        // File dirAListar=new File("C:\\Users\\JOLMRIV\\Documents\\ADA-25-26");
        // listarDirectoriosRecursivo(dirAListar, 0);
        
        // Ejercicio 4

        //File dir=new File("C:\\Users\\JOLMRIV\\Downloads");
        //mostrarArchivosConExtension(dir,"pdf");

        // Ejercicio 5

        //File dir=new File("C:\\Users\\JOLMRIV\\Downloads\\p");
        //borrarArchivosTxt(dir);





    }
    
    public static void listarDirectoriosRecursivo(File dir,int nivelTab){

        // Creamos la tabulación necesaria para el nivel
        String tabulacion="";

        for(int i=0;i<nivelTab;i++){
            tabulacion+="\t";
        }

        

        if(dir.isDirectory()){

            File[] archiviosDentro = dir.listFiles();

            for (File f:archiviosDentro){

                if(f.isDirectory()){
                    
                    System.out.println(tabulacion+f.getName());

                    // Mostramos el nombre del directorio y llamamos de nuevo al método y aumentamos la tabulación
                    nivelTab++;
                    listarDirectoriosRecursivo(f, nivelTab);
                    
                    // Disminuimos el nivel de tabulación al salir del directorio interior
                    nivelTab--;
                }else{
                    // Mostramos el nombre del archivo
                    System.out.println(tabulacion+f.getName());
                }


            }


        }else{
            // Salimos sin mostrar nada porque no es un directorio el elemento pasado por parámetro
            System.out.println("No ha introducido un directorio.");
        }
    }

    public static void mostrarArchivosConExtension(File dir, String extension) {

        // Comprobamos que la ruta existe y es un directorio
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("La ruta no es un directorio válido.");
            return;
        }

        // Obtenemos la lista con los archivos dentro del directorio
        File[] archivos = dir.listFiles();

        // Lista para añadir los archivos con la extensión correcta
        ArrayList<File> archivosConExtensionX=new ArrayList<File>();

        // Recorremos la lista y nos quedamos sólo con los que cumplen la condición
        for(File f:archivos){
            if(f.getName().endsWith(extension)){
                archivosConExtensionX.add(f);
            }
        }

        // Mostramos los archivos encontrados
        if (archivosConExtensionX.size() == 0) {
            System.out.println("No se encontraron archivos con la extensión ." + extension);
        } else {
            System.out.println("Archivos con extensión ." + extension + ":");
            for (File archivo : archivosConExtensionX) {
                System.out.println("\t" + archivo.getName());
            }
        }
    }

    public static void borrarArchivosTxt(File dir) {

        String extension="txt";

        // Comprobamos que la ruta existe y es un directorio
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("La ruta no es un directorio válido.");
            return;
        }

        // Obtenemos la lista con los archivos dentro del directorio
        File[] archivos = dir.listFiles();

        // Lista para añadir los archivos con la extensión correcta
        ArrayList<File> archivosConExtensionX=new ArrayList<File>();

        // Recorremos la lista y nos quedamos sólo con los que cumplen la condición
        for(File f:archivos){
            if(f.getName().endsWith(extension)){
                archivosConExtensionX.add(f);
            }
        }

        // Mostramos los archivos encontrados
        if (archivosConExtensionX.size() == 0) {
            System.out.println("No hay ningún archivo a borrar.");
        } else {
            System.out.println("Archivos con extensión ." + extension + ":");
            for (File archivo : archivosConExtensionX) {
                if(archivo.delete()){
                    System.out.println("Se ha borrado el archivo "+archivo.getName());
                }else{
                    System.out.println("No se ha podido borrar el archivo "+archivo.getName());
                }
            }
        }
    }




}
