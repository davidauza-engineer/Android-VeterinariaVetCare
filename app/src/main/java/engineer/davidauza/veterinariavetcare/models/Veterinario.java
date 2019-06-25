package engineer.davidauza.veterinariavetcare.models;

/**
 * {@link Veterinario} es una {@link Persona}. Este cuenta con un número de tarjeta profesional, una
 * especialidad médica, un total consultas realizadas y un teléfono.
 */
public class Veterinario extends Persona {

    /**
     * Llave para acceder al número de tarjeta profesional del veterinario en la base de datos.
     */
    public static final String TARJETA_PROFESIONAL = "tarjetaProfesional";

    /**
     * Llave para acceder a la especialidad médica del veterinario en la base de datos.
     */
    public static final String ESPECIALIDAD = "especialidad";

    /**
     * Llave para acceder al número de consultas realizadas por el veterinario en la base de datos.
     */
    public static final String CONSULTAS_REALIZADAS = "consultasRealizadas";

    /**
     * Llave para acceder al teléfono del veterinario en la base de datos.
     */
    public static final String TELEFONO = "telefono";

    /**
     * Contiene la URL necesaria para acceder al microservicio que permite registrar nuevos
     * {@link Veterinario}s.
     */
    public static final String URL_SET =
            "https://davidauza-engineer.000webhostapp.com/web_service/set_veterinario.php";

    /**
     * Contiene la URL necesaria para acceder al microservicio que permite consultar los
     * {@link Veterinario}s registrados.
     */
    public static final String URL_GET =
            "https://davidauza-engineer.000webhostapp.com/web_service/get_veterinario.php";

    /**
     * El número de tarjeta profesional del veterinario.
     */
    private String mTarjetaProfesional; // TODO cambiar tipo a String y cambiar nombre por numeroDeRegistroProfesional

    /**
     * La especialidad médica del veterinario.
     */
    private String mEspecialidad; // TODO ¿mEspecialidadMedica debería ser un array? Sí

    /**
     * El número de consultas realizadas por el veterinario;
     */
    private String mConsultasRelizadas;

    /**
     * El número de teléfono del veterinario.
     */
    private String mTelefono;

    /**
     * Constructor para crear un nuevo objeto {@link Veterinario}.
     *
     * @param pId                  es el ID único para el Veterinario.
     * @param pNombre              es el nombre del Veterinario.
     * @param pNumeroDeIdentidad   es el número de identidad del Veterinario.
     * @param pDireccion           es la dirección del Veterinario.
     * @param pTarjetaProfesional  es el número de tarjeta profesional del Veterinario.
     * @param pEspecialidad        es la especialidad médica del Veterinario.
     * @param pConsultasRealizadas es el total de consultas realizadas por el Veterinario.
     * @param pTelefono            es el teléfono del Veterinario.
     */
    public Veterinario(String pId,
                       String pNombre,
                       String pNumeroDeIdentidad,
                       String pDireccion,
                       String pTarjetaProfesional,
                       String pEspecialidad,
                       String pConsultasRealizadas,
                       String pTelefono) {
        super(pId, pNombre, pNumeroDeIdentidad, pDireccion);
        mTarjetaProfesional = pTarjetaProfesional;
        mEspecialidad = pEspecialidad;
        mConsultasRelizadas = pConsultasRealizadas;
        mTelefono = pTelefono;
    }

    /**
     * Constructor para crear un nuevo objeto {@link Veterinario}.
     *
     * @param pNombre              es el nombre del Veterinario.
     * @param pTarjetaProfesional  es el número de tarjeta profesional del Veterinario.
     * @param pEspecialidad        es la especialidad médica del Veterinario.
     * @param pConsultasRealizadas es el total de consultas realizadas por el Veterinario.
     */
    public Veterinario(String pNombre,
                       String pTarjetaProfesional,
                       String pEspecialidad,
                       String pConsultasRealizadas) {
        super(pNombre);
        mTarjetaProfesional = pTarjetaProfesional;
        mEspecialidad = pEspecialidad;
        mConsultasRelizadas = pConsultasRealizadas;
    }

    /**
     * Obtener el número de tarjeta profesional del Veterinario.
     */
    public String getTarjetaProfesional() {
        return mTarjetaProfesional;
    }

    /**
     * Obtener la especialidad médica del veterinario.
     */
    public String getEspecialidad() {
        return mEspecialidad;
    }

    /**
     * Obtener el total de consultas realizadas por el veterinario.
     */
    public String getConsultasRelizadas() {
        return mConsultasRelizadas;
    }

    /**
     * Obtener el teléfono del veterinario.
     */
    public String getTelefono() {
        return mTelefono;
    }
}
