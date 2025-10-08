package com.ada.mvc;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibreriaModelo {
    private final String ruta = "libreria.txt";

    // Método para leer los libros del archivo de texto
    public List<Libro> cargarLibrosDesdeArchivo() throws IOException {
        List<Libro> libros = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos.length == 2) {
                Libro libro = new Libro(datos[0].trim(), datos[1].trim());
                libros.add(libro);
            }
        }
        br.close();
        return libros;
    }

    // Método para guardar un libro nuevo en el archivo de texto
    public void guardarLibroEnArchivo(Libro libro) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true));
        bw.write(libro.getTitulo() + "," + libro.getAutor());
        bw.newLine();
        bw.close();
    }
}