package com.drivequestrental.hilos;

import com.drivequestrental.logica.ArchivoVehiculos;
import com.drivequestrental.logica.GestorVehiculos;


public class VehiculoLoaderThread implements Runnable {
    private final GestorVehiculos gestor;

    // Constructor que recibe el GestorVehiculos
    public VehiculoLoaderThread(GestorVehiculos gestor) {
        this.gestor = gestor;
    }

    @Override
    public void run() {
        synchronized (gestor) {
            System.out.println("Cargando vehículos en segundo plano...");
            try {
                Thread.sleep(1000); // Simula tiempo de carga
                gestor.getListaVehiculos().addAll(ArchivoVehiculos.cargarVehiculos());
                System.out.println("Vehículos cargados exitosamente en hilo.");
                gestor.notify(); // Despierta a quien espere
            } catch (InterruptedException e) {
                System.out.println("Carga interrumpida.");
            }
        }
    }
}
