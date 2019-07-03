package engineer.davidauza.veterinariavetcare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import engineer.davidauza.veterinariavetcare.R;
import engineer.davidauza.veterinariavetcare.models.Consulta;

/**
 * Esta Activity es responsable de mostrar la interfaz gráfica principal.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Esta constante contiene la llave con la cual se identifica el extra que se añade al intent
     * antes de iniciar la SeleccionActivity.
     */
    public static final String EXTRA_BOTON =
            "engineer.davidauza.veterinariavetcare.activities.ExtraBoton";

    /**
     * Contiene la referencia hacia el botón registrar.
     */
    private Button mBotonRegistrar;

    /**
     * Contiene la referencia hacia el botón consultar.
     */
    private Button mBotonConsultar;

    /**
     * Contiene la referencia hacia el botón reporte consultas.
     */
    private Button mBotonReporteConsultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configurarBotones();
    }

    /**
     * Este método configura el escucha que usarán botones de la interfaz para iniciar la
     * SeleccionActivity, donde el usuario selecciona qué tipo de registro/consulta desea realizar.
     * Se pone un extra con valor 0 si el botón presionado fue Registrar, o de 1 si el botón
     * presionado fue Consultar. Dicho extra es obtenido en la SeleccionActivity.
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, SeleccionActivity.class);
        if (v == mBotonRegistrar) {
            intent.putExtra(EXTRA_BOTON, 0);
        } else if (v == mBotonConsultar) {
            intent.putExtra(EXTRA_BOTON, 1);
        }
        startActivity(intent);
    }

    /**
     * Este métoodo configura los botones de la interfaz gráfica.
     */
    private void configurarBotones() {
        mBotonRegistrar = findViewById(R.id.btn_registrar);
        mBotonRegistrar.setOnClickListener(this);
        mBotonConsultar = findViewById(R.id.btn_consultar);
        mBotonConsultar.setOnClickListener(this);
        mBotonReporteConsultas = findViewById(R.id.btn_reporte_consultas);
        mBotonReporteConsultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        ListadoConsultaActivity.class);
                intent.putExtra(SeleccionActivity.EXTRA_POSICION_SPINNER, Consulta.KEY);
                startActivity(intent);
            }
        });
    }
}
