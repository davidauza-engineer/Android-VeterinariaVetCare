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

import engineer.davidauza.veterinariavetcare.R;
import engineer.davidauza.veterinariavetcare.adapters.ConsultaAdapter;
import engineer.davidauza.veterinariavetcare.adapters.MascotaAdapter;
import engineer.davidauza.veterinariavetcare.adapters.VeterinarioAdapter;
import engineer.davidauza.veterinariavetcare.models.Consulta;
import engineer.davidauza.veterinariavetcare.models.Mascota;
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
                        String nombreMascota = jsonObject.optString("nombre");
                        String fechaDeNacimiento = jsonObject.optString("fechaDeNacimiento");
                        String sexoString = jsonObject.optString("sexo");
                        boolean sexo = false;
                        if (sexoString.
                                equals(getString(R.string.registro_mascota_txt_sexo_masculino))) {
                            sexo = true;
                        }
                        String especie = jsonObject.optString("especie");
                        mMascotasArrayList.add(new Mascota(nombreMascota, fechaDeNacimiento,
                                sexo, especie));
                        break;
                    // Si se están consultando los veterinarios
                    case 1:
                        String nombreVeterinario = jsonObject.optString("nombre");
                        String tarjetaProfesional = jsonObject.optString("tarjetaProfesional");
                        String especialidad = jsonObject.optString("especialidad");
                        String consultasRealizadas =
                                jsonObject.optString("consultasRealizadas");
                        mVeterinariosArrayList.add(new Veterinario(nombreVeterinario,
                                tarjetaProfesional, especialidad, consultasRealizadas));
                        break;
                    // Si están consultando las consultas
                    case 2:
                        String fecha = jsonObject.optString("fecha");
                        String motivo = jsonObject.optString("motivo");
                        String veterinario = jsonObject.optString("veterinario");
                        String mascotaAtendida = jsonObject.optString("mascotaAtendida");
                        mConsultaArrayList.add(new Consulta(fecha, motivo, veterinario,
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
}
