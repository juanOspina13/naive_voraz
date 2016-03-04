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
public class Empleado {
    String nombre;
    int nivelAlegria;
    ArrayList<Empleado> listaSubordinados;
    int alegriaAcumuladaMejorCaso;
    ArrayList<Empleado> solucionParcial;
    

    public Empleado() {
    }

    public Empleado(String nombre, int nivelAlegria, ArrayList<Empleado> listaSubordinados) {
        this.nombre = nombre;
        this.nivelAlegria = nivelAlegria;
        this.listaSubordinados = listaSubordinados;
        this.alegriaAcumuladaMejorCaso = nivelAlegria;
    }

    public void setSolucionParcial(ArrayList<Empleado> solucionParcial) {
        this.solucionParcial = solucionParcial;
    }

    public ArrayList<Empleado> getListaSubordinados() {
        return listaSubordinados;
    }

    public int getNivelAlegria() {
        return nivelAlegria;
    }

    public int getAlegriaAcumuladaMejorCaso() {
        return alegriaAcumuladaMejorCaso;
    }

    public void setAlegriaAcumuladaMejorCaso(int alegriaAcumuladaMejorCaso) {
        this.alegriaAcumuladaMejorCaso = alegriaAcumuladaMejorCaso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setListaSubordinados(ArrayList<Empleado> listaSubordinados) {
        this.listaSubordinados = listaSubordinados;
    }

    public void setNivelAlegria(int nivelAlegria) {
        this.nivelAlegria = nivelAlegria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getAlegriaSubOrdinados() {
        int alegriaSubOrdinados = 0;
        for (Empleado e : listaSubordinados) {
            alegriaSubOrdinados += e.getNivelAlegria();
        }
        return alegriaSubOrdinados;
    }
    
    public String stringSubOrdinados() {
        String string = "";
        for (Empleado empleado : listaSubordinados) {
            string += "\n"+empleado.getNombre();
        }
        return string;
    }
    
    public void imprimirArbol(Empleado e) {
        if (e != null) {
            System.out.println("\nNombre: "+ e.getNombre());
            if(!e.getListaSubordinados().isEmpty()) {
                System.out.println("Con subordinados: ");
                for (Empleado ee : e.getListaSubordinados()) {
                    System.out.print(ee.getNombre()+" ");
                }
                for (Empleado ee : e.getListaSubordinados()) {
                    imprimirArbol(ee);
                }
            }
        }
    }
}
