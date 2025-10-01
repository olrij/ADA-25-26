package com.dam.ada;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AccesoAleatorio {

    public static void main(String[] args) {
        cambiarNota();
    }

    public static void cambiarNota() {
        try (RandomAccessFile raf = new RandomAccessFile("notas", "rw")) {

            int id = 0;

            try {
                id = raf.readInt();
                // Lo busco
                while (id != 2) {
            
                    raf.skipBytes(12);
                    id=raf.readInt();
                    

                }
                // Lo encuentro

                // Devuelvo el puntero
                //raf.seek(raf.getFilePointer()-12);
                raf.skipBytes(2);
                raf.writeDouble(7.0);

            } catch (EOFException e) {

                System.out.println("He salido del archivo sin encontrar el alumno");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
