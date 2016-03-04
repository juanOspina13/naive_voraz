/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedaExhaustiva;

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
//LISTA RENGLON A RENGLON DE compatibilidades.txt

    String permutacionInicial = "-";
    boolean primeraVez = true;
    LinkedList<String> ficheroCompatibilidades;
    String[] lista;
    int indiceAux1 = 0;
    LinkedList<String> permutacionesPosibles;
    String ficheroTxtInicial;
    LinkedList<Integer> costosPermutacion;
    int contador = 0;
    int numeroVecesLlamadoRecursivo = 0;
    int matriz[][];
    String nombreEmpresa;
    LinkedList<SolucionAlegrias> conjuntoSoluciones;
    LinkedList<Empleado> listaEmpleados;
    LinkedList<Integer> listaSoluciones;
    LinkedList<Compatibilidad> listaCompatibilidades;
    LinkedList<SolucionAlegrias> solucionesValidas = new LinkedList<SolucionAlegrias>();
    int acumNumeroEmpleados = 0;
    //por motivos practicos cree a Dios como empleado e99 que va a ser el jefe de el que no tiene jefes
    Empleado e99;

    public DP(String nombreEmpresa) {
        ficheroTxtInicial = new String();
        this.nombreEmpresa = nombreEmpresa;
        listaEmpleados = new LinkedList<Empleado>();
        listaSoluciones = new LinkedList<Integer>();
        conjuntoSoluciones = new LinkedList<SolucionAlegrias>();
        listaCompatibilidades = new LinkedList<Compatibilidad>();
        permutacionesPosibles = new LinkedList<String>();
        costosPermutacion = new LinkedList<Integer>();
        e99 = new Empleado("Dios", 0, null);
        ficheroCompatibilidades = new LinkedList<String>();
    }

    //despues de verificar que es una solucion valida se añade a la lista de soluciones validas.
    public void añadirSolucionValida(SolucionAlegrias solucion) {
        solucionesValidas.add(solucion);
    }
//funcion para imprimir las soluciones validas de la BE

    public void imprimirSolucionesValidas() {
        for (int i = 0; i < solucionesValidas.size(); i++) {
            System.out.println("solucion numero " + i + "con contenido " + solucionesValidas.get(i).getNumerosRespuesta() + " con alegria " + solucionesValidas.get(i).getAlegriaTotal());
        }
    }

    //esta funcion se llama despues de organizar las soluciones validas descendentemente, 
    // entonces la que presente mas alegria va a ser la que este de primera.
    public SolucionAlegrias devolverSolucionMaxima(double porcentajeAlegria, double porcentajePersonas) {
        porcentajeAlegria = porcentajeAlegria / 100;
        porcentajePersonas = porcentajePersonas / 100;
        SolucionAlegrias solucionOptima = null;
        double solucionInicial = 0;
        for (int i = 0; i < solucionesValidas.size(); i++) {


            double solucionIndiceI = solucionesValidas.get(i).getAlegriaTotal() * porcentajeAlegria + solucionesValidas.get(i).getNumeroPersonasQueVan() * porcentajePersonas;
            //  System.out.println("con los porcentajes la solucion " + solucionesValidas.get(i).getNumerosRespuesta() + "tiene un valor de " + solucionIndiceI);
            if (solucionIndiceI > solucionInicial) {
                solucionOptima = solucionesValidas.get(i);
                solucionInicial = solucionOptima.getNumeroPersonasQueVan() * porcentajePersonas + solucionOptima.getAlegriaTotal() * porcentajeAlegria;
            }
        }

        return solucionOptima;
    }
//funcion que devuelve los empleados que no van en la respuesta

    public LinkedList<String> devolverNombresNoVan(String solucion) {
        LinkedList<String> nombresEmpleados = new LinkedList<String>();
        for (int i = 0; i < solucion.length(); i++) {
            if (solucion.charAt(i) == '0') {
                nombresEmpleados.add(listaEmpleados.get(i).getNombre());
            }

        }
        return nombresEmpleados;
    }

    //lista que contiene los nombres de los empleados que quedan.
    public LinkedList<String> devolverNombresRestantes() {
        LinkedList<String> nombres = new LinkedList<String>();
        for (int i = 0; i < listaEmpleados.size(); i++) {
            nombres.add(listaEmpleados.get(i).getNombre());
        }
        return nombres;
    }

    //elimina el empleado por su nombre
    public void quitarEmpleadoPorNombre(String nombre) {
        Empleado a = null;
        for (int i = 0; i < listaEmpleados.size(); i++) {
            a = listaEmpleados.get(i);
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                // System.out.println("se quito el empleado"+nombre);
                listaEmpleados.remove(i);
            }


        }

    }

    //devuelve la ultima linea del Txt (que va a contener los empleados, su nivel de alegria y jerarquia)
    public String getFicheroTxtInicial() {
        return ficheroTxtInicial;
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
            } catch (Exception e23) {
            }

        }

    }

    //devuelve el tamaño de la lista de Empleados.
    public int devolverTamañoListaEmpleados() {
        return listaEmpleados.size();
    }

    //Se le asigna al jefe de todos Dios como jefe.
    public void asignarJefeDeJefes() {
        listaEmpleados.get(0).setJefe(e99);
        //        System.out.println("a " + listaEmpleados.get(i).getNombre() + " se le asigno el jefe " + e99.getNombre());
    }

    //devuelve la cadena de caracteres antes del nombre. Ejemplo:(ASD(ASD(Raul = (ASD(ASD
    public String devolverAntesDe(String nombreEmpleado) {
        String[] arregloAuxiliar = ficheroTxtInicial.split(nombreEmpleado);
        return arregloAuxiliar[0];
    }

    //devuelve el nombre del jefe 
    public String devolverNombreJefe(String cadenaRespuesta) {
        // queda ( NOMBREJEFE
        String[] aux = cadenaRespuesta.split("\\ ");
        return aux[1];
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

    //se añade la solucion a la lista de solucioness
    public void añadirSolucion(int solucion) {
        listaSoluciones.add(solucion);
    }

    //se añade la solucion despues  de que halla pasado por los filtros que determianran si es valida o no
    public void añadirSolucionFinal(SolucionAlegrias solucion) {
        conjuntoSoluciones.add(solucion);
    }

    //imprime el conjunto de permutaciones posibles
    public void imprimirPermutaciones() {
        for (int i = 0; i < permutacionesPosibles.size(); i++) {

            System.out.println("permutacion " + i + permutacionesPosibles.get(i));
        }
    }

//le entran los nombres de los dos empleados y devuelve la Compatibilidad asociada.    
    public Compatibilidad devolverCompatibilidadPorNombre(String nombreE1, String nombreE2) throws Exception {
        Compatibilidad a = null;
        for (int i = 0; i < listaCompatibilidades.size(); i++) {
            a = listaCompatibilidades.get(i);
            if ((a.getEmpleado1().getNombre().equalsIgnoreCase(nombreE1)) && (a.getEmpleado2().getNombre().equalsIgnoreCase(nombreE2))) {
                return a;
            }
        }
        throw new Exception("la compatibilidad no existe");

    }

    // convierte las permtaciones a compatibilidades 
    public void partirLinea(String cadena) {

        lista = cadena.split("\\-");

    }

    //devuelve la compatibilidad dada por la permotacion
    public int calcularCostoPermutacion() {
        int acum = 0;
        for (int i = 1; i < lista.length - 1; i++) {
//            Compatibilidad nueva = new Compatibilidad
            try {
                Compatibilidad a = devolverCompatibilidadPorNombre(lista[i], lista[i + 1]);
                acum += a.getNumero();
                //   System.out.println("se le sumo al acum " + a.getNumero() + " y ahora vale " + acum);

            } catch (Exception ex) {
                // System.out.println("fallo");
            }
        }
        return acum;



    }

    //añade la compatibilidad dada por una permutacion a la lista de compatibilidades de permutaciones
    public void añadirCostoPermutacion(int costo) {
        costosPermutacion.add(costo);

    }

    //busca la permutacion que tiene el grado de compatibilidad mas alta y devuelve el indice en la lista
    public int devolverIndicePermutacionMejorCosto() {
        int max = 0;
        int mejorIndice = 0;
        for (int i = 0; i < costosPermutacion.size(); i++) {
            if (max < costosPermutacion.get(i)) {
                max = costosPermutacion.get(i);
                mejorIndice = i;
            }
        }

        return mejorIndice;
    }

//getter del costo de permutacion
    public LinkedList<Integer> getCostosPermutacion() {
        return costosPermutacion;
    }

    //devuelve la lista de las permutaciones posibles
    public LinkedList<String> getPermutacionesPosibles() {
        return permutacionesPosibles;
    }

    //funcion que realiza las permutaciones de un conjunto de Strings.
    public void permutar(String a, LinkedList<String> conjunto) {
        if (primeraVez) {
            permutacionInicial += conjunto.get(0);
            primeraVez = false;
        }


        if (conjunto.size() == 1) {
            String permutacion = a + "-" + conjunto.get(0) + permutacionInicial;
            //  System.out.println(permutacion);
            contador++;
            permutacionesPosibles.add(permutacion);
            //   System.out.println("permutacion numero: " + contador);
        }
        for (int i = 0; i < conjunto.size(); i++) {
            String b = conjunto.remove(i);
            permutar(a + "-" + b, conjunto);
            conjunto.add(i, b);


        }
    }

    //ordena las soluciones de alegrias descendentemente
    public LinkedList<SolucionAlegrias> ordenarSoluciones() {

        for (int i = 0; i < conjuntoSoluciones.size(); i++) {
            for (int j = 0; j < conjuntoSoluciones.size(); j++) {
                if (conjuntoSoluciones.get(i).getAlegriaTotal() > conjuntoSoluciones.get(j).getAlegriaTotal()) {
                    SolucionAlegrias aux = conjuntoSoluciones.get(i);
                    conjuntoSoluciones.set(i, conjuntoSoluciones.get(j));
                    conjuntoSoluciones.set(j, aux);
                }
            }
        }
        return conjuntoSoluciones;

    }
//imprime todas las soluciones ordenadas

    public void imprimirSolucionesOrdenadas() {
        int acum = 0;
        for (int i = 0; i < conjuntoSoluciones.size(); i++) {
            //System.out.println("Respuesta nro " + acum + "alegria :" + conjuntoSoluciones.get(i).getAlegriaTotal() + " conjunto de soluciones: " + conjuntoSoluciones.get(i).getNumerosRespuesta());
            acum++;
        }
    }

    //imprime los empleados y su respectivo jefe
    public void imprimirEmpleados() {
        for (int i = 0; i < listaEmpleados.size(); i++) {
            System.out.println("empleado numero " + i + " " + listaEmpleados.get(i).getNombre() + " con jefe " + listaEmpleados.get(i).getJefe().getNombre());
        }
    }

    //imprime las soluciones del arreglo de soluciones
    public void imprimirSoluciones() {
        for (int i = 0; i < listaSoluciones.size(); i++) {
            System.out.println("solucion numero " + i + " " + listaSoluciones.get(i));
        }
    }

    //devuelve la lista de soluciones
    public LinkedList<Integer> getListaSoluciones() {
        return listaSoluciones;
    }

    //verifica si la solcion que entra es valida( es valida si no estan jefes y sus empleados juntos)
    public boolean esSolucionValida(String solucionAVerificar) {
        Empleado aux = null;
        for (int i = 0; i < solucionAVerificar.length(); i++) {
            for (int j = 0; j < solucionAVerificar.length(); j++) {
                if (solucionAVerificar.charAt(i) == '1' && solucionAVerificar.charAt(j) == '1') {
                    if (listaEmpleados.get(i).getNombre().equalsIgnoreCase(listaEmpleados.get(j).getJefe().getNombre())) {
                        {
                            //               System.out.println("en " +solucionAVerificar + "Aqui se esta llevando el jefe y el subordinado con i,j "+i+","+j);
                            return false;
                        }
                    }
                }

            }
        }

        return true;
    }

    //devuelve los nombres correspondientes a la solucion 
    public String devolverNombresEmpleados(String solucion) {
        String acum = "";
        for (int i = 0; i < solucion.length(); i++) {
            if (solucion.charAt(i) == '1') {
                /*      System.out.println("el caracter en " + i + "es 1");
                System.out.println("se le suma  " + listaCompatibilidades.get(i).getNumero());
                 */
                acum += listaCompatibilidades.get(i).getEmpleado1().getNombre() + "-" + listaCompatibilidades.get(i).getEmpleado2().getNombre() + ",";
            }
        }
        return acum;
    }

    //Devuelve la alegria dada por una solucion, siendo ella por Ejemplo: "1001"
    public int calcularAlegriaTotal(String solucion) {
        int acum = 0;
        for (int i = 0; i < solucion.length(); i++) {
            //System.out.println("i vale :" + i);
            if (solucion.charAt(i) == '1') {
                //   System.out.println("ENTRO y se toma la alegria de :"+listaEmpleados.get(i).getNombre());
                /* System.out.println("el caracter en " + i + "es 1");
                System.out.println("se le suma  " + listaCompatibilidades.get(i).getNumero());
                 */
                acum += listaEmpleados.get(i).getNivelAlegria();
            } else {
                //  System.out.println("NO ENTRO y no se toma la alegria de :"+listaEmpleados.get(i).getNombre());
            }
        }
        return acum;
    }

    //le añade ceros a la izquierda a la solucion parcial
    public String añadirCeros(String solucionParcial) {
        while (solucionParcial.length() < listaEmpleados.size()) {
            solucionParcial = "0" + solucionParcial;
            añadirCeros(solucionParcial);
        }

        return solucionParcial;
    }

    //añade la compatibilidad a la lista de compatibilidades
    public void añadirCompatibilidad(Compatibilidad nueva) {
        listaCompatibilidades.add(nueva);
    }

    //devuelve el tamaño de la lista de empleados
    public int devolverTamañoEmpleados() {
        int tamaño = listaEmpleados.size();
        return tamaño;

    }

    //Devuelve el tamaño de las compatibilidades disponibles
    public int devolverTamañoCompatibilidades() {

        int tamaño = listaCompatibilidades.size();
        return tamaño;
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

    //toma como entrado el indice en la lista de empleados y devuelve el nombre del empleado
    public String devolverNombreEmpleado(int indice) {
        return listaEmpleados.get(indice).getNombre();
    }

    //añade el empleado a la lista de empleados.
    public void añadirEmpleado(Empleado nuevoEmpleado) throws Exception {
        Empleado a = null;
        for (int i = 0; i < listaEmpleados.size(); i++) {
            a = listaEmpleados.get(i);
            if (a.getNombre().equalsIgnoreCase(nuevoEmpleado.getNombre())) {
                throw new Exception("el empleado ya existe");
            }
        }
        listaEmpleados.add(nuevoEmpleado);
        acumNumeroEmpleados++;
    }

    // organiza descendentemente las compatibilidades el I y el J corresponden a la persona
    public void organizarCompatibilidades() {
        Compatibilidad a = null;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                a = new Compatibilidad(matriz[i][j], listaEmpleados.get(i), listaEmpleados.get(j));
                listaCompatibilidades.add(a);
            }
        }



        for (int i = 0; i < listaCompatibilidades.size(); i++) {
            for (int j = i; j < listaCompatibilidades.size(); j++) {
                if (listaCompatibilidades.get(i).getNumero() < listaCompatibilidades.get(j).getNumero()) {
                    Compatibilidad aux = listaCompatibilidades.get(i);
                    listaCompatibilidades.set(i, listaCompatibilidades.get(j));
                    listaCompatibilidades.set(j, aux);

                }
            }
        }

    }

    //devuelve una matriz con el mismo tamaño que la que le entra, inicializada en 0
    public int[] generarSolucionTrivial(int matriz[]) {
        int solucionTrivial[] = new int[matriz.length];
        for (int i = 0; i < solucionTrivial.length; i++) {
            solucionTrivial[i] = 0;
        }
        return solucionTrivial;
    }

    //* public int[] generarSolucionSiguiente(int matriz[],int numeroSolucion) {
    public double devolverBinario(int numeroInicial) {


        int exp = 0, digito;
        double binario = 0;
        int numero = numeroInicial;


        while (numero != 0) {
            digito = numero % 2;
            binario = binario + digito * Math.pow(10, exp);
            exp++;
            numero = numero / 2;
        }
        return binario;

    }

    //devuelve un acumulador relacionado con el numero de empleados
    public int getAcumNumeroEmpleados() {
        return acumNumeroEmpleados;
    }
//PARTE COMPATIBILIDADES, CARGA LAS COMPATIBILIDADES DESDE TXT

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

    //imprime las compatibilidades.
    public void imprimirCompatibilidades() {
        for (int i = 1; i < ficheroCompatibilidades.size() - 1; i++) {
            System.out.println("linea " + i + " contenido " + ficheroCompatibilidades.get(i));
        }
    }

    //funcion mediante la cual se van a crear los empleados
    public void crearEmpleados() {

        ficheroTxtInicial = ficheroCompatibilidades.getLast();

        arregloAuxiliar(ficheroTxtInicial);

    }

    //funcion mediante la cual se van a crear las compatibilidades.
    public void crearCompatibilidades() {
        for (int i = 1; i < ficheroCompatibilidades.size() - 1; i++) {//   }
            String[] auxiliar = ficheroCompatibilidades.get(i).split("\\ ");
            String nombreEmpleado1 = auxiliar[0];
            for (int i1 = 1; i1 < auxiliar.length; i1++) {
                //System.out.println("se va a crear la compatibilidad entre " + nombreEmpleado1 + " y " + auxiliar[i1]);
                crearCompatibilidadEmpleado(nombreEmpleado1, auxiliar[i1]);
            }
        }
    }
    //crea la compatibilidad entre dos empleados.

    public void crearCompatibilidadEmpleado(String nombreEmpleado1, String cadenaCaracteres) {
        String sinParentesis = "";
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
        /* System.out.println("el nombre1 es " + nombreEmpleado1);
        System.out.println("el nombre2 es " + auxiliar[0]);
        System.out.println("el valor es  " + auxiliar[1]);
         */
        try {
            int compatibilidad = Integer.parseInt(auxiliar[1]);
            Empleado empleado1 = devolverEmpleadoPorNombre(nombreEmpleado1);
            Empleado empleado2 = devolverEmpleadoPorNombre(nombreEmpleado2);
            Compatibilidad compatibilidadNueva = new Compatibilidad(compatibilidad, empleado1, empleado2);

            listaCompatibilidades.add(compatibilidadNueva);
        } catch (Exception e23) {
            //   e23.printStackTrace();
            //    System.out.println("hay un error en el archivo");
        }
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
}
