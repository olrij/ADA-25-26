package com.dam.ada;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FicherosBinarios {

    public static void main(String[] args) {
        // escribirFichero();
        //leerFichero();
        obtenerNotasEnLista();

    }

    public static void escribirFichero() {

        try (FileOutputStream fos = new FileOutputStream("notas")) {
            DataOutputStream dos = new DataOutputStream(fos);

            // Rellenar doc

            dos.writeInt(1);
            dos.writeChar(' ');
            dos.writeDouble(8.75);
            dos.writeChar('\n');

            dos.writeInt(2);
            dos.writeChar(' ');
            dos.writeDouble(6.4);
            dos.writeChar('\n');

            dos.writeInt(3);
            dos.writeChar(' ');
            dos.writeDouble(4.97);
            dos.writeChar('\n');

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

        public static void obtenerNotasEnLista() {
        try (FileInputStream fis = new FileInputStream("notas")) {
            DataInputStream dis = new DataInputStream(fis);

            ArrayList<Double> notas=new ArrayList<Double>();

            Double nota;

            try {
                while (true) {
                    
                    dis.readInt();
                    dis.readChar();
                    nota=dis.readDouble();
                    dis.readChar();
                    notas.add(nota);
                }
            } catch (EOFException e) {

                System.out.println("He salidpo deñl bucle");
            }


            for(Double d:notas){
                System.out.println(d);
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void leerFichero() {
        try (FileInputStream fis = new FileInputStream("notas")) {
            DataInputStream dis = new DataInputStream(fis);

            int id;
            Double nota;

            try {
                while (true) {
                    
                    id=dis.readInt();
                    dis.readChar();
                    nota=dis.readDouble();
                    dis.readChar();
                    System.out.println(id+" tiene la nota "+nota);
                }
            } catch (EOFException e) {

                System.out.println("He salidpo deñl bucle");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
