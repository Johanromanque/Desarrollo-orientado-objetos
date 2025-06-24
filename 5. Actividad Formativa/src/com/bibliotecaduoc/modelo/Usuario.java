package com.bibliotecaduoc.modelo;

import java.util.Objects;

public class Usuario {
    private String rut;
    private String nombre;
    // Constructor para crear un usuario con RUT y nombre
    public Usuario(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }
    //
    @Override
    public String toString() {
        return nombre + " (RUT: " + rut + ")";
    }
    // Dos usuarios se consideran iguales si tienen el mismo RUT
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return rut.equalsIgnoreCase(usuario.rut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rut.toLowerCase());
    }
}
