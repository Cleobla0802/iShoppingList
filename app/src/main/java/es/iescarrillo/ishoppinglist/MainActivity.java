package es.iescarrillo.ishoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextView tvDetalle;
    private Button btnAdd, btnPendiente, btnVerDetalles;

    private ArrayList<Producto> productos = new ArrayList<>();
    private int idCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        tvDetalle = findViewById(R.id.tvDetalle);
        btnAdd = findViewById(R.id.btnAdd);
        btnPendiente = findViewById(R.id.btnPendiente);
        btnVerDetalles = findViewById(R.id.btnVerDetalles);

        // Productos de ejemplo
        productos.add(new Producto(idCounter++, "Leche", "Entera", true));
        productos.add(new Producto(idCounter++, "Pan", "Integral", true));
        productos.add(new Producto(idCounter++, "Huevos", "Docena", true));

        actualizarSpinner();

        // Botón Ver Detalles
       btnVerDetalles.setOnClickListener(v -> {
            int selectedPosition = spinner.getSelectedItemPosition();
            if (selectedPosition != Spinner.INVALID_POSITION) {
                Producto p = productos.get(selectedPosition);

                Intent intent = new Intent(MainActivity.this, DetallesProductos.class);
                intent.putExtra("producto", p);
                intent.putExtra("position", selectedPosition); // ← AÑADIR ESTA LÍNEA
                startActivity(intent);
            }
        });

        // Botón añadir producto
        btnAdd.setOnClickListener(v -> {
            Producto nuevo = new Producto(idCounter++, "Producto " + idCounter, "Nota ejemplo", true);
            productos.add(nuevo);
            actualizarSpinner();
            spinner.setSelection(productos.size() - 1);
        });

        // Botón marcar como pendiente
        btnPendiente.setOnClickListener(v -> {
            int selectedPosition = spinner.getSelectedItemPosition();
            if (selectedPosition != Spinner.INVALID_POSITION) {
                Producto p = productos.get(selectedPosition);
                p.setEstado(true);
                tvDetalle.setText("Producto marcado como pendiente: " + p.getNombre());
            }
        });
    }

    private void actualizarSpinner() {
        ArrayList<String> nombres = new ArrayList<>();
        for (Producto p : productos) {
            nombres.add(p.getNombre());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}