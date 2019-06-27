package engineer.davidauza.veterinariavetcare.models;

/**
 * Esta clase representa un {@link Ave}, que es una {@link Especie}. Su nombre es "Ave" y
 * contiene un arreglo de Strings con nombres de diferentes razas de {@link Ave}s.
 */
public class Ave extends Especie {

    /**
     * El nombre de la {@link Especie}: {@link Ave}
     */
    public static final String NOMBRE = "Ave";

    /**
     * Las razas de la {@link Especie}: {@link Ave}
     */
    public static final String[] RAZAS = {"Águila", "Azulejo", "Cacatúa", "Canario", "Desconocida",
            "Gallina", "Gallo", "Halcón", "Loro", "Otra", "Perico", "Pollo"};

    /**
     * Constructor para crear un nuevo objeto {@link Ave}.
     */
    public Ave() {
        super(NOMBRE);
    }
}
