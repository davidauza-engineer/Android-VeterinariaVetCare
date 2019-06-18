package engineer.davidauza.veterinariavetcare.models;

/**
 * Esta clase representa una {@link Consulta}. Una consulta tiene un ID, una fecha, un motivo, una
 * patología asociada, un veterinario que la realiza, unos exámenes asociados, unos tratamientos
 * asociados, y una mascota atendida.
 */
public class Consulta {

    /**
     * Llave para acceder al ID de la consulta en la base de datos.
     */
    public static final String ID = "id";

    /**
     * Llave para acceder a la fecha de la consulta en la base de datos.
     */
    public static final String FECHA = "fecha";

    /**
     * Llave para acceder al motivo de la consulta en la base de datos.
     */
    public static final String MOTIVO = "motivo";

    /**
     * Llave para acceder a la patología asociada en la base de datos.
     */
    public static final String PATOLOGIA_ASOCIADA = "patologiaAsociada";

    /**
     * Llave para acceder al veterinario en la base de datos.
     */
    public static final String VETERINARIO = "veterinario";

    /**
     * Llave para acceder a los examenes en la base de datos.
     */
    public static final String EXAMENES = "examenes";

    /**
     * Llave para acceder a los tratamientos en la base de datos.
     */
    public static final String TRATAMIENTOS = "tratamientos";

    /**
     * Llave para acceder a la mascota atendida en la base de datos.
     */
    public static final String MASCOTA_ATENDIDA = "mascotaAtendida";

    /**
     * Contiene la URL necesaria para acceder al microservicio que permite registrar nuevas
     * {@link Consulta}s.
     */
    public static final String URL =
            "https://davidauza-engineer.000webhostapp.com/web_service/set_consulta.php";

    /**
     * El ID de la consulta.
     */
    private String mId;

    /**
     * La fecha de la consulta.
     */
    private String mFecha;

    /**
     * El motivo de la consulta.
     */
    private String mMotivo;

    /**
     * La patología asociada a la consulta realizada.
     */
    private String mPatologiaAsociada;

    /**
     * El veterinario que atendió la consulta.
     */
    private String mVeterinario;

    /**
     * Los exámenes ordenados en la consulta.
     */
    private String mExamenes;

    /**
     * Los tratamientos ordenados en la consulta.
     */
    private String mTratamientos;

    /**
     * La mascota atendida en la consulta.
     */
    private String mMascotaAtendida;

    /**
     * Constructor para crear un nuevo objeto {@link Consulta}.
     *
     * @param pId                es el id de la consulta.
     * @param pFecha             es la fecha de la consulta.
     * @param pMotivo            es el motivo de la consulta.
     * @param pPatologiaAsociada es la patología asociada a la consulta.
     * @param pVeterinario       es el veterinario que atendió la consulta.
     * @param pExamenes          son los exámenes ordenados en la consulta.
     * @param pTratamientos      son los tratamientos ordenados en la consulta.
     * @param pMascotaAtendida   es la mascota atendida durante la consulta.
     */
    public Consulta(String pId,
                    String pFecha,
                    String pMotivo,
                    String pPatologiaAsociada,
                    String pVeterinario,
                    String pExamenes,
                    String pTratamientos,
                    String pMascotaAtendida) {
        mId = pId;
        mFecha = pFecha;
        mMotivo = pMotivo;
        mPatologiaAsociada = pPatologiaAsociada;
        mVeterinario = pVeterinario;
        mExamenes = pExamenes;
        mTratamientos = pTratamientos;
        mMascotaAtendida = pMascotaAtendida;
    }

    /**
     * Este método retorna el ID de la consulta.
     */
    public String getId() {
        return mId;
    }

    /**
     * Este método retorna la fecha de la consulta.
     */
    public String getFecha() {
        return mFecha;
    }

    /**
     * Este método retorna el motivo de la consulta.
     */
    public String getMotivo() {
        return mMotivo;
    }

    /**
     * Este método retorna la patología asociada a la consulta.
     */
    public String getPatologiaAsociada() {
        return mPatologiaAsociada;
    }

    /**
     * Este método retorna el veterinario que realizó la consulta.
     */
    public String getVeterinario() {
        return mVeterinario;
    }

    /**
     * Este método retorna los exámenes ordenados en la consulta.
     */
    public String getExamenes() {
        return mExamenes;
    }

    /**
     * Este método retorna los tratamientos ordenados en la consulta.
     */
    public String getTratamientos() {
        return mTratamientos;
    }

    /**
     * Este método retorna la mascota atendida en la consulta.
     */
    public String getMascotaAtendida() {
        return mMascotaAtendida;
    }
}
