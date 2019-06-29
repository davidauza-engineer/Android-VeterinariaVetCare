package engineer.davidauza.veterinariavetcare.models;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase representa un {@link Dueno} que es una {@link Persona}. Contiene una mapa cuya llave
 * para sus elementos es el nombre + id de cada {@link Mascota} que el {@link Dueno} posee, y
 * almacena las fechas en que se relaciona la {@link Mascota} con su {@link Dueno}.
 */
public class Dueno extends Persona {

    /**
     * Mapa que almacena las fechas en que se registra una {@link Mascota}. La llave de cada uno de
     * los elementos del mapa es el id + nombre de la {@link Mascota} registrada.
     */
    private Map<String, String> mMascotaFechaMapa;

    /**
     * Constructor para crear un nuevo objeto {@link Dueno}.
     *
     * @param pId                es el ID del {@link Dueno}.
     * @param pNombre            es el nombre del {@link Dueno}.
     * @param pApellido          es el apellido del {@link Dueno}.
     * @param pNumeroDeDocumento es el número de documento del {@link Dueno}.
     * @param pDireccion         es la dirección del {@link Dueno}.
     * @param pTelefono          es el teléfono del {@link Dueno}.
     * @param pMascota           es la {@link Mascota} que se registra junto con el {@link Dueno}.
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
        mMascotaFechaMapa = new HashMap<>();
        String llaveMascota = " " + pMascota.getId() + "-" + pMascota.getNombre() + " ";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaRegistro = " Registrada en: " + formato.format(pFechaActual) + " ";
        mMascotaFechaMapa.put(llaveMascota, fechaRegistro);
    }

    @Override
    public String toString() {
        return "ID: " + getId() +
                "\nNombre: " + getNombre() + " " + getApellido() +
                "\nDocumento: " + getNumeroDeDocumento() +
                "\nDirección: " + getDireccion() +
                "\nTeléfono: " + getTelefono() +
                "\nMascotas: " + Collections.singletonList(mMascotaFechaMapa);
    }
}
