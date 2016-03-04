package busquedaExhaustiva;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Main {

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
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //     nueva.imprimirEmpleados();
        try {

            //vamos a verificar que las compatibilidades sean simetricas

            nueva.verificarSimetrias();
        } catch (Exception ex) {
            System.exit(0);

        }

        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------");
        System.out.println("INICIO PARTE 1");
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------");
        int pesoAlegria = Integer.parseInt(JOptionPane.showInputDialog(null, "cuanto % quieres que valga la alegria??"));
        int pesoMaximoPersonas = 100 - pesoAlegria;
        System.out.println("la alegria va a tener un peso de " + pesoAlegria + "% y el maximo de personas va a tener un peso de " + pesoMaximoPersonas + "%");
        int numeroInicial = (int) Math.pow(2, nueva.devolverTamañoEmpleados());

        while (numeroInicial > 0) {
            numeroInicial--;
            int solucionEnBinario = (int) nueva.devolverBinario(numeroInicial);
            String solucionEnString = "" + solucionEnBinario;
            String solucionEnString1 = nueva.añadirCeros(solucionEnString);
            //    nueva.añadirSolucion(solucionEnBinario);
            // System.out.println("SOLUCION :" + solucionEnString1);
            //System.out.println("con compatibilidad de " + nueva.calcularAlegriaTotal(solucionEnString1));
            //          System.out.println("con empleados :" + nueva.devolverNombresEmpleados("" + solucionEnBinario));
            SolucionAlegrias nuevaSolucion = new SolucionAlegrias(nueva.calcularAlegriaTotal("" + solucionEnString1), solucionEnString1);
            nuevaSolucion.calcularNumeroPersonas();
            // System.out.println("solucion "+solucionEnString1+" con un numero de personas de "+nuevaSolucion.getNumeroPersonasQueVan());

            nueva.añadirSolucionFinal(nuevaSolucion);
        }

        LinkedList<SolucionAlegrias> solucionesOrdenadas = nueva.ordenarSoluciones();
        //    nueva.imprimirSolucionesOrdenadas();

        for (int i = 0; i < solucionesOrdenadas.size(); i++) {
            if (nueva.esSolucionValida("" + solucionesOrdenadas.get(i).getNumerosRespuesta())) {
                System.out.println(" la solucion " + solucionesOrdenadas.get(i).getNumerosRespuesta() + " es una solucion valida");
                nueva.añadirSolucionValida(solucionesOrdenadas.get(i));
            } else {
                //System.out.println(" la solucion " + solucionesOrdenadas.get(i).getNumerosRespuesta() + " es una solucion invalida");
            }

        }
        //nueva.devolverSolucionMaxima(pesoAlegria, pesoMaximoPersonas);

        System.out.println("la solucion optima es :" + nueva.devolverSolucionMaxima(pesoAlegria, pesoMaximoPersonas).getNumerosRespuesta() + " con un total de alegria:" + nueva.devolverSolucionMaxima(pesoAlegria, pesoMaximoPersonas).getAlegriaTotal() + " y un con :" + nueva.devolverSolucionMaxima(pesoAlegria, pesoMaximoPersonas).getNumeroPersonasQueVan() + " personas ");

        //     nueva.imprimirEmpleados();
        // nueva.imprimirCompatibilidades();

        //SABEMOS QUE LA PRIMERA LINEA DE "COMPATIBILIDADES " VA SA SER EL NUMERO DE EMPLEADOS
        //LA ULTIMA LA JERARQUIA
        // System.out.println("la cadena co nla q se va a trabjaar es "+nueva.getFicheroTxtInicial()); 


        //TODO:: SEGUNDA PARTE 
        //INICIAMOS TRABAJANDO CON LOS EMPLEADOS QUE VAN A IR 
        String solucion = nueva.devolverSolucionMaxima(pesoAlegria, pesoMaximoPersonas).getNumerosRespuesta();
        //     nueva.imprimirEmpleados();
        System.out.println("----------------------------------------------------------------");

        System.out.println("----------------------------------------------------------------");
        System.out.println("INICIO PARTE 2");
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------------------------------------------");
        LinkedList<String> listaEmpleadosAQuitar = nueva.devolverNombresNoVan(solucion);
        for (int i = 0; i < listaEmpleadosAQuitar.size(); i++) {
            nueva.quitarEmpleadoPorNombre(listaEmpleadosAQuitar.get(i));

        }


        LinkedList<String> listaEmpleadosQuedan = nueva.devolverNombresRestantes();
        /*   for (int i = 0; i < listaEmpleadosQuedan.size(); i++) {
        System.out.println("en lista empleados i " + i + " contenido " + listaEmpleadosQuedan.get(i));
        }*/
        nueva.permutar("", listaEmpleadosQuedan);


        //      nueva.imprimirPermutaciones();
        //String cadena = "-Laura-David-Cindy";
        for (int i = 0; i < nueva.getPermutacionesPosibles().size(); i++) {
            nueva.partirLinea(nueva.getPermutacionesPosibles().get(i));
            nueva.añadirCostoPermutacion(nueva.calcularCostoPermutacion());


        }


        int i = nueva.devolverIndicePermutacionMejorCosto();
        // System.out.println("el mejor costo es permutacion la da la permutacion:" + i);
        System.out.println("siendo la permutacion " + nueva.getPermutacionesPosibles().get(i));
        System.out.println("con una compatibilidad de :" + nueva.getCostosPermutacion().get(i));



    }

    }
