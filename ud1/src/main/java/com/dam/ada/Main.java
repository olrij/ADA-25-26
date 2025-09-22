package com.dam.ada;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //crearEstructura();
        //listarDatos();
        escribir("prueba2.txt","siguo por aquí");
        leerCarACar("prueba2.txt");
        //leerLineaALinea("./ud1/prueba.txt");

    }

    public static void crearEstructura(){

        File pruebaTxt=new File("./datos/prueba.txt"); 
        File dir1=new File("./datos/dir1"); 
        File aTxt=new File("./datos/dir1/a.txt"); 
        File dir2=new File("./datos/dir2"); 

        try {
            if(pruebaTxt.createNewFile()){
                System.out.println("He creado "+pruebaTxt.getName());
            }
            
            if(dir1.mkdir()){
                System.out.println("He creado "+dir1.getName());
            }

            if(aTxt.createNewFile()){
                System.out.println("He creado "+aTxt.getName());
            }

            if(dir2.mkdir()){
                System.out.println("He creado "+dir2.getName());
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void listarDatos(){
        File datos=new File("./datos");

        File[] archivos = datos.listFiles();

        for(File a:archivos){
            System.out.println(a.getName());

            if(a.isDirectory()){
                File[] archivos2 = a.listFiles();
                for(File b:archivos2){
                    System.out.println("\t"+b.getName());
                }

            }

        }
    }

    public static void mostrarDirectoriosDentro(){}

    public static void leerCarACar(String ruta){
        try {
            // 1
            FileReader fr=new FileReader(ruta);

            // 2. Operación

            int caracter=0;
            while (caracter!=-1){
                caracter=fr.read();
                if(caracter!=-1){
                    System.out.print((char)caracter);
                }
    
            }

            System.out.println();
            

            // 3. cerrar
            fr.close();



        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void escribir(String ruta,String texto){
        try {
            // 1
            FileWriter fw=new FileWriter(ruta,false);



            // 2. Operación

            fw.write(texto);
            

            // 3. cerrar
            fw.flush();
            fw.close();



        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void leerLineaALinea(String ruta){
        try {
            // 1
            FileReader fr=new FileReader(ruta);
            BufferedReader br=new BufferedReader(fr);

            // 2. Operación

            String linea=br.readLine();
            while (linea!=null){
                System.out.println(linea);
                linea=br.readLine();
    
            }
            

            // 3. cerrar
            fr.close();



        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}