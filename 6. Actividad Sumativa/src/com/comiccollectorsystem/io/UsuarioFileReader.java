package com.comiccollectorsystem.io;

import com.comiccollectorsystem.modelo.Comic;
import com.comiccollectorsystem.modelo.Usuario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class UsuarioFileReader {
    public static HashMap<String, Usuario> cargarUsuarios(String ruta) {
        HashMap<String, Usuario> usuarios = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            Usuario usuarioActual = null;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                if (linea.startsWith("Usuario:")) {
                    String nombre = linea.substring(linea.indexOf(":") + 2, linea.lastIndexOf("[")).trim();
                    String rut = linea.substring(linea.indexOf("[") + 1, linea.indexOf("]")).trim();
                    usuarioActual = new Usuario(rut, nombre);
                    usuarios.put(rut, usuarioActual);
                } else if (linea.startsWith("-") && usuarioActual != null) {
                    String[] partes = linea.substring(1).split("por");
                    if (partes.length == 2) {
                        String titulo = partes[0].trim();
                        String autor = partes[1].trim();
                        Comic comic = new Comic(titulo, autor, "Cómic");
                        comic.marcarPrestado();
                        usuarioActual.agregarCompra(comic);
                    }
                }
            }
            System.out.println("Usuarios cargados desde archivo: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al leer usuarios: " + e.getMessage());
        }
        return usuarios;
    }
}