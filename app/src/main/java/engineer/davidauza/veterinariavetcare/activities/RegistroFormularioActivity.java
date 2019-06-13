package engineer.davidauza.veterinariavetcare.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import engineer.davidauza.veterinariavetcare.R;

public class RegistroFormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configurarInterfazGráfica();
    }

    /**
     * Este método selecciona la interfaz gráfica adecuada según la selección del usuario en
     * activity_registro.xml
     */
    private void configurarInterfazGráfica() {
        int registroSeleccionadoSpinner =
                getIntent().getIntExtra(RegistroActivity.EXTRA_POSICION_SPINNER, -1);
        switch (registroSeleccionadoSpinner) {
            case 0:
                setContentView(R.layout.activity_registro_mascota);
                break;
            case 1:
                setContentView(R.layout.activity_registro_veterinario);
                break;
            default:
                finish();
                break;
        }
    }
}
