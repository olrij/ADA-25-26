package com.ada.mvc;

import java.io.IOException;
import java.util.List;

public class LibreriaControlador {
    private LibreriaModelo modelo;
    private LibreriaVista vista;

    public LibreriaControlador(LibreriaModelo modelo, LibreriaVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    // Método para iniciar el flujo de la aplicación
    public void start() throws IOException {

        while (true) {
            // Mostrar los libros actuales
            List<Libro> libros = modelo.cargarLibrosDesdeArchivo();
            vista.imprimirLibros(libros);

            // Pedir detalles de un nuevo libro a través de la Vista
            Libro nuevoLibro = vista.nuevoLibro();

            // Agregar el nuevo libro al archivo de texto
            modelo.guardarLibroEnArchivo(nuevoLibro);

            // Mostrar mensaje de confirmación
            vista.mostrarMensaje("¡Libro añadido exitosamente!");

            // Volver a mostrar la lista actualizada de libros
            libros = modelo.cargarLibrosDesdeArchivo();
            vista.imprimirLibros(libros);
        }

    }
}
