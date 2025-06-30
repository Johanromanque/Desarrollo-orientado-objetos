package com.comiccollectorsystem.modelo;

import java.util.Objects;

public class Comic {
    private String titulo;
    private String autor;
    private String tipo; // Ej: "Cómic", "Novela Gráfica", "Figura"
    private boolean disponible;

    public Comic(String titulo, String autor, String tipo) {
        this.titulo = titulo;
        this.autor = autor;
        this.tipo = tipo;
        this.disponible = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void marcarPrestado() {
        this.disponible = false;
    }

    public void marcarDisponible() {
        this.disponible = true;
    }

    @Override
    public String toString() {
        return "[" + tipo + "] \"" + titulo + "\" por " + autor +
                (disponible ? " — Disponible" : " — No disponible");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comic)) return false;
        Comic comic = (Comic) o;
        return titulo.equalsIgnoreCase(comic.titulo)
                && autor.equalsIgnoreCase(comic.autor)
                && tipo.equalsIgnoreCase(comic.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo.toLowerCase(), autor.toLowerCase(), tipo.toLowerCase());
    }
}
