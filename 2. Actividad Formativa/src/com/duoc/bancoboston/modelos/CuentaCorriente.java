package com.duoc.bancoboston.modelos;

public class CuentaCorriente extends Cuenta {
    public CuentaCorriente(String numeroCuenta, double saldo) {
        super(numeroCuenta, saldo);
    }
    @Override
    public void girar(double monto) {
        if (monto <= saldo) {
            saldo -= monto;
        } else {
            System.out.println("Saldo insuficiente en cuenta corriente.");
        }
    }

    @Override
    public void consultarSaldo() {
        System.out.println("Saldo de cuenta corriente: $" + saldo);
    }
    @Override
    public void depositar(double monto) {
        saldo += monto;
    }
}

