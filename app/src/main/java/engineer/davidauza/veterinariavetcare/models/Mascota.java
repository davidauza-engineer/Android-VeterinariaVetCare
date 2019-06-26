package engineer.davidauza.veterinariavetcare.models;

import java.util.Date;

/**
 * Esta clase representa una {@link Mascota}. Una mascota tiene un id, un nombre, un sexo, una fecha
 * de nacimiento, un padre, una madre, una raza, una especie, enfermedades que puede padecer,
 * consultas a las que ha asistido, exámenes que se ha realizado, tratamientos a los que se ha
 * sometido, y propietarios a los que ha pertenecido.
 */
public class Mascota {

    /**
     * Llave para acceder al ID de la mascota en la base de datos.
     */
    public static final String ID = "id";

    /**
     * Llave para acceder al nombre de la mascota en la base de datos.
     */
    public static final String NOMBRE = "nombre";

    /**
     * Llave para acceder al sexo de la mascota en la base de datos.
     */
    public static final String SEXO = "sexo";

    /**
     * Llave para acceder a la fecha de nacimiento de la mascota en la base de datos.
     */
    public static final String FECHA_DE_NACIMIENTO = "fechaDeNacimiento";

    /**
     * Llave para acceder al padre de la mascota en la base de datos.
     */
    public static final String PADRE = "padre";

    /**
     * Llave para acceder a la madre de la mascota en la base de datos.
     */
    public static final String MADRE = "madre";

    /**
     * Llave para acceder a la raza de la mascota en la base de datos.
     */
    public static final String RAZA = "raza";

    /**
     * Llave para acceder a la especie de la mascota en la base de datos.
     */
    public static final String ESPECIE = "especie";

    /**
     * Llave para acceder a las enfermedades de la mascota en la base de datos.
     */
    public static final String ENFERMEDADES = "enfermedades";

    /**
     * Llave para acceder a las consultas de la mascota en la base de datos.
     */
    public static final String CONSULTAS = "consultas";

    /**
     * Llave para acceder a los exámenes de la mascota en la base de datos.
     */
    public static final String EXAMENES = "examenes";

    /**
     * Llave para acceder a los tratamientos de la mascota en la base de datos.
     */
    public static final String TRATAMIENTOS = "tratamientos";

    /**
     * Llave para acceder a los propietarios de la mascota en la base de datos.
     */
    public static final String PROPIETARIOS = "propietarios";

    /**
     * Contiene la URL necesaria para acceder al microservicio que permite registrar nuevas
     * {@link Mascota}s.
     */
    public static final String URL_SET =
            "https://davidauza-engineer.000webhostapp.com/web_service/set_mascota.php";

    /**
     * Contiene la URL necesaria para acceder al microservicio que permite consultar las
     * {@link Mascota}s registradas.
     */
    public static final String URL_GET =
            "https://davidauza-engineer.000webhostapp.com/web_service/get_mascota.php";

    /**
     * El ID único para cada mascota.
     */
    private int mId;

    /**
     * El nombre de la mascota.
     */
    private String mNombre;

    /**
     * El sexo de la mascota.
     */
    private boolean mSexo;

    /**
     * La fecha de nacimiento de la mascota.
     */
    private Date mFechaDeNacimiento;

    /**
     * El padre de la mascota.
     */
    private Mascota mPadre;

    /**
     * La madre de la mascota.
     */
    private Mascota mMadre;

    /**
     * La raza de la mascota.
     */
    private String mRaza;

    /**
     * La especie de la mascota.
     */
    private String mEspecie;

    /**
     * Las enfermedades que puede haber sufrido la mascota.
     */
    private String mEnfermedades; // TODO añadir boolean enfermedad crónica ¿Cómo manejo la enfermedad crónica?

    /**
     * Las consultas a las que ha asistido la mascota.
     */
    private String mConsultas;

    /**
     * Los exámenes que se le han practicado a la mascota.
     */
    private String mExamenes;

    /**
     * Los tratamientos que se la han practicado a la mascota.
     */
    private String mTratamientos;

    /**
     * Los propietarios de la mascota.
     */
    private String mPropietarios; // TODO cambiar tipo a Propietario[]
    // TODO añadir miembro propietarioActual "incluso pudierar tener un dueño más de una vez"
    // TODO "Cada vez que una persona se hace cargo de la mascota se registra la fecha de paso."

    /**
     * Constructor para crear un nuevo objeto {@link Mascota}.
     *
     * @param pId                es el ID de la {@link Mascota}.
     * @param pNombre            es el nombre de la {@link Mascota}.
     * @param pSexo              es el sexo de la {@link Mascota}.
     * @param pFechaDeNacimiento es la fecha de nacimiento de la {@link Mascota}.
     * @param pPadre             es el padre de la {@link Mascota}.
     * @param pMadre             es la madre de la {@link Mascota}.
     * @param pRaza              es la raza de la {@link Mascota}.
     * @param pEspecie           es la especie de la {@link Mascota}.
     * @param pEnfermedades      son las enfermedades que puede sufrir la {@link Mascota}.
     * @param pConsultas         son las consultas a las que puede haber asistido la
     *                           {@link Mascota}.
     * @param pExamenes          son los exámenes que se le puede haber practicado a la
     *                           {@link Mascota}.
     * @param pTratamientos      son los tratamientos a los que pudo ser sometida la
     *                           {@link Mascota}.
     * @param pPropietarios      son los propietarios que ha tenido la {@link Mascota}.
     */
    public Mascota(int pId,
                   String pNombre,
                   boolean pSexo,
                   Date pFechaDeNacimiento,
                   Mascota pPadre,
                   Mascota pMadre,
                   String pRaza,
                   String pEspecie,
                   String pEnfermedades,
                   String pConsultas,
                   String pExamenes,
                   String pTratamientos,
                   String pPropietarios) {
        mId = pId;
        mNombre = pNombre;
        mSexo = pSexo;
        mFechaDeNacimiento = pFechaDeNacimiento;
        mPadre = pPadre;
        mMadre = pMadre;
        mRaza = pRaza;
        mEspecie = pEspecie;
        mEnfermedades = pEnfermedades;
        mConsultas = pConsultas;
        mExamenes = pExamenes;
        mTratamientos = pTratamientos;
        mPropietarios = pPropietarios;
    }

    /**
     * Constructor para crear un nuevo objeto {@link Mascota}.
     *
     * @param pNombre            es el nombre de la {@link Mascota}.
     * @param pFechaDeNacimiento es la fecha de nacimiento de la {@link Mascota}.
     * @param pSexo              es el sexo de la {@link Mascota}.
     * @param pEspecie           es la especie de la {@link Mascota}.
     */
    public Mascota(String pNombre,
                   Date pFechaDeNacimiento,
                   boolean pSexo,
                   String pEspecie) {
        mNombre = pNombre;
        mFechaDeNacimiento = pFechaDeNacimiento;
        mSexo = pSexo;
        mEspecie = pEspecie;
    }

    /**
     * Constructor para crear un nuevo objeto {@link Mascota}.
     *
     * @param pNombre es el nombre de la {@link Mascota}.
     */
    public Mascota(String pNombre) {
        mNombre = pNombre;
    }

    /**
     * Obtener el ID de la mascota.
     */
    public int getId() {
        return mId;
    }

    /**
     * Obtener el nombre de la mascota.
     */
    public String getNombre() {
        return mNombre;
    }

    /**
     * Obtener el sexo de la mascota.
     */
    public boolean getSexo() {
        return mSexo;
    }

    /**
     * Obtener la fecha de nacimiento de la mascota.
     */
    public Date getFechaDeNacimiento() {
        return mFechaDeNacimiento;
    }

    /**
     * Obtener el padre de la mascota.
     */
    public Mascota getPadre() {
        return mPadre;
    }

    /**
     * Obtener la madre de la mascota.
     */
    public Mascota getMadre() {
        return mMadre;
    }

    /**
     * Obtener la raza de la mascota.
     */
    public String getRaza() {
        return mRaza;
    }

    /**
     * Obtener la especie de la mascota.
     */
    public String getEspecie() {
        return mEspecie;
    }

    /**
     * Obtener las enfermedades de la mascota.
     */
    public String getEnfermedades() {
        return mEnfermedades;
    }

    /**
     * Obtener las consultas de la mascota.
     */
    public String getConsultas() {
        return mConsultas;
    }

    /**
     * Obtener los exámenes de la mascota.
     */
    public String getExamenes() {
        return mExamenes;
    }

    /**
     * Obtener los tratamientos de la mascota.
     */
    public String getTratamientos() {
        return mTratamientos;
    }

    /**
     * Obtener los propietarios de la mascota.
     */
    public String getPropietarios() {
        return mPropietarios;
    }
}
