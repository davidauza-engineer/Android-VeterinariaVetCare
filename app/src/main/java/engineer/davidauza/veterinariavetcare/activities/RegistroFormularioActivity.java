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
import engineer.davidauza.veterinariavetcare.models.Consulta;
import engineer.davidauza.veterinariavetcare.models.Mascota;
import engineer.davidauza.veterinariavetcare.models.Veterinario;

public class RegistroFormularioActivity extends AppCompatActivity {

    private RequestQueue mRequest;

    /**
     * Esta variable almacena el objeto {@link Mascota} que será registrado en la base de datos.
     */
    private Mascota mMascota;

    /**
     * Esta variable almacena el objeto {@link Veterinario} que será registrado en la base de datos.
     */
    private Veterinario mVeterinario;

    /**
     * Esta variable almacena el objeto {@link Consulta} que será registrado en la base de datos.
     */
    private Consulta mConsulta;

    /**
     * Esta variable almacena la posición seleccionada en el Spinner en activity_seleccionn.xml.
     * 0 indica que se cargue la interfaz gráfica para registrar una {@link Mascota}.
     * 1 indica que se cargue la interfaz gráfica para registrar un {@link Veterinario}.
     * 2 indica que se cargue la interfaz gráfica para registrar una {@link Consulta}.
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
     * activity_seleccionn.xml
     */
    private void seleccionarInterfazGráfica() {
        mRegistroSeleccionadoSpinner =
                getIntent().getIntExtra(SeleccionActivity.EXTRA_POSICION_SPINNER, -1);
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
     * Este método hace el registro pertinente en la base de datos, según el valor que contiene
     * mRegistroSeleccionadoSpinner. Dicho valor es provisto por el usuario en activity_seleccion.xmll
     * Si es 0 se registrará una {@link Mascota}, si es 1 se registrará un {@link Veterinario}, y si
     * es 2 se registrará una {@link Consulta}.
     */
    private void registrarEnBaseDeDatos() {
        String url = "";
        String toastExito = "";
        String toastError = "";
        // Crear un Map que será enviado al servidor para hacer el registro en la base de datos
        Map<String, String> parametros = null;
        switch (mRegistroSeleccionadoSpinner) {
            case 0:
                crearMascota();
                url = Mascota.URL_SET;
                toastExito = getString(R.string.registro_mascota_toast_exito);
                toastError = getString(R.string.registro_mascota_toast_error);
                parametros = crearParametrosMascota();
                break;
            case 1:
                crearVeterinario();
                url = Veterinario.URL_SET;
                toastExito = getString(R.string.registro_veterinario_toast_exito);
                toastError = getString(R.string.registro_veterinario_toast_error);
                parametros = crearParametrosVeterinario();
                break;
            case 2:
                crearConsulta();
                url = Consulta.URL_SET;
                toastExito = getString(R.string.registro_consulta_toast_exito);
                toastError = getString(R.string.registro_consulta_toast_error);
                parametros = crearParametrosConsulta();
                break;
        }
        final String toastExitoFinal = toastExito;
        final String toastErrorFinal = toastError;
        final Map<String, String> parametrosFinal = parametros;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Cuando se obtenga respuesta del servidor crear toast informando al
                        // usuario
                        Toast.makeText(RegistroFormularioActivity.this, toastExitoFinal,
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Si se presenta algún error, imprimirlo en forma de Toast
                Toast.makeText(RegistroFormularioActivity.this,
                        toastErrorFinal + " " + error, Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return parametrosFinal;
            }
        };
        mRequest.add(stringRequest);
    }


    /**
     * Este método crea un nuevo objeto {@link Mascota} con base en los valores suministrados por el
     * usuario en la interfaz gráfica.
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
        String fechaDeNacimiento = obtenerFecha(R.id.dte_fecha);
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
     * Este método crea un Map que contiene los valores para ser enviados al servidor y
     * posteriormente a la base de datos, para el caso de las {@link Mascota}s.
     */
    private Map<String, String> crearParametrosMascota() {
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

    /**
     * Este método crea un nuevo objeto {@link Veterinario} con base en los valores suministrados
     * por el usuario en la interfaz gráfica.
     */
    private void crearVeterinario() {
        // Crear ID para el Veterinario
        String id = Double.toString(Math.random() * 1_000);
        // Obtener nombre del Veterinario
        EditText nombreEditText = findViewById(R.id.txt_nombre_veterinario);
        String nombre = nombreEditText.getText().toString();
        // Obtener el número de identidad del Veterinario
        EditText numeroDeIdentidadEditText = findViewById(R.id.txt_numero_identidad_veterinario);
        String numeroDeIdentidad = numeroDeIdentidadEditText.getText().toString();
        // Obtener dirección del Veterinario
        EditText direccionEditText = findViewById(R.id.txt_direccion_veterinario);
        String direccion = direccionEditText.getText().toString();
        // Obtener el número de tarjeta profesional del Veterinario
        EditText tarjetaProfesionalEditText =
                findViewById(R.id.txt_tarjeta_profesional_veterinario);
        String tarjetaProfesional = tarjetaProfesionalEditText.getText().toString();
        // Obtener especialidad del Veterinario
        EditText especialidadEditText = findViewById(R.id.txt_especialidad_veterinario);
        String especialidad = especialidadEditText.getText().toString();
        // Obtener cosultas realizadas por el Veterinario
        EditText consultasRealizadasEditText =
                findViewById(R.id.txt_consultas_realizadas_veterinario);
        String consultasRealizadas = consultasRealizadasEditText.getText().toString();
        // Obtener teléfono Veterinario
        EditText telefonoEditText = findViewById(R.id.txt_telefono_veterinario);
        String telefono = telefonoEditText.getText().toString();
        // Crear Veterinario
        mVeterinario = new Veterinario(id, nombre, numeroDeIdentidad, direccion, tarjetaProfesional,
                especialidad, consultasRealizadas, telefono);
    }

    /**
     * Este método crea un Map que contiene los valores para ser enviados al servidor y
     * posteriormente a la base de datos, para el caso de los {@link Veterinario}s.
     */
    private Map<String, String> crearParametrosVeterinario() {
        Map<String, String> parametros = new HashMap<>();
        parametros.put(Veterinario.ID, mVeterinario.getId());
        parametros.put(Veterinario.NOMBRE, mVeterinario.getNombre());
        parametros.put(Veterinario.NUMERO_DE_IDENTIDAD, mVeterinario.getNumeroDeIdentidad());
        parametros.put(Veterinario.DIRECCION, mVeterinario.getDireccion());
        parametros.put(Veterinario.TARJETA_PROFESIONAL, mVeterinario.getTarjetaProfesional());
        parametros.put(Veterinario.ESPECIALIDAD, mVeterinario.getEspecialidad());
        parametros.put(Veterinario.CONSULTAS_REALIZADAS, mVeterinario.getConsultasRelizadas());
        parametros.put(Veterinario.TELEFONO, mVeterinario.getTelefono());
        return parametros;
    }

    /**
     * Este método crea un nuevo objeto {@link Consulta} con base en los valores suministrados por
     * el usuario en la interfaz gráfica.
     */
    private void crearConsulta() {
        // Crear ID para la consulta
        String id = Double.toString(Math.random() * 1_000_000);
        // Obtener fecha
        String fecha = obtenerFecha(R.id.dte_fecha);
        // Obtener motivo
        EditText motivoEditText = findViewById(R.id.txt_motivo_consulta);
        String motivo = motivoEditText.getText().toString();
        // Obtener patología asociada.
        EditText patologiaAsociadaEditText = findViewById(R.id.txt_patologia_asociada_consulta);
        String patologiaAsociada = patologiaAsociadaEditText.getText().toString();
        // Obtener veterinario
        EditText veterinarioEditText = findViewById(R.id.txt_veterinario_consulta);
        String veterinario = veterinarioEditText.getText().toString();
        // Obtener exámenes
        EditText examenesEditText = findViewById(R.id.txt_examenes_consulta);
        String examenes = examenesEditText.getText().toString();
        // Obtener tratamientos
        EditText tratamientosEditText = findViewById(R.id.txt_tratamientos_consulta);
        String tratamientos = tratamientosEditText.getText().toString();
        // Obtener mascotaAtendida
        EditText mascotaAtenidaEditText = findViewById(R.id.txt_mascota_atendida_consulta);
        String mascotaAtendida = mascotaAtenidaEditText.getText().toString();
        // Crear Consulta
        mConsulta = new Consulta(id, fecha, motivo, patologiaAsociada, veterinario, examenes,
                tratamientos, mascotaAtendida);
    }

    /**
     * Este método crea un Map que contiene los valores para ser enviados al servidor y
     * posteriormente a la base de datos, para el caso de las {@link Consulta}s.
     */
    private Map<String, String> crearParametrosConsulta() {
        Map<String, String> parametros = new HashMap<>();
        parametros.put(Consulta.ID, mConsulta.getId());
        parametros.put(Consulta.FECHA, mConsulta.getFecha());
        parametros.put(Consulta.MOTIVO, mConsulta.getMotivo());
        parametros.put(Consulta.PATOLOGIA_ASOCIADA, mConsulta.getPatologiaAsociada());
        parametros.put(Consulta.VETERINARIO, mConsulta.getVeterinario());
        parametros.put(Consulta.EXAMENES, mConsulta.getExamenes());
        parametros.put(Consulta.TRATAMIENTOS, mConsulta.getTratamientos());
        parametros.put(Consulta.MASCOTA_ATENDIDA, mConsulta.getMascotaAtendida());
        return parametros;
    }

    /**
     * Este método retorna un String con la fecha presente en el DatePicke indicado, en el formato
     * dia/mes/año.
     *
     * @param pDatePicker es el DatePicker de donde se obtiene la fecha.
     * @return retorna un String en el formato dia/mes/año.
     */
    private String obtenerFecha(int pDatePicker) {
        DatePicker datePicker = findViewById(pDatePicker);
        int dia = datePicker.getDayOfMonth();
        // Se añada 1, puesto que el DatePicker retorna el índice del mes
        int mes = datePicker.getMonth() + 1;
        int ano = datePicker.getYear();
        return dia + "/" + mes + "/" + ano;
    }
}
