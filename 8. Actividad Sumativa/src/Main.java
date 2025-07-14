import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        PrimesList nuevaLista = new PrimesList(); // Crear una nueva instancia de PrimesList
        List<Integer> syncList = Collections.synchronizedList(nuevaLista); // Crear una lista sincronizada para manejar la concurrencia

        // Cargar primos desde archivo
        PrimesFiles.loadPrimesFromCSV("src/primes.csv", nuevaLista);
        System.out.println("-----------------------------------------------------------");
        System.out.println("Cantidad numeros de primos cargada desde el archivo: " + nuevaLista.getPrimeCount());
        System.out.println("Lista de números primos cargados por CSV: " + nuevaLista);


        System.out.println("-----------------------------------------------------------");
        System.out.println("\nComenzando la ejecución de hilos para añadir más números primos...");
        System.out.println("-----------------------------------------------------------");

        // Crear y ejecutar 2 hilos
        Thread[] hilos = new Thread[2];

        // Inicializar los hilos con la lista compartida
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new PrimesThread(nuevaLista), "Hilo-" + (i + 1));
            hilos[i].start();
        }

        // Esperar a que los 2 hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("Un hilo fue interrumpido.");
            }
        }

        // Mostrar resultados actualizados
        System.out.println("\nTotal de primos encontrados tras hilos: " + nuevaLista.getPrimeCount());
        System.out.println("Lista final de numeros primos: " + nuevaLista);

        // Guardar lista final en archivo
        PrimesFiles.writePrimes("src/primes_final.txt", nuevaLista);
    }
}