package es.iescarrillo.ishoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerProductos;
    Button btnDetalle, btnAnadir, btnAnadirPendiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatosProductos.cargarDatos();
        spinnerProductos = findViewById(R.id.spinnerProductos);
        btnDetalle = findViewById(R.id.btnDetalle);
        btnAnadir = findViewById(R.id.btnAnadir);
        btnAnadirPendiente = findViewById(R.id.btnAnadirPendiente);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, DatosProductos.lista);
        spinnerProductos.setAdapter(adapter);

        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto p = (Producto) spinnerProductos.getSelectedItem();
                Intent i = new Intent(MainActivity.this, DetalleActivity.class);
                i.putExtra("producto", p);
                startActivity(i);
            }
        });

        btnAnadir.setOnClickListener(v -> startActivity(new Intent(this, AnadirActivity.class)));

        btnAnadirPendiente.setOnClickListener(v -> startActivity(new Intent(this, AnadirPendienteActivity.class)));
    }
}
