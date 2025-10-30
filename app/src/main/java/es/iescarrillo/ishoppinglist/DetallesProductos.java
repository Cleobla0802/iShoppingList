package es.iescarrillo.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetallesProductos extends AppCompatActivity {

    private TextView tvNombre, tvNota, tvEstado;
    private Button btnEditar, btnVolver;
    private Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        tvNombre = findViewById(R.id.tvNombre);
        tvNota = findViewById(R.id.tvNota);
        tvEstado = findViewById(R.id.tvEstado);
        btnEditar = findViewById(R.id.btnEditar);
        btnVolver = findViewById(R.id.btnVolver);

        // Obtener el producto de la pantalla anterior
        producto = (Producto) getIntent().getSerializableExtra("producto");

        // Mostrar los datos del producto
        mostrarDatosProducto();

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir pantalla de edición
                Intent intent = new Intent(DetallesProductos.this, EditarProducto.class);
                intent.putExtra("producto", producto);
                intent.putExtra("position", position);
                startActivity(intent); // ← Solo startActivity normal
                finish(); // ← Cerrar esta pantalla
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra esta pantalla y vuelve a la anterior
            }
        });

        // En DetallesProductos.java, añade esto en onCreate después de los otros botones:

        Button btnEditar = findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir pantalla de edición
                Intent intent = new Intent(DetallesProductos.this, EditarProducto.class);
                intent.putExtra("producto", producto);
                intent.putExtra("position", getIntent().getIntExtra("position", -1));
                startActivityForResult(intent, 1); // Usamos startActivityForResult para recibir los cambios
            }
        });
    }

    private void mostrarDatosProducto() {
        tvNombre.setText("Nombre: " + producto.getNombre());
        tvNota.setText("Nota: " + producto.getNota());
        tvEstado.setText("Estado: " + (producto.isEstado() ? "POR COMPRAR" : "COMPRADO"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Actualizar los datos mostrados
            producto = (Producto) data.getSerializableExtra("productoActualizado");
            mostrarDatosProducto();
        }
    }
}