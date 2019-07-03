package engineer.davidauza.veterinariavetcare.models;

import java.util.Date;

/**
 * Esta clase representa una {@link Consulta}. Una {@link Consulta} tiene un código, una fecha,
 * un motivo, una patología, un veterinario que la realiza, unos exámenes asociados, un
 * tratamientinto, una enfermedad crónica y una mascota atendida.
 */
public class Consulta {

    /**
     * Llave para acceder al cógigo de la {@link Consulta} en la base de datos.
     */
    public static final String CODIGO = "codigo";

    /**
     * Llave para acceder a la {@link Mascota} atendida en la {@link Consulta} en la base de datos.
     */
    public static final String MASCOTA_ATENDIDA = "mascotaAtendida";

    /**
     * Llave para acceder a la fecha de la {@link Consulta} en la base de datos.
     */
    public static final String FECHA = "fecha";

    /**
     * Llave para acceder al motivo de la {@link Consulta} en la base de datos.
     */
    public static final String MOTIVO = "motivo";

    /**
     * Llave para acceder a los examenes físicos relacionados con la {@link Consulta} en la base de
     * datos.
     */
    public static final String EXAMENES_FISICOS = "examenesFisicos";

    /**
     * Llave para acceder al {@link Veterinario} que realiza la {@link Consulta} en la base de
     * datos.
     */
    public static final String VETERINARIO = "veterinario";

    /**
     * Llave para acceder a la {@link Patologia} diagnosticada en la {@link Consulta} en la base de
     * datos.
     */
    public static final String PATOLOGIA = "patologia";

    /**
     * Llave para acceder a la {@link EnfermedadCronica} diagnosticada en la {@link Consulta} en la
     * base de datos.
     */
    public static final String ENFERMEDAD_CRONICA = "enfermedadCronica";

    /**
     * Llave para acceder al {@link Tratamiento} asociado a la {@link Consulta} en la base de datos.
     */
    public static final String TRATAMIENTO = "tratamiento";

    /**
     * Contiene la URL necesaria para acceder al microservicio que permite registrar nuevas
     * {@link Consulta}s.
     */
    public static final String URL_SET =
            "https://davidauza-engineer.000webhostapp.com/web_service/set_consulta_final.php";

    /**
     * Contiene la URL necesaria para acceder al microservicio que permite consultar las
     * {@link Consulta}s registradas.
     */
    public static final String URL_GET =
            "https://davidauza-engineer.000webhostapp.com/web_service/get_consulta.php";

    /**
     * Código que identifica la {@link Consulta}.
     */
    private int mCodigo;

    /**
     * La {@link Mascota} atendida en la {@link Consulta}.
     */
    private Mascota mMascotaAtendida;

    /**
     * La fecha de la {@link Consulta}.
     */
    private Date mFecha;

    /**
     * El motivo de la {@link Consulta}.
     */
    private String mMotivo;

    /**
     * Los exámenes físicos ordenados en la {@link Consulta}.
     */
    private String mExamenesFisicos;

    /**
     * El {@link Veterinario} que atiende la {@link Consulta}.
     */
    private Veterinario mVeterinario;

    /**
     * La {@link Patologia} diagnosticada en la {@link Consulta} realizada.
     */
    private Patologia mPatologia;

    /**
     * La {@link EnfermedadCronica} diagnosticada en la {@link Consulta} realizada.
     */
    private EnfermedadCronica mEnfermedadCronica;

    /**
     * El {@link Tratamiento} asociado a la {@link Consulta}.
     */
    private Tratamiento mTratamiento;

    /**
     * Constructor para crear un nuevo objeto {@link Consulta}.
     *
     * @param pCodigo            es el código de la {@link Consulta}.
     * @param pMascotaAtendida   es la {@link Mascota} atendida durante la {@link Consulta}.
     * @param pFecha             es la fecha de la {@link Consulta}.
     * @param pMotivo            es el motivo de la {@link Consulta}.
     * @param pExamenesFisicos   son los exámenes físicos ordenados en la {@link Consulta}.
     * @param pVeterinario       es el {@link Veterinario} que atendió la {@link Consulta}.
     * @param pPatologia         es la {@link Patologia} diagnosticada en la {@link Consulta}.
     * @param pEnfermedadCronica es la {@link EnfermedadCronica} diagnosticada en la
     *                           {@link Consulta}.
     * @param pTratamiento       es el {@link Tratamiento} asociado a la {@link Consulta}.
     */
    public Consulta(int pCodigo,
                    Mascota pMascotaAtendida,
                    Date pFecha,
                    String pMotivo,
                    String pExamenesFisicos,
                    Veterinario pVeterinario,
                    Patologia pPatologia,
                    EnfermedadCronica pEnfermedadCronica,
                    Tratamiento pTratamiento) {
        mCodigo = pCodigo;
        mMascotaAtendida = pMascotaAtendida;
        mFecha = pFecha;
        mMotivo = pMotivo;
        mExamenesFisicos = pExamenesFisicos;
        mVeterinario = pVeterinario;
        mPatologia = pPatologia;
        mEnfermedadCronica = pEnfermedadCronica;
        mTratamiento = pTratamiento;
    }

    /**
     * Constructor para crear un nuevo objeto {@link Consulta}.
     *
     * @param pFecha           es la fecha de la {@link Consulta}.
     * @param pMotivo          es el motivo de la {@link Consulta}.
     * @param pVeterinario     es el {@link Veterinario} que atendió la {@link Consulta}.
     * @param pMascotaAtendida es la {@link Mascota} atendida durante la {@link Consulta}.
     */
    public Consulta(Date pFecha,
                    String pMotivo,
                    Veterinario pVeterinario,
                    Mascota pMascotaAtendida) {
        mFecha = pFecha;
        mMotivo = pMotivo;
        mVeterinario = pVeterinario;
        mMascotaAtendida = pMascotaAtendida;
    }

    /**
     * Este método retorna el código de la {@link Consulta}.
     */
    public int getCodigo() {
        return mCodigo;
    }

    /**
     * Este método retorna la {@link Mascota} atendida en la {@link Consulta}.
     */
    public Mascota getMascotaAtendida() {
        return mMascotaAtendida;
    }

    /**
     * Este método retorna la fecha de la {@link Consulta}.
     */
    public Date getFecha() {
        return mFecha;
    }

    /**
     * Este método retorna el motivo de la {@link Consulta}.
     */
    public String getMotivo() {
        return mMotivo;
    }

    /**
     * Este método retorna los exámenes físicos ordenados en la {@link Consulta}.
     */
    public String getExamenesFisicos() {
        return mExamenesFisicos;
    }

    /**
     * Este método retorna el {@link Veterinario} que realizó la {@link Consulta}.
     */
    public Veterinario getVeterinario() {
        return mVeterinario;
    }

    /**
     * Este método retorna la {@link Patologia} diagnosticada en la {@link Consulta}.
     */
    public Patologia getPatologia() {
        return mPatologia;
    }

    /**
     * Este método retorna la {@link EnfermedadCronica} diagnosticada en la {@link Consulta}.
     */
    public EnfermedadCronica getEnfermedadCronica() {
        return mEnfermedadCronica;
    }

    /**
     * Este método retorna el {@link Tratamiento} asociado a la {@link Consulta}.
     */
    public Tratamiento getTratamiento() {
        return mTratamiento;
    }
}
