package com.dam.ada.ejerciciosResueltos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class EjerciciosFlujosBytes {

    public static void main(String[] args) {

        ejercicio2();
        leerDocBinario(new File("parejas.dat"));
        ejercicio3();


        
    }

    public static void ejercicio2(){
        Scanner sc = new Scanner(System.in);
        String nombreArchivo = "parejas.dat";

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombreArchivo))) {

            System.out.println("Introduce parejas de números enteros. Pulsa ENTER sin escribir nada para finalizar.");

            while (true) {
                System.out.print("Número 1: ");
                String linea1 = sc.nextLine().trim();
                if (linea1.isEmpty()) break; 

                System.out.print("Número 2: ");
                String linea2 = sc.nextLine().trim();
                if (linea2.isEmpty()) break;  

                try {
                    int num1 = Integer.parseInt(linea1);
                    int num2 = Integer.parseInt(linea2);

                    // Escribimos los enteros en formato binario con un espacio entre medias
                    dos.writeInt(num1);
                    dos.writeChar(' ');
                    dos.writeInt(num2);
                    dos.writeChar('\n');

                } catch (NumberFormatException e) {
                    System.out.println("Debes introducir un número entero válido.");
                }
            }

            System.out.println("Archivo binario creado correctamente: " + nombreArchivo);

        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
    }

    public static void leerDocBinario(File f){
        try (DataInputStream dis = new DataInputStream(new FileInputStream(f))) {

            try {
                while (true) {
                    
                    System.out.print(dis.readInt());
                    System.out.print(dis.readChar());
                    System.out.print(dis.readInt());
                    System.out.print(dis.readChar());
                }
            } catch (EOFException e) {

                System.out.println("Se ha leído el fichero por completo");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void ejercicio3(){

        String nombreArchivo = "parejas.dat";
        double media=0;
        double mediaPonderada=0;

        ArrayList<Integer> primeraColumna=new ArrayList<Integer>();
        ArrayList<Integer> segundaColumna=new ArrayList<Integer>();

        // Leo el archivo y añado los valores en los arraylist con los que haré los cálculos
        try (DataInputStream dis = new DataInputStream(new FileInputStream(nombreArchivo))) {

            try {
                while (true) {

                    primeraColumna.add(dis.readInt());
                    //Quito el espacio
                    dis.readChar();

                    segundaColumna.add(dis.readInt());
                    // Quitoel salto de línea
                    dis.readChar();
                }
            } catch (EOFException e) {

                System.out.println("Se ha leído el fichero por completo");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        // Hacemos los cálculos

        int dividendoMediaPonderada=0;
        for(int i=0;i<primeraColumna.size();i++){
            media+=primeraColumna.get(i);

            mediaPonderada=primeraColumna.get(i)*segundaColumna.get(i);
            dividendoMediaPonderada+=segundaColumna.get(i);
        }

        media=media/primeraColumna.size();
        mediaPonderada=mediaPonderada/dividendoMediaPonderada;

        // Imprimimos los resultados
        System.out.println("La media es: "+media);
        System.out.println("La media ponderada es: "+mediaPonderada);


    }

}
