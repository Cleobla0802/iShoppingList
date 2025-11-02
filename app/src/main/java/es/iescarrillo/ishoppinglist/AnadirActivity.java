package es.iescarrillo.ishoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AnadirActivity extends AppCompatActivity {
    EditText txtNombre, txtNota;
    Button btnGuardar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);

        txtNombre = findViewById(R.id.txtNombre);
        txtNota = findViewById(R.id.txtNota);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnGuardar.setOnClickListener(v -> {
            int id = DatosProductos.lista.size() + 1;
            DatosProductos.lista.add(new Producto(id, txtNombre.getText().toString(), txtNota.getText().toString(), false));
            finish();
        });

        btnCancelar.setOnClickListener(v -> finish());
    }
}
