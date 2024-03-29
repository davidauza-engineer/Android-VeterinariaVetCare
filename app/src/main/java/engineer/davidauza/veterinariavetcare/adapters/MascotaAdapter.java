package engineer.davidauza.veterinariavetcare.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import engineer.davidauza.veterinariavetcare.R;
import engineer.davidauza.veterinariavetcare.models.Mascota;

/**
 * {@link MascotaAdapter} es un {@link RecyclerView.Adapter} que provee la interfaz gráfica para
 * cada {@link Mascota}.
 */
public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MyViewHolder> {

    /**
     * ArrayList que contiene la lista de mascotas para mostrar.
     */
    private ArrayList<Mascota> mMascotas;

    /**
     * Es el Context de ListadoConsultaActivity.
     */
    private Context mContexto;

    /**
     * Esta constante contiene el formato que se usará para mostrar la fecha al ser consultada.
     */
    private static final SimpleDateFormat FORMATO = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Constructor para crear un nuevo objeto {@link MascotaAdapter}.
     *
     * @param pMascotas es el ArrayList que contiene la lista de mascotas para mostrar.
     */
    public MascotaAdapter(ArrayList<Mascota> pMascotas, Context pContext) {
        mMascotas = pMascotas;
        mContexto = pContext;
    }

    /**
     * Crear nuevos Views, invocado por el LayoutManager.
     */
    @NonNull
    @Override
    public MascotaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Crear un nuevo View
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_mascota, viewGroup, false);
        return new MyViewHolder(view);
    }

    /**
     * Reemplaza el contenido de un View. Invocado por el LayoutManager.
     */
    @Override
    public void onBindViewHolder(@NonNull MascotaAdapter.MyViewHolder myViewHolder, int i) {
        // Obtener el elemento actual en la posición i
        Mascota mascotaActual = mMascotas.get(i);

        // Reemplazar el contenido del View con dicho elemento
        myViewHolder.mNombreMascota.setText(mascotaActual.getNombre());
        String sexo = mContexto.getString(R.string.registro_mascota_txt_sexo_femenino);
        if (mascotaActual.getSexo()) {
            sexo = mContexto.getString(R.string.registro_mascota_txt_sexo_masculino);
        }
        myViewHolder.mSexoMascota.setText(sexo);
        // Dar formato a la fecha
        String fechaDeNacimiento = FORMATO.format(mascotaActual.getFechaDeNacimiento());
        myViewHolder.mFechaDeNacimientoMascota.setText(fechaDeNacimiento);
        myViewHolder.mPadreMascota.setText(mascotaActual.getPadre().getNombre());
        myViewHolder.mMadreMascota.setText(mascotaActual.getMadre().getNombre());
        myViewHolder.mEspecieMascota.setText(mascotaActual.getEspecie().getNombre());
        myViewHolder.mRazaMascota.setText(mascotaActual.getRaza());
        myViewHolder.mDuenoActualMascota.setText(mascotaActual.getDuenoActual().getNombre());
    }

    /**
     * Retorna el tamaño del ArrayList que contiene las {@link Mascota}s. Es invocado por el LayoutManager.
     */
    @Override
    public int getItemCount() {
        return mMascotas.size();
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
         * El TextView que muestra el nombre de la {@link Mascota}.
         */
        public TextView mNombreMascota;

        /**
         * El TextView que muestra el sexo de la {@link Mascota}.
         */
        public TextView mSexoMascota;

        /**
         * El TextView que muestra la fecha de nacimiento de la {@link Mascota}.
         */
        public TextView mFechaDeNacimientoMascota;

        /**
         * El TextView que muestra el padre de la {@link Mascota}.
         */
        public TextView mPadreMascota;

        /**
         * El TextView que muestra la madre de la {@link Mascota}.
         */
        public TextView mMadreMascota;

        /**
         * El TextView que muestra la especie de la {@link Mascota}.
         */
        public TextView mEspecieMascota;

        /**
         * El TextView que muestra la raza de la {@link Mascota}.
         */
        public TextView mRazaMascota;

        /**
         * El TextView que muestra el dueño actual de la {@link Mascota}.
         */
        public TextView mDuenoActualMascota;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mNombreMascota = itemView.findViewById(R.id.txt_nombre_mascota);
            mSexoMascota = itemView.findViewById(R.id.txt_sexo_mascota);
            mFechaDeNacimientoMascota = itemView.findViewById(R.id.txt_fecha_de_nacimiento_mascota);
            mPadreMascota = itemView.findViewById(R.id.txt_padre_mascota);
            mMadreMascota = itemView.findViewById(R.id.txt_madre_mascota);
            mEspecieMascota = itemView.findViewById(R.id.txt_especie_mascota);
            mRazaMascota = itemView.findViewById(R.id.txt_raza_mascota);
            mDuenoActualMascota = itemView.findViewById(R.id.txt_dueno_actual_mascota);
        }
    }
}
