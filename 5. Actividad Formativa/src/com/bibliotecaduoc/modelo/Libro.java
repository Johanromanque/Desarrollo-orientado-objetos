package com.bibliotecaduoc.modelo;

import java.util.Objects;

public class Libro {
    private String titulo;
    private String autor;
    private boolean prestado;
    private String rutPrestamo; // Rut del usuario que lo tiene prestado

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean estaPrestado() {
        return prestado;
    }
    // marca el libro como prestado
    public void prestar(Usuario usuario) {
        this.prestado = true;
        this.rutPrestamo = usuario.getRut();
    }
    // marca el libro como devuelto
    public void devolver() {
        this.prestado = false;
        this.rutPrestamo = null;
    }

    @Override
    public String toString() {
        return "\"" + titulo + "\" de " + autor + (prestado ? " (Prestado)" : " (Disponible)");
    }
    // Dos libros se consideran iguales si tienen el mismo título (ignora mayúsculas)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return titulo.equalsIgnoreCase(libro.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo.toLowerCase());
    }
}
