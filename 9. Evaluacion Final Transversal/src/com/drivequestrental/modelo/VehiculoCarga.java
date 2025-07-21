package com.drivequestrental.modelo;
import com.drivequestrental.interfaces.Factura;


public class VehiculoCarga extends Vehiculo implements Factura {
    private double capacidadCarga;

    public VehiculoCarga() {}

    public VehiculoCarga(String patente, String marca, int anio, int diasArriendo, double valorDiario, double capacidadCarga) {
        super(patente, marca, anio, diasArriendo, valorDiario);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Vehículo de Carga - Patente: " + patente + ", Marca: " + marca + ", Año: " + anio +
                ", Capacidad de carga: " + capacidadCarga + "kg, Días arriendo: " + diasArriendo + ", Valor diario: $" + valorDiario);
    }

    @Override
    public void mostrarBoleta() {
        double totalBruto = valorDiario * diasArriendo;
        double montoIVA = totalBruto * IVA;
        double descuento = totalBruto * DESCUENTO_CARGA;
        double totalFinal = totalBruto + montoIVA - descuento;

        System.out.println("\n---- BOLETA VEHÍCULO CARGA ----");
        mostrarDatos();
        System.out.println("Subtotal: $" + totalBruto);
        System.out.println("IVA (19%): $" + montoIVA);
        System.out.println("Descuento (7%): -$" + descuento);
        System.out.println("Total a pagar: $" + totalFinal);
    }

}
