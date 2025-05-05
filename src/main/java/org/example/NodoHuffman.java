package org.example;

import java.io.Serializable;

public class NodoHuffman implements Comparable<NodoHuffman>, Serializable {
    private static final long serialVersionUID = 1L;

    private Character caracter;
    private Integer frecuencia;
    private NodoHuffman izquierda;
    private NodoHuffman derecha;

    public NodoHuffman(Character caracter, Integer frecuencia) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        this.izquierda = null;
        this.derecha = null;
    }

    public NodoHuffman(Integer frecuencia, NodoHuffman izquierda, NodoHuffman derecha) {
        this.caracter = null;
        this.frecuencia = frecuencia;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public boolean esHoja() {
        return izquierda == null && derecha == null;
    }

    @Override
    public int compareTo(NodoHuffman otro) {
        return this.frecuencia.compareTo(otro.frecuencia);
    }

    public Character getCaracter() {
        return caracter;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public NodoHuffman getIzquierda() {
        return izquierda;
    }

    public NodoHuffman getDerecha() {
        return derecha;
    }
}