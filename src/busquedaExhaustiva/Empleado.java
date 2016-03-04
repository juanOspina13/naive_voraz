/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedaExhaustiva;

/**
 *
 * @author juan
 */
public class Empleado {

    String nombre;
    int nivelAlegria;
    int nivelSubordinacion = 1;
    int AlegriaSubordinados = -9999;
    boolean vaAIr;
    Empleado jefe;
// para poderme ubicar en el arbol debe haber un nivel de subordinacion, va a ser la profundidad

    public Empleado(String nombre, int nivelAlegria, Empleado jefe) {
        //System.out.println("se creo el empleado con el nombre " + nombre + " y alegria de " + nivelAlegria);
        this.nombre = nombre;
        this.nivelAlegria = nivelAlegria;
        this.jefe = jefe;
        if (jefe == null) {
            nivelSubordinacion = 0;
        }




        //todos en comienzo podran asistir a la fiesta
        vaAIr = true;
    }


    public void setAlegriaSubordinados(int AlegriaSubordinados) {
        this.AlegriaSubordinados = AlegriaSubordinados;
    }

    public void setJefe(Empleado jefe) {
        this.jefe = jefe;
        //System.out.println("se le asigno al empleado "+nombre+" el jefe "+jefe.getNombre());
    }

    public void setNivelSubordinacion(int nivelSubordinacion) {
        this.nivelSubordinacion = nivelSubordinacion;
    }

    int devolverNivelSubordinacion(Empleado jefe) {
        while (jefe.getJefe() != null) {
            nivelSubordinacion++;
            //debe ser el jefe de el jefe, en este caso el jefe de e3;
            jefe = jefe.getJefe();
            devolverNivelSubordinacion(jefe);
            if (jefe != null) {
            }


        }
        return nivelSubordinacion;


    }

    public Empleado getJefe() {
        return jefe;
    }

    public int getNivelAlegria() {
        return nivelAlegria;
    }

    public int getNivelSubordinacion() {
        return nivelSubordinacion;
    }

    public String getNombre() {
        return nombre;
    }

 

    public void setNivelAlegria(int nivelAlegria) {
        this.nivelAlegria = nivelAlegria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
}
