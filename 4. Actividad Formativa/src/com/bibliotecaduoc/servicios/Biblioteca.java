package com.bibliotecaduoc.servicios;

import com.bibliotecaduoc.excepciones.LibroNoEncontradoException;
import com.bibliotecaduoc.excepciones.LibroYaPrestadoException;
import com.bibliotecaduoc.modelo.Libro;
import com.bibliotecaduoc.modelo.Usuario;

import java.util.HashMap;

public class Biblioteca {
    private HashMap<String, Libro> libros;
    private HashMap<String, Usuario> usuarios;

    public Biblioteca() {
        libros = new HashMap<>();
        usuarios = new HashMap<>();
        cargarLibrosIniciales(); // Cargamos libros desde aquí
    }

    public void agregarLibro(Libro libro) {
        libros.put(libro.getTitulo(), libro);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getRut(), usuario);
    }

    public Libro buscarLibro(String titulo) throws LibroNoEncontradoException {
        for (Libro libro : libros.values()) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        throw new LibroNoEncontradoException("Libro no encontrado: " + titulo);
    }


    public void prestarLibro(String titulo, String rut) throws LibroNoEncontradoException, LibroYaPrestadoException {
        // Validar si el usuario existe antes de continuar
        Usuario usuario = usuarios.get(rut);
        if (usuario == null) {
            System.out.println("Usuario no encontrado. Debe registrarlo primero.");
            return;
        }

        Libro libro = buscarLibro(titulo);

        if (libro.estaPrestado()) {
            throw new LibroYaPrestadoException("El libro ya fue prestado.");
        }

        libro.prestar(usuario);
        System.out.println("Libro prestado exitosamente a " + usuario.getNombre() + ".");
    }



    public void mostrarLibrosDisponibles() {
        System.out.println("Libros en biblioteca:");
        for (Libro libro : libros.values()) {
            System.out.println(" - " + libro);
        }
    }

    public void devolverLibro(String titulo) throws LibroNoEncontradoException {
        Libro libro = buscarLibro(titulo);
        if (!libro.estaPrestado()) {
            System.out.println("El libro ya está disponible.");
        } else {
            libro.devolver();
            System.out.println("Libro devuelto correctamente.");
        }
    }


    private void cargarLibrosIniciales() {
        agregarLibro(new Libro("Fundacion", "Isaac Asimov"));
        agregarLibro(new Libro("Cien Años de Soledad", "Gabriel García Márquez"));
        agregarLibro(new Libro("1984", "George Orwell"));
        agregarLibro(new Libro("Fahrenheit 451", "Ray Bradbury"));
        agregarLibro(new Libro("La Odisea", "Homero"));
        agregarLibro(new Libro("El señor de los anillos", "J.R.R. Tolkien"));
        agregarLibro(new Libro("Un mundo feliz", "Aldous Huxley"));
        agregarLibro(new Libro("Rayuela", "Julio Cortázar"));
        agregarLibro(new Libro("Papelucho", "Marcela Paz"));
    }
}
