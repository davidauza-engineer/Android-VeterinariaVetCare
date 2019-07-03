package engineer.davidauza.veterinariavetcare.models;

/**
 * {@link EnfermedadCronica} es una {@link Patologia} que se diagnostica en una {@link Consulta}. La
 * {@link EnfermedadCronica} al igual que la {@link Patologia se identifica} con un código y un
 * nombre.
 */
public class EnfermedadCronica extends Patologia {

    /**
     * Este arreglo de Strings contiene el nombre de las diferentes enfermedades crónicas.
     */
    public static final String[] NOMBRES = {"Anorexia", "Artritis", "Colitis Nerviosa", "Depresión",
            "Desconocida", "Diabetes", "Ninguna", "Obesidad", "SIDA felino", "Sobrepeso"};

    /**
     * Constructor para crear un nuevo objeto {@link EnfermedadCronica}.
     *
     * @param pIndiceEnfermedadCronica es el índice de la {@link EnfermedadCronica} seleccionada en
     *                                 la interfaz gráfica.
     * @param pArregloDeNombres        es el arreglo de Strings del cual se tomará el nombre.
     */
    public EnfermedadCronica(Integer pIndiceEnfermedadCronica, String[] pArregloDeNombres) {
        super(pIndiceEnfermedadCronica, pArregloDeNombres);
    }

    /**
     * Constructor para crear un nuevo objeto {@link EnfermedadCronica}.
     *
     * @param pDescripcionEnfermedadCronica es la descripción de la {@link EnfermedadCronica} en
     *                                      forma de String.
     */
    public EnfermedadCronica(String pDescripcionEnfermedadCronica) {
        super(pDescripcionEnfermedadCronica);
    }
}
