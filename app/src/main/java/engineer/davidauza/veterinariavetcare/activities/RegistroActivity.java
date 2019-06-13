package engineer.davidauza.veterinariavetcare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import engineer.davidauza.veterinariavetcare.R;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        configurarSpinner();
        configurarBotonContinuar();
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
    }

    /**
     * Este método configura el botón para continuar al correspondiente formulario según el registro
     * que se vaya a realizar. //TODO validar esta descripción y método
     */
    private void configurarBotonContinuar() {
        Button botonContinuar = findViewById(R.id.btn_continuar);
        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroActivity.this,
                        RegistroFormularioActivity.class);
                startActivity(intent);
            }
        });
    }
}
