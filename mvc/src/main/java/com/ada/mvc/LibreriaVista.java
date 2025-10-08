package com.ada.mvc;

import java.util.List;
import java.util.Scanner;

public class LibreriaVista {
    private Scanner scanner;

    public LibreriaVista() {
        this.scanner = new Scanner(System.in);
    }

    // Método para mostrar la lista de libros
    public void imprimirLibros(List<Libro> libros) {
        System.out.println("\nLista de libros:");
        for (Libro libro : libros) {
            System.out.println("Título: " + libro.getTitulo() + ", Autor: " + libro.getAutor());
        }
        System.out.println("---------------------------");
    }

    // Método para mostrar mensajes al usuario
    public void mostrarMensaje(String mensage) {
        System.out.println(mensage);
    }

    // Método para pedir los detalles de un nuevo libro al usuario
    public Libro nuevoLibro() {
        System.out.println("\nAgregar un nuevo libro:");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        return new Libro(titulo, autor);
    }
}
