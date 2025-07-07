package com.duocuc.primesecure;

import java.util.ArrayList;

// esta clase extiende ArrayList<Integer> y permite añadir y eliminar números primos
public class PrimesList extends ArrayList<Integer> {
    // Método estático para verificar si un número es primo
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    // Método para verificar si un número es primo
    @Override
    public boolean add(Integer n) {
        if (isPrime(n)) {
            return super.add(n);
        } else {
            throw new IllegalArgumentException("Solo numeros primos pueden ser añadidos a esta lista.");
        }
    }
    @Override
    public boolean remove(Object o) {
        if (o instanceof Integer && isPrime((Integer) o)) {
            return super.remove(o);
        } else {
            throw new IllegalArgumentException("Solo numeros primos pueden ser eliminados de esta lista.");
        }
    }

    public int getPrimeCount() {
        return this.size();
    }
}
