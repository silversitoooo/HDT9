package org.example;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Clase que implementa un árbol de Huffman para codificación.
 * Esta clase es serializable para poder guardarla en un archivo.
 */
public class ArbolHuffman implements Serializable {
    private static final long serialVersionUID = 1L;

    private NodoHuffman raiz;
    private Map<Character, String> codigos;

    /**
     * Constructor para crear un árbol de Huffman a partir de una tabla de frecuencias.
     *
     * @param frecuencias Mapa que contiene cada carácter y su frecuencia.
     */
    public ArbolHuffman(Map<Character, Integer> frecuencias) {
        construirArbol(frecuencias);
        this.codigos = new HashMap<>();
        generarCodigos(raiz, "");
    }

    /**
     * Constructor vacío para deserialización.
     */
    public ArbolHuffman() {
        this.codigos = new HashMap<>();
    }

    /**
     * Construye el árbol de Huffman a partir de una tabla de frecuencias.
     *
     * @param frecuencias Mapa que contiene cada carácter y su frecuencia.
     */
    private void construirArbol(Map<Character, Integer> frecuencias) {
        // Crear cola de prioridad (min-heap) para ordenar nodos por frecuencia
        PriorityQueue<NodoHuffman> colaPrioridad = new PriorityQueue<>();

        // Añadir cada carácter a la cola como un nodo hoja
        for (Map.Entry<Character, Integer> entry : frecuencias.entrySet()) {
            colaPrioridad.add(new NodoHuffman(entry.getKey(), entry.getValue()));
        }

        // Caso especial: un solo carácter en el texto
        if (colaPrioridad.size() == 1) {
            NodoHuffman nodo = colaPrioridad.poll();
            raiz = new NodoHuffman(nodo.getFrecuencia(), nodo, null);
            return;
        }

        // Construir el árbol combinando los dos nodos con menor frecuencia
        while (colaPrioridad.size() > 1) {
            NodoHuffman izquierda = colaPrioridad.poll();
            NodoHuffman derecha = colaPrioridad.poll();

            // Crear un nodo interno con los dos nodos extraídos como hijos
            NodoHuffman nodoInterno = new NodoHuffman(
                    izquierda.getFrecuencia() + derecha.getFrecuencia(),
                    izquierda,
                    derecha
            );

            colaPrioridad.add(nodoInterno);
        }

        // La raíz es el último nodo que queda en la cola
        raiz = colaPrioridad.poll();
    }

    /**
     * Genera los códigos Huffman para cada carácter recorriendo el árbol.
     *
     * @param nodo Nodo actual en el recorrido.
     * @param codigo Código acumulado en el recorrido.
     */
    private void generarCodigos(NodoHuffman nodo, String codigo) {
        if (nodo == null) {
            return;
        }

        // Si es una hoja, guardar el código para este carácter
        if (nodo.esHoja()) {
            codigos.put(nodo.getCaracter(), codigo.isEmpty() ? "0" : codigo);  // Caso especial para un solo carácter
            return;
        }

        // Recorrer hijo izquierdo (añadir 0 al código)
        generarCodigos(nodo.getIzquierda(), codigo + "0");

        // Recorrer hijo derecho (añadir 1 al código)
        generarCodigos(nodo.getDerecha(), codigo + "1");
    }

    /**
     * Decodifica una cadena binaria usando el árbol Huffman.
     *
     * @param binario Cadena binaria a decodificar.
     * @return Texto decodificado.
     */
    public String decodificar(String binario) {
        if (raiz == null) {
            return "";
        }

        StringBuilder resultado = new StringBuilder();
        NodoHuffman nodoActual = raiz;

        for (char bit : binario.toCharArray()) {
            // Navegar por el árbol según el bit actual
            if (bit == '0') {
                nodoActual = nodoActual.getIzquierda();
            } else {
                nodoActual = nodoActual.getDerecha();
            }

            // Si llegamos a una hoja, añadir su carácter al resultado y volver a la raíz
            if (nodoActual != null && nodoActual.esHoja()) {
                resultado.append(nodoActual.getCaracter());
                nodoActual = raiz;
            }
        }

        return resultado.toString();
    }

    /**
     * Obtiene el mapa de códigos Huffman.
     *
     * @return Mapa que asocia cada carácter con su código Huffman.
     */
    public Map<Character, String> getCodigos() {
        return codigos;
    }

    /**
     * Obtiene la raíz del árbol.
     *
     * @return Nodo raíz del árbol.
     */
    public NodoHuffman getRaiz() {
        return raiz;
    }

    /**
     * Establece la raíz del árbol.
     *
     * @param raiz Nueva raíz del árbol.
     */
    public void setRaiz(NodoHuffman raiz) {
        this.raiz = raiz;
    }

    /**
     * Establece el mapa de códigos Huffman.
     *
     * @param codigos Nuevo mapa de códigos.
     */
    public void setCodigos(Map<Character, String> codigos) {
        this.codigos = codigos;
    }
}
