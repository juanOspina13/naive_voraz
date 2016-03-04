/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Voraces.seleccion;

/**
 *
 * @author oscaraca
 */
public class Empleado {
    private String nombre;
    private int nivelAlegria;
    private Empleado jefeDirecto;

    public Empleado() {
    }

    public Empleado(String nombre, int nivelAlegria, Empleado jefeDirecto) {
        this.nombre = nombre;
        this.nivelAlegria = nivelAlegria;
        this.jefeDirecto = jefeDirecto;
    }

    public Empleado getJefeDirecto() {
        return jefeDirecto;
    }

    public int getNivelAlegria() {
        return nivelAlegria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setJefeDirecto(Empleado jefeDirecto) {
        this.jefeDirecto = jefeDirecto;
    }

    public void setNivelAlegria(int nivelAlegria) {
        this.nivelAlegria = nivelAlegria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
