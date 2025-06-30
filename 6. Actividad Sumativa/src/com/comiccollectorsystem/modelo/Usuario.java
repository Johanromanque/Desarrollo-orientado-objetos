package com.comiccollectorsystem.modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Usuario {
    private String rut;
    private String nombre;
    private ArrayList<Comic> compras = new ArrayList<>();

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

    public ArrayList<Comic> getCompras() {
        return compras;
    }

    public void agregarCompra(Comic c) {
        compras.add(c);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Usuario: " + nombre + " [" + rut + "]\n");
        sb.append("   Compras:\n");
        if (compras.isEmpty()) {
            sb.append("   - Sin compras registradas\n");
        } else {
            for (Comic c : compras) {
                sb.append("   - ").append(c.getTitulo()).append(" por ").append(c.getAutor()).append("\n");
            }
        }
        return sb.toString();
    }

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
