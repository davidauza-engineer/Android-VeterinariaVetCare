package engineer.davidauza.veterinariavetcare.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import engineer.davidauza.veterinariavetcare.R;
import engineer.davidauza.veterinariavetcare.adapters.ConsultaAdapter;
import engineer.davidauza.veterinariavetcare.adapters.MascotaAdapter;
import engineer.davidauza.veterinariavetcare.adapters.VeterinarioAdapter;
import engineer.davidauza.veterinariavetcare.models.Ave;
import engineer.davidauza.veterinariavetcare.models.Canino;
import engineer.davidauza.veterinariavetcare.models.Consulta;
import engineer.davidauza.veterinariavetcare.models.Dueno;
import engineer.davidauza.veterinariavetcare.models.Especie;
import engineer.davidauza.veterinariavetcare.models.Felino;
import engineer.davidauza.veterinariavetcare.models.Mascota;
import engineer.davidauza.veterinariavetcare.models.Roedor;
import engineer.davidauza.veterinariavetcare.models.Veterinario;

/**
 * Esta Activity es responsable por traer y presentar la información de la base de datos con base en
 * la opción seleccionada por el usuario en el Spinner en activity_seleccion.xml
 */
public class ListadoConsultaActivity extends AppCompatActivity
        implements Response.Listener<JSONArray>, Response.ErrorListener {

    private RequestQueue mRequest;

    /**
     * Esta variable almacena la posición seleccionada en el Spinner en activity_seleccion.xml.
     * 0 indica que se consultarán las Mascotas.
     * 1 indica que se consultarán los Veterinarios.
     * 2 indica que se consultarán las Consultas.
     */
    private int mConsultaSeleccionadaSpinner;

    /**
     * El RecyclerView que mostrará la lista de elementos disponibles en la base de datos.
     */
    private RecyclerView mRecyclerView;

    /**
     * Un ArrayList que contendrá la lista de {@link Mascota}s, si se están consultando las
     * {@link Mascota}s.
     */
    private ArrayList<Mascota> mMascotasArrayList = new ArrayList<>();

    /**
     * Un ArrayList que contendrá la lista de {@link Veterinario}s, si se están consultando los
     * {@link Veterinario}s.
     */
    private ArrayList<Veterinario> mVeterinariosArrayList = new ArrayList<>();

    /**
     * Un ArrayList que contendrá la lista de {@link Consulta}s, si se están consultando las
     * {@link Consulta}s.
     */
    private ArrayList<Consulta> mConsultaArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_consulta);
        // Mostrar toast que dice: "Cargando..."
        Toast.makeText(ListadoConsultaActivity.this,
                getString(R.string.listado_consulta_toast_cargando), Toast.LENGTH_SHORT).show();
        configurarRecyclerView();
        // Configurar la librería Volley que se encarga de interactuar con la base de datos
        mRequest = Volley.newRequestQueue(ListadoConsultaActivity.this);
        cargarWebService();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // En caso de error informar al usuario a través de un Toast.
        Toast.makeText(ListadoConsultaActivity.this,
                getString(R.string.listado_consulta_toast_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONArray response) {
        try {
            for (int i = 0; i < response.length(); i++) {
                JSONObject jsonObject = response.getJSONObject(i);
                switch (mConsultaSeleccionadaSpinner) {
                    // Si se están consultando las mascotas
                    case 0:
                        String nombreMascota = jsonObject.optString(Mascota.NOMBRE);
                        String sexoString = jsonObject.optString(Mascota.SEXO);
                        boolean sexo = false;
                        if (sexoString.
                                equals(getString(R.string.registro_mascota_txt_sexo_masculino))) {
                            sexo = true;
                        }
                        String fechaDeNacimientoString =
                                jsonObject.optString(Mascota.FECHA_DE_NACIMIENTO);
                        Date fechaDeNacimiento = construirFecha(fechaDeNacimientoString);
                        String padreString = jsonObject.optString(Mascota.PADRE);
                        Mascota padre = new Mascota(padreString);
                        String madreString = jsonObject.optString(Mascota.MADRE);
                        Mascota madre = new Mascota(madreString);
                        String especieString = jsonObject.optString("especie");
                        Especie especie = null;
                        switch (especieString) {
                            case Ave.NOMBRE:
                                especie = new Ave();
                                break;
                            case Canino.NOMBRE:
                                especie = new Canino();
                                break;
                            case Felino.NOMBRE:
                                especie = new Felino();
                                break;
                            case Roedor.NOMBRE:
                                especie = new Roedor();
                                break;
                            default:
                                especie = new Especie();
                                break;
                        }
                        String raza = jsonObject.optString(Mascota.RAZA);
                        String duenoActualString = jsonObject.optString(Mascota.DUENO_ACTUAL);
                        Dueno duenoActual = new Dueno(duenoActualString);
                        String historialDuenos = jsonObject.getString(Mascota.DUENOS);
                        mMascotasArrayList.add(new Mascota(nombreMascota, sexo, fechaDeNacimiento,
                                padre, madre, especie, raza, duenoActual));
                        break;
                    // Si se están consultando los veterinarios
                    case 1:
                        String nombreVeterinario = jsonObject.optString("nombre");
                        String registroProfesional = jsonObject.optString("tarjetaProfesional");
                        long numeroDeRegistroProfesional = Long.parseLong(registroProfesional);
                        // Obtener especialidades
                        // TODO Actualizar backend
                        String especialidadesString = jsonObject.optString("especialidad");
                        especialidadesString += "$";
                        char[] especialidadesArreglo = especialidadesString.toCharArray();
                        boolean unaEspecialidad = true;
                        for (int j = 0; j < especialidadesArreglo.length; j++) {
                            if (especialidadesArreglo[j] == ',') {
                                unaEspecialidad = false;
                                break;
                            }
                        }
                        ArrayList<String> especialidades = new ArrayList<>();
                        if (unaEspecialidad) {
                            especialidadesString = especialidadesString.
                                    substring(0, especialidadesString.length() - 1);
                            especialidades.add(especialidadesString);
                        } else {
                            int ultimoInicioDePalabra = 0;
                            for (int j = 0; j < especialidadesArreglo.length; j++) {
                                if (especialidadesArreglo[j] == ',' ||
                                        especialidadesArreglo[j] == '$') {
                                    String especialidadPorAgregar = "";
                                    for (int k = ultimoInicioDePalabra; k < j; k++) {
                                        especialidadPorAgregar += especialidadesArreglo[k];
                                    }
                                    especialidades.add(especialidadPorAgregar);
                                    ultimoInicioDePalabra = j + 1;
                                }
                            }
                        }
                        mVeterinariosArrayList.add(new Veterinario(nombreVeterinario,
                                numeroDeRegistroProfesional, especialidades));
                        break;
                    // Si están consultando las consultas
                    case 2:
                        String fechaString = jsonObject.optString("fecha");
                        Date fecha = construirFecha(fechaString);
                        String motivo = jsonObject.optString("motivo");
                        String veterinario = jsonObject.optString("veterinario");
                        Veterinario veterinarioConsulta = new Veterinario(veterinario);
                        String mascotaAtendidaString =
                                jsonObject.optString("mascotaAtendida");
                        Mascota mascotaAtendida = new Mascota(mascotaAtendidaString);
                        mConsultaArrayList.add(new Consulta(fecha, motivo, veterinarioConsulta,
                                mascotaAtendida));
                        break;
                }
            }
            configurarAdaptador();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método configura el RecyclerView necesario para mostrar el listado de ítems disponibles
     * en la base de datos.
     */
    private void configurarRecyclerView() {
        mRecyclerView = findViewById(R.id.ly_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(ListadoConsultaActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Este método se encarga de contactar al servidor para que este ejecute el microservicio y se
     * obtenga un objeto JSON con los valores solicitados.
     */
    private void cargarWebService() {
        mConsultaSeleccionadaSpinner = getIntent().
                getIntExtra(SeleccionActivity.EXTRA_POSICION_SPINNER, -1);
        String url = "";
        switch (mConsultaSeleccionadaSpinner) {
            case 0:
                url = Mascota.URL_GET;
                break;
            case 1:
                url = Veterinario.URL_GET;
                break;
            case 2:
                url = Consulta.URL_GET;
                break;
        }
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, ListadoConsultaActivity.this,
                ListadoConsultaActivity.this);
        mRequest.add(jsonArrayRequest);
    }

    /**
     * Este método configura el adaptador correspondiente para que el RecyclerView muestre la lista
     * adecuadamente en la interfaz gráfica.
     */
    private void configurarAdaptador() {
        RecyclerView.Adapter adaptador = null;
        switch (mConsultaSeleccionadaSpinner) {
            // Si se va a consultar la lista de mascotas
            case 0:
                adaptador = new MascotaAdapter(mMascotasArrayList,
                        ListadoConsultaActivity.this);
                break;
            // Si se va a consultar la lista de veterinarios
            case 1:
                adaptador = new VeterinarioAdapter(mVeterinariosArrayList);
                break;
            // Si se va a consultar la lista de Consultas
            case 2:
                adaptador = new ConsultaAdapter(mConsultaArrayList);
                break;
        }
        mRecyclerView.setAdapter(adaptador);
    }

    /**
     * Este método convierte un String que contiene una fecha en formato dd/mm/aaaa a un objeto
     * {@link Date}
     *
     * @param pFechaString es el String en formato dd/mm/aaaa.
     * @return un objeto {@link Date} con la fecha correspondiente.
     */
    private Date construirFecha(String pFechaString) {
        char[] charArray = pFechaString.toCharArray();
        int contador = 0;
        String diaString = "";
        String mesString = "";
        String anoString = "";
        for (int j = 0; j < charArray.length; j++) {
            if (charArray[j] == '/') {
                contador++;
            } else {
                if (contador == 0) {
                    diaString += charArray[j];
                } else if (contador == 1) {
                    mesString += charArray[j];
                } else {
                    anoString += charArray[j];
                }
            }
        }
        int dia = Integer.parseInt(diaString);
        int mes = Integer.parseInt(mesString);
        int ano = Integer.parseInt(anoString);
        Calendar calendario = Calendar.getInstance();
        // Se resta a uno al mes puesto que el método set tiene en cuenta los meses
        // del 0 al 11
        calendario.set(ano, mes - 1, dia);
        return calendario.getTime();
    }
}
