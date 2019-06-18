package engineer.davidauza.veterinariavetcare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import engineer.davidauza.veterinariavetcare.R;

public class SeleccionActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    /**
     * Esta constante contiene la llave con la cual se identifica el extra que se añade al intent
     * antes de iniciar la RegistroFormularioActivity.
     */
    public static final String EXTRA_POSICION_SPINNER =
            "engineer.davidauza.veterinariavetcare.activities.ExtraPosicionSpinner";

    /**
     * Esta variable contiene el ítem que está seleccionado actualmente en el Spinner.
     */
    private int mItemSeleccionado;

    /**
     * Esta variable alamacena el valor pasado como extra en un intent antes de abrir esta activity.
     * Su valor es 0 si el botón seleccionado en la interfaz anterior fue Registrar, ó 1 si el botón
     * presionado fue consultar.
     */
    private int mBotonOrigen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion);
        // Obtener el extra que viene en el intent que abre la actividad
        mBotonOrigen = getIntent().getIntExtra(MainActivity.EXTRA_BOTON, -1);
        configurarTextoAyuda();
        configurarSpinner();
        configurarBotonContinuar();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Se seleccionó un item en el Spinner.
        mItemSeleccionado = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Por defecto está seleccionado mascota.
    }

    /**
     * Este método configura el texto de ayuda encima del Spinner, basado en el valor recibido al
     * abrir la Activity.
     */
    private void configurarTextoAyuda() {
        TextView textoAyuda = findViewById(R.id.txt_texto_ayuda);
        if (mBotonOrigen == 0) {
            textoAyuda.setText(getString(R.string.registro_registrar_txt_spinner));
        } else if (mBotonOrigen == 1) {
            textoAyuda.setText(getString(R.string.registro_consultar_txt_spinner));
        }
    }

    /**
     * Este método asocia la lista de opciones para el Spinner en la interfaz gráfica
     * activity_seleccion.xmll
     */
    private void configurarSpinner() {
        Spinner spinner = findViewById(R.id.spn_spineer);
        // Creación de ArrayAdapter usando el array de Strings y un diseño por defecto para el
        // Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.registro_spn_opciones, android.R.layout.simple_spinner_item);
        // Especificar el diseño que se usará cuando aparece la lista de opciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Aplicar el adaptador al Spinner
        spinner.setAdapter(adapter);
        // Aplicar el escucha para la selección del usuario
        spinner.setOnItemSelectedListener(SeleccionActivity.this);
    }

    /**
     * Este método configura el botón para continuar, ya sea al registro escogido ó al listado de
     * consultas, según el valor de mBotonOrigen y la opción elegida en el Spinner, la cual se
     * pasará como extra a la siguiente Activity.
     */
    private void configurarBotonContinuar() {
        Button botonContinuar = findViewById(R.id.btn_continuar);
        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if (mBotonOrigen == 0) {
                    intent = new Intent(SeleccionActivity.this,
                            RegistroFormularioActivity.class);
                } else if (mBotonOrigen == 1) {
                    intent = new Intent(SeleccionActivity.this,
                            ListadoConsultaActivity.class);
                }
                // Se envía la posición a RegistroFormularioActivity ó a
                // ListadoConsultaActivity según corresponda
                intent.putExtra(EXTRA_POSICION_SPINNER, mItemSeleccionado);
                startActivity(intent);
            }
        });
    }
}
