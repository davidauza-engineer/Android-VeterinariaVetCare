package engineer.davidauza.veterinariavetcare.models;

/**
 * Esta clase representa un {@link Roedor}, que es una {@link Especie}. Su nombre es "Roedor" y
 * contiene un arreglo de Strings con nombres de diferentes razas de {@link Roedor}es.
 */
public class Roedor extends Especie {

    /**
     * El nombre de la {@link Especie}: {@link Roedor}
     */
    public static final String NOMBRE = "Roedor";

    /**
     * Las razas de la {@link Especie}: {@link Roedor}
     */
    public static final String[] RAZAS = {"Ardilla", "Desconocida", "Hámster", "Otra", "Ratón",
            "Rata"};

    /**
     * Constructor para crear un nuevo objeto {@link Roedor}.
     */
    public Roedor() {
        super(NOMBRE);
    }
}
