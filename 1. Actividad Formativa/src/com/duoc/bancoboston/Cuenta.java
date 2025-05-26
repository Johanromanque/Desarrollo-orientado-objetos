package com.duoc.bancoboston;

public class Cuenta {

    private int numeroCuenta;
    private int saldo;

    public Cuenta(int numeroCuenta, int saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public int getSaldo() {
        return saldo;
    }

    public void depositar(int monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.println("\nDepósito exitoso. Saldo actual: $" + saldo);
        } else {
            System.out.println("\nMonto inválido para depósito.");
        }
    }

    public void girar(int monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            System.out.println("\nGiro exitoso. Saldo actual: $" + saldo);
        } else {
            System.out.println("\nSaldo insuficiente o monto inválido.");
        }
    }

    public void consultarSaldo() {
        System.out.println("\nSaldo actual: $" + saldo);
    }
}
