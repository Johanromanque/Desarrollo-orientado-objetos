import com.drivequestrental.hilos.VehiculoLoaderThread;
import com.drivequestrental.logica.ArchivoVehiculos;
import com.drivequestrental.logica.GestorVehiculos;
import com.drivequestrental.modelo.VehiculoCarga;
import com.drivequestrental.modelo.VehiculoPasajero;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear carpeta 'data/' si no existe
        new File("data").mkdirs();

        GestorVehiculos gestor = new GestorVehiculos();

        // Hilo de carga
        Thread carga = new Thread(new VehiculoLoaderThread(gestor));
        carga.start();

        // Esperar a que termine la carga
        synchronized (gestor) {
            try {
                System.out.println("Esperando a que se carguen los datos...");
                gestor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Scanner sc = new Scanner(System.in);
        String opcion;

        // Menú interactivo
        do {
            System.out.println("\n MENÚ PRINCIPAL");
            System.out.println("1. Ver vehículos disponibles");
            System.out.println("2. Agregar vehículo de carga");
            System.out.println("3. Agregar vehículo de pasajeros");
            System.out.println("4. Mostrar boleta");
            System.out.println("5. Guardar y salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    gestor.listarVehiculos();
                    break;
                case "2":
                    System.out.print("Patente: ");
                    String patenteCarga = sc.nextLine();
                    System.out.print("Marca: ");
                    String marcaCarga = sc.nextLine();
                    System.out.print("Año: ");
                    int anioCarga = Integer.parseInt(sc.nextLine());
                    System.out.print("Días de arriendo: ");
                    int diasCarga = Integer.parseInt(sc.nextLine());
                    System.out.print("Valor diario: ");
                    double valorCarga = Double.parseDouble(sc.nextLine());
                    System.out.print("Capacidad de carga (kg): ");
                    double capacidad = Double.parseDouble(sc.nextLine());

                    VehiculoCarga vCarga = new VehiculoCarga(patenteCarga, marcaCarga, anioCarga, diasCarga, valorCarga, capacidad);
                    gestor.agregarVehiculo(vCarga);
                    break;

                case "3":
                    System.out.print("Patente: ");
                    String patentePas = sc.nextLine();
                    System.out.print("Marca: ");
                    String marcaPas = sc.nextLine();
                    System.out.print("Año: ");
                    int anioPas = Integer.parseInt(sc.nextLine());
                    System.out.print("Días de arriendo: ");
                    int diasPas = Integer.parseInt(sc.nextLine());
                    System.out.print("Valor diario: ");
                    double valorPas = Double.parseDouble(sc.nextLine());
                    System.out.print("Máximo de pasajeros: ");
                    int pasajeros = Integer.parseInt(sc.nextLine());

                    VehiculoPasajero vPas = new VehiculoPasajero(patentePas, marcaPas, anioPas, diasPas, valorPas, pasajeros);
                    gestor.agregarVehiculo(vPas);
                    break;

                case "4":
                    System.out.print("Ingrese la patente del vehículo para mostrar su boleta: ");
                    String patenteBuscar = sc.nextLine();
                    gestor.mostrarBoletaPorPatente(patenteBuscar);
                    break;


                case "5":
                    ArchivoVehiculos.guardarVehiculos(gestor.getListaVehiculos());
                    System.out.println("Vehículos guardados. ¡Hasta luego!");
                    break;


                default:
                    System.out.println("Opción no válida.");
            }

        } while (!opcion.equals("4"));
    }
}
