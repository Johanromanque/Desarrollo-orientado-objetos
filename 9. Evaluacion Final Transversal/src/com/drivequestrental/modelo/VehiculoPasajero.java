package com.drivequestrental.modelo;

import com.drivequestrental.interfaces.Factura;


public class VehiculoPasajero extends Vehiculo implements Factura {
    private int maxPasajeros;

    public VehiculoPasajero() {}

    public VehiculoPasajero(String patente, String marca, int anio, int diasArriendo, double valorDiario, int maxPasajeros) {
        super(patente, marca, anio, diasArriendo, valorDiario);
        this.maxPasajeros = maxPasajeros;
    }

    public int getMaxPasajeros() {
        return maxPasajeros;
    }

    public void setMaxPasajeros(int maxPasajeros) {
        this.maxPasajeros = maxPasajeros;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Vehículo de Pasajeros - Patente: " + patente + ", Marca: " + marca + ", Año: " + anio +
                ", Máx. Pasajeros: " + maxPasajeros + ", Días arriendo: " + diasArriendo + ", Valor diario: $" + valorDiario);
    }

    @Override
    public void mostrarBoleta() {
        double totalBruto = valorDiario * diasArriendo;
        double montoIVA = totalBruto * IVA;
        double descuento = totalBruto * DESCUENTO_PASAJEROS;
        double totalFinal = totalBruto + montoIVA - descuento;

        System.out.println("\n---- BOLETA VEHÍCULO PASAJEROS ----");
        mostrarDatos();
        System.out.println("Subtotal: $" + totalBruto);
        System.out.println("IVA (19%): $" + montoIVA);
        System.out.println("Descuento (5%): -$" + descuento);
        System.out.println("Total a pagar: $" + totalFinal);

    }
}
