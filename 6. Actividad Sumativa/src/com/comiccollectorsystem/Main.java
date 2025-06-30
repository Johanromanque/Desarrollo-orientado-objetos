package com.comiccollectorsystem;
import com.comiccollectorsystem.io.ComicCSVReader;
import com.comiccollectorsystem.io.UsuarioFileReader;
import com.comiccollectorsystem.io.UsuarioFileWriter;

import com.comiccollectorsystem.modelo.Usuario;
import com.comiccollectorsystem.excepciones.ComicNoDisponibleException;
import com.comiccollectorsystem.excepciones.UsuarioNoEncontradoException;
import com.comiccollectorsystem.servicios.ComicStore;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ComicStore tienda = new ComicStore();
        tienda.getComics().addAll(ComicCSVReader.cargarComics("src/comic.csv"));

        tienda.getUsuarios().putAll(UsuarioFileReader.cargarUsuarios("usuarios.txt"));

        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== ComicCollectorSystem =====");
            System.out.println("1. Ver cómics disponibles");
            System.out.println("2. Comprar cómic");
            System.out.println("3. Registrar usuario");
            System.out.println("4. Salir");
            System.out.print("Seleccione opción: ");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    if (tienda.getComics().isEmpty()) {
                        System.out.println("No hay cómics disponibles.");
                    } else {
                        tienda.getComics().forEach(System.out::println);
                    }
                    break;

                case "2":
                    try {
                        System.out.print("Ingrese RUT: ");
                        String rut = sc.nextLine();

                        System.out.print("Ingrese título del cómic: ");
                        String titulo = sc.nextLine();

                        tienda.comprarComic(rut, titulo);
                        System.out.println("Compra realizada.");
                    } catch (Exception e) {
                        System.out.println("Error " + e.getMessage());
                    }
                    break;

                case "3":
                    System.out.print("Ingrese RUT: ");
                    String rut = sc.nextLine();
                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();

                    if (tienda.getUsuarios().containsKey(rut)) {
                        System.out.println("Usuario ya registrado.");
                    } else {
                        tienda.registrarUsuario(new Usuario(rut, nombre));
                        System.out.println("Usuario registrado.");
                    }
                    break;

                case "4":
                    // Guardar usuarios antes de salir
                    UsuarioFileWriter.guardarUsuarios("usuarios.txt", tienda.getUsuarios());
                    System.out.println("Datos guardados. ¡Hasta luego!");
                    salir = true;
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

        sc.close();
    }
}