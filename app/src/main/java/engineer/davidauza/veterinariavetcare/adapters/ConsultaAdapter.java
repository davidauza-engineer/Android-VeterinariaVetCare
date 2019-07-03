package engineer.davidauza.veterinariavetcare.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import engineer.davidauza.veterinariavetcare.R;
import engineer.davidauza.veterinariavetcare.models.Consulta;
import engineer.davidauza.veterinariavetcare.models.Veterinario;

/**
 * {@link ConsultaAdapter} es un {@link RecyclerView.Adapter} que provee la interfaz gráfica para
 * cada {@link Consulta}.
 */
public class ConsultaAdapter extends RecyclerView.Adapter<ConsultaAdapter.MyViewHolder> {

    /**
     * ArrayList que contiene la lista de {@link Consulta}s para mostrar.
     */
    private ArrayList<Consulta> mConsultas;

    /**
     * Constructor para crear un nuevo objeto {@link ConsultaAdapter}.
     *
     * @param pConsultas es el ArrayList que contiene la lista de {@link Consulta}s para
     *                   mostrar.
     */
    public ConsultaAdapter(ArrayList<Consulta> pConsultas) {
        mConsultas = pConsultas;
    }

    /**
     * Esta constante contiene el formato que se usará para mostrar la fecha al ser consultada.
     */
    private static final SimpleDateFormat FORMATO = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Crear nuevos Views, invocado por el LayoutManager.
     */
    @NonNull
    @Override
    public ConsultaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Crear un nuevo View
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_consulta, viewGroup, false);
        return new MyViewHolder(view);
    }

    /**
     * Reemplaza el contenido de un View. Invocado por el LayoutManager.
     */
    @Override
    public void onBindViewHolder(@NonNull ConsultaAdapter.MyViewHolder myViewHolder, int i) {
        // Obtener el elemento actual en la posición i
        Consulta consultaActual = mConsultas.get(i);

        // Reemplazar el contenido del View con dicho elemento
        myViewHolder.mCodigo.setText(Integer.toString(consultaActual.getCodigo()));
        myViewHolder.mMascotaAtendida.setText(consultaActual.getMascotaAtendida().getNombre());
        // Dar formato a la fecha
        String fecha = FORMATO.format(consultaActual.getFecha());
        myViewHolder.mFechaConsulta.setText(fecha);
        myViewHolder.mMotivoConsulta.setText(consultaActual.getMotivo());
        myViewHolder.mExamenes.setText(consultaActual.getExamenesFisicos());
        myViewHolder.mVeterinarioConsulta.setText(consultaActual.getVeterinario().getNombre());
        myViewHolder.mPatologia.setText(consultaActual.getPatologia().getDescripcion());
        myViewHolder.mEnfermedadCronica.
                setText(consultaActual.getEnfermedadCronica().getDescripcion());
        myViewHolder.mTratamiento.setText(consultaActual.getTratamiento().getDescripcion());
    }

    /**
     * Retorna el tamaño del ArrayList que contiene las {@link Consulta}s. Es invocado por el
     * LayoutManager.
     */
    @Override
    public int getItemCount() {
        return mConsultas.size();
    }

    /**
     * Proveer una referencia a los Views para cada ítem.
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        /**
         * El LinearLayout que contiene la interfaz gráfica de cada ítem de la lista
         */
        public View mView;

        /**
         * El TextView que muestra el código de la {@link Consulta}.
         */
        public TextView mCodigo;

        /**
         * El TextView que muestra la mascota atendida en la {@link Consulta}.
         */
        public TextView mMascotaAtendida;

        /**
         * El TextView que muestra la fecha de la {@link Consulta}.
         */
        public TextView mFechaConsulta;

        /**
         * El TextView que muestra el motivo de la {@link Consulta}.
         */
        public TextView mMotivoConsulta;

        /**
         * El TextView que muestra los exámenes físicos de la {@link Consulta}.
         */
        public TextView mExamenes;

        /**
         * El TextView que muestra el {@link Veterinario} que atendió la {@link Consulta}.
         */
        public TextView mVeterinarioConsulta;

        /**
         * El TextView que muestra la patología diagnosticada en la {@link Consulta}.
         */
        public TextView mPatologia;

        /**
         * El TextView que muestra la enfermedad crónica diagnosticada en la {@link Consulta}.
         */
        public TextView mEnfermedadCronica;

        /**
         * El TextView que muestra el tratamiento recetado en la {@link Consulta}.
         */
        public TextView mTratamiento;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mCodigo = itemView.findViewById(R.id.txt_codigo_consulta);
            mMascotaAtendida = itemView.findViewById(R.id.txt_mascota_atendida_consulta);
            mFechaConsulta = itemView.findViewById(R.id.txt_fecha_consulta);
            mMotivoConsulta = itemView.findViewById(R.id.txt_motivo_consulta);
            mExamenes = itemView.findViewById(R.id.txt_examenes_consulta);
            mVeterinarioConsulta = itemView.findViewById(R.id.txt_veterinario_consulta);
            mPatologia = itemView.findViewById(R.id.txt_patologia_consulta);
            mEnfermedadCronica = itemView.findViewById(R.id.txt_enfermedad_cronica_consulta);
            mTratamiento = itemView.findViewById(R.id.txt_tratamiento_consulta);
        }
    }
}
