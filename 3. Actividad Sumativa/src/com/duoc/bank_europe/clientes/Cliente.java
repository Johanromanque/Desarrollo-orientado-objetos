package com.duoc.bank_europe.clientes;

import com.duoc.bank_europe.cuentas.CuentaBancaria;

public class Cliente implements InfoCliente {
    private String nombre;
    private String rut;
    private CuentaBancaria cuenta;

    public Cliente(String nombre, String rut, CuentaBancaria cuenta) {
        this.nombre = nombre;
        this.rut = rut;
        this.cuenta = cuenta;
    }
    public String getRut() {
        return rut;
    }

    public CuentaBancaria getCuenta() {
        return cuenta;
    }

    @Override
    public void mostrarInformacionCliente() {
        System.out.println("----------------------------------");
        System.out.println("Nombre: " + nombre);
        System.out.println("RUT: " + rut);
        System.out.println("Numero de cuenta: " + cuenta.getNumeroCuenta());
        System.out.println("Saldo: $" + cuenta.getSaldo());
        System.out.println("----------------------------------");
    }
}
