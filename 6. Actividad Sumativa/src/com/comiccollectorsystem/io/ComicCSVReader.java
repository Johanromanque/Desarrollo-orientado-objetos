package com.comiccollectorsystem.io;

import com.comiccollectorsystem.modelo.Comic;

import java.io.*;
import java.util.ArrayList;

public class ComicCSVReader {
    public static ArrayList<Comic> cargarComics(String rutaArchivo) {
        ArrayList<Comic> listaComics = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split(",");
                if (datos.length >= 3) {
                    Comic nuevoComic = new Comic(datos[0].trim(), datos[1].trim(), datos[2].trim());
                    listaComics.add(nuevoComic);

                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo CSV: " + e.getMessage());
        }
        return listaComics;
    }
}