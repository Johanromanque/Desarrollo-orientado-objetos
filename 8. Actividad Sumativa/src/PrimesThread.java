import java.util.Random;

public class PrimesThread implements Runnable {

    private final PrimesList listaPrimosCompartida;// Lista compartida de números primos
    private final Random rnd = new Random();// Generador de números aleatorios

    // Constructor que recibe la lista compartida de números primos
    public PrimesThread(PrimesList listaPrimosCompartida) {
        this.listaPrimosCompartida = listaPrimosCompartida;
    }

    @Override
    public void run() {// Método que se ejecuta al iniciar el hilo
        for (int i = 0; i < 10; i++) { // El hilo intentará agregar 10 números aleatorios
            int numero = rnd.nextInt(100) + 2; // Número aleatorio entre 2 y 101

            if (PrimesList.isPrime(numero)) {// Verificar si el número es primo
                synchronized (listaPrimosCompartida) { // Bloquear la lista compartida para evitar problemas de concurrencia
                    listaPrimosCompartida.add(numero); // Añadir primo
                    listaPrimosCompartida.notifyAll(); // Notificar a otros hilos si es necesario
                    System.out.println("[" + Thread.currentThread().getName() + "] agregó primo: " + numero);
                }
            } else {
                System.out.println("[" + Thread.currentThread().getName() + "] no es primo: " + numero);
            }

            try {
                Thread.sleep(1000); // Simular tiempo de procesamiento
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido: " + Thread.currentThread().getName());
            }
        }
    }
}
