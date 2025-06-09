package com.duoc.bank_europe.cuentas;

public class CuentaCorriente extends CuentaBancaria {
    public CuentaCorriente(String numeroCuenta, double saldo){
        super(numeroCuenta, saldo);
    }

    @Override
    public double calcularInteres() {
        // En una cuenta corriente, el interés es 0.
        return saldo * 0.01; // Por ejemplo, 1% del saldo como interés.
    }
}
