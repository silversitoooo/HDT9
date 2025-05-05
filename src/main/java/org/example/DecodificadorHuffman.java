package org.example;

/**
 * Clase para decodificar datos comprimidos usando el árbol de Huffman.
 */
public class DecodificadorHuffman {
    private ArbolHuffman arbol;

    /**
     * Constructor que inicializa el decodificador con un árbol de Huffman.
     *
     * @param arbol Árbol de Huffman para decodificación.
     */
    public DecodificadorHuffman(ArbolHuffman arbol) {
        this.arbol = arbol;
    }

    /**
     * Convierte un array de bytes a una cadena binaria.
     *
     * @param bytes Array de bytes a convertir.
     * @return Cadena de caracteres con la representación binaria.
     */
    public String convertirABinario(byte[] bytes) {
        StringBuilder binario = new StringBuilder();

        // Convertir cada byte a su representación binaria
        for (byte b : bytes) {
            // Convertir a entero sin signo (0-255)
            int valorSinSigno = b & 0xFF;

            // Convertir a cadena binaria de 8 bits
            String bin = Integer.toBinaryString(valorSinSigno);

            // Añadir ceros a la izquierda si es necesario
            while (bin.length() < 8) {
                bin = "0" + bin;
            }

            binario.append(bin);
        }

        return binario.toString();
    }

    /**
     * Quita los bits de relleno de una cadena binaria.
     *
     * @param binario Cadena binaria con relleno.
     * @param relleno Número de bits de relleno.
     * @return Cadena binaria sin relleno.
     */
    public String quitarRelleno(String binario, int relleno) {
        if (relleno > 0 && binario.length() > relleno) {
            return binario.substring(0, binario.length() - relleno);
        }
        return binario;
    }

    /**
     * Decodifica una cadena binaria usando el árbol de Huffman.
     *
     * @param binario Cadena binaria a decodificar.
     * @return Texto decodificado.
     */
    public String decodificar(String binario) {
        return arbol.decodificar(binario);
    }

    /**
     * Realiza todo el proceso de decodificación: conversión de bytes a binario,
     * eliminación del relleno y decodificación.
     *
     * @param datos Datos comprimidos.
     * @param relleno Número de bits de relleno.
     * @return Texto decodificado.
     */
    public String procesarDatos(byte[] datos, int relleno) {
        String binario = convertirABinario(datos);
        String binarioSinRelleno = quitarRelleno(binario, relleno);
        return decodificar(binarioSinRelleno);
    }
}