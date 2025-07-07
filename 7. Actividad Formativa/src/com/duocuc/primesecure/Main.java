package com.duocuc.primesecure;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de PrimesList y definir el rango de números a procesar
        PrimesList primesList = new PrimesList();

        // Crear dos hilos para procesar la suma de números primos en el rango
        HiloSumaPrimos hilo1 = new HiloSumaPrimos(primesList,1,100);
        HiloSumaPrimos hilo2 = new HiloSumaPrimos(primesList, 101, 200);

        // Iniciar los hilos y esperar a que terminen
        hilo1.start();
        hilo2.start();

        // Esperar a que ambos hilos terminen su ejecución
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {// Manejo de excepciones en caso de interrupción del hilo
            e.printStackTrace();
        }

        System.out.println("\nCantidad de numeros primos encontrados: " + primesList.getPrimeCount());
        System.out.println("\nLista de numeros primos: " + primesList);
    }
}
