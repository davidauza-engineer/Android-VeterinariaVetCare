package engineer.davidauza.veterinariavetcare.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import engineer.davidauza.veterinariavetcare.R;
import engineer.davidauza.veterinariavetcare.models.Mascota;

public class RegistroFormularioActivity extends AppCompatActivity {

    private RequestQueue mRequest;

    private StringRequest mStringRequest;

    /**
     * Esta variable almacena el objeto mascota que será registrado en la base de datos.
     */
    private Mascota mMascota;

    /**
     * Esta variable almacena la posición seleccionada en el Spinner en activity_registro.xml.
     * 0 indica que se cargue la interfaz gráfica para registrar una mascota.
     * 1 indica que se cargue la interfaz gráfica para registrar un veterinario.
     * 2 indica que se cargue la interfaz gráfica para registrar una consulta.
     */
    private int mRegistroSeleccionadoSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seleccionarInterfazGráfica();
        configurarBotonRegistrar();
        // Configurar la librería Volley que se encarga de interactuar con la base de datos
        mRequest = Volley.newRequestQueue(RegistroFormularioActivity.this);
    }

    /**
     * Este método selecciona la interfaz gráfica adecuada según la selección del usuario en
     * activity_registro.xml
     */
    private void seleccionarInterfazGráfica() {
        mRegistroSeleccionadoSpinner =
                getIntent().getIntExtra(RegistroActivity.EXTRA_POSICION_SPINNER, -1);
        switch (mRegistroSeleccionadoSpinner) {
            case 0:
                setContentView(R.layout.activity_registro_mascota);
                break;
            case 1:
                setContentView(R.layout.activity_registro_veterinario);
                break;
            case 2:
                setContentView(R.layout.activity_registro_consulta);
                break;
            default:
                finish();
                break;
        }
    }

    /**
     * Este método configura el botón Registrar para realizar el registro en la base de datos
     */
    private void configurarBotonRegistrar() {
        Button botonRegistrar = findViewById(R.id.btn_registrar);
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarEnBaseDeDatos();
            }
        });
    }

    /**
     * Este método realiza el registro en la base de datos con base en la selección que el usuario
     * realizón en activity_registro.xml
     * 0 indica que se va a registrar una mascota.
     * 1 indica que se va a registrar un veterinario.
     * 2 indica que se va a registrar una consulta.
     */
    private void registrarEnBaseDeDatos() {
        switch (mRegistroSeleccionadoSpinner) {
            case 0:
                crearMascota();
                registarMascota();
                break;
        }
    }


    /**
     * Este método crea un nuevo objeto mascota con base en los valores suministrados por el usuario
     * en la interfaz gráfica.
     */
    private void crearMascota() {
        // Crear ID para la mascota.
        String id = Double.toString(Math.random() * 100_000);
        // Obtener el nombre de la mascota
        EditText editTextNombre = findViewById(R.id.txt_nombre_mascota);
        String nombre = editTextNombre.getText().toString();
        // Obtener el sexo de la mascota
        RadioButton botonMasculino = findViewById(R.id.btn_sexo_masculino);
        RadioButton botonFemenino = findViewById(R.id.btn_sexo_femenino);
        String sexo = "";
        if (botonMasculino.isChecked()) {
            sexo = getString(R.string.registro_mascota_txt_sexo_masculino);
        } else if (botonFemenino.isChecked()) {
            sexo = getString(R.string.registro_mascota_txt_sexo_femenino);
        }
        // Obtener la fecha de nacimiento de la mascota
        DatePicker fechaDatePicker = findViewById(R.id.dte_fecha);
        int dia = fechaDatePicker.getDayOfMonth();
        int mes = fechaDatePicker.getMonth();
        int ano = fechaDatePicker.getYear();
        String fechaDeNacimiento = dia + "/" + mes + "/" + ano;
        // Obtener padre
        EditText padreEditText = findViewById(R.id.txt_padre_mascota);
        String padre = padreEditText.getText().toString();
        // Obtener madre
        EditText madreEditText = findViewById(R.id.txt_madre_mascota);
        String madre = madreEditText.getText().toString();
        // Obtener raza
        EditText razaEditText = findViewById(R.id.txt_raza_mascota);
        String raza = razaEditText.getText().toString();
        // Obtener especie
        EditText especieEditText = findViewById(R.id.txt_especie_mascota);
        String especie = especieEditText.getText().toString();
        // Obtener enfermedades
        EditText enfermedadesEditText = findViewById(R.id.txt_enfermedades_mascota);
        String enfermedades = enfermedadesEditText.getText().toString();
        // Obtener consultas
        EditText consultasEditText = findViewById(R.id.txt_consultas_mascota);
        String consultas = consultasEditText.getText().toString();
        // Obtener examentes
        EditText examentesEditText = findViewById(R.id.txt_examenes_mascota);
        String examenes = examentesEditText.getText().toString();
        // Obtener tratamientos
        EditText tratamientosEditText = findViewById(R.id.txt_tratamientos_mascota);
        String tratamientos = tratamientosEditText.getText().toString();
        // Obtener propietarios
        EditText propietariosEditText = findViewById(R.id.txt_propietarios_mascota);
        String propietarios = propietariosEditText.getText().toString();
        // Crear mascota
        mMascota = new Mascota(id, nombre, sexo, fechaDeNacimiento, padre, madre, raza, especie,
                enfermedades, consultas, examenes, tratamientos, propietarios);
    }

    /**
     * Este método realiza el registro de la mascota en la base de datos.
     */
    private void registarMascota() {
        String url = "https://davidauza-engineer.000webhostapp.com/web_service/set_mascota.php";
        mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Cuando se obtenga respuesta del servidor crear toast informando al usuario
                Toast.makeText(RegistroFormularioActivity.this,
                        getString(R.string.registro_mascota_toast_exito), Toast.LENGTH_LONG).show();
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Si se presenta algún error, imprimirlo en forma de Toast
                Toast.makeText(RegistroFormularioActivity.this,
                        getString(R.string.registro_mascota_toast_error) + " " + error,
                        Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Crear un HashMap que será enviado al servidor para hacer el registro en la base
                // de datos
                Map<String, String> parametros = new HashMap<>();
                parametros.put(Mascota.ID, mMascota.getId());
                parametros.put(Mascota.NOMBRE, mMascota.getNombre());
                parametros.put(Mascota.SEXO, mMascota.getSexo());
                parametros.put(Mascota.FECHA_DE_NACIMIENTO, mMascota.getFechaDeNacimiento());
                parametros.put(Mascota.PADRE, mMascota.getPadre());
                parametros.put(Mascota.MADRE, mMascota.getMadre());
                parametros.put(Mascota.RAZA, mMascota.getRaza());
                parametros.put(Mascota.ESPECIE, mMascota.getEspecie());
                parametros.put(Mascota.ENFERMEDADES, mMascota.getEnfermedades());
                parametros.put(Mascota.CONSULTAS, mMascota.getConsultas());
                parametros.put(Mascota.EXAMENES, mMascota.getExamenes());
                parametros.put(Mascota.TRATAMIENTOS, mMascota.getTratamientos());
                parametros.put(Mascota.PROPIETARIOS, mMascota.getPropietarios());
                return parametros;
            }
        };
        mRequest.add(mStringRequest);
    }
}
