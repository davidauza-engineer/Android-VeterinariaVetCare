package engineer.davidauza.veterinariavetcare.models;

public class Veterinario extends Persona {

    /**
     * Llave para acceder al número de tarjeta profesional del veterinario en la base de datos.
     */
    public static final String NUMERO_TARJETA_PROFESIONAL = "numeroDeTarjetaProfesion";

    /**
     * Llave para acceder a la especialidad médica del veterinario en la base de datos.
     */
    public static final String ESPECIALIDAD_MEDICA = "especialidadMedica";

    /**
     * Llave para acceder al número de consultas realizadas por el veterinario en la base de datos.
     */
    public static final String TOTAL_CONSULTAS_REALIZADAS = "consultasRealizadas";

    /**
     * Llave para acceder al teléfono del veterinario en la base de datos.
     */
    public static final String TELEFONO = "telefono";

    /**
     * El número de tarjeta profesional del veterinario.
     */
    private String mNumeroDeTarjetaProfesional;

    /**
     * La especialidad médica del veterinario.
     */
    private String mEspecialidadMedica;

    /**
     * El número de consultas realizadas por el veterinario;
     */
    private String mTotalConsultasRelizadas;

    /**
     * El número de teléfono del veterinario.
     */
    private String mTelefono;

    /**
     * Constructor para crear un nuevo objeto {@link Veterinario}.
     *
     * @param pId                         es el ID único para el Veterinario.
     * @param pNombre                     ese el nombre del Veterinario.
     * @param pNumeroDeIdentidad          es el número de identidad del Veterinario.
     * @param pDireccion                  es la dirección del Veterinario.
     * @param pNumeroDeTarjetaProfesional es el número de tarjeta profesional del Veterinario.
     * @param pEspecialidadMedica         es la especialidad médica del Veterinario.
     * @param pTotalConsultasRealizadas   es el total de consultas realizadas por el Veterinario.
     * @param pTelefono                   es el teléfono del Veterinario.
     */
    public Veterinario(String pId,
                       String pNombre,
                       String pNumeroDeIdentidad,
                       String pDireccion,
                       String pNumeroDeTarjetaProfesional,
                       String pEspecialidadMedica,
                       String pTotalConsultasRealizadas,
                       String pTelefono) {
        super(pId, pNombre, pNumeroDeIdentidad, pDireccion);
        mNumeroDeTarjetaProfesional = pNumeroDeTarjetaProfesional;
        mEspecialidadMedica = pEspecialidadMedica;
        mTotalConsultasRelizadas = pTotalConsultasRealizadas;
        mTelefono = pTelefono;
    }

    /**
     * Obtener el número de tarjeta profesional del Veterinario.
     */
    public String getNumeroDeTarjetaProfesional() {
        return mNumeroDeTarjetaProfesional;
    }

    /**
     * Obtener la especialidad médica del veterinario.
     */
    public String getEspecialidadMedica() {
        return mEspecialidadMedica;
    }

    /**
     * Obtener el total de consultas realizadas por el veterinario.
     */
    public String getTotalConsultasRelizadas() {
        return mTotalConsultasRelizadas;
    }

    /**
     * Obtener el teléfono del veterinario.
     */
    public String getTelefono() {
        return mTelefono;
    }
}
