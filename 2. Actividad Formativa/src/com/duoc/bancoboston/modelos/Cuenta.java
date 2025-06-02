package com.duoc.bancoboston.modelos;

public abstract class Cuenta {
    private final String numeroCuenta;
    protected double saldo;

    // Constructor principal
    public Cuenta(String numeroCuenta, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public abstract void consultarSaldo();
    public abstract void depositar(double monto);
    public abstract void girar(double monto);


}
