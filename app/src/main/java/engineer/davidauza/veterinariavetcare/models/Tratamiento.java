package engineer.davidauza.veterinariavetcare.models;

/**
 * Esta clase representa un {@link Tratamiento} recetado en una {@link Consulta}. Consta de una
 * {@link Medicamento}, una dosis en mg, una frecuencia en horas, y una cantidad de días de duración
 * del {@link Tratamiento}.
 */
public class Tratamiento {

    /**
     * El {@link Medicamento} que compone el {@link Tratamiento}.
     */
    private Medicamento mMedicamento;

    /**
     * La dosis en mg del {@link Medicamento}.
     */
    private byte mDosisMg;

    /**
     * La frecuencia en horas del {@link Tratamiento}.
     */
    private byte mFrecuenciaHoras;

    /**
     * La cantidad de días de duración del {@link Tratamiento}.
     */
    private byte mCantidadDeDias;

    /**
     * Es la descripción del {@link Tratamiento} en forma de String.
     */
    private String mDescripcion;

    /**
     * Constructor para crear un nuevo objeto {@link Tratamiento}.
     *
     * @param pMedicamento     es el {@link Medicamento} recetado en la {@link Consulta}.
     * @param pDosisMg         es la dosis del {@link Medicamento} recetado en la {@link Consulta}.
     * @param pFrecuenciaHoras es la frecuencia en horas que se debe tomar el {@link Medicamento}.
     * @param pCantidadDeDias  es la cantidad de días en los que el {@link Tratamiento} debe ser
     *                         aplicado.
     */
    public Tratamiento(Medicamento pMedicamento,
                       byte pDosisMg,
                       byte pFrecuenciaHoras,
                       byte pCantidadDeDias) {
        mMedicamento = pMedicamento;
        mDosisMg = pDosisMg;
        mFrecuenciaHoras = pFrecuenciaHoras;
        mCantidadDeDias = pCantidadDeDias;
    }

    /**
     * Constructor para crear un nuevo objeto {@link Tratamiento}.
     *
     * @param pDescripcion es la descripción del {@link Tratamiento} en forma de String.
     */
    public Tratamiento(String pDescripcion) {
        mDescripcion = pDescripcion;
    }

    /**
     * Retorna el {@link Medicamento} del {@link Tratamiento}.
     */
    public Medicamento getMedicamento() {
        return mMedicamento;
    }

    /**
     * Retorna la dosis en mg recetada en el {@link Tratamiento}.
     */
    public byte getDosisMg() {
        return mDosisMg;
    }

    /**
     * Retorna la frecuencia en horas asignada en el {@link Tratamiento}.
     */
    public byte getFrecuenciaHoras() {
        return mFrecuenciaHoras;
    }

    /**
     * Retorna la cantidad de días especificada para el {@link Tratamiento}.
     */
    public byte getCantidadDeDias() {
        return mCantidadDeDias;
    }

    /**
     * Retorna la descripción del {@link Tratamiento} en forma de String.
     */
    public String getDescripcion() {
        return mDescripcion;
    }

    @Override
    public String toString() {
        int indice = getMedicamento().getIndice();
        if (indice == 22) {
            return Medicamento.NOMBRES[22];
        } else {
            return "Medicamento: " + Medicamento.NOMBRES[indice] +
                    "\nDosis en mg: " + getDosisMg() +
                    "\nFrecuencia en horas: " + getFrecuenciaHoras() +
                    "\nCantidad de días: " + getCantidadDeDias();
        }
    }
}
