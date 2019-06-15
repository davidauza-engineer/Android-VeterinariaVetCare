package engineer.davidauza.veterinariavetcare.models;

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
     * mascotas.
     */
    public static final String URL =
            "https://davidauza-engineer.000webhostapp.com/web_service/set_mascota.php";
    /**
     * El ID único para cada mascota.
     */
    private String mId;
    /**
     * El nombre de la mascota.
     */
    private String mNombre;
    /**
     * El sexo de la mascota.
     */
    private String mSexo;
    /**
     * La fecha de nacimiento de la mascota.
     */
    private String mFechaDeNacimiento;
    /**
     * El padre de la mascota.
     */
    private String mPadre;
    /**
     * La madre de la mascota.
     */
    private String mMadre;
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
    private String mEnfermedades;
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
    private String mPropietarios;

    /**
     * Constructor para crear un nuevo objeto {@link Mascota}.
     *
     * @param pId                es el ID de la mascota.
     * @param pNomnre            es el nombre de la mascota.
     * @param pSexo              es el sexo de la mascota.
     * @param pFechaDeNacimiento es la fecha de nacimiento de la mascota.
     * @param pPadre             es el padre de la mascota.
     * @param pMadre             es la madre de la mascota.
     * @param pRaza              es la raza de la mascota.
     * @param pEspecie           es la especie de la mascota.
     * @param pEnfermedades      son las enfermedades que puede sufrir la mascota.
     * @param pConsultas         son las consultas a las que puede haber asistido la mascota.
     * @param pExamenes          son los exámenes que se le puede haber practicado a las mascota.
     * @param pTratamientos      son los tratamientos a los que pudo ser sometida la mascota.
     * @param pPropietarios      son los propietarios que ha tenido la mascota.
     */
    public Mascota(String pId,
                   String pNomnre,
                   String pSexo,
                   String pFechaDeNacimiento,
                   String pPadre,
                   String pMadre,
                   String pRaza,
                   String pEspecie,
                   String pEnfermedades,
                   String pConsultas,
                   String pExamenes,
                   String pTratamientos,
                   String pPropietarios) {
        mId = pId;
        mNombre = pNomnre;
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
     * Obtener el ID de la mascota.
     */
    public String getId() {
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
    public String getSexo() {
        return mSexo;
    }

    /**
     * Obtener la fecha de nacimiento de la mascota.
     */
    public String getFechaDeNacimiento() {
        return mFechaDeNacimiento;
    }

    /**
     * Obtener el padre de la mascota.
     */
    public String getPadre() {
        return mPadre;
    }

    /**
     * Obtener la madre de la mascota.
     */
    public String getMadre() {
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
