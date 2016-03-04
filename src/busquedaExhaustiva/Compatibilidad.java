/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedaExhaustiva;

/**
 *
 * @author juan
 */
public class Compatibilidad {

    int numero;
    Empleado Empleado1;
    Empleado Empleado2;

    public Compatibilidad(int numero, Empleado Empleado1, Empleado Empleado2) {
        this.numero = numero;
        this.Empleado1 = Empleado1;
        this.Empleado2 = Empleado2;
   //     System.out.println("se creo la compatibilidad entre " + Empleado1.getNombre() + " y " + Empleado2.getNombre() + " con un valor de " + numero);
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
