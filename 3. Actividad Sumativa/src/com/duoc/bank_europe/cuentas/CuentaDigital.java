package com.duoc.bank_europe.cuentas;

public class CuentaDigital extends CuentaBancaria {
    public CuentaDigital(String numeroCuenta, double saldo) {
        super(numeroCuenta, saldo);
    }

    @Override
    public double calcularInteres() {
        return saldo * 0.15; // Por ejemplo, 1.5% del saldo como interés.
    }
}
