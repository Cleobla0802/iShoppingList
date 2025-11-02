package es.iescarrillo.ishoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Button;
import android.content.Intent;

public class EditarActivity extends AppCompatActivity {
    EditText txtNombre, txtNota;
    Switch swPendiente;
    Button btnGuardar, btnCancelar;
    Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        producto = (Producto) getIntent().getSerializableExtra("producto");

        txtNombre = findViewById(R.id.txtNombre);
        txtNota = findViewById(R.id.txtNota);
        swPendiente = findViewById(R.id.swPendiente);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);

        txtNombre.setText(producto.getNombre());
        txtNota.setText(producto.getNota());
        swPendiente.setChecked(producto.isPendiente());

        btnGuardar.setOnClickListener(v -> {
            for (Producto p : DatosProductos.lista) {
                if (p.getId() == producto.getId()) {
                    p.setNombre(txtNombre.getText().toString());
                    p.setNota(txtNota.getText().toString());
                    p.setPendiente(swPendiente.isChecked());
                    break;
                }
            }

            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });

        btnCancelar.setOnClickListener(v -> finish());
    }
}
