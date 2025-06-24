package com.bibliotecaduoc.servicios;

import com.bibliotecaduoc.excepciones.LibroNoEncontradoException;
import com.bibliotecaduoc.excepciones.LibroYaPrestadoException;
import com.bibliotecaduoc.modelo.Libro;
import com.bibliotecaduoc.modelo.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Biblioteca {

    private HashMap<String, Libro> libros;         // Búsqueda por título
    private TreeSet<Libro> librosOrdenados;        // Libros ordenados por titulo (sin duplicados)
    private ArrayList<Libro> listaLibros;          // Acceso secuencial por índice

    private HashMap<String, Usuario> usuarios;     // Búsqueda por RUT
    private HashSet<Usuario> setUnicoUsuarios;     // usuarios únicos por RUT sin duplicados

    // Constructor de la biblioteca
    public Biblioteca() {
        libros = new HashMap<>();
        librosOrdenados = new TreeSet<>((l1, l2) -> l1.getTitulo().compareToIgnoreCase(l2.getTitulo()));
        listaLibros = new ArrayList<>();

        usuarios = new HashMap<>();
        setUnicoUsuarios = new HashSet<>();

        cargarLibrosIniciales();
    }

    // Métodos para agregar libros
    public void agregarLibro(Libro libro) {
        libros.put(libro.getTitulo(), libro);
        librosOrdenados.add(libro);
        listaLibros.add(libro); // NUEVO: también agregamos a ArrayList
    }

    // Método para agregar un usuario
    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getRut(), usuario);
        setUnicoUsuarios.add(usuario);
    }

    // Métodos para buscar libro
    public Libro buscarLibro(String titulo) throws LibroNoEncontradoException {
        for (Libro libro : libros.values()) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        throw new LibroNoEncontradoException("Libro no encontrado: " + titulo);
    }
    // Método para prestar un libro a un usuario
    public void prestarLibro(String titulo, String rut) throws LibroNoEncontradoException, LibroYaPrestadoException {
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
    // Método para devolver un libro
    public void devolverLibro(String titulo) throws LibroNoEncontradoException {
        Libro libro = buscarLibro(titulo);
        if (!libro.estaPrestado()) {
            System.out.println("El libro ya está disponible.");
        } else {
            libro.devolver();
            System.out.println("Libro devuelto correctamente.");
        }
    }
    // Muestra libros que no están prestados (usando ArrayList)
    public void mostrarLibrosDisponibles() {
        System.out.println("Libros disponibles en la biblioteca:");
        for (Libro libro : listaLibros) { // MOSTRAMOS usando el ArrayList
            if (!libro.estaPrestado()) {
                System.out.println(" - " + libro);
            }
        }
    }
    // Muestra todos los libros ordenados alfabéticamente por título (usando TreeSet
    public void mostrarLibrosOrdenados() {
        System.out.println("Catálogo ordenado de libros:");
        for (Libro libro : librosOrdenados) {
            System.out.println(" - " + libro);
        }
    }
    // Muestra todos los usuarios únicos registrados (usando HashSet)
    public void mostrarUsuariosUnicos() {
        System.out.println("Usuarios registrados (únicos por RUT):");
        for (Usuario usuario : setUnicoUsuarios) {
            System.out.println(" - " + usuario);
        }
    }
    // Carga conjunto inicial de libros predefinidos
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
