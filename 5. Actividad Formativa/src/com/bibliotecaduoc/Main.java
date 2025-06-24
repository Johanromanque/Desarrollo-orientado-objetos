package com.bibliotecaduoc;

import com.bibliotecaduoc.excepciones.LibroNoEncontradoException;
import com.bibliotecaduoc.excepciones.LibroYaPrestadoException;
import com.bibliotecaduoc.modelo.Usuario;
import com.bibliotecaduoc.servicios.Biblioteca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner para leer entradas del usuario
        Biblioteca biblioteca = new Biblioteca(); // Instancia de la biblioteca que contiene los libros y usuarios
        boolean salir = false; // Variable para controlar el bucle del menú

        System.out.println(" Bienvenido al Sistema de Biblioteca DUOC UC ");
        // menú principal del sistema de biblioteca
        while (!salir) {
            System.out.println("\n========= MENÚ PRINCIPAL =========");
            System.out.println("1. Ver libros disponibles");
            System.out.println("2. Ver libros ordenados por título");
            System.out.println("3. Prestar un libro");
            System.out.println("4. Devolver un libro");
            System.out.println("5. Registrar usuario");
            System.out.println("6. Ver usuarios registrados");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción (1-7): ");

            String opcionStr = sc.nextLine();
            int opcion;
            // Validación de la entrada del usuario
            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                System.out.println(" Entrada inválida. Ingrese un número del 1 al 7.");
                continue;
            }
            // Procesamiento de la opción seleccionada
            switch (opcion) {
                case 1:
                    biblioteca.mostrarLibrosDisponibles();
                    break;
                case 2:
                    biblioteca.mostrarLibrosOrdenados();
                    break;
                case 3:
                    System.out.print("Ingrese título del libro a prestar: ");
                    String tituloPrestar = sc.nextLine();

                    System.out.print("Ingrese RUT del usuario: ");
                    String rutPrestar = sc.nextLine();
                    // Intento de prestar el libro
                    try {
                        biblioteca.prestarLibro(tituloPrestar, rutPrestar);
                    } catch (LibroNoEncontradoException | LibroYaPrestadoException e) {
                        System.out.println("Error " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Ingrese título del libro a devolver: ");
                    String tituloDevolver = sc.nextLine();

                    try {
                        biblioteca.devolverLibro(tituloDevolver);
                    } catch (LibroNoEncontradoException e) {
                        System.out.println("Error " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("Ingrese RUT del usuario: ");
                    String rut = sc.nextLine();

                    System.out.print("Ingrese nombre del usuario: ");
                    String nombre = sc.nextLine();

                    biblioteca.agregarUsuario(new Usuario(rut, nombre));
                    System.out.println("Usuario registrado exitosamente.");
                    break;
                case 6:
                    biblioteca.mostrarUsuariosUnicos();
                    break;
                case 7:
                    salir = true;
                    System.out.println("Gracias por usar el sistema. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente con un número del 1 al 7.");
            }
        }

        sc.close();
    }
}
