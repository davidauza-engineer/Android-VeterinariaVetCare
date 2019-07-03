package engineer.davidauza.veterinariavetcare.models;

import java.util.ArrayList;

/**
 * {@link Veterinario} es una {@link Persona}. Este cuenta con un número de registro profesional y
 * una o más especialidades médicas.
 */
public class Veterinario extends Persona {

    /**
     * Llave para acceder al número de registro profesional del {@link Veterinario} en la base de
     * datos.
     */
    public static final String NUMERO_DE_REGISTRO_PROFESIONAL = "numeroDeRegistroProfesional";

    /**
     * Llave para acceder a las especialidades médicas del {@link Veterinario} en la base de datos.
     */
    public static final String ESPECIALIDADES_MEDICAS = "especialidadesMedicas";

    /**
     * Contiene la URL necesaria para acceder al microservicio que permite registrar nuevos
     * {@link Veterinario}s.
     */
    public static final String URL_SET =
            "https://davidauza-engineer.000webhostapp.com/web_service/set_veterinario_final.php";

    /**
     * Contiene la URL necesaria para acceder al microservicio que permite consultar los
     * {@link Veterinario}s registrados.
     */
    public static final String URL_GET =
            "https://davidauza-engineer.000webhostapp.com/web_service/get_veterinario_final.php";

    /**
     * Arreglo de Strings que contiene las posibles especialidades médicas que un
     * {@link Veterinario} puede tener.
     */
    public static final String[] ARREGLO_ESPECIALIDADES_MEDICAS = {"Anestesiología", "Cardiología",
            "Cirugía", "Dermatología", "Fisioterapia", "Neurología", "Ninguna", "Oftalmología",
            "Oncología", "Ortopedia", "Otra"};

    /**
     * El número de registro profesional del {@link Veterinario}.
     */
    private long mNumeroDeRegistroProfesional;

    /**
     * La lista de especialidades médicas con las que cuenta el {@link Veterinario}.
     */
    private ArrayList<String> mEspecialidadesMedicas;

    /**
     * Constructor para crear un nuevo objeto {@link Veterinario}.
     *
     * @param pId                          es el ID único para el {@link Veterinario}.
     * @param pNombre                      es el nombre del {@link Veterinario}.
     * @param pApellido                    es el apellido del {@link Veterinario}.
     * @param pNumeroDeIdentidad           es el número de identidad del {@link Veterinario}.
     * @param pDireccion                   es la dirección del {@link Veterinario}.
     * @param pTelefono                    es el teléfono del {@link Veterinario}.
     * @param pNumeroDeRegistroProfesional es el número de registro profesional del
     *                                     {@link Veterinario}.
     * @param pEspecialidadesMedicas       es la lista de especialidades médicas que puede poseer el
     *                                     {@link Veterinario}.
     */
    public Veterinario(int pId,
                       String pNombre,
                       String pApellido,
                       long pNumeroDeIdentidad,
                       String pDireccion,
                       long pTelefono,
                       long pNumeroDeRegistroProfesional,
                       ArrayList<String> pEspecialidadesMedicas) {
        super(pId, pNombre, pApellido, pNumeroDeIdentidad, pDireccion, pTelefono);
        mNumeroDeRegistroProfesional = pNumeroDeRegistroProfesional;
        mEspecialidadesMedicas = pEspecialidadesMedicas;
    }

    /**
     * Constructor para crear un nuevo objeto {@link Veterinario}.
     *
     * @param pNombre                      es el nombre del {@link Veterinario}.
     * @param pNumeroDeRegistroProfesional es el número de registro profesional del
     *                                     {@link Veterinario}.
     * @param pEspecialidadesMedicas       es la lista de especialidades médicas que puede poseer el
     *                                     {@link Veterinario}.
     */
    public Veterinario(String pNombre,
                       long pNumeroDeRegistroProfesional,
                       ArrayList<String> pEspecialidadesMedicas) {
        super(pNombre);
        mNumeroDeRegistroProfesional = pNumeroDeRegistroProfesional;
        mEspecialidadesMedicas = pEspecialidadesMedicas;
    }

    /**
     * Constructor para crear un nuevo objeto {@link Veterinario}.
     *
     * @param pNombre es el nombre del {@link Veterinario}.
     */
    public Veterinario(String pNombre) {
        super(pNombre);
    }

    /**
     * Obtener el número de registro profesional del {@link Veterinario}.
     */
    public long getNumeroDeRegistroProfesional() {
        return mNumeroDeRegistroProfesional;
    }

    /**
     * Obtener la lista de especialidades médicas que puede poseer el {@link Veterinario}.
     */
    public ArrayList<String> getEspecialidadesMedicas() {
        return mEspecialidadesMedicas;
    }
}