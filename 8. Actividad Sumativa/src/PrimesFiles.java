import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PrimesFiles {

    // Carga números desde un archivo CSV y los añade a la lista si son primos
    public static void loadPrimesFromCSV(String filePath, PrimesList list) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    try {
                        int number = Integer.parseInt(line);
                        list.add(number);
                    } catch (NumberFormatException e) {
                        System.out.println("Línea inválida en archivo: '" + line + "'");
                    } catch (IllegalArgumentException e) {
                        System.out.println("No es primo y no se agregó: " + line);
                    }
                }
            }
            System.out.println("\nArchivo CSV cargado correctamente.");
        }
    }

    // Escribe todos los números primos de la lista en un archivo de texto
    public static void writePrimes(String filePath, PrimesList list) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Integer prime : list) {
                writer.write("Numero: " + prime + " Tu número es un número primo.");
                writer.newLine(); // Salto de línea
            }
            System.out.println("-----------------------------------------------------------");
            System.out.println("Archivo de salida generado en: " + filePath);
        }
    }
}

