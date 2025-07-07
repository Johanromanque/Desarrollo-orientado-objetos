package com.duocuc.primesecure;

// Clase que representa un hilo para sumar números primos en un rango específico
public class HiloSumaPrimos extends Thread{
    // Atributos de la clase
    private PrimesList primesList;
    private int start;
    private int end;

    // Constructor que recibe la lista de primos y el rango de números a procesar
    public HiloSumaPrimos(PrimesList primesList, int start, int end) {
        this.primesList = primesList;
        this.start = start;
        this.end = end;
    }
    // Método que se ejecuta al iniciar el hilo
    @Override
    public void run() {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            if (PrimesList.isPrime(i)) {
                sum += i;

                // Se agrega metodo synchronized para evitar problemas de concurrencia al modificar la lista
                synchronized (primesList) {
                    primesList.add(i); // Solo un hilo puede ejecutar esta línea a la vez
                }
            }
        }
    }
}
