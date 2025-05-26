
import com.duoc.bancoboston.Cliente;
import com.duoc.bancoboston.Cuenta;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Cliente cliente = null;
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;
        do {
            System.out.println("\n=====================================");
            System.out.println("       BIENVENIDO AL BANCO - BOSTON      ");
            System.out.println("=====================================");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Ver datos del cliente");
            System.out.println("3. Depositar");
            System.out.println("4. Girar");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();  // ← AQUÍ lees la opción correctamente
            scanner.nextLine(); // ← limpia el buffer luego del número

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese RUT: ");
                    String rut = scanner.nextLine();
                    System.out.print("Ingrese Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese Apellido Paterno: ");
                    String apP = scanner.nextLine();
                    System.out.print("Ingrese Apellido Materno: ");
                    String apM = scanner.nextLine();
                    System.out.print("Ingrese Domicilio: ");
                    String domicilio = scanner.nextLine();
                    System.out.print("Ingrese Comuna: ");
                    String comuna = scanner.nextLine();
                    System.out.print("Ingrese Teléfono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Ingrese número de cuenta (9 dígitos): ");
                    int numCuenta = scanner.nextInt();
                    System.out.print("Ingrese saldo inicial: ");
                    int saldo = scanner.nextInt();
                    scanner.nextLine(); // limpiar buffer

                    Cuenta cuenta = new Cuenta(numCuenta, saldo);
                    cliente = new Cliente(rut, nombre, apP, apM, domicilio, comuna, telefono, cuenta); // Solo asignar

                    System.out.println("-------------------------------------");
                    System.out.println("¡Cliente registrado exitosamente!");
                    break;

                case 2:
                    if (cliente != null) {
                        cliente.verDatos();
                    } else {
                        System.out.println("\nNo hay clientes registrados.");
                    }
                    break;

                case 3:
                    if (cliente != null) {
                        System.out.print("Ingrese monto a depositar: ");
                        int montoDeposito = scanner.nextInt();
                        cliente.getCuenta().depositar(montoDeposito);
                    } else {
                        System.out.println("\nNo hay clientes registrados.");
                    }
                    break;

                case 4:
                    if (cliente != null) {
                        System.out.print("Ingrese monto a girar: ");
                        int montoGiro = scanner.nextInt();
                        cliente.getCuenta().girar(montoGiro);
                    } else {
                        System.out.println("\nNo hay clientes registrados.");
                    }
                    break;

                case 5:
                    if (cliente != null) {
                        cliente.getCuenta().consultarSaldo();
                    } else {
                        System.out.println("\nNo hay clientes registrados.");
                    }
                    break;

                case 6:
                    System.out.println("--------------------------------------");
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("\nOpción no válida. Intente nuevamente.");


            }
        } while (opcion != 6);

        scanner.close();
    }
}