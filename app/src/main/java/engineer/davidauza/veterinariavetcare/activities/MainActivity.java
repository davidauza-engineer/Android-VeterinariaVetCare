package engineer.davidauza.veterinariavetcare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import engineer.davidauza.veterinariavetcare.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configurarBotonRegistrar();
    }

    /**
     * Este método configura el botón registrar de la interfaz gráfica principal, para abrir la
     * actividad RegistroActivity.
     */
    private void configurarBotonRegistrar() {
        Button botonRegistrar = findViewById(R.id.btn_registrar);
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}
