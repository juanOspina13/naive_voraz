/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Voraces.Organizacion;

import java.util.LinkedList;

/**
 *
 * @author juan
 */
public class Solucion {

    LinkedList<Integer> nodosRestantes;
    int compatibilidad;
    int tamaño = 0;

    public Solucion(int compatibilidad, LinkedList<Integer> nodosRestantes) {
        this.compatibilidad = compatibilidad;
        this.nodosRestantes = nodosRestantes;
        this.tamaño = nodosRestantes.size();

    }

    public int getCompatibilidad() {
        return compatibilidad;
    }

    public int getTamaño() {
        return tamaño;
    }

    public LinkedList<Integer> getNodosRestantes() {
        return nodosRestantes;
    }
}
