/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedaExhaustiva;

/**
 *
 * @author juan
 */
public class SolucionAlegrias {

    int alegria;
    String numerosRespuesta;
    int numeroPersonas;

    public SolucionAlegrias(int alegria, String numerosRespuesta) {
        this.alegria = alegria;
        this.numerosRespuesta = numerosRespuesta;
    }

    public void calcularNumeroPersonas() {
        int acum = 0;
        for (int i = 0; i < numerosRespuesta.length(); i++) {
            if (numerosRespuesta.charAt(i) == '1') {
                acum++;
            }
        }
        numeroPersonas=acum;
    }

    public int getNumeroPersonasQueVan() {
        return numeroPersonas;
    }

    public int getAlegriaTotal() {
        return alegria;
    }

    public String getNumerosRespuesta() {
        return numerosRespuesta;
    }
}
