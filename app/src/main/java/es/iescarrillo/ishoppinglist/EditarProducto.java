package es.iescarrillo.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import androidx.appcompat.app.AppCompatActivity;

public class EditarProducto extends AppCompatActivity {

    private EditText etNombre, etNota;
    private CheckBox cbNecesitaCompra;
    private Button btnGuardar, btnCancelar;
    private Producto producto;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);

        etNombre = findViewById(R.id.etNombre);
        etNota = findViewById(R.id.etNota);
        cbNecesitaCompra = findViewById(R.id.cbNecesitaCompra);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar = findViewById(R.id.btnCancelar);

        // Obtener el producto y su posición
        producto = (Producto) getIntent().getSerializableExtra("producto");
        position = getIntent().getIntExtra("position", -1);

        // Mostrar los datos actuales del producto
        if (producto != null) {
            etNombre.setText(producto.getNombre());
            etNota.setText(producto.getNota());
            cbNecesitaCompra.setChecked(producto.isEstado());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCambios();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });
    }

    private void guardarCambios() {
        // Actualizar el producto con los nuevos datos
        producto.setNombre(etNombre.getText().toString());
        producto.setNota(etNota.getText().toString());
        producto.setEstado(cbNecesitaCompra.isChecked());

        // Volver directamente al MainActivity
        Intent intent = new Intent(EditarProductoActivity.this, MainActivity.class);
        intent.putExtra("productoActualizado", producto);
        startActivity(intent);
        finish();
    }

    private void cancelar() {
        // Volver al MainActivity sin guardar
        Intent intent = new Intent(EditarProductoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}