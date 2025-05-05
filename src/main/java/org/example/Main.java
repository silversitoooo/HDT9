package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                mostrarAyuda();
                return;
            }

            String operacion = args[0].toLowerCase();

            switch (operacion) {
                case "comprimir":
                    if (args.length < 2) {
                        System.out.println("Error: Se requiere un archivo de entrada para comprimir.");
                        return;
                    }
                    HuffmanUtil.compress(args[1]);
                    break;

                case "descomprimir":
                    if (args.length < 3) {
                        System.out.println("Error: Se requieren los archivos .huff y .hufftree para descomprimir.");
                        return;
                    }
                    HuffmanUtil.decompress(args[1], args[2]);
                    break;

                default:
                    System.out.println("Operación no reconocida: " + operacion);
                    mostrarAyuda();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostrarAyuda() {
        System.out.println("Uso del programa:");
        System.out.println("  Comprimir: java -jar HDT9.2-1.0-SNAPSHOT.JAR comprimir <archivo_entrada>");
        System.out.println("  Descomprimir: java -jar HDT9.2-1.0-SNAPSHOT.JAR descomprimir <archivo.huff> <archivo.hufftree>");
    }
}