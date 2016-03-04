/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Voraces.Organizacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author juan
 */
public class DP {

    Empleado e99;
    LinkedList<Compatibilidad> listaCompatibilidades;
    LinkedList<Empleado> listaEmpleados;
    String ficheroTxtInicial;
    LinkedList<String> ficheroCompatibilidades;
    String nombresSolucion;
    String nombre;
    int tamaño = 5;
    int maximoGlobal = 0;
    LinkedList<Empleado> empleados;
    LinkedList<Integer> indicesEmpleados;
    LinkedList<Integer> indicesNoSeLLevanEmpleados;
    LinkedList<Solucion> solucionesOptimas;
    int[][] compatibilidades;
    int dimension;

    public DP(String nombre) {
        this.nombre = nombre;
        nombresSolucion = new String();
        solucionesOptimas = new LinkedList<Solucion>();
        empleados = new LinkedList<Empleado>();
        e99 = new Empleado("Dios", 0, null);
        indicesEmpleados = new LinkedList<Integer>();
        indicesNoSeLLevanEmpleados = new LinkedList<Integer>();
        ficheroCompatibilidades = new LinkedList<String>();
        listaEmpleados = new LinkedList<Empleado>();
        ficheroTxtInicial = new String();
        listaCompatibilidades = new LinkedList<Compatibilidad>();
        /*        for (int i = 0; i < dimension; i++) {
        indicesEmpleados.add(i);
        }*/

        /*raul=0 laura=1 pepe=2 david=3 cindy=4
        Raul (Raul,0) (Laura,0) (Pepe,1) (David,2) (Cindy,3)
        Laura (Raul,0) (Laura,0) (Pepe,-2) (David,1) (Cindy,3)
        Pepe (Raul,1) (Laura,-2) (Pepe,0) (David,2) (Cindy,3)
        David (Raul,2) (Laura,1) (Pepe,2) (David,0) (Cindy,2)
        Cindy (Raul,3) (Laura,3) (Pepe,2) (David,2) (Cindy,0)
        
        //COMPATIBILIDADES E1 RAUL
        // Raul (Raul,0) (Laura,0) (Pepe,1) (David,2) (Cindy,3)
        compatibilidades[0][0] = 0;
        compatibilidades[0][1] = 0;
        compatibilidades[0][2] = 1;
        compatibilidades[0][3] = 2;
        compatibilidades[0][4] = 3;
        
        
        //COMPATIBILIDADES E1 LAURA 
        //        Laura (Raul,0) (Laura,0) (Pepe,-2) (David,1) (Cindy,3)
        compatibilidades[1][0] = 0;
        compatibilidades[1][1] = 0;
        compatibilidades[1][2] = -2;
        compatibilidades[1][3] = 1;
        compatibilidades[1][4] = 3;
        
        
        //COMPATIBILIDADES E1 PEPE
        //   Pepe (Raul,1) (Laura,-2) (Pepe,0) (David,2) (Cindy,3)
        compatibilidades[2][0] = 1;
        compatibilidades[2][1] = -2;
        compatibilidades[2][2] = 0;
        compatibilidades[2][3] = 2;
        compatibilidades[2][4] = 3;
        
        //COMPATIBILIDADES E1 DAVID
        //David (Raul,2) (Laura,1) (Pepe,2) (David,0) (Cindy,2)
        compatibilidades[3][0] = 2;
        compatibilidades[3][1] = 1;
        compatibilidades[3][2] = 2;
        compatibilidades[3][3] = 0;
        compatibilidades[3][4] = 2;
        
        //COMPATIBILIDADES E1 CINDY
        //    Cindy (Raul,3) (Laura,3) (Pepe,2) (David,2) (Cindy,0)
        compatibilidades[4][0] = 3;
        compatibilidades[4][1] = 3;
        compatibilidades[4][2] = 2;
        compatibilidades[4][3] = 2;
        compatibilidades[4][4] = 0;*/
    }

    public LinkedList<Integer> getIndicesEmpleados() {
        return indicesEmpleados;
    }

    public void añadirEmpleados(Empleado empleado) {
        empleados.add(empleado);

    }

    public void imprimirEmpleados() {

        for (int i = 0; i < listaEmpleados.size(); i++) {
            System.out.println("empleado i " + i + "-> " + listaEmpleados.get(i).getNombre());
        }
    }
    //imprime las compatibilidades.

    public void imprimirCompatibilidades() {
        for (int i = 1; i < ficheroCompatibilidades.size() - 1; i++) {
            System.out.println("linea " + i + " contenido " + ficheroCompatibilidades.get(i));
        }
    }

    public void leerFicheroCompatibilidades() {

        try {

            FileReader fr = new FileReader("ficheroCompatibilidades.txt");
            BufferedReader bf = new BufferedReader(fr);
            String sCadena;
            while ((sCadena = bf.readLine()) != null) {
                //
                ficheroCompatibilidades.add(sCadena);
                //   	System.out.println(sCadena);
            }





        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    //funcion mediante la cual se van a crear las compatibilidades.
    public void crearCompatibilidades() {
        dimension = Integer.parseInt(ficheroCompatibilidades.get(0));
        compatibilidades = new int[dimension][dimension];

   //     System.out.println("la dimension va a valer" + dimension);
        for (int i = 1; i < ficheroCompatibilidades.size() - 1; i++) {//   }
            String[] auxiliar = ficheroCompatibilidades.get(i).split("\\ ");
            String nombreEmpleado1 = auxiliar[0];
            for (int i1 = 1; i1 < auxiliar.length; i1++) {
                //System.out.println("se va a crear la compatibilidad entre " + nombreEmpleado1 + " y " + auxiliar[i1]);
                crearCompatibilidadEmpleado(nombreEmpleado1, auxiliar[i1]);
            }
        }
    }

    //devuelve el empleado por su nombre
    public Empleado devolverEmpleadoPorNombre(String nombre) throws Exception {
        Empleado a = null;
        for (int i = 0; i < listaEmpleados.size(); i++) {
            //       System.out.println("entra con el nombre " + nombre + " y el i es " + listaEmpleados.get(i));
            a = listaEmpleados.get(i);
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                return a;
            }
        }
        //    System.out.println(nombre);
        throw new Exception("el empleado no se encontro");
    }

    public void crearCompatibilidadEmpleado(String nombreEmpleado1, String cadenaCaracteres) {
        String sinParentesis = "";
        int indice1 = devolverIndiceNombre(nombreEmpleado1);
        for (int i = 0; i < cadenaCaracteres.length(); i++) {
            if ((cadenaCaracteres.charAt(i) == '(') || (cadenaCaracteres.charAt(i) == ')')) {
            } else {
                sinParentesis += cadenaCaracteres.charAt(i);
            }
        }
        // System.out.println("quitando el parentesis queda " + nombreEmpleado1 + " y " + sinParentesis);
        String[] auxiliar;
        auxiliar = sinParentesis.split("\\,");
        String nombreEmpleado2 = auxiliar[0];
        int indice2 = devolverIndiceNombre(nombreEmpleado2);
        int compatibilidad = Integer.parseInt(auxiliar[1]);

        compatibilidades[indice1][indice2] = compatibilidad;
        //  System.out.println("la posicion i,j" + indice1 + "," + indice2 + " pasa a valer " + compatibilidad);
        //System.out.println("dados por " + nombreEmpleado1 + " y " + nombreEmpleado2);

        /* System.out.println("el nombre1 es " + nombreEmpleado1);
        System.out.println("el nombre2 es " + auxiliar[0]);
        System.out.println("el valor es  " + auxiliar[1]);
         */
        try {
            //       compatibilidades[indice1][indice2] = compatibilidad;
            //     System.out.println("la posicion i,j" + indice1 + "," + indice2 + " pasa a valer " + compatibilidad);

            Empleado empleado1 = devolverEmpleadoPorNombre(nombreEmpleado1);
            Empleado empleado2 = devolverEmpleadoPorNombre(nombreEmpleado2);
            Compatibilidad compatibilidadNueva = new Compatibilidad(compatibilidad, empleado1, empleado2);

            listaCompatibilidades.add(compatibilidadNueva);
        } catch (Exception e23) {
            //   e23.printStackTrace();
            //    System.out.println("hay un error en el archivo");
        }
    }

    public void quitarEmpleado(String nombreEmpleado) {
        for (int i = 0; i < empleados.size(); i++) {
            if (nombreEmpleado.equalsIgnoreCase(empleados.get(i).getNombre())) {
                empleados.remove(i);
            }
        }
    }

    public String devolverNombreEmpleadoPorIndice(int indice) {
        return listaEmpleados.get(indice).getNombre();
    }

    public int devolverIndiceNombre(String nombre) {
        Empleado a = null;

        for (int i = 0; i < listaEmpleados.size(); i++) {
            a = listaEmpleados.get(i);
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return 0;
    }

    public boolean estaElIndiceEnLista(int indice) {
        int aux = 0;
        for (int i = 0; i < indicesEmpleados.size(); i++) {
            aux = indicesEmpleados.get(i);
            if (aux == indice) {
                return true;
            }
        }
        return false;
    }

    public boolean estaIndicesNeg(int indice) {
        int aux = 0;
        for (int i = 0; i < indicesNoSeLLevanEmpleados.size(); i++) {
            aux = indicesNoSeLLevanEmpleados.get(i);
            if (aux == indice) {
                return true;
            }
        }
        return false;
    }

    public void quitarIndiceLista(int indice) {
        indicesNoSeLLevanEmpleados.add(indice);

    }

    public int[] devolverMaximoFila(int indice) {
        //   System.out.println("entro a devolvermaximoFila con el indice " + indice);
        int[] indiceYValor = new int[2];
        int maximo = 0;
        int i;
        for (i = 0; i < compatibilidades.length; i++) {
            if ((compatibilidades[indice][i] > maximo) && estaElIndiceEnLista(i) && (estaIndicesNeg(i) == false)) {
                maximo = compatibilidades[indice][i];
                indiceYValor[0] = i;



            }
        }

        //empleados.remove(i);
        quitarIndiceLista(indice);
        //  quitarIndiceLista(indiceYValor[0]);
        String nombre = devolverNombreEmpleadoPorIndice(indice);
        String nombreAux = devolverNombreEmpleadoPorIndice(indiceYValor[0]);
        // System.out.println("Se quitan" + nombre + " de la lista");
        //System.out.println("debe llamarse la recursion empezando desde " + nombreAux);
        indiceYValor[1] = maximo;
        return indiceYValor;
    }

    public LinkedList<Empleado> getEmpleados() {
        return empleados;
    }

    public void imprimirSoluciones() {
        Solucion aux = null;
        for (int i = 0; i < solucionesOptimas.size(); i++) {

            System.out.println("solucion i" + i + " tamaño " + solucionesOptimas.get(i).getNodosRestantes().size());
            System.out.println("Solucion i" + i + " valor " + solucionesOptimas.get(i).getCompatibilidad());
            aux = solucionesOptimas.get(i);
            if (solucionesOptimas.get(i).getTamaño() > 0) {
                for (int j = 0; j < aux.getTamaño(); j++) {
                    //   aux.imprimirNodosRestantes();
                }
            }
        }
    }

    public void añadirSolucion(Solucion solucion) {
        solucionesOptimas.add(solucion);

    }

    public boolean estaIndice(int indice) {
        for (int i = 0; i < indicesEmpleados.size(); i++) {
            if (indicesEmpleados.get(i) == indice) {
                return true;

            }
        }
        return false;
    }

    //funcion mediante la cual se van a crear los empleados
    public void crearEmpleados() {

        ficheroTxtInicial = ficheroCompatibilidades.getLast();

        arregloAuxiliar(ficheroTxtInicial);

    }

    //hacemos un split con el ficheroTxtInicial para poder sacar los parametros implicitos en el.
    void arregloAuxiliar(String cadenaCaracteres) {

        String[] auxiliar = cadenaCaracteres.split("\\ ");
        for (int i = 0; i < auxiliar.length; i++) {
            //     System.out.println("el arreglo auxiliar vale en i:" + i + "-> " + auxiliar[i]);
            try {
                int alegria = Integer.parseInt(auxiliar[i]);
                String nombre = auxiliar[i - 1];
                Empleado nuevoEmpleado = new Empleado(nombre, alegria, null);
                listaEmpleados.add(nuevoEmpleado);
                //   System.out.println("se añadio el empleado con nombre" + nuevoEmpleado.getNombre());
            } catch (Exception e23) {
            }

        }

    }
    //toma como entrado el indice en la lista de empleados y devuelve el nombre del empleado

    public String devolverNombreEmpleado(int indice) {
        return listaEmpleados.get(indice).getNombre();
    }

    //Los parentesis abiertos son primordiales para saber quien es el jefe de quien, si hay dos consecutivos abiertos hemos encontrado el jefe.
    public String devolverStringRespectoAParentesisAbierto(String antesDeEmpleado) {
        int acum = 0;
        String respuesta = "";
        String aux[];
        for (int i = antesDeEmpleado.length() - 1; i >= 0; i--) {
            if (antesDeEmpleado.charAt(i) == '(') {
                acum++;

                if (acum == 2) {
                    //            System.out.println("va desde i,fin " + i + " ," + antesDeEmpleado.length());
                    respuesta = antesDeEmpleado.substring(i, antesDeEmpleado.length() - 1);
                    return respuesta;
                }
                //       System.out.println("encontro un parentesis");

            }
            if (antesDeEmpleado.charAt(i) == ')') {
                acum--;
                //        System.out.println("encontro cierre de  parentesis");

            }
        }
        return respuesta;
    }

    //devuelve el nombre del jefe 
    public String devolverNombreJefe(String cadenaRespuesta) {
        // queda ( NOMBREJEFE
        String[] aux = cadenaRespuesta.split("\\ ");
        return aux[1];
    }

    //va a verificar que las simetrias de las compatibilidades sean correctas
    public void verificarSimetrias() throws Exception {
        for (int i = 0; i < listaCompatibilidades.size(); i++) {
            for (int j = 0; j < listaCompatibilidades.size(); j++) {
                if ((listaCompatibilidades.get(i).getEmpleado1().getNombre().equalsIgnoreCase(listaCompatibilidades.get(j).getEmpleado2().getNombre())
                        && (listaCompatibilidades.get(i).getEmpleado2().getNombre().equalsIgnoreCase(listaCompatibilidades.get(j).getEmpleado1().getNombre())))
                        && (listaCompatibilidades.get(i).getNumero() != listaCompatibilidades.get(j).getNumero())) {


                    System.out.println("las compatibilidades entre :" + listaCompatibilidades.get(i).getEmpleado1().getNombre() + "-" + listaCompatibilidades.get(i).getEmpleado2().getNombre());
                    System.out.println("y:" + listaCompatibilidades.get(j).getEmpleado1().getNombre() + "-" + listaCompatibilidades.get(j).getEmpleado2().getNombre());
                    System.out.println("Deberian ser simetricas, y no lo son");

                    throw new Exception("compatibilidadesAsimetricas");
                }
            }
        }
    }

    //devuelve la cadena de caracteres antes del nombre. Ejemplo:(ASD(ASD(Raul = (ASD(ASD
    public String devolverAntesDe(String nombreEmpleado) {
        String[] arregloAuxiliar = ficheroTxtInicial.split(nombreEmpleado);
        return arregloAuxiliar[0];
    }

    //devuelve el tamaño de la lista de Empleados.
    public int devolverTamañoListaEmpleados() {
        return listaEmpleados.size();
    }

    public void asignarJefeDeJefes() {
        listaEmpleados.get(0).setJefe(e99);
        //        System.out.println("a " + listaEmpleados.get(i).getNombre() + " se le asigno el jefe " + e99.getNombre());
    }

    public LinkedList<Integer> determinarCualesVan() {
        LinkedList<Integer> aux = new LinkedList<Integer>();
        for (int i = 0; i < indicesNoSeLLevanEmpleados.size(); i++) {
            if (estaIndice(indicesNoSeLLevanEmpleados.get(i))) {
            } else {
                aux.add(indicesNoSeLLevanEmpleados.get(i));
            }
        }
        return aux;
    }

    public void inicializarIndices() {

        for (int i = 0; i < dimension; i++) {
            indicesEmpleados.add(i);
        }
    }

    public void recursion(int indiceEmpleado) {


        while (indicesEmpleados.size() != indicesNoSeLLevanEmpleados.size()) {
            for (int i = 0; i < indicesEmpleados.size(); i++) {
                //         System.out.println("indices disponibles :" + indicesEmpleados.get(i));
            }
            int[] indiceYValor = devolverMaximoFila(indiceEmpleado);
     //       System.out.println("la recursion se llamo con el indice de empleado" + indiceEmpleado);
            //   System.out.println("el valor indice " + indiceYValor[0]);
     //       System.out.println("al maximo global se le va a sumar valor " + indiceYValor[1]);
            maximoGlobal += indiceYValor[1];
            String nombreEmpleado1 = listaEmpleados.get(indiceEmpleado).getNombre();
            String nombreEmpleado2 = listaEmpleados.get(indiceYValor[0]).getNombre();
            nombresSolucion += nombreEmpleado1 + "-";//+ "-" + nombreEmpleado2;
            if (indicesEmpleados.size() == indicesNoSeLLevanEmpleados.size()) {
                nombresSolucion += nombreEmpleado1 + "-" + nombreEmpleado2;//+ "-" + nombreEmpleado2;
                maximoGlobal += compatibilidades[indiceEmpleado][indiceYValor[0]];
            }
            //     System.out.println("el maximo paso a valer:" + maximoGlobal);
            //indice y valor 0 dan el empleado 2 el inicial es el empleado 1
            recursion(indiceYValor[0]);

        }
    }

    public int getMaximoGlobal() {
        return maximoGlobal;
    }

    public String imprimirSolucion() {

        //Raul-CindyCindy-LauraLaura-DavidDavid-PepePepe-Raul
        return nombresSolucion;

    }

    public void imprimirCompatibilidadesMatriz() {
        for (int i = 0; i < compatibilidades.length; i++) {
            for (int j = 0; j < compatibilidades.length; j++) {

                System.out.println("la posicion i,j " + i + "," + j + "--->" + listaEmpleados.get(i).getNombre() + "," + listaEmpleados.get(j).getNombre() + " es " + compatibilidades[i][j]);
            }
        }
    }
}
