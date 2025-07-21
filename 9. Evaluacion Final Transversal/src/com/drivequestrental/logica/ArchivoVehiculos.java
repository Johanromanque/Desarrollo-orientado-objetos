package com.drivequestrental.logica;

import com.drivequestrental.modelo.Vehiculo;
import com.drivequestrental.modelo.VehiculoCarga;
import com.drivequestrental.modelo.VehiculoPasajero;

import java.io.*;
import java.util.ArrayList;

public class ArchivoVehiculos {

    private static final String RUTA = "data/vehiculos.txt";

    // Guardar lista de vehículos en archivo
    public static void guardarVehiculos(ArrayList<Vehiculo> lista) {
        try {
            // Crear carpeta si no existe
            File archivo = new File(RUTA);
            File carpeta = archivo.getParentFile();
            if (carpeta != null && !carpeta.exists()) {
                carpeta.mkdirs(); // crea la carpeta "data"
            }
            // Escribir en el archivo
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            for (Vehiculo v : lista) {
                if (v instanceof VehiculoCarga) {
                    VehiculoCarga vc = (VehiculoCarga) v;
                    bw.write("CARGA;" + vc.getPatente() + ";" + vc.getMarca() + ";" + vc.getAnio() + ";" +
                            vc.getDiasArriendo() + ";" + vc.getValorDiario() + ";" + vc.getCapacidadCarga());
                } else if (v instanceof VehiculoPasajero) {
                    VehiculoPasajero vp = (VehiculoPasajero) v;
                    bw.write("PASAJERO;" + vp.getPatente() + ";" + vp.getMarca() + ";" + vp.getAnio() + ";" +
                            vp.getDiasArriendo() + ";" + vp.getValorDiario() + ";" + vp.getMaxPasajeros());
                }
                bw.newLine();
            }
            bw.close();
            System.out.println("Vehículos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar vehículos: " + e.getMessage());
        }
    }

    // Cargar lista desde archivo
    public static ArrayList<Vehiculo> cargarVehiculos() {
        ArrayList<Vehiculo> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                String tipo = partes[0];
                String patente = partes[1];
                String marca = partes[2];
                int anio = Integer.parseInt(partes[3]);
                int dias = Integer.parseInt(partes[4]);
                double valorDiario = Double.parseDouble(partes[5]);

                if (tipo.equalsIgnoreCase("CARGA")) {
                    double capacidad = Double.parseDouble(partes[6]);
                    VehiculoCarga vc = new VehiculoCarga(patente, marca, anio, dias, valorDiario, capacidad);
                    lista.add(vc);
                } else if (tipo.equalsIgnoreCase("PASAJERO")) {
                    int maxPasajeros = Integer.parseInt(partes[6]);
                    VehiculoPasajero vp = new VehiculoPasajero(patente, marca, anio, dias, valorDiario, maxPasajeros);
                    lista.add(vp);
                }
            }
            System.out.println("Vehículos cargados correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado, se creará al guardar.");
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }

        return lista;
    }
}
