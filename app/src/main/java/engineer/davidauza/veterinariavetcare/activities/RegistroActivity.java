package engineer.davidauza.veterinariavetcare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import engineer.davidauza.veterinariavetcare.R;

public class RegistroActivity extends AppCompatActivity
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
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
     * Este método asocia la lista de opciones para el Spinner en la interfaz gráfica
     * activity_registro.xml
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
        spinner.setOnItemSelectedListener(RegistroActivity.this);
    }

    /**
     * Este método configura el botón para continuar al correspondiente formulario según el registro
     * que se vaya a realizar.
     */
    private void configurarBotonContinuar() {
        Button botonContinuar = findViewById(R.id.btn_continuar);
        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroActivity.this,
                        RegistroFormularioActivity.class);
                // Se envía la posición a RegistroFormularioActivity.class
                intent.putExtra(EXTRA_POSICION_SPINNER, mItemSeleccionado);
                startActivity(intent);
            }
        });
    }
}
