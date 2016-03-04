/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Voraces.Organizacion;

import busquedaExhaustiva.*;

/**
 *
 * @author juan
 */
public class Compatibilidad {

    int numero;
    Empleado Empleado1;
    Empleado Empleado2;

    Compatibilidad(int compatibilidad, Voraces.Organizacion.Empleado empleado1, Voraces.Organizacion.Empleado empleado2) {
        this.numero = compatibilidad;
        this.Empleado1 = empleado1;
        this.Empleado2 = empleado2;
    }

    public Empleado getEmpleado1() {
        return Empleado1;
    }

    public Empleado getEmpleado2() {
        return Empleado2;
    }

    public int getNumero() {
        return numero;
    }
}
