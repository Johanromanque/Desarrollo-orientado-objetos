package com.drivequestrental.logica;

import com.drivequestrental.interfaces.Factura;
import com.drivequestrental.modelo.Vehiculo;

import java.util.ArrayList;

public class GestorVehiculos {
    private final ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

    // Método para agregar un vehículo si la patente es única
    // Método sincronizado
    public synchronized boolean agregarVehiculo(Vehiculo nuevo) {
        for (Vehiculo v : listaVehiculos) {
            if (v.getPatente().equalsIgnoreCase(nuevo.getPatente())) {
                System.out.println("Error: Ya existe un vehículo con esa patente.");
                return false;
            }
        }
        listaVehiculos.add(nuevo);
        System.out.println("Vehículo agregado correctamente.");
        return true;
    }

    // Método para listar todos los vehículos
    public void listarVehiculos() {
        if (listaVehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
            return;
        }
        for (Vehiculo v : listaVehiculos) {
            v.mostrarDatos();
        }
    }

    // Mostrar boleta de un vehículo específico por patente
    public void mostrarBoletaPorPatente(String patente) {
        for (Vehiculo v : listaVehiculos) {
            if (v.getPatente().equalsIgnoreCase(patente)) {
                if (v instanceof Factura) {
                    ((Factura) v).mostrarBoleta();
                    return;
                } else {
                    System.out.println("Este vehículo no implementa la interfaz Factura.");
                    return;
                }
            }
        }
        System.out.println("No se encontró ningún vehículo con la patente: " + patente);
    }

    // Getter para obtener la lista (opcional, si necesitas acceso desde otra clase)
    public ArrayList<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }
}
