package engineer.davidauza.veterinariavetcare.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase representa una {@link Patologia}, que se diagnostica en una {@link Consulta}. Las
 * {@link Patologia}s se identifican por un código y un nombre.
 */
public class Patologia {

    /**
     * Este arreglo de Strings contiene el nombre de las diferentes {@link Patologia}s.
     */
    public static final String[] NOMBRES = {"Brucelosis", "Desconocida",
            "Encefalopatías Espongiformes Transmisibles", "Estomatitis Vesicular", "Fiebre Aftosa",
            "Fiebre del Nilo Occidental", "Fiebre del Valle del Rift",
            "Fiebre Hemorrágica Crimea-Congo", "Lengua Azul", "Ninguna", "Otra", "Rabia",
            "Salmonella", "Tuberculosis", "Triquina"};

    /**
     * Mapa que almacena el código de la {@link Patologia}, junto con su nombre.
     */
    private Map<Integer, String> mCodigoPatologiaMapa;

    /**
     * String que almacena la {@link Patologia} como String.
     */
    private String mDescripcion;

    /**
     * Constructor para crear un nuevo objeto {@link Patologia}.
     *
     * @param pIndicePatologia  es el índice de la {@link Patologia} seleccionada en la interfaz
     *                          gráfica.
     * @param pArregloDeNombres es el arreglo de Strings del cual se tomará el nombre.
     */
    public Patologia(Integer pIndicePatologia, String[] pArregloDeNombres) {
        mCodigoPatologiaMapa = new HashMap<>();
        mCodigoPatologiaMapa.put(pIndicePatologia, pArregloDeNombres[pIndicePatologia]);
    }

    /**
     * Constructor para crear un nuevo objeto {@link Patologia}.
     *
     * @param pDescripcion es la descripción de la {@link Patologia} en forma de String.
     */
    public Patologia(String pDescripcion) {
        mDescripcion = pDescripcion;
    }

    /**
     * Retorna la descripción de la {@link Patologia} en forma de String.
     */
    public String getDescripcion() {
        return mDescripcion;
    }

    @Override
    public String toString() {
        return Collections.singletonList(mCodigoPatologiaMapa).toString();
    }
}
