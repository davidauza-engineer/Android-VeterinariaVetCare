package engineer.davidauza.veterinariavetcare.models;

/**
 * Esta clase representa una {@link Especie} en general. Por defecto su nombre es
 * "Desconocida - Otra" y tiene una única raza "Desconocida - Otra".
 */
public class Especie {

    /**
     * El nombre por defecto de la {@link Especie}.
     */
    public static final String NOMBRE = "Desconocida - Otra";

    /**
     * Las razas que contiene la {@link Especie} por defecto.
     */
    public static final String[] RAZAS = {NOMBRE};

    /**
     * El nombre de la {@link Especie} para las instancias de la clase.
     */
    private String mNombre;

    /**
     * Constructor para crear un nuevo objeto {@link Especie}.
     *
     * @param pNombre es el nombre de la sub especie que está llamando el constructor. Ejemplos:
     *                Canino, Felino, Ave, etc.
     */
    public Especie(String pNombre) {
        mNombre = pNombre;
    }

    /**
     * Constructor para crear un nuevo objeto {@link Especie}.
     */
    public Especie() {
        mNombre = NOMBRE;
    }

    /**
     * Este método retorna el nombre de la especie.
     *
     * @return el nombre de la especie.
     */
    public String getNombre() {
        return mNombre;
    }
}
