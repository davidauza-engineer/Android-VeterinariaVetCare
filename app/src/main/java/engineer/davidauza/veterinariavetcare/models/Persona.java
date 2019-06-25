package engineer.davidauza.veterinariavetcare.models;

/**
 * {@link Persona} representa a una persona. Un persona tiene un ID único que la identifica, un
 * nombre, un número de identidad y una dirección.
 */
public class Persona {

    /**
     * Llave para acceder al ID de la persona en la base de datos.
     */
    public static final String ID = "id";

    /**
     * Llave para acceder al nombre de la persona en la base de datos.
     */
    public static final String NOMBRE = "nombre";

    /**
     * Llave para acceder al numero de identidad de la persona en la base de datos.
     */
    public static final String NUMERO_DE_IDENTIDAD = "numeroDeIdentidad";

    /**
     * Llave para acceder a la dirección de la persona en la base de datos.
     */
    public static final String DIRECCION = "direccion";

    /**
     * El ID único de la persona.
     */
    private String mId;

    /**
     * El nombre de la persona.
     */
    private String mNombre; // TODO dividir en nombre y apellido.

    /**
     * El número de identidad de la persona.
     */
    private String mNumeroDeIdentidad; // TODO cambiar tipo a int

    /**
     * La dirección de la persona.
     */
    private String mDireccion; // TODO añadir teléfonos, eliminar de Veterinario.

    /**
     * Constructor para crear un nuevo objeto {@link Persona}.
     *
     * @param pId                es el ID de la persona.
     * @param pNombre            es el nombre de la persona.
     * @param pNumeroDeIdentidad es el número de identidad de la persona.
     * @param pDireccion         es la dirección de la persona.
     */
    public Persona(String pId,
                   String pNombre,
                   String pNumeroDeIdentidad,
                   String pDireccion) {
        mId = pId;
        mNombre = pNombre;
        mNumeroDeIdentidad = pNumeroDeIdentidad;
        mDireccion = pDireccion;
    }

    /**
     * Constructor para crear un nuebo objeto {@link Persona}.
     *
     * @param pNombre es el nombre de la persona.
     */
    public Persona(String pNombre) {
        mNombre = pNombre;
    }

    /**
     * Obtener el ID de la persona.
     */
    public String getId() {
        return mId;
    }

    /**
     * Obtener el nombre de la persona.
     */
    public String getNombre() {
        return mNombre;
    }

    /**
     * Obtener el número de identidad de la persona.
     */
    public String getNumeroDeIdentidad() {
        return mNumeroDeIdentidad;
    }

    /**
     * Obtener la dirección de la persona.
     */
    public String getDireccion() {
        return mDireccion;
    }
}
