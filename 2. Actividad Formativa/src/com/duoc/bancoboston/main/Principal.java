package com.duoc.bancoboston.main;
import com.duoc.bancoboston.modelos.*;
import  java.util.Scanner;


public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = null;

        int opcion = 0;
        do {
            System.out.println("\n=====================================");
            System.out.println("    BIENVENIDO AL BANCO - BOSTON      ");
            System.out.println("=====================================");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Ver datos del cliente");
            System.out.println("3. Depositar");
            System.out.println("4. Girar");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();  // Leer la opción correctamente
            sc.nextLine(); // Limpiar el buffer luego del número

            switch (opcion){
                case 1:
                    System.out.print("Ingrese RUT: ");
                    String rut = sc.nextLine();
                    System.out.print("Ingrese Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingrese Apellido Paterno: ");
                    String apP = sc.nextLine();
                    System.out.print("Ingrese Apellido Materno: ");
                    String apM = sc.nextLine();
                    System.out.print("Ingrese Domicilio: ");
                    String domicilio = sc.nextLine();
                    System.out.print("Ingrese Comuna: ");
                    String comuna = sc.nextLine();
                    System.out.print("Ingrese Teléfono: ");
                    String telefono = sc.nextLine();
                    System.out.print("Ingrese número de cuenta (9 dígitos): ");
                    String numCuenta = sc.nextLine();
                    System.out.print("Ingrese saldo inicial: ");
                    double saldo = sc.nextDouble();

                    System.out.println("Seleccione tipo de cuenta:");
                    System.out.println("1. Cuenta Corriente");
                    System.out.println("2. Cuenta Ahorro");
                    System.out.println("3. Cuenta Crédito");
                    int tipoCuenta = sc.nextInt();

                    Cuenta cuenta;
                    switch (tipoCuenta) {
                        case 1:
                            cuenta = new CuentaCorriente(numCuenta, saldo);
                            break;
                        case 2:
                            cuenta = new CuentaAhorro(numCuenta, saldo);
                            break;
                        case 3:
                            cuenta = new CuentaCredito(numCuenta, saldo);
                            break;
                        default:
                            System.out.println("Opción inválida. Se asignará Cuenta Corriente.");
                            cuenta = new CuentaCorriente(numCuenta, saldo);
                    }

                    cliente = new Cliente(rut, nombre, apP, apM, domicilio, comuna, telefono, cuenta);
                    System.out.println("¡Cliente registrado exitosamente!");
                    break;

                case 2:
                    if (cliente != null) cliente.mostrarDatosCliente();
                    else System.out.println("No hay clientes registrados.");
                    break;

                case 3:
                    if (cliente != null) {
                        System.out.print("Ingrese monto a depositar: ");
                        double montoDeposito = sc.nextDouble();
                        cliente.depositar(montoDeposito);
                    } else {
                        System.out.println("No hay clientes registrados.");
                    }
                    break;

                case 4:
                    if (cliente != null) {
                        System.out.print("Ingrese monto a girar: ");
                        double montoGiro = sc.nextDouble();
                        cliente.girar(montoGiro);
                    } else {
                        System.out.println("No hay clientes registrados.");
                    }
                    break;

                case 5:
                    if (cliente != null) {
                        cliente.consultarSaldo();
                    } else {
                        System.out.println("No hay clientes registrados.");
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);

        sc.close();
    }
}