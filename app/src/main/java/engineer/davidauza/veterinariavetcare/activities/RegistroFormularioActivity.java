package engineer.davidauza.veterinariavetcare.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import engineer.davidauza.veterinariavetcare.R;
import engineer.davidauza.veterinariavetcare.models.Consulta;
import engineer.davidauza.veterinariavetcare.models.Mascota;
import engineer.davidauza.veterinariavetcare.models.Veterinario;

/**
 * Esta Activity es responsable de mostrar la interfaz gráfica según la elección del usuario en
 * activity_seleccion.xml y de enviar al servidor los datos que el usuario registre en dicha intefaz
 * gráfica.
 */
public class RegistroFormularioActivity extends AppCompatActivity
        implements Response.Listener<JSONArray>, Response.ErrorListener {

    private RequestQueue mRequestRegistrar;

    private RequestQueue mRequestCargar;

    /**
     * El ArrayList que almacena el nombre de las {@link Mascota}s macho (potencialmente padres)
     * almacenadas en la base de datos.
     */
    private ArrayList<String> mMascotasPadre = new ArrayList<>();

    /**
     * El ArrayList que almacena el nombre de las {@link Mascota}s hembra (potencialmente madres)
     * almacenadas en la base de datos.
     */
    private ArrayList<String> mMascotasMadre = new ArrayList<>();

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
        configurarInterfazGrafica();
        configurarBotonRegistrar();
        // Configurar la librería Volley que se encarga de interactuar con la base de datos
        mRequestRegistrar = Volley.newRequestQueue(RegistroFormularioActivity.this);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // En caso de error al cargar las mascotas padre o madre de la base de datos, informar al
        // usuario a través de un Toast.
        Toast.makeText(RegistroFormularioActivity.this,
                getString(R.string.registro_mascota_toast_error_carga), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONArray response) {
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject jsonObject = response.getJSONObject(i);
                String sexoMascota = jsonObject.optString("sexo");
                // Si las mascotas obtenidas de la base de datos son machos, añadirlos a la lista de
                // padres. Si son hembras, a la lista de madres.
                if (sexoMascota.equals(getString(R.string.registro_mascota_txt_sexo_masculino))) {
                    mMascotasPadre.add(jsonObject.optString("nombre"));
                } else if (sexoMascota.
                        equals(getString(R.string.registro_mascota_txt_sexo_femenino))) {
                    mMascotasMadre.add(jsonObject.optString("nombre"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método configura la interfaz gráfica adecuada según la selección del usuario en
     * activity_seleccion.xml
     */
    private void configurarInterfazGrafica() {
        mRegistroSeleccionadoSpinner =
                getIntent().getIntExtra(SeleccionActivity.EXTRA_POSICION_SPINNER, -1);
        switch (mRegistroSeleccionadoSpinner) {
            case 0:
                setContentView(R.layout.activity_registro_mascota);
                configurarSpinner(R.id.spn_padre_mascota);
                configurarSpinner(R.id.spn_madre_mascota);
                cargarMascotas();
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
     * mRegistroSeleccionadoSpinner. Dicho valor es provisto por el usuario en
     * activity_seleccion.xml. Si es 0 se registrará una {@link Mascota}, si es 1 se registrará un
     * {@link Veterinario}, y si es 2 se registrará una {@link Consulta}.
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
        mRequestRegistrar.add(stringRequest);
    }


    /**
     * Este método crea un nuevo objeto {@link Mascota} con base en los valores suministrados por el
     * usuario en la interfaz gráfica.
     */
    private void crearMascota() {
        // Crear ID para la mascota.
        int id = (int) (Math.random() * 100_000);
        // Obtener el nombre de la mascota
        EditText editTextNombre = findViewById(R.id.txt_nombre_mascota);
        String nombre = editTextNombre.getText().toString();
        // Obtener el sexo de la mascota
        RadioButton botonMasculino = findViewById(R.id.btn_sexo_masculino);
        boolean sexo = false;
        if (botonMasculino.isChecked()) {
            sexo = true;
        }
        // Obtener la fecha de nacimiento de la mascota
        Date fechaDeNacimiento = obtenerFecha(R.id.dte_fecha);
        // Obtener padre
        Mascota padre = obtenerMascotaPadre(R.id.spn_padre_mascota, mMascotasPadre);
        // Obtener madre
        Mascota madre = obtenerMascotaPadre(R.id.spn_madre_mascota, mMascotasMadre);
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
        parametros.put(Mascota.ID, Integer.toString(mMascota.getId()));
        parametros.put(Mascota.NOMBRE, mMascota.getNombre());
        // Traducir sexo de la mascota de boolean a String
        String sexo = getString(R.string.registro_mascota_txt_sexo_femenino);
        if (mMascota.getSexo()) {
            sexo = getString(R.string.registro_mascota_txt_sexo_masculino);
        }
        parametros.put(Mascota.SEXO, sexo);
        // Dar formato a la fecha
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaDeNacimiento = formato.format(mMascota.getFechaDeNacimiento());
        parametros.put(Mascota.FECHA_DE_NACIMIENTO, fechaDeNacimiento);
        parametros.put(Mascota.PADRE, mMascota.getPadre().getNombre());
        parametros.put(Mascota.MADRE, mMascota.getMadre().getNombre());
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
        //String fecha = obtenerFecha(R.id.dte_fecha);
        //TODO reemplazar los siguiente:
        String fecha = "";
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
     * Este método retorna un objeto Date con la fecha presente en el DatePicker indicado, en el
     * formato día/mes/año.
     *
     * @param pDatePicker es el DatePicker de donde se obtiene la fecha.
     * @return retorna un objeto Date en el formato día/mes/año.
     */
    private Date obtenerFecha(int pDatePicker) {
        DatePicker datePicker = findViewById(pDatePicker);
        int dia = datePicker.getDayOfMonth();
        int mes = datePicker.getMonth();
        int ano = datePicker.getYear();
        Calendar calendario = Calendar.getInstance();
        calendario.set(ano, mes, dia);
        return calendario.getTime();
    }

    /**
     * Este método configura el Spinner que se pase como argumento.
     *
     * @param pSpinner es el Spinner que se pasa como argumento.
     */
    private void configurarSpinner(int pSpinner) {
        Spinner spinner = findViewById(pSpinner);
        ArrayAdapter<String> adaptador = null;
        if (pSpinner == R.id.spn_padre_mascota) {
            mMascotasPadre.add(getString(R.string.registro_mascota_txt_ayuda_padres));
            mMascotasPadre.add(getString(R.string.registro_mascota_txt_padre_defecto));
            // Creación de ArrayAdapter usando el ArrayList de Strings y un diseño por defecto para
            // el Spinner
            adaptador = new ArrayAdapter<>(RegistroFormularioActivity.this,
                    android.R.layout.simple_spinner_item, mMascotasPadre);
        } else if (pSpinner == R.id.spn_madre_mascota) {
            mMascotasMadre.add(getString(R.string.registro_mascota_txt_ayuda_padres));
            mMascotasMadre.add(getString(R.string.registro_mascota_txt_madre_defecto));
            // Creación de ArrayAdapter usando el ArrayList de Strings y un diseño por defecto para
            // el Spinner
            adaptador = new ArrayAdapter<>(RegistroFormularioActivity.this,
                    android.R.layout.simple_spinner_item, mMascotasMadre);
        }
        // Especificar el diseño que se usará cuando aparece la lista de opciones
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Aplicar el adaptador al Spinner
        spinner.setAdapter(adaptador);
    }

    /**
     * Este método configura la petición que se hace al servidor para obtener las {@link Mascota}s
     * registradas en la base de datos.
     */
    private void cargarMascotas() {
        // Configurar la librería Volley que se encarga de interactuar con la base de datos
        mRequestCargar = Volley.newRequestQueue(RegistroFormularioActivity.this);
        // Cargar web service
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                Mascota.URL_GET, null, RegistroFormularioActivity.this,
                RegistroFormularioActivity.this);
        mRequestCargar.add(jsonArrayRequest);
    }

    /**
     * Este método retorna una {@link Mascota} padre o madre.
     *
     * @param pSpinner             es el Spinner del cual se va a tomar la selección.
     * @param pArregloMascotaPadre es el ArrayList donde se encuentran los nombres de las posibles
     *                             mascotas padre o madre.
     * @return el objeto {@link Mascota} que corresponde según la selección en el Spinner.
     */
    private Mascota obtenerMascotaPadre(int pSpinner, ArrayList<String> pArregloMascotaPadre) {
        Spinner spinner = findViewById(pSpinner);
        int posicionSpinner = spinner.getSelectedItemPosition();
        if (posicionSpinner == 0) {
            posicionSpinner = 1;
        }
        return new Mascota(pArregloMascotaPadre.get(posicionSpinner));
    }
}
