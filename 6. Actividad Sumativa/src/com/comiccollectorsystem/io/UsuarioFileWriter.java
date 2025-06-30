package com.comiccollectorsystem.io;

import com.comiccollectorsystem.modelo.Usuario;

import java.io.*;
import java.util.HashMap;

public class UsuarioFileWriter {
    public static void guardarUsuarios(String ruta, HashMap<String, Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (Usuario u : usuarios.values()) {
                bw.write(u.toString());
                bw.newLine();
                bw.newLine();
            }
            System.out.println("Usuarios guardados correctamente en: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al guardar archivo: " + e.getMessage());
        }
    }
}