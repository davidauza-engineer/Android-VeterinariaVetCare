package engineer.davidauza.veterinariavetcare.models;

/**
 * {@link Persona} representa a una persona. Un persona tiene un ID único que la identifica, un
 * nombre, un apellido, un número de documento, una dirección y un teléfono.
 */
public class Persona {

    /**
     * Llave para acceder al ID de la {@link Persona} en la base de datos.
     */
    public static final String ID = "id";

    /**
     * Llave para acceder al nombre de la {@link Persona} en la base de datos.
     */
    public static final String NOMBRE = "nombre";

    /**
     * Llave para acceder al apellido de la {@link Persona} en la base de datos.
     */
    public static final String APELLIDO = "apellido";

    /**
     * Llave para acceder al número de documento de la {@link Persona} en la base de datos.
     */
    public static final String NUMERO_DE_DOCUMENTO = "numeroDeDocumento";

    /**
     * Llave para acceder a la dirección de la {@link Persona} en la base de datos.
     */
    public static final String DIRECCION = "direccion";

    /**
     * Llave para acceder al teléfono de la {@link Persona} en la base de datos.
     */
    public static final String TELEFONO = "telefono";

    /**
     * El ID único de la {@link Persona}.
     */
    private int mId;

    /**
     * El nombre de la {@link Persona}.
     */
    private String mNombre;

    /**
     * El apellido de la {@link Persona}.
     */
    private String mApellido;

    /**
     * El número de documento de la {@link Persona}.
     */
    private long mNumeroDeDocumento;

    /**
     * La dirección de la {@link Persona}.
     */
    private String mDireccion;

    /**
     * El teléfono de la {@link Persona}.
     */
    private long mTelefono;

    /**
     * Constructor para crear un nuevo objeto {@link Persona}.
     *
     * @param pId                es el ID de la {@link Persona}.
     * @param pNombre            es el nombre de la {@link Persona}.
     * @param pApellido          es el apellido de la {@link Persona}.
     * @param pNumeroDeDocumento es el número de documento de la {@link Persona}.
     * @param pDireccion         es la dirección de la {@link Persona}.
     * @param pTelefono          es el teléfono de la {@link Persona}.
     */
    public Persona(int pId,
                   String pNombre,
                   String pApellido,
                   long pNumeroDeDocumento,
                   String pDireccion,
                   long pTelefono) {
        mId = pId;
        mNombre = pNombre;
        mApellido = pApellido;
        mNumeroDeDocumento = pNumeroDeDocumento;
        mDireccion = pDireccion;
        mTelefono = pTelefono;
    }

    /**
     * Constructor para crear un nuevo objeto {@link Persona}.
     *
     * @param pNombre            es el nombre de la {@link Persona}.
     * @param pNumeroDeDocumento es el número de documento de la {@link Persona}.
     * @param pDireccion         es la dirección de la {@link Persona}.
     * @param pTelefono          es el teléfono de la {@link Persona}.
     */
    public Persona(String pNombre,
                   long pNumeroDeDocumento,
                   String pDireccion,
                   long pTelefono) {
        mNombre = pNombre;
        mNumeroDeDocumento = pNumeroDeDocumento;
        mDireccion = pDireccion;
        mTelefono = pTelefono;
    }

    /**
     * Constructor para crear un nuevo objeto {@link Persona}.
     *
     * @param pNombre es el nombre de la {@link Persona}.
     */
    public Persona(String pNombre) {
        mNombre = pNombre;
    }

    /**
     * Obtener el ID de la {@link Persona}.
     */
    public int getId() {
        return mId;
    }

    /**
     * Obtener el nombre de la {@link Persona}.
     */
    public String getNombre() {
        return mNombre;
    }

    /**
     * Obtener el apellido de la {@link Persona}.
     */
    public String getApellido() {
        return mApellido;
    }

    /**
     * Obtener el número de documento de la {@link Persona}.
     */
    public long getNumeroDeDocumento() {
        return mNumeroDeDocumento;
    }

    /**
     * Obtener la dirección de la {@link Persona}.
     */
    public String getDireccion() {
        return mDireccion;
    }

    /**
     * Obtener el teléfono de la {@link Persona}.
     */
    public long getTelefono() {
        return mTelefono;
    }
}
