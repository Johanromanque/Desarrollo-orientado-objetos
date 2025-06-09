package com.duoc.bank_europe.cuentas;

public abstract class CuentaBancaria {


    private String numeroCuenta;
    protected double saldo;

    // Constructor
    public CuentaBancaria(String numeroCuenta, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    // Constructor sobrecargado
    public CuentaBancaria(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0.0; // Saldo inicial por defecto
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        } else {
            System.out.println("\nEl monto a depositar debe ser mayor a 0.");
        }
    }

    public void girar(double monto) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
        } else if (monto <= 0) {
            System.out.println("\nEl monto a girar debe ser mayor a 0.");
        } else {
            System.out.println("Saldo insuficiente para realizar el giro.");
        }
    }

    public abstract double calcularInteres(); // Método abstracto para calcular el interés.
}