package engineer.davidauza.veterinariavetcare.models;

/**
 * Esta clase representa un {@link Felino}, que es una {@link Especie}. Su nombre es "Felino" y
 * contiene un arreglo de Strings con nombres de diferentes razas de {@link Felino}s.
 */
public class Felino extends Especie {

    /**
     * El nombre de la {@link Especie}: {@link Felino}
     */
    public static final String NOMBRE = "Felino";

    /**
     * Las razas de la {@link Especie}: {@link Felino}
     */
    public static final String[] RAZAS = {"Angora", "Criolla", "Desconocida", "Europeo", "Otra",
            "Persa", "Siamés"};

    /**
     * Constructor para crear un nuevo objeto {@link Felino}.
     */
    public Felino() {
        super(NOMBRE);
    }
}