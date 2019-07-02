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
     * Constructor para crear un nuevo objeto {@link Medicamento}.
     *
     * @param pIndiceMedicamento
     */
    public Medicamento(Integer pIndiceMedicamento) {
        mCodigoMedicamentoMapa = new HashMap<>();
        mCodigoMedicamentoMapa.put(pIndiceMedicamento, NOMBRES[pIndiceMedicamento]);
    }

    @Override
    public String toString() {
        return Collections.singletonList(mCodigoMedicamentoMapa).toString();
    }
}
