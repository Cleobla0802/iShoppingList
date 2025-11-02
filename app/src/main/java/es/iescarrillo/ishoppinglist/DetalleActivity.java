package es.iescarrillo.ishoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

public class DetalleActivity extends AppCompatActivity {
    TextView txtNombre, txtNota, txtPendiente;
    Button btnEditar, btnVolver;
    Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        producto = (Producto) getIntent().getSerializableExtra("producto");
        txtNombre = findViewById(R.id.txtNombre);
        txtNota = findViewById(R.id.txtNota);
        txtPendiente = findViewById(R.id.txtPendiente);
        btnEditar = findViewById(R.id.btnEditar);
        btnVolver = findViewById(R.id.btnVolver);

        txtNombre.setText(producto.getNombre());
        txtNota.setText(producto.getNota());
        txtPendiente.setText(producto.isPendiente() ? "Pendiente" : "No pendiente");

        btnEditar.setOnClickListener(v -> {
            Intent i = new Intent(this, EditarActivity.class);
            i.putExtra("producto", producto);
            startActivity(i);
        });

        btnVolver.setOnClickListener(v -> finish());
    }
}
