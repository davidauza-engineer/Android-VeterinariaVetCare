package engineer.davidauza.veterinariavetcare.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase representa un {@link Dueno}  que es una {@link Persona}. Contiene una mapa que
 * almacena las {@link Mascota}s que posee, y la fecha de registro de esta.
 */
public class Dueno extends Persona {

    /**
     * Mapa que almacena las {@link Mascota}s que el {@link Dueno} posee y la fecha en que se
     * registra su pertenencia.
     */
    private Map<Mascota, Date> mascotaFechaMapa;

    /**
     * Constructor para crear un nuevo objeto {@link Dueno}.
     *
     * @param pId                es el ID del {@link Dueno}.
     * @param pNombre            es el nombre del {@link Dueno}.
     * @param pApellido          es el apellido del {@link Dueno}.
     * @param pNumeroDeDocumento es el número de documento del {@link Dueno}.
     * @param pDireccion         es la dirección del {@link Dueno}.
     * @param pTelefono          es el teléfono del {@link Dueno}.
     * @param pMascota           es la {@link Mascota} que se está registrando con el {@link Dueno}.
     * @param pFechaActual       es la fecha en la cual se está realizando el registro de la
     *                           {@link Mascota} y del {@link Dueno}.
     */
    public Dueno(int pId,
                 String pNombre,
                 String pApellido,
                 int pNumeroDeDocumento,
                 String pDireccion,
                 int pTelefono,
                 Mascota pMascota,
                 Date pFechaActual) {
        super(pId, pNombre, pApellido, pNumeroDeDocumento, pDireccion, pTelefono);
        mascotaFechaMapa = new HashMap<>();
        mascotaFechaMapa.put(pMascota, pFechaActual);
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
