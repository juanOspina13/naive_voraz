/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramacionDinamica.seleccion;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class pruebas {
    public static void main (String args[]) {
        Empleado e17 = new Empleado("carla", 6, new ArrayList());
        Empleado e16 = new Empleado("freddy", 8, new ArrayList());
        Empleado e15 = new Empleado("sara", 3, new ArrayList());
        Empleado e14 = new Empleado("carlos", 4, new ArrayList());
        ArrayList<Empleado> subCindy = new ArrayList();
        subCindy.add(e17);
        subCindy.add(e16);
        Empleado e13 = new Empleado("cindy", 1, subCindy);
        Empleado e12 = new Empleado("andrea", 3, new ArrayList());
        ArrayList<Empleado> subRaul = new ArrayList();
        subRaul.add(e15);
        subRaul.add(e14);
        Empleado e11 = new Empleado("raul", 2, subRaul);
        ArrayList<Empleado> subSandra = new ArrayList();
        subSandra.add(e13);
        subSandra.add(e12);
        Empleado e10 = new Empleado("sandra", 2, subSandra);
        ArrayList<Empleado> subPepe = new ArrayList();
        subPepe.add(e11);
        subPepe.add(e10);
        Empleado e9 = new Empleado("pepe", 5, subPepe);
        Empleado e8 = new Empleado("pedro", 2, new ArrayList());
        ArrayList<Empleado> subKelly = new ArrayList();
        subKelly.add(e9);
        subKelly.add(e8);
        Empleado e7 = new Empleado("kelly", 3, subKelly);
        Empleado e6 = new Empleado("pablo", 4, new ArrayList());
        Empleado e5 = new Empleado("paula", 5, new ArrayList());
        Empleado e51 = new Empleado("ned", -1, new ArrayList());
        Empleado e4 = new Empleado("david", 7, new ArrayList());
        Empleado e3 = new Empleado("manuel", 2, new ArrayList());
        ArrayList<Empleado> subRalf = new ArrayList();
        subRalf.add(e6);
        subRalf.add(e5);
        subRalf.add(e51);
        Empleado e2 = new Empleado("ralf", 5, subRalf);
        ArrayList<Empleado> subJuan = new ArrayList();
        subJuan.add(e4);
        subJuan.add(e3);
        subJuan.add(e2);
        Empleado e1 = new Empleado("juan", 2, subJuan);
        ArrayList<Empleado> subOscar = new ArrayList();
        subOscar.add(e7);
        subOscar.add(e1);
        Empleado e0 = new Empleado("oscar", 6, subOscar);
        
        //HASTA AQUI SE HA CREADO UN ÁRBOL DE EJEMPLO CON JERARQUÍAS Y ALEGRÍAS
        
        Fiesta party = new Fiesta(e0); //LA FIESTA TIENE COMO ENTRADA EN SU CONSTRUCTOR EL EMPLEADO JEFE DEL CUAL SE DESPRENDE TODO EL ARBOL DE JERARQUÍAS
        //e0.imprimirArbol(e0);
        double importanciaNoPersonas = -1;
        try {
            
            while (importanciaNoPersonas<0 || importanciaNoPersonas >100) importanciaNoPersonas = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite la importancia que le desea dar al numero de personas (numero de 0 a 100)"));
            
            double inp = importanciaNoPersonas/100;
            ArrayList<Empleado> seleccionarEmpleados = party.seleccionarEmpleados(inp); //importancia no personas
            System.out.println("se va a llamar el metodo con inp " + inp);
        
            System.out.println("se van a invitar " + seleccionarEmpleados.size()+" empleados los cuales son:");
            int alegriaTotal = 0;
            for (Empleado empleado : seleccionarEmpleados) {
                System.out.println(empleado.getNombre());
                alegriaTotal+=empleado.getNivelAlegria();
            }

            System.out.println("con una alegría total de " + alegriaTotal);

            System.out.println("la recursion se corrio "+ party.getNoVecesRecursion()+" veces");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error", "debe ser un numero", JOptionPane.ERROR_MESSAGE);
        }
    }
}
