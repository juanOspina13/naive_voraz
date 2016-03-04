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
public class DPMesa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DP nueva = new DP("Univalle");

        //TODO:: PRIMERA PARTE DEL PROYECTO

        //CARGAMOS LAS COMPATIBILIDADES DESDE TXT
        nueva.leerFicheroCompatibilidades();
        nueva.crearEmpleados();
        nueva.crearCompatibilidades();



        //  System.out.println("la cadena co nla q se va a trabjaar es " + nueva.getFicheroTxtInicial());

        // nueva.imprimirEmpleados();
        // nueva.imprimirCompatibilidades();

        //SABEMOS QUE LA PRIMERA LINEA DE "COMPATIBILIDADES " VA SA SER EL NUMERO DE EMPLEADOS
        //LA ULTIMA LA JERARQUIA
        // System.out.println("la cadena co nla q se va a trabjaar es "+nueva.getFicheroTxtInicial()); 


        //CARGAR LA LISTA DESDE TXT Y CREAR LOS EMPLEADOS EN EL PROGRAMA

        nueva.asignarJefeDeJefes();

        //ASIGNARLE EL JEFE A CADA EMPLEADO!!
        //devuelve el nombre del empleado con respecto a su indice en la listaEmpleados

        for (int i = 1; i < nueva.devolverTamañoListaEmpleados(); i++) {
            String nombreEmpleado = nueva.devolverNombreEmpleado(i);

            //devuelve la cadena que se encuentra antes del nombre del empleado
            String aux = nueva.devolverAntesDe(nombreEmpleado);

            //devuelve la cadena de respuesta siendo ( NOMBREJEFE
            String cadenaRespuesta = nueva.devolverStringRespectoAParentesisAbierto(aux);
            //devuelve el nombre del jefe
            String nombreJefe = nueva.devolverNombreJefe(cadenaRespuesta);
            try {
                //devuelve el empleado Jefe
                Empleado empleado = nueva.devolverEmpleadoPorNombre(nombreEmpleado);
                Empleado jefe = nueva.devolverEmpleadoPorNombre(nombreJefe);

                empleado.setJefe(jefe);

            } catch (Exception ex) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //     nueva.imprimirEmpleados();
        try {

            //vamos a verificar que las compatibilidades sean simetricas

            nueva.verificarSimetrias();
        } catch (Exception ex) {
            System.exit(0);

        }

        nueva.inicializarIndices();
        nueva.recursion(0);

        //nueva.imprimirCompatibilidades();
        //     nueva.imprimirCompatibilidadesMatriz();
        //nueva.recursion(0);

        System.out.println("la solucion optima es " + nueva.imprimirSolucion());
        System.out.println("con un valor de " + nueva.getMaximoGlobal());
        //       nueva.imprimirEmpleados();

        //empezamos desde el 0(Raul) y terminamos en 0 (Raul)
        //se tiene q sacar matrices del mejor costo hasta raul, por ejemplo mejor desde laura hasta raul..etc
        //si llega a un vertice le problema se achiquita en despejar el inicial y empezando desde el segundo
        //y luego se debe encontrar el minimo desde el segundo hasta el inicial otra ves( cerrar la mesa)

        /*
        Assume we have N destinations. 
        There are N size-1 problems, one for each of the N destinations. 
        The best way to visit each is the shortest path from node 0 to node N. 
        We store the best solution to each of these problems in a table:
        
        best[subset][end] = dist[0][end]
        
        Here, subset represents which destinations have been visited. 
        More specifically, subset is a bitmask: an integer where bit i is 1 if 
        destination i has been visited and 0 otherwise. 
        Also note that the stored solution is the solution to the A-Z TSP problem, 
        so it does not include the cost to return to the origin. 
        The variable end is the destination that this A-Z problem ends at.
        best[subset][end] = min(best[subset \ { end }][i] + dist[i][end])
         * 
         */
        // TODO code application logic here
    }
}
