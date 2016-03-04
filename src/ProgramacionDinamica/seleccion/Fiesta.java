/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ProgramacionDinamica.seleccion;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Fiesta {
    Empleado jefeMayoritario;
    double importanciaNoPersonas;
    double importanciaAlegria;
    int noVecesRecursion;

    public Fiesta() {
    }

    

    public Fiesta(Empleado jefeMayoritario) {
        this.jefeMayoritario = jefeMayoritario;
    }
    
    public ArrayList<Empleado> seleccionarEmpleados(double importanciaNoPersonas) { //importanciaNoPersonas PONDERADO QUE SE VA A TENER EN CUENTA PARA LA MAXIMIZACIÓN DEL NÚMERO DE PERSONAS Y LA ALEGRÍA QUE PUEDEN IR EN CONTRA EN ALGUNOS CASOS
        this.importanciaNoPersonas = importanciaNoPersonas;
        this.importanciaAlegria = 1 - importanciaNoPersonas;
        noVecesRecursion = 0;
        
        return recursion2(jefeMayoritario);
    }
    
    private ArrayList<Empleado> recursion2(Empleado empleado) { //SOLUCION DINÁMICA, EXPLORA TODAS LAS SOLUCIONES Y CON LAS SOLUCIONES DE LOS PROBLEMAS EN LOS NIVELES MAS PROFUNDOS DEL ARBOL EMPIEZA A CONSTRUIR LAS SOLUCIONES DE MAS ARRIBA
        noVecesRecursion++;
        if(empleado == null) return null;
        
        if(empleado.getListaSubordinados().isEmpty()) {
            ArrayList<Empleado> arreglo = new ArrayList();
            empleado.setAlegriaAcumuladaMejorCaso(empleado.getNivelAlegria()); 
            if (empleado.getNivelAlegria()<0) {
                if(importanciaNoPersonas>importanciaAlegria) {
                    arreglo.add(empleado);
                }
            } else {
                arreglo.add(empleado);
            }
            empleado.setSolucionParcial(arreglo);
            
            return arreglo;
        }
        else {
            
            ArrayList<Empleado> opcion1 = new ArrayList();
            opcion1.add(empleado);
            for (Empleado e : empleado.getListaSubordinados()) {
                for (Empleado ee : e.getListaSubordinados()) {
                    opcion1.addAll(recursion2(ee));
                }
            }
            
            ArrayList<Empleado> opcion2 = new ArrayList();
            for (Empleado e : empleado.getListaSubordinados()) {
                opcion2.addAll(recursion2(e));
            }
            
            double pesoOpcion1 = (importanciaNoPersonas*opcion1.size()) + (importanciaAlegria*calcularPesoAlegriaSolucion(opcion1));
            double pesoOpcion2 = (importanciaNoPersonas*opcion2.size()) + (importanciaAlegria*calcularPesoAlegriaSolucion(opcion2));;
            
            if (pesoOpcion1 > pesoOpcion2) {
                return opcion1;
            }
            else return opcion2;
            
        }
    }
    
    public void setImportanciaNoPersonas(int importanciaNoPersonas) {
        this.importanciaNoPersonas = importanciaNoPersonas;
    }

    public void setImportanciaAlegria(int importanciaAlegria) {
        this.importanciaAlegria = importanciaAlegria;
    }

    public int getNoVecesRecursion() {
        return noVecesRecursion;
    }
    
    private int calcularPesoAlegriaSolucion(ArrayList<Empleado> empleados) {
        int peso = 0;
        
        for (Empleado e : empleados) {
            peso+=e.getNivelAlegria();
        }
        
        return peso;
    }

    
}
