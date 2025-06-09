package com.duoc.bank_europe;
import com.duoc.bank_europe.clientes.*;
import com.duoc.bank_europe.cuentas.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Cliente> clientes = new ArrayList<>();

        while (true) {
            System.out.println("\n===== MENÚ BANK EUROPE =====");
            System.out.println("1. Registrar cliente y cuenta");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Girar dinero");
            System.out.println("4. Consultar saldo e interés");
            System.out.println("5. Mostrar información del cliente");
            System.out.println("6. Salir");
            System.out.print("\nSeleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese RUT del cliente: ");
                    String rut = scanner.nextLine();

                    // Validar si ya existe
                    Cliente existente = buscarClientePorRUT(clientes, rut);
                    if (existente != null) {
                        System.out.println("\nYa existe un cliente con ese RUT.");
                        break;
                    }

                    System.out.print("Ingrese nombre del cliente: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Ingrese número de cuenta (9 dígitos): ");
                    String numeroCuenta = scanner.nextLine();

                    System.out.print("Ingrese saldo inicial: ");
                    double saldo = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("\nSeleccione tipo de cuenta:");
                    System.out.println("1. Cuenta Corriente");
                    System.out.println("2. Cuenta Ahorros");
                    System.out.println("3. Cuenta Digital");
                    int tipoCuenta = scanner.nextInt();
                    scanner.nextLine();

                    CuentaBancaria cuenta;
                    switch (tipoCuenta) {
                        case 1: cuenta = new CuentaCorriente(numeroCuenta, saldo); break;
                        case 2: cuenta = new CuentaAhorros(numeroCuenta, saldo); break;
                        case 3: cuenta = new CuentaDigital(numeroCuenta, saldo); break;
                        default:
                            System.out.println("\nTipo de cuenta no válido.");
                            break;
                    }

                    cuenta = switch (tipoCuenta) {
                        case 1 -> new CuentaCorriente(numeroCuenta, saldo);
                        case 2 -> new CuentaAhorros(numeroCuenta, saldo);
                        case 3 -> new CuentaDigital(numeroCuenta, saldo);
                        default -> null;
                    };

                    if (cuenta == null) {
                        break;
                    }

                    clientes.add(new Cliente(nombre, rut, cuenta));
                    System.out.println("\nCliente registrado exitosamente.");
                    break;

                case 2: // Depositar
                    Cliente clienteDep = seleccionarCliente(clientes, scanner);
                    if (clienteDep != null) {
                        System.out.print("Ingrese monto a depositar: ");
                        double monto = scanner.nextDouble();
                        clienteDep.getCuenta().depositar(monto);
                        System.out.println("\nDepósito exitoso.");
                    }
                    break;

                case 3: // Girar
                    Cliente clienteGiro = seleccionarCliente(clientes, scanner);
                    if (clienteGiro != null) {
                        System.out.print("Ingrese monto a girar: ");
                        double monto = scanner.nextDouble();
                        clienteGiro.getCuenta().girar(monto);
                        System.out.println("\nGiro realizado.");
                    }
                    break;

                case 4: // Consultar saldo/interés
                    Cliente clienteSaldo = seleccionarCliente(clientes, scanner);
                    if (clienteSaldo != null) {
                        CuentaBancaria cta = clienteSaldo.getCuenta();
                        System.out.println("\n----------------------------------");
                        System.out.println("Saldo actual: $" + cta.getSaldo());
                        System.out.println("Interés: $" + cta.calcularInteres());
                        System.out.println("----------------------------------");
                    }
                    break;

                case 5: // Mostrar datos cliente
                    Cliente clienteInfo = seleccionarCliente(clientes, scanner);
                    if (clienteInfo != null) {
                        clienteInfo.mostrarInformacionCliente();
                    }
                    break;

                case 6:
                    System.out.println("\nGracias por usar Bank Europe.");
                    return;

                default:
                    System.out.println("\nOpción no válida.");
            }
        }
    }

    // 🔍 Buscar cliente por RUT
    private static Cliente buscarClientePorRUT(ArrayList<Cliente> lista, String rut) {
        for (Cliente c : lista) {
            if (c.getRut().equalsIgnoreCase(rut)) {
                return c;
            }
        }
        return null;
    }

    // ✅ Seleccionar cliente por RUT
    private static Cliente seleccionarCliente(ArrayList<Cliente> clientes, Scanner scanner) {
        System.out.print("Ingrese RUT del cliente: ");
        String rut = scanner.nextLine();
        Cliente c = buscarClientePorRUT(clientes, rut);
        if (c == null) {
            System.out.println("\nCliente no encontrado.");
        }
        return c;
    }
}