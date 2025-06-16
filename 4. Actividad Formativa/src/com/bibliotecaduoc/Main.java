package com.bibliotecaduoc;

import com.bibliotecaduoc.excepciones.LibroNoEncontradoException;
import com.bibliotecaduoc.excepciones.LibroYaPrestadoException;
import com.bibliotecaduoc.modelo.Usuario;
import com.bibliotecaduoc.servicios.Biblioteca;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        boolean salir = false;

        System.out.println("\nBienvenido al Sistema de DUOC UC");

        while (!salir) {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Ver todos los libros");
            System.out.println("2. Prestar un libro");
            System.out.println("3. Devolver un libro");
            System.out.println("4. Registrar usuario");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        biblioteca.mostrarLibrosDisponibles();
                        break;
                    case 2:
                        try {
                            System.out.print("Ingrese título del libro a prestar: ");
                            String titulo = sc.nextLine().toLowerCase();

                            System.out.print("Ingrese RUT del usuario que lo pide: ");
                            String rut = sc.nextLine();

                            biblioteca.prestarLibro(titulo, rut);

                        } catch (LibroNoEncontradoException | LibroYaPrestadoException e) {
                            System.out.println(e.getMessage());
                        }
                        break;


                    case 3:
                        System.out.print("Ingrese título del libro a devolver: ");
                        String tituloDevolver = sc.nextLine();
                        biblioteca.devolverLibro(tituloDevolver);
                        break;

                    case 4:
                        System.out.print("Ingrese RUT del usuario: ");
                        String rut = sc.nextLine();
                        System.out.print("Ingrese nombre del usuario: ");
                        String nombre = sc.nextLine();
                        biblioteca.agregarUsuario(new Usuario(rut, nombre));
                        System.out.println("Usuario registrado exitosamente.");
                        break;
                    case 5:
                        salir = true;
                        System.out.println("Gracias por usar el sistema.");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            } catch (LibroNoEncontradoException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Entrada inválida. Use solo números en el menú.");
            }
        }

        sc.close();
    }
}
