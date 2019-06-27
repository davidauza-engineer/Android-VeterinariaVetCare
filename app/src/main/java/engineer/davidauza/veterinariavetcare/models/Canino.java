package engineer.davidauza.veterinariavetcare.models;

/**
 * Esta clase representa un {@link Canino}, que es una {@link Especie}. Su nombre es "Canino" y
 * contiene un arreglo de Strings con nombres de diferentes razas de {@link Canino}s.
 */
public class Canino extends Especie {

    /**
     * El nombre de la {@link Especie}: {@link Canino}
     */
    public static final String NOMBRE = "Canino";

    /**
     * Las razas de la {@link Especie}: {@link Canino}
     */
    public static final String[] RAZAS = {"Beagle", "Bóxer", "Bulldog", "Cocker Spaniel", "Criolla",
            "Dálmata", "Desconocida", "Golden Retriever", "Gran Danés", "Labrador", "Otra",
            "Pastor Alemán", "Pitbull", "Springer Spaniel"};

    /**
     * Constructor para crear un nuevo objeto {@link Canino}.
     */
    public Canino() {
        super(NOMBRE);
    }
}