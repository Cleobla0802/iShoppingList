package es.iescarrillo.ishoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class AnadirPendienteActivity extends AppCompatActivity {
    Spinner spinner;
    Button btnGuardar, btnCancelar;
    ArrayList<Producto> noPendientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_pendiente);

        spinner = findViewById(R.id.spinner);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);

        for (Producto p : DatosProductos.lista) {
            if (!p.isPendiente()) noPendientes.add(p);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, noPendientes);
        spinner.setAdapter(adapter);

        btnGuardar.setOnClickListener(v -> {
            Producto p = (Producto) spinner.getSelectedItem();
            p.setPendiente(true);
            finish();
        });

        btnCancelar.setOnClickListener(v -> finish());
    }
}
