package com.bibliotecaduoc.modelo;

public class Libro {
    private String titulo;
    private String autor;
    private boolean prestado;
    private Usuario prestadoA;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false;
        this.prestadoA = null;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean estaPrestado() {
        return prestado;
    }

    public void prestar(Usuario usuario) {
        this.prestado = true;
        this.prestadoA = usuario;
    }

    public void devolver() {
        this.prestado = false;
        this.prestadoA = null;
    }

    @Override
    public String toString() {
        if (prestado) {
            return titulo + " de " + autor + " — Prestado a: " + prestadoA.getNombre();
        } else {
            return titulo + " de " + autor + " — Disponible";
        }
    }
}
