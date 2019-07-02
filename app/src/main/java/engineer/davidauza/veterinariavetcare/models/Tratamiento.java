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
