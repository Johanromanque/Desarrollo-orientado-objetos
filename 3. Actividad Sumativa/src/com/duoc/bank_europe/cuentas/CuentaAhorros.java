package com.duoc.bank_europe.cuentas;

public class CuentaAhorros extends CuentaBancaria {
    public CuentaAhorros(String numeroCuenta, double saldo) {
        super(numeroCuenta, saldo);
    }

    @Override
    public double calcularInteres() {
        return saldo * 0.03; // 3% de interés
    }
}