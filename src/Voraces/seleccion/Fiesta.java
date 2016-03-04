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
public class Fiesta {
    ArrayList<Empleado> listaEmpleados;

    public Fiesta() {
        Empleado e1 = new Empleado("Raul", 5, null);
        Empleado e2 = new Empleado("Laura", 2, e1);
        Empleado e3 = new Empleado("Pepe", 1, e1);
        Empleado e4 = new Empleado("David", -2, e1);
        Empleado e5 = new Empleado("Cindy", 4, e3);
        listaEmpleados = new ArrayList();
        listaEmpleados.add(e1);
        listaEmpleados.add(e2);
        listaEmpleados.add(e3);
        listaEmpleados.add(e4);
        listaEmpleados.add(e5);
        ArrayList<Empleado> empleadosSeleccionados = seleccionarEmpleados(0);
        System.out.println("se va a invitar " + empleadosSeleccionados.size() + " empleados");
        int alegriaTotal = 0;
        for (Empleado empleadoInvitado : empleadosSeleccionados) {
            alegriaTotal+=empleadoInvitado.getNivelAlegria();
            System.out.println("se va a invitar a: " + empleadoInvitado.getNombre());
        }
        System.out.println("con una alegria total de " + alegriaTotal);
    }

    public Fiesta(ArrayList<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }
    
    public ArrayList<Empleado> seleccionarEmpleados(int i){
        
        if (i==listaEmpleados.size()) return listaEmpleados;
        int alegriaI = listaEmpleados.get(i).getNivelAlegria();
        int alegriaSubordinados = 0;
        ArrayList<Empleado> subordinados = new ArrayList();
        for (int j=i; j<listaEmpleados.size(); j++) {
            if (j!=i) {
                Empleado empleadoI = listaEmpleados.get(i);
                Empleado empleadoJ = listaEmpleados.get(j);
                if(empleadoI.getNombre().equals(empleadoJ.getJefeDirecto().getNombre())) {
                    alegriaSubordinados+=empleadoJ.getNivelAlegria();
                    subordinados.add(empleadoJ);
                }
            }
        }
        
        if (alegriaI>=alegriaSubordinados) {    //SI LA ALEGRIA DEL JEFE ES MAYOR QUE LA DE SUS 
                                                //SUBORDINADOS DIRECTOS NO SE DEBEN INVIAR LOS SUBORDINADOS
                                                //SI LAS ALEGRIAS SON IGUALES TAMBIEN ES MAS CONVENIENTE NO
                                                //INVITAR A LOS SUBORDINADOS PARA TENER MAS POSIBILIDADES DE
                                                //INVITAR MAS ABAJO EN LA JERARQUÍA
            
            for (int a=0; a<subordinados.size(); a++) { 
                listaEmpleados.remove(subordinados.get(a));
            }
            i++;                                //YA NO SE TENDRA EN CUENTA DICHO JEFE EN LA SIGUIENTE ITERACIÓN
        } else {
            listaEmpleados.remove(i);           //PERO SI LA ALEGRIA DE LOS SUBORNINADOS ES MAYOR SE DECIDE
                                                //NO INVITAR AL JEFE DIRECTO DE DICHOS SUBORDINADOS
        }
        
        return seleccionarEmpleados(i);
    }
}
