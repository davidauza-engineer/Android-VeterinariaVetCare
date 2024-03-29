package engineer.davidauza.veterinariavetcare.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import engineer.davidauza.veterinariavetcare.R;
import engineer.davidauza.veterinariavetcare.models.Veterinario;

/**
 * {@link VeterinarioAdapter} es un {@link RecyclerView.Adapter} que provee la interfaz gráfica para
 * cada {@link Veterinario}.
 */
public class VeterinarioAdapter extends RecyclerView.Adapter<VeterinarioAdapter.MyViewHolder> {

    /**
     * ArrayList que contiene la lista de {@link Veterinario}s para mostrar.
     */
    private ArrayList<Veterinario> mVeterinarios;

    /**
     * Constructor para crear un nuevo objeto {@link VeterinarioAdapter}.
     *
     * @param pVeterinarios es el ArrayList que contiene la lista de {@link Veterinario}s para
     *                      mostrar.
     */
    public VeterinarioAdapter(ArrayList<Veterinario> pVeterinarios) {
        mVeterinarios = pVeterinarios;
    }

    /**
     * Crear nuevos Views, invocado por el LayoutManager.
     */
    @NonNull
    @Override
    public VeterinarioAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Crear un nuevo View
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_veterinario, viewGroup, false);
        return new MyViewHolder(view);
    }

    /**
     * Reemplaza el contenido de un View. Invocado por el LayoutManager.
     */
    @Override
    public void onBindViewHolder(@NonNull VeterinarioAdapter.MyViewHolder myViewHolder, int i) {
        // Obtener el elemento actual en la posición i
        Veterinario veterinarioActual = mVeterinarios.get(i);

        // Reemplazar el contenido del View con dicho elemento
        myViewHolder.mNombreVeterinario.setText(veterinarioActual.getNombre());
        myViewHolder.mDocumentoVeterinario.
                setText(Long.toString(veterinarioActual.getNumeroDeDocumento()));
        myViewHolder.mDireccionVeterinario.setText(veterinarioActual.getDireccion());
        myViewHolder.mTelefonoVeterinario.setText(Long.toString(veterinarioActual.getTelefono()));
        myViewHolder.mNumeroDeRegistroProfesional.
                setText(Long.toString(veterinarioActual.getNumeroDeRegistroProfesional()));
        String especialidades = veterinarioActual.getEspecialidadesMedicas().toString();
        especialidades = especialidades.replaceAll("[\\[\\]]", "");
        myViewHolder.mEspecialidadesVeterinario.setText(especialidades);
    }

    /**
     * Retorna el tamaño del ArrayList que contiene los {@link Veterinario}s. Es invocado por el
     * LayoutManager.
     */
    @Override
    public int getItemCount() {
        return mVeterinarios.size();
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
         * El TextView que muestra el nombre del {@link Veterinario}.
         */
        public TextView mNombreVeterinario;

        /**
         * El TextView que muestra el número de documento del {@link Veterinario}.
         */
        public TextView mDocumentoVeterinario;

        /**
         * El TextView que muestra la dirección del {@link Veterinario}.
         */
        public TextView mDireccionVeterinario;

        /**
         * El TextView que muestra el teléfono del {@link Veterinario}.
         */
        public TextView mTelefonoVeterinario;

        /**
         * El TextView que muestra el número de registro profesional del {@link Veterinario}.
         */
        public TextView mNumeroDeRegistroProfesional;

        /**
         * El TextView que muestra las especialidades del {@link Veterinario}.
         */
        public TextView mEspecialidadesVeterinario;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mNombreVeterinario = itemView.findViewById(R.id.txt_nombre_veterinario);
            mDocumentoVeterinario = itemView.findViewById(R.id.txt_documento_veterinario);
            mDireccionVeterinario = itemView.findViewById(R.id.txt_direccion_veterinario);
            mTelefonoVeterinario = itemView.findViewById(R.id.txt_telefono_veterinario);
            mNumeroDeRegistroProfesional =
                    itemView.findViewById(R.id.txt_registro_profesional_veterinario);
            mEspecialidadesVeterinario = itemView.findViewById(R.id.txt_especialidades_veterinario);
        }
    }
}
