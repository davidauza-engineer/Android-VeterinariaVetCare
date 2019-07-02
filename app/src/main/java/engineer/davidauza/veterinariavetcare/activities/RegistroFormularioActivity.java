package engineer.davidauza.veterinariavetcare.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
import com.thomashaertel.widget.MultiSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import engineer.davidauza.veterinariavetcare.R;
import engineer.davidauza.veterinariavetcare.models.Ave;
import engineer.davidauza.veterinariavetcare.models.Canino;
import engineer.davidauza.veterinariavetcare.models.Consulta;
import engineer.davidauza.veterinariavetcare.models.Dueno;
import engineer.davidauza.veterinariavetcare.models.EnfermedadCronica;
import engineer.davidauza.veterinariavetcare.models.Especie;
import engineer.davidauza.veterinariavetcare.models.Felino;
import engineer.davidauza.veterinariavetcare.models.Mascota;
import engineer.davidauza.veterinariavetcare.models.Medicamento;
import engineer.davidauza.veterinariavetcare.models.Patologia;
import engineer.davidauza.veterinariavetcare.models.Roedor;
import engineer.davidauza.veterinariavetcare.models.Tratamiento;
import engineer.davidauza.veterinariavetcare.models.Veterinario;

/**
 * Esta Activity es responsable de mostrar la interfaz gráfica según la elección del usuario en
 * activity_seleccion.xml y de enviar al servidor los datos que el usuario registre en dicha intefaz
 * gráfica.
 */
public class RegistroFormularioActivity extends AppCompatActivity
        implements Response.Listener<JSONArray>, Response.ErrorListener,
        AdapterView.OnItemSelectedListener {

    private RequestQueue mRequestRegistrar;

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
     * El ArrayList que almacena el nombre de las {@link Mascota}s almacenadas en la base de datos.
     */
    private ArrayList<String> mMascotasTodas = new ArrayList<>();

    /**
     * El ArrayList que almacenará las razas según la especie seleccionada por el usuario.
     */
    private ArrayList<String> mRazas = new ArrayList<>();

    /**
     * El ArrayList que almacenará los nombre de los {@link Veterinario}s en la interfaz de registro
     * de {@link Consulta}.
     */
    private ArrayList<String> mVeterinariosConsulta = new ArrayList<>();

    /**
     * Contiene el Spinner que despliega la lista de razas según la {@link Especie} seleccionada.
     */
    private Spinner mSpinnerRaza;

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
     * Esta variable almacena el objeto {@link Dueno} que será registrado en la base de datos.
     */
    private Dueno mDueno;

    /**
     * Esta variable almacena la posición seleccionada en el Spinner en activity_seleccionn.xml.
     * 0 indica que se cargue la interfaz gráfica para registrar una {@link Mascota}.
     * 1 indica que se cargue la interfaz gráfica para registrar un {@link Veterinario}.
     * 2 indica que se cargue la interfaz gráfica para registrar una {@link Consulta}.
     */
    private int mRegistroSeleccionadoSpinner;

    /**
     * El formato de fecha utilizado.
     */
    private static final SimpleDateFormat FORMATO = new SimpleDateFormat("dd/MM/yyyy");

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
                // Si se está en la interfaz de registro de mascotas
                if (mRegistroSeleccionadoSpinner == 0) {
                    String sexoMascota = jsonObject.optString(Mascota.SEXO);
                    // Si las mascotas obtenidas de la base de datos son machos, añadirlos a la
                    // lista de padres. Si son hembras, a la lista de madres.
                    if (sexoMascota.
                            equals(getString(R.string.registro_mascota_txt_sexo_masculino))) {
                        mMascotasPadre.add(jsonObject.optString(Mascota.NOMBRE));
                    } else if (sexoMascota.
                            equals(getString(R.string.registro_mascota_txt_sexo_femenino))) {
                        mMascotasMadre.add(jsonObject.optString(Mascota.NOMBRE));
                    }
                    // Si se está en la interfaz de registro de consultas
                } else if (mRegistroSeleccionadoSpinner == 2) {
                    String veterinario = jsonObject.optString(Veterinario.NOMBRE);
                    mVeterinariosConsulta.add(veterinario);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Escucha para el Spinner de Selección de {@link Especie}.
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                // Si se selecciona especie Ave
                refrescarSpinnerRaza(Ave.RAZAS, position);
                break;
            case 2:
                // Si se selecciona especie Canino
                refrescarSpinnerRaza(Canino.RAZAS, position);
                break;
            case 3:
                // Si se selecciona especie Desconocida - Otra
                refrescarSpinnerRaza(Especie.RAZAS, position);
                break;
            case 4:
                // Si se selecciona especie Felino
                refrescarSpinnerRaza(Felino.RAZAS, position);
                break;
            case 5:
                // Si se selecciona especie Roedor
                refrescarSpinnerRaza(Roedor.RAZAS, position);
                break;
            default:
                // Si se selecciona Selecciona la opción correcta
                String[] noSeleccion = {getString(R.string.registro_mascota_txt_ayuda_razas)};
                refrescarSpinnerRaza(noSeleccion, position);
                break;
        }
    }

    /**
     * Método necesario para implementar la interfaz AdapterView.OnItemSelectedListener.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Método necesario para implementar la interfaz AdapterView.OnItemSelectedListener
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
                cargarWebService(Mascota.URL_GET);
                configurarSpinner(R.id.spn_especie_mascota);
                configurarSpinner(R.id.spn_raza_mascota);
                break;
            case 1:
                setContentView(R.layout.activity_registro_veterinario);
                configurarMultiSpinnerEspecialidadesVeterinario();
                break;
            case 2:
                setContentView(R.layout.activity_registro_consulta);
                configurarWebServiceMascotaConsulta();
                cargarWebService(Veterinario.URL_GET);
                configurarSpinner(R.id.spn_mascota_atendida_consulta);
                configurarSpinner(R.id.spn_veterinario_consulta);
                configurarSpinner(R.id.spn_patologia_consulta);
                configurarSpinner(R.id.spn_medicamento_consulta);
                configurarSpinner(R.id.spn_enfermedad_cronica_consulta);
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
        // Obtener especie
        Spinner spinnerEspecie = findViewById(R.id.spn_especie_mascota);
        Especie especie = null;
        switch (spinnerEspecie.getSelectedItemPosition()) {
            case 1:
                especie = new Ave();
                break;
            case 2:
                especie = new Canino();
                break;
            case 4:
                especie = new Felino();
                break;
            case 5:
                especie = new Roedor();
                break;
            default:
                especie = new Especie();
                break;
        }
        // Obtener raza
        String raza = "";
        int posicion = mSpinnerRaza.getSelectedItemPosition();
        switch (especie.getNombre()) {
            case Ave.NOMBRE:
                // Si el usuario no ha seleccionado una raza ir a la posición 5 del arreglo donde se
                // encuentra la palabra Desconocida
                if (posicion == 0) {
                    posicion = 5;
                }
                raza = mRazas.get(posicion);
                break;
            case Canino.NOMBRE:
                // Si el usuario no ha seleccionado una raza ir a la posición 7 del arreglo donde se
                // encuentra la palabra Desconocida
                if (posicion == 0) {
                    posicion = 7;
                }
                raza = mRazas.get(posicion);
                break;
            case Felino.NOMBRE:
                // Si el usuario no ha seleccionado una raza ir a la posición 3 del arreglo donde se
                // encuentra la palabra Desconocida
                if (posicion == 0) {
                    posicion = 3;
                }
                raza = mRazas.get(posicion);
                break;
            case Roedor.NOMBRE:
                // Si el usuario no ha seleccionado una raza ir a la posición 2 del arreglo donde se
                // encuentra la palabra Desconocida
                if (posicion == 0) {
                    posicion = 2;
                }
                raza = mRazas.get(posicion);
                break;
            default:
                raza = Especie.RAZAS[0];
                break;
        }
        // Crear mascota
        mMascota = new Mascota(id, nombre, sexo, fechaDeNacimiento, padre, madre, especie, raza);
        // Crea Dueño y asignárselo a la Mascota.
        mDueno = crearDueno();
        ArrayList<Dueno> duenos = new ArrayList<>();
        duenos.add(mDueno);
        mMascota.setDuenos(duenos);
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
        String fechaDeNacimiento = FORMATO.format(mMascota.getFechaDeNacimiento());
        parametros.put(Mascota.FECHA_DE_NACIMIENTO, fechaDeNacimiento);
        parametros.put(Mascota.PADRE, mMascota.getPadre().getNombre());
        parametros.put(Mascota.MADRE, mMascota.getMadre().getNombre());
        parametros.put(Mascota.ESPECIE, mMascota.getEspecie().getNombre());
        parametros.put(Mascota.RAZA, mMascota.getRaza());
        parametros.put(Mascota.DUENOS, mMascota.getDuenos().toString());
        parametros.put(Mascota.DUENO_ACTUAL, mMascota.getDuenoActual().toString());
        return parametros;
    }

    /**
     * Este método crea un {@link Dueno} con base en los datos suministrados en la interfaz gráfica.
     *
     * @return un objeto {@link Dueno}.
     */
    private Dueno crearDueno() {
        // Crear ID
        int id = (int) (Math.random() * 100_000);
        // Obtener nombre
        EditText nombreDuenoEditText = findViewById(R.id.txt_nombre_dueno_mascota);
        String nombre = nombreDuenoEditText.getText().toString();
        // Obtener apellido
        EditText apellidoDuenoEditText = findViewById(R.id.txt_apellido_dueno_mascota);
        String apellido = apellidoDuenoEditText.getText().toString();
        // Obtener número de documento
        EditText documentoDuenoEditText = findViewById(R.id.txt_documento_dueno_mascota);
        String documento = documentoDuenoEditText.getText().toString();
        long numeroDeDocumento = 0;
        if (!documento.equals("")) {
            numeroDeDocumento = Long.parseLong(documento);
        }
        // Obtener dirección
        EditText direccionEditText = findViewById(R.id.txt_direccion_dueno_mascota);
        String direccion = direccionEditText.getText().toString();
        // Obtener teléfono
        EditText telefonoEditText = findViewById(R.id.txt_telefono_dueno_mascota);
        String numeroDeTelefono = telefonoEditText.getText().toString();
        long telefono = 0;
        if (!numeroDeTelefono.equals("")) {
            telefono = Long.parseLong(numeroDeTelefono);
        }
        // Obtener mascota
        Mascota mascota = mMascota;
        // Obtener fecha de registro mascota
        Date fecha = Calendar.getInstance().getTime();
        return new Dueno(id, nombre, apellido, numeroDeDocumento, direccion, telefono, mascota,
                fecha);
    }

    /**
     * Este método crea un nuevo objeto {@link Veterinario} con base en los valores suministrados
     * por el usuario en la interfaz gráfica.
     */
    private void crearVeterinario() {
        // Crear ID para el Veterinario
        int id = (int) (Math.random() * 1_000);
        // Obtener nombre del Veterinario
        EditText nombreEditText = findViewById(R.id.txt_nombre_veterinario);
        String nombre = nombreEditText.getText().toString();
        // Obtener apellido del Veterinario
        EditText apellidoEditText = findViewById(R.id.txt_apellido_veterinario);
        String apellido = apellidoEditText.getText().toString();
        // Obtener el número de documento del Veterinario
        EditText numeroDeDocumentoEditText = findViewById(R.id.txt_numero_documento_veterinario);
        String numeroIdentidad = numeroDeDocumentoEditText.getText().toString();
        long numeroDeDocumento = 0;
        if (!numeroIdentidad.equals("")) {
            numeroDeDocumento = Long.parseLong(numeroIdentidad);
        }
        // Obtener dirección del Veterinario
        EditText direccionEditText = findViewById(R.id.txt_direccion_veterinario);
        String direccion = direccionEditText.getText().toString();
        // Obtener teléfono Veterinario
        EditText telefonoEditText = findViewById(R.id.txt_telefono_veterinario);
        String numeroTelefono = telefonoEditText.getText().toString();
        long telefono = 0;
        if (!numeroTelefono.equals("")) {
            telefono = Long.parseLong(numeroTelefono);
        }
        // Obtener el número de registro profesional del Veterinario
        EditText registroProfesionalEditText =
                findViewById(R.id.txt_registro_profesional_veterinario);
        String registroProfesional = registroProfesionalEditText.getText().toString();
        long numeroDeRegistroProfesional = 0;
        if (!registroProfesional.equals("")) {
            numeroDeRegistroProfesional = Long.parseLong(registroProfesional);
        }
        // Obtener especialidades del Veterinario
        ArrayList<String> especialidades = new ArrayList<>();
        MultiSpinner multiSpinnerEspecialidades = findViewById(R.id.spn_especialidad_veterinario);
        boolean[] seleccionesUsuario = multiSpinnerEspecialidades.getSelected();
        for (int i = 0; i < seleccionesUsuario.length; i++) {
            // Si el usuario no ha seleccionado nada en el MultiSpinner asignar la especialidad
            // Ninguna
            if (i == 0) {
                if (seleccionesUsuario[i]) {
                    especialidades.add(Veterinario.ARREGLO_ESPECIALIDADES_MEDICAS[6]);
                    break;
                }
            } else {
                // Si el usuario hizo una selección, asignársela a la lista de especialidades.
                if (seleccionesUsuario[i]) {
                    // Se resta uno al índice ya que el arreglo boolean posee un índice adicional
                    // comparado con el arreglo de Strings que contiene las especialidades de los
                    // veterinarios.
                    especialidades.add(Veterinario.ARREGLO_ESPECIALIDADES_MEDICAS[i - 1]);
                }
            }
        }
        // Crear Veterinario
        mVeterinario = new Veterinario(id, nombre, apellido, numeroDeDocumento, direccion,
                telefono, numeroDeRegistroProfesional, especialidades);
    }

    /**
     * Este método crea un Map que contiene los valores para ser enviados al servidor y
     * posteriormente a la base de datos, para el caso de los {@link Veterinario}s.
     */
    private Map<String, String> crearParametrosVeterinario() {
        Map<String, String> parametros = new HashMap<>();
        parametros.put(Veterinario.ID, Integer.toString(mVeterinario.getId()));
        parametros.put(Veterinario.NOMBRE, mVeterinario.getNombre());
        parametros.put(Veterinario.APELLIDO, mVeterinario.getApellido());
        parametros.put(Veterinario.NUMERO_DE_DOCUMENTO,
                Long.toString(mVeterinario.getNumeroDeDocumento()));
        parametros.put(Veterinario.DIRECCION, mVeterinario.getDireccion());
        parametros.put(Veterinario.TELEFONO, Long.toString(mVeterinario.getTelefono()));
        parametros.put(Veterinario.NUMERO_DE_REGISTRO_PROFESIONAL,
                Long.toString(mVeterinario.getNumeroDeRegistroProfesional()));
        parametros.put(Veterinario.ESPECIALIDADES_MEDICAS,
                mVeterinario.getEspecialidadesMedicas().toString());
        return parametros;
    }

    /**
     * Este método configura el MultiSpinner usado para elegir las especialidades médicas de los
     * {@link Veterinario}s.
     */
    private void configurarMultiSpinnerEspecialidadesVeterinario() {
        int tamanoArreglo = Veterinario.ARREGLO_ESPECIALIDADES_MEDICAS.length + 1;
        String[] opciones = new String[tamanoArreglo];
        opciones[0] = getString(R.string.registro_veterinario_txt_especialidades_defecto);
        for (int i = 1; i < tamanoArreglo; i++) {
            opciones[i] = Veterinario.ARREGLO_ESPECIALIDADES_MEDICAS[i - 1];
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(RegistroFormularioActivity.this,
                android.R.layout.simple_spinner_item, opciones);
        final MultiSpinner multiSpinner = findViewById(R.id.spn_especialidad_veterinario);
        multiSpinner.setAdapter(adaptador, false,
                new MultiSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] selected) {
                        // Escucha al seleccionar una opción
                        boolean otraSeleccion = false;
                        // Si hay un ítem seleccionado diferente de 0 quitar selección a 0
                        for (int i = 1; i < selected.length; i++) {
                            if (selected[i]) {
                                otraSeleccion = true;
                                break;
                            }
                        }
                        if (otraSeleccion) {
                            selected[0] = false;
                        } else {
                            if (!selected[0]) {
                                selected[0] = true;
                            }
                        }
                        // Si la opción Ninguna, número 7 dentro del arreglo, está seleccionada,
                        // quitar la selección del resto de opciones.
                        if (selected[7]) {
                            for (int i = 0; i < selected.length; i++) {
                                if (i != 7) {
                                    selected[i] = false;
                                }
                            }
                        }
                        multiSpinner.setSelected(selected);
                    }
                });
        // Configurar la selección por defecto.
        boolean[] itemsSeleccionados = new boolean[adaptador.getCount()];
        itemsSeleccionados[0] = true;
        multiSpinner.setSelected(itemsSeleccionados);
    }

    /**
     * Este método crea un nuevo objeto {@link Consulta} con base en los valores suministrados por
     * el usuario en la interfaz gráfica.
     */
    private void crearConsulta() {
        // Crear ID para la consulta
        int codigo = (int) (Math.random() * 1_000_000);
        // Obtener mascota atendida
        Spinner spinnerMascota = findViewById(R.id.spn_mascota_atendida_consulta);
        int posicionMascota = spinnerMascota.getSelectedItemPosition();
        Mascota mascotaAtendida = new Mascota(mMascotasTodas.get(posicionMascota));
        // Obtener fecha
        Date fecha = obtenerFecha(R.id.dte_fecha);
        // Obtener motivo
        EditText motivoEditText = findViewById(R.id.txt_motivo_consulta);
        String motivo = motivoEditText.getText().toString();
        // Obtener exámenes físicos
        EditText examenesFisicosEditText = findViewById(R.id.txt_examenes_fisicos_consulta);
        String examenesFisicos = examenesFisicosEditText.getText().toString();
        // Obtener veterinario que atendió la consulta
        Spinner spinnerVeterinario = findViewById(R.id.spn_veterinario_consulta);
        Veterinario veterinarioConsulta = new Veterinario(mVeterinariosConsulta.
                get(spinnerVeterinario.getSelectedItemPosition()));
        // Obtener patología
        Spinner spinnerPatologia = findViewById(R.id.spn_patologia_consulta);
        int posicion = spinnerPatologia.getSelectedItemPosition();
        if (posicion == 0) {
            // Si no se ha seleccionado ninguna Patología, seleccionar el índice 1 que lleva a la
            // Patología "Desconocida" en el arreglo de Patologías
            posicion = 1;
        } else {
            // Restar uno para que la selección en la interfaz de usuario coincida con el arreglo de
            // Patologías
            posicion -= 1;
        }
        Patologia patologia = new Patologia(posicion, Patologia.NOMBRES);
        //Obtener enfermedad crónica
        Spinner enfermedadCronicaSpinner = findViewById(R.id.spn_enfermedad_cronica_consulta);
        int posicionEnfermedadCronicaSpinner = enfermedadCronicaSpinner.getSelectedItemPosition();
        if (posicionEnfermedadCronicaSpinner == 0) {
            // Si no se ha seleccionado ninguna enfermedad crónica, seleccionar el índice 4 que
            // lleva a la enfermedad crónica "Desconocida" en el arreglo de enfermedades crónicas
            posicionEnfermedadCronicaSpinner = 4;
        } else {
            // Restar uno para que la selección en la interfaz de usuario coincida con el arreglo de
            // enfermedades crónicas
            posicionEnfermedadCronicaSpinner -= 1;
        }
        EnfermedadCronica enfermedadCronica =
                new EnfermedadCronica(posicionEnfermedadCronicaSpinner, EnfermedadCronica.NOMBRES);
        // Obtener tratamiento
        // Obtener medicamento
        Spinner medicamentoSpinner = findViewById(R.id.spn_medicamento_consulta);
        int posicionMedicamentoSeleccionado = medicamentoSpinner.getSelectedItemPosition();
        if (posicionMedicamentoSeleccionado == 0) {
            posicionMedicamentoSeleccionado = 22;
        } else {
            posicionMedicamentoSeleccionado -= 1;
        }
        Medicamento medicamento = new Medicamento(posicionMedicamentoSeleccionado);
        // Obtener dosis en mg
        EditText dosisEditText = findViewById(R.id.txt_dosis_consulta);
        byte dosis = 0;
        if (dosisEditText.getVisibility() == View.VISIBLE) {
            String dosisString = dosisEditText.getText().toString();
            if (!dosisString.equals("")) {
                dosis = Byte.parseByte(dosisString);
            }
        }
        // Obtener frecuencia en horas
        EditText frecuenciaEditText = findViewById(R.id.txt_frecuencia_consulta);
        byte frecuencia = 0;
        if (frecuenciaEditText.getVisibility() == View.VISIBLE) {
            String frecuenciaString = frecuenciaEditText.getText().toString();
            if (!frecuenciaString.equals("")) {
                frecuencia = Byte.parseByte(frecuenciaString);
            }
        }
        // Obtener cantid de días del Tratamiento
        EditText diasEditText = findViewById(R.id.txt_cantidad_dias_consulta);
        byte diasTratamiento = 0;
        if (diasEditText.getVisibility() == View.VISIBLE) {
            String diasString = diasEditText.getText().toString();
            if (!diasString.equals("")) {
                diasTratamiento = Byte.parseByte(diasString);
            }
        }
        Tratamiento tratamiento = new Tratamiento(medicamento, dosis, frecuencia, diasTratamiento);
        // Crear Consulta
        mConsulta = new Consulta(codigo, mascotaAtendida, fecha, motivo, examenesFisicos, veterinarioConsulta,
                patologia, enfermedadCronica, tratamiento);
    }

    /**
     * Este método crea un Map que contiene los valores para ser enviados al servidor y
     * posteriormente a la base de datos, para el caso de las {@link Consulta}s.
     */
    private Map<String, String> crearParametrosConsulta() {
        Map<String, String> parametros = new HashMap<>();
        parametros.put(Consulta.CODIGO, Integer.toString(mConsulta.getCodigo()));
        // Dar formato a la fecha
        String fecha = FORMATO.format(mConsulta.getFecha());
        parametros.put(Consulta.FECHA, fecha);
        parametros.put(Consulta.MOTIVO, mConsulta.getMotivo());
        parametros.put(Consulta.EXAMENES_FISICOS, mConsulta.getExamenesFisicos());
        parametros.put(Consulta.VETERINARIO, mConsulta.getVeterinario().getNombre());
        parametros.put(Consulta.PATOLOGIA, mConsulta.getPatologia().toString());
        parametros.put(Consulta.ENFERMEDAD_CRONICA, mConsulta.getEnfermedadCronica().toString());
        parametros.put(Consulta.TRATAMIENTO, mConsulta.getTratamiento().toString());
        parametros.put(Consulta.MASCOTA_ATENDIDA, mConsulta.getMascotaAtendida().getNombre());
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
            mMascotasPadre.add(getString(R.string.registro_mascota_txt_ayuda));
            mMascotasPadre.add(getString(R.string.registro_mascota_txt_padre_defecto));
            // Creación de ArrayAdapter usando el ArrayList de Strings y un diseño por defecto para
            // el Spinner
            adaptador = new ArrayAdapter<>(RegistroFormularioActivity.this,
                    android.R.layout.simple_spinner_item, mMascotasPadre);
        } else if (pSpinner == R.id.spn_madre_mascota) {
            mMascotasMadre.add(getString(R.string.registro_mascota_txt_ayuda));
            mMascotasMadre.add(getString(R.string.registro_mascota_txt_madre_defecto));
            // Creación de ArrayAdapter usando el ArrayList de Strings y un diseño por defecto para
            // el Spinner
            adaptador = new ArrayAdapter<>(RegistroFormularioActivity.this,
                    android.R.layout.simple_spinner_item, mMascotasMadre);
        } else if (pSpinner == R.id.spn_especie_mascota) {
            String[] especies = {getString(R.string.registro_mascota_txt_ayuda), Ave.NOMBRE,
                    Canino.NOMBRE, Especie.NOMBRE, Felino.NOMBRE, Roedor.NOMBRE};
            // Creación de ArrayAdapter usando el array de Strings y un diseño por defecto para el
            // Spinner
            adaptador = new ArrayAdapter<>(RegistroFormularioActivity.this,
                    android.R.layout.simple_spinner_item, especies);
            // Aplicar el escucha para que la lista de razas se despliegue una vez se seleccione la
            // especie.
            spinner.setOnItemSelectedListener(RegistroFormularioActivity.this);
        } else if (pSpinner == R.id.spn_raza_mascota) {
            mSpinnerRaza = spinner;
            mRazas.add(getString(R.string.registro_mascota_txt_ayuda_razas));
            // Creación de ArrayAdapter usando el ArrayList de Strings y un diseño por defecto para
            // el Spinner
            adaptador = new ArrayAdapter<>(RegistroFormularioActivity.this,
                    android.R.layout.simple_spinner_item, mRazas);
        } else if (pSpinner == R.id.spn_mascota_atendida_consulta) {
            mMascotasTodas.add(getString(R.string.registro_consulta_txt_ayuda));
            mMascotasTodas.add(getString(R.string.registro_consulta_txt_mascota_defecto));
            // Creación de ArrayAdapter usando el ArrayList de Strings y un diseño por defecto para
            // el Spinner
            adaptador = new ArrayAdapter<>(RegistroFormularioActivity.this,
                    android.R.layout.simple_spinner_item, mMascotasTodas);
        } else if (pSpinner == R.id.spn_veterinario_consulta) {
            mVeterinariosConsulta.add(getString(R.string.registro_consulta_txt_ayuda));
            mVeterinariosConsulta.
                    add(getString(R.string.registro_consulta_txt_veterinario_defecto));
            // Creación de ArrayAdapter usando el ArrayList de Strings y un diseño por defecto para
            // el Spinner
            adaptador = new ArrayAdapter<>(RegistroFormularioActivity.this,
                    android.R.layout.simple_spinner_item, mVeterinariosConsulta);
        } else if (pSpinner == R.id.spn_patologia_consulta) {
            String[] arregloPatologias = new String[Patologia.NOMBRES.length + 1];
            arregloPatologias[0] = getString(R.string.registro_consulta_txt_ayuda);
            System.arraycopy(Patologia.NOMBRES, 0, arregloPatologias, 1,
                    Patologia.NOMBRES.length);
            // Creación de ArrayAdapter usando el array de Strings y un diseño por defecto para el
            // Spinner
            adaptador = new ArrayAdapter<>(RegistroFormularioActivity.this,
                    android.R.layout.simple_spinner_item, arregloPatologias);
        } else if (pSpinner == R.id.spn_medicamento_consulta) {
            String[] arregloMedicamentos = new String[Medicamento.NOMBRES.length + 1];
            arregloMedicamentos[0] = getString(R.string.registro_consulta_txt_ayuda_medicamento);
            System.arraycopy(Medicamento.NOMBRES, 0, arregloMedicamentos, 1,
                    Medicamento.NOMBRES.length);
            // Creación de ArrayAdapter usando el array de Strings y un diseño por defecto para el
            // Spinner
            adaptador = new ArrayAdapter<>(RegistroFormularioActivity.this,
                    android.R.layout.simple_spinner_item, arregloMedicamentos);
            // Configurar escucha para esconder o mostrar los campos del Tratamiento según se escoja
            // o no un Medicamento.
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position,
                                           long id) {
                    EditText dosisEditText = findViewById(R.id.txt_dosis_consulta);
                    EditText frecuenciaEditText = findViewById(R.id.txt_frecuencia_consulta);
                    EditText diasEditText = findViewById(R.id.txt_cantidad_dias_consulta);
                    if (position == 0 || position == 23) {
                        if (dosisEditText.getVisibility() == View.VISIBLE) {
                            dosisEditText.setVisibility(View.GONE);
                            frecuenciaEditText.setVisibility(View.GONE);
                            diasEditText.setVisibility(View.GONE);
                        }
                    } else {
                        if (dosisEditText.getVisibility() == View.GONE) {
                            dosisEditText.setVisibility(View.VISIBLE);
                            frecuenciaEditText.setVisibility(View.VISIBLE);
                            diasEditText.setVisibility(View.VISIBLE);
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else if (pSpinner == R.id.spn_enfermedad_cronica_consulta) {
            String[] arregloEnfermedadesCronicas = new String[EnfermedadCronica.NOMBRES.length + 1];
            arregloEnfermedadesCronicas[0] = getString(R.string.registro_consulta_txt_ayuda);
            System.arraycopy(EnfermedadCronica.NOMBRES, 0, arregloEnfermedadesCronicas,
                    1, EnfermedadCronica.NOMBRES.length);
            // Creación de ArrayAdapter usando el array de Strings y un diseño por defecto para el
            // Spinner
            adaptador = new ArrayAdapter<>(RegistroFormularioActivity.this,
                    android.R.layout.simple_spinner_item, arregloEnfermedadesCronicas);
        }
        // Especificar el diseño que se usará cuando aparece la lista de opciones
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Aplicar el adaptador al Spinner
        spinner.setAdapter(adaptador);
    }

    /**
     * Este método configura la petición que se hace al servidor para obtener los datos de una tabla
     * en la base de datos.
     *
     * @param pUrl es la URL del microservicio que se consumirá para obtener los datos solicitados.
     */
    private void cargarWebService(String pUrl) {
        // Configurar la librería Volley que se encarga de interactuar con la base de datos
        RequestQueue mRequestCargar = Volley.newRequestQueue(RegistroFormularioActivity.this);
        // Cargar web service
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, pUrl,
                null, RegistroFormularioActivity.this,
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

    /**
     * Este método refresca el Spinner con la lista de razas cuando el usuario selecciona una
     * {@link Especie} en el Spinner de {@link Especie}s.
     *
     * @param pRazas   es un arreglo de Strings que contiene las razas disponibles.
     * @param posicion es la posición en la lista de {@link Especie}s que el usuario seleccionó.
     */
    private void refrescarSpinnerRaza(String[] pRazas, int posicion) {
        mRazas.clear();
        if (posicion != 3 && posicion != 0) {
            mRazas.add(getString(R.string.registro_mascota_txt_ayuda));
        }
        Collections.addAll(mRazas, pRazas);
        // Creación de ArrayAdapter usando el ArrayList de Strings y un diseño por defecto para
        // el Spinner
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(RegistroFormularioActivity.this,
                android.R.layout.simple_spinner_item, mRazas);
        // Especificar el diseño que se usará cuando aparece la lista de opciones
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerRaza.setAdapter(adaptador);
        if (posicion != 0 && !mSpinnerRaza.isEnabled()) {
            mSpinnerRaza.setEnabled(true);
        } else if (posicion == 0) {
            mSpinnerRaza.setEnabled(false);
        }
    }

    /**
     * Este método configura la petición que se hace al servidor para obtener los datos de las
     * {@link Mascota}s registradas en la base de datos, para ser cargadas al momento de registrar
     * una {@link Consulta}.
     */
    private void configurarWebServiceMascotaConsulta() {
        // Configurar la librería Volley que se encarga de interactuar con la base de datos
        RequestQueue requestCargar =
                Volley.newRequestQueue(RegistroFormularioActivity.this);
        // Cargar web service
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                Mascota.URL_GET, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String mascota = jsonObject.optString(Mascota.NOMBRE);
                        mMascotasTodas.add(mascota);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, RegistroFormularioActivity.this);
        requestCargar.add(jsonArrayRequest);
    }
}
