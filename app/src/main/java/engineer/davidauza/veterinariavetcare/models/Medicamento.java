package engineer.davidauza.veterinariavetcare.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase representa un {@link Medicamento}. Un {@link Medicamento} cuenta con un código que es
 * el índice de la posición en el array NOMBRES y un nombre que es el String contenido en dicha
 * posición.
 */
public class Medicamento {

    /**
     * Arreglo de Strings que contiene los nombres de los {@link Medicamento}s.
     */
    public static final String[] NOMBRES = {"Adrenalina", "Amino glucósidos", "Antibiótico",
            "Anticestodicos", "Anticoagulantes", "Antinematódicos", "Antiprotozoarios",
            "Beta lactámicos", "Cardiología", "Cefalosporina", "Cimetidina", "Clorafenicol",
            "Cristaloides", "Coloides", "Contra la arritmia",
            "Corticosteroides suprarrenales sintéticos", "Diuréticos osmóticos",
            "Diuréticos del asa de Henle", "Diuréticos ahorradores de potasio",
            "Ectoparasiticidas o acaricidas", "Hematinicos", "Marcrolidos y lincosamidas",
            "Ninguno", "Omeprazol", "Penicilina", "Quinolonas y fluoroquinolonas", "Ranitidina",
            "Sulfonamidas", "Tetracilinas"};

    /**
     * Mapa cuya llave es la posición del {@link Medicamento} en el arreglo de Strings NOMBRES y
     * cuyo contenido String es el nombre del {@link Medicamento}.
     */
    private Map<Integer, String> mCodigoMedicamentoMapa;

    /**
     * Almacena el valor del índice del {@link Medicamento} creado. Este índice es a su vez el
     * código del {@link Medicamento}.
     */
    private int mIndice;

    /**
     * Constructor para crear un nuevo objeto {@link Medicamento}.
     *
     * @param pIndiceMedicamento es el índice del {@link Medicamento} donde se buscará el nombre del
     *                           {@link Medicamento} en el arreglo NOMBRES.
     */
    public Medicamento(Integer pIndiceMedicamento) {
        mIndice = pIndiceMedicamento;
        mCodigoMedicamentoMapa = new HashMap<>();
        mCodigoMedicamentoMapa.put(mIndice, NOMBRES[pIndiceMedicamento]);
    }

    /**
     * Retorna el valor del índice en el arreglo NOMBRES, que a su vez es el código del
     * {@link Medicamento}, donde se encuentra el nombre del {@link Medicamento}.
     */
    public int getIndice() {
        return mIndice;
    }

    @Override
    public String toString() {
        return Collections.singletonList(mCodigoMedicamentoMapa).toString();
    }
}
