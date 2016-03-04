/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Voraces.seleccion;

import java.util.ArrayList;

/**
 *
 * @author oscaraca
 */
public class pruebas {
    public static void main(String [] args) {
        
        Empleado e1 = new Empleado("Raul", 5, null); //EMPLEADOS EJEMPLO PROYECTO
        Empleado e2 = new Empleado("Laura", 2, e1);
        Empleado e3 = new Empleado("Pepe", 1, e1);
        Empleado e4 = new Empleado("David", -2, e1);
        Empleado e5 = new Empleado("Cindy", 4, e3);
        ArrayList<Empleado> listaEmpleados = new ArrayList();
        listaEmpleados.add(e1);                     //LOS EMPLEADOS SE DEBEN AÑADIR DE EN ORDEN DE JERARQUÍA
        listaEmpleados.add(e2);                     //NINGÚN JEFE PUEDE SER AÑADIDO AL ARREGLO DESPUES DE
        listaEmpleados.add(e3);                     //ALGUNOS DE LOS SUBORDINADOS
        listaEmpleados.add(e4);
        listaEmpleados.add(e5);
        
        Fiesta f = new Fiesta(listaEmpleados);
        
        ArrayList<Empleado> empleadosSeleccionados = f.seleccionarEmpleados(0); //SE INICIA EN 0 PORQUE SE VA A
                                                                                //TENER EN CUENTA DESDE ESA POSICIÓN
                                                                                //EN LA LISTA DE LOS EMPLEADOS
                                                                                //PARA VER QUIENES SE VAN A IR
                                                                                //ELIMINANDO DE LA LISTA
                                                                                //RECURSIVAMENTE ESTE NUMERO SE
                                                                                //MODIFICA PARA TENER EN CUENTA
                                                                                //LOS DISTINTOS CASOS POSIBLES
                                                                                //LOS CUALES ESTAN EXPLICADOS
                                                                                //EN LA IMPLEMENTACION DEL MÉTODO
        
        System.out.println("se va a invitar " + empleadosSeleccionados.size() + " empleados");
        int alegriaTotal = 0;
        for (Empleado empleadoInvitado : empleadosSeleccionados) {
            alegriaTotal+=empleadoInvitado.getNivelAlegria();
            System.out.println("se va a invitar a: " + empleadoInvitado.getNombre());
        }
        System.out.println("con una alegria total de " + alegriaTotal);
    }
}
