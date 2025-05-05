package org.example;

import java.util.Scanner;

public class InterfazConsola {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== Compresor Huffman ===");
            System.out.println("1. Comprimir un archivo");
            System.out.println("2. Descomprimir un archivo");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la ruta del archivo a comprimir: ");
                    String archivoEntrada = scanner.nextLine();
                    try {
                        HuffmanUtil.compress(archivoEntrada);
                    } catch (Exception e) {
                        System.out.println("Error al comprimir el archivo: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Ingrese la ruta del archivo .huff: ");
                    String archivoHuff = scanner.nextLine();
                    System.out.print("Ingrese la ruta del archivo .hufftree: ");
                    String archivoArbol = scanner.nextLine();
                    try {
                        HuffmanUtil.decompress(archivoHuff, archivoArbol);
                    } catch (Exception e) {
                        System.out.println("Error al descomprimir el archivo: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}