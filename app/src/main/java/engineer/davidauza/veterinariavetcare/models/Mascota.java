package engineer.davidauza.veterinariavetcare.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Esta clase representa una {@link Mascota}. Una mascota tiene un id, un nombre, un sexo, una fecha
 * de nacimiento, un padre, una madre, una raza, una especie, enfermedades que puede padecer,
 * consultas a las que ha asistido, exámenes que se ha realizado, tratamientos a los que se ha
 * sometido, y propietarios a los que ha pertenecido.
 */
public class Mascota {

    /**
     * Llave para acceder al ID de la {@link Mascota} en la base de datos.
     */
    public static final String ID = "id";

    /**
     * Llave para acceder al nombre de la {@link Mascota} en la base de datos.
     */
    public static final String NOMBRE = "nombre";

    /**
     * Llave para acceder al sexo de la {@link Mascota} en la base de datos.
     */
    public static final String SEXO = "sexo";

    /**
     * Llave para acceder a la fecha de nacimiento de la {@link Mascota} en la base de datos.
     */
    public static final String FECHA_DE_NACIMIENTO = "fechaDeNacimiento";

    /**
     * Llave para acceder al padre de la {@link Mascota} en la base de datos.
     */
    public static final String PADRE = "padre";

    /**
     * Llave para acceder a la madre de la {@link Mascota} en la base de datos.
     */
    public static final String MADRE = "madre";

    /**
     * Llave para acceder a la especie de la {@link Mascota} en la base de datos.
     */
    public static final String ESPECIE = "especie";

    /**
     * Llave para acceder a la raza de la {@link Mascota} en la base de datos.
     */
    public static final String RAZA = "raza";

    /**
     * Llave para acceder a la lista de dueños de la {@link Mascota} en la base de datos.
     */
    public static final String DUENOS = "duenos";

    /**
     * LLave para acceder al dueño actual de la {@link Mascota} en la base de datos.
     */
    public static final String DUENO_ACTUAL = "duenoActual";

    /**
     * Contiene la URL necesaria para acceder al microservicio que permite registrar nuevas
     * {@link Mascota}s.
     */
    public static final String URL_SET =
            "https://davidauza-engineer.000webhostapp.com/web_service/set_mascota_final.php";

    /**
     * Contiene la URL necesaria para acceder al microservicio que permite consultar las
     * {@link Mascota}s registradas.
     */
    public static final String URL_GET =
            "https://davidauza-engineer.000webhostapp.com/web_service/get_mascota.php";

    /**
     * El ID único para cada {@link Mascota}.
     */
    private int mId;

    /**
     * El nombre de la {@link Mascota}.
     */
    private String mNombre;

    /**
     * El sexo de la {@link Mascota}.
     */
    private boolean mSexo;

    /**
     * La fecha de nacimiento de la {@link Mascota}.
     */
    private Date mFechaDeNacimiento;

    /**
     * El padre de la {@link Mascota}.
     */
    private Mascota mPadre;

    /**
     * La madre de la {@link Mascota}.
     */
    private Mascota mMadre;

    /**
     * La {@link Especie} de la {@link Mascota}.
     */
    private Especie mEspecie;

    /**
     * La raza de la {@link Mascota}.
     */
    private String mRaza;

    /**
     * Un ArrayList que contiene la lista de dueños de la {@link Mascota}.
     */
    private ArrayList<Dueno> mDuenos;

    /**
     * El Dueño actual de la {@link Mascota}.
     */
    private Dueno mDuenoActual;

    /**
     * Constructor para crear un nuevo objeto {@link Mascota}.
     *
     * @param pId                es el ID de la {@link Mascota}.
     * @param pNombre            es el nombre de la {@link Mascota}.
     * @param pSexo              es el sexo de la {@link Mascota}.
     * @param pFechaDeNacimiento es la fecha de nacimiento de la {@link Mascota}.
     * @param pPadre             es el padre de la {@link Mascota}.
     * @param pMadre             es la madre de la {@link Mascota}.
     * @param pEspecie           es la {@link Especie} de la {@link Mascota}.
     * @param pRaza              es la raza de la {@link Mascota}.
     */
    public Mascota(int pId,
                   String pNombre,
                   boolean pSexo,
                   Date pFechaDeNacimiento,
                   Mascota pPadre,
                   Mascota pMadre,
                   Especie pEspecie,
                   String pRaza) {
        mId = pId;
        mNombre = pNombre;
        mSexo = pSexo;
        mFechaDeNacimiento = pFechaDeNacimiento;
        mPadre = pPadre;
        mMadre = pMadre;
        mEspecie = pEspecie;
        mRaza = pRaza;
    }

    /**
     * Constructor para crear un nuevo objeto {@link Mascota}.
     *
     * @param pNombre            es el nombre de la {@link Mascota}.
     * @param pFechaDeNacimiento es la fecha de nacimiento de la {@link Mascota}.
     * @param pSexo              es el sexo de la {@link Mascota}.
     * @param pEspecie           es la {@link Especie} de la {@link Mascota}.
     */
    public Mascota(String pNombre,
                   Date pFechaDeNacimiento,
                   boolean pSexo,
                   Especie pEspecie) {
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
     * Obtener el ID de la {@link Mascota}.
     */
    public int getId() {
        return mId;
    }

    /**
     * Obtener el nombre de la {@link Mascota}.
     */
    public String getNombre() {
        return mNombre;
    }

    /**
     * Obtener el sexo de la {@link Mascota}.
     */
    public boolean getSexo() {
        return mSexo;
    }

    /**
     * Obtener la fecha de nacimiento de la {@link Mascota}.
     */
    public Date getFechaDeNacimiento() {
        return mFechaDeNacimiento;
    }

    /**
     * Obtener el padre de la {@link Mascota}.
     */
    public Mascota getPadre() {
        return mPadre;
    }

    /**
     * Obtener la madre de la {@link Mascota}.
     */
    public Mascota getMadre() {
        return mMadre;
    }

    /**
     * Obtener la {@link Especie} de la {@link Mascota}.
     */
    public Especie getEspecie() {
        return mEspecie;
    }

    /**
     * Obtener la raza de la {@link Mascota}.
     */
    public String getRaza() {
        return mRaza;
    }

    /**
     * Obtener la lista de dueños de la {@link Mascota}.
     */
    public ArrayList<Dueno> getDuenos() {
        return mDuenos;
    }

    /**
     * Obtener el dueño actual de la {@link Mascota}.
     */
    public Dueno getDuenoActual() {
        return mDuenoActual;
    }

    /**
     * Este método configura los dueños de la  {@link Mascota}.
     *
     * @param pDuenos es la lista de dueños de la {@link Mascota}.
     */
    public void setDuenos(ArrayList<Dueno> pDuenos) {
        mDuenos = pDuenos;
        mDuenoActual = mDuenos.get(mDuenos.size() - 1);
    }
}
